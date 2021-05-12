/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.cours.m2.tutoVideoDessin.gui;

import javafx.geometry.Point2D;
import javafx.scene.transform.Transform;

/**
 * Un rectangle dont les cotés sont parallèles aux axes Horizontaux et Verticaux.
 * Représente par exemple la portion de la scène que l'on veut afficher.
 * @author francois
 */
public class RectangleHV{
    
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public RectangleHV(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    @Override
    public String toString() {
        return "RectangleHV{" + "xMin=" + xMin + ", xMax=" + xMax + ", yMin=" + yMin + ", yMax=" + yMax + '}';
    }
    
    /**
     * Calcule un rectangle ayant même centre, mais des cotés dont la taille
     * est multipliée par facteur.
     * @param facteur
     * @return le nouveau rectangle.
     */
    public RectangleHV scale(double facteur) {
        double dx = this.xMax - this.xMin;
        double dy = this.yMax - this.yMin;
        double cx = (this.xMax + this.xMin) /2;
        double cy = (this.yMax + this.yMin) /2;
        double nxmin = cx - (dx / 2) * facteur;
        double nxmax = cx + (dx / 2) * facteur;
        double nymin = cy - (dy / 2) * facteur;
        double nymax = cy + (dy / 2) * facteur;
        return new RectangleHV(nxmin, nxmax, nymin, nymax);    
    }
    
    /**
     * calcule un nouveau rectangle décalé en horizontal vers la gauche tel
     * que this et ce nouveau rectangle partage portionConservee surface.
     * Ex si portionConservee = 0, le nouveau rectangle est à gauche de l'ancien.
     * si portionConservee = 0.66, le nouveau rectangle est décalé de ~1/3 vers
     * la gauche, les 2/3 qui étaient à droite de l'ancien rectangle se retrouve
     * à gauche du nouveau.
     * @param portionDeplacee {@code 0 <= portionDeplacee <= 1)
     * @return un nouveau RectangleHV décalé à gauche par rapport à this
     */
    public RectangleHV translateGauche(double portionConservee) {
        double dx = (this.xMax - this.xMin)*(1-portionConservee);
        return new RectangleHV(this.xMin-dx,this.xMax-dx,this.yMin,this.yMax);
    }
    
    public RectangleHV translateDroite(double portionConservee) {
        double dx = (this.xMax - this.xMin)*(1-portionConservee);
        return new RectangleHV(this.xMin+dx,this.xMax+dx,this.yMin,this.yMax);
    }
    
    public RectangleHV translateHaut(double portionConservee) {
        double dy = (this.yMax - this.yMin)*(1-portionConservee);
        return new RectangleHV(this.xMin,this.xMax,this.yMin-dy,this.yMax-dy);
    }
    
   public RectangleHV translateBas(double portionConservee) {
        double dy = (this.yMax - this.yMin)*(1-portionConservee);
        return new RectangleHV(this.xMin,this.xMax,this.yMin+dy,this.yMax+dy);
    }
    
    /**
     * Calcule une transformation translation+scale(uniforme) permettant au rectangle
     * this de "tenir" entierement dans le rectangle vue.
     * @param vue le rectangle qui doit contenir this après transformation.
     * En particulier le rectangle (0,0)-(largeur,hauteur) d'une fenêtre graphique
     * @return une transformation permettant de représenter entièrement this dans vue
     */
    public Transform fitTransform(RectangleHV vue) {
        double minX1 = this.xMin;
        double maxX1 = this.xMax;
        double minY1 = this.yMin;
        double maxY1 = this.yMax;
        double dx1 = (maxX1 - minX1);
        double dy1 = (maxY1 - minY1);
        double cx1 = (maxX1 + minX1) / 2;
        double cy1 = (maxY1 + minY1) / 2;
        double minX2 = vue.xMin;
        double minY2 = vue.yMin;
        double maxX2 = vue.xMax;
        double maxY2 = vue.yMax;
        double dx2 = maxX2 - minX2;
        double dy2 = maxY2 - minY2;
        double cx2 = (maxX2 + minX2) / 2;
        double cy2 = (maxY2 + minY2) / 2;
        // je ramène le centre du rectangle 1 en 0,0
        Transform ttrans1 = Transform.translate(-cx1, -cy1);
        // je calcule (si possible) une échelle
        Transform tscale = Transform.scale(1, 1);
        if (dx1 > 0 && dy1 > 0 && dx2 > 0 && dy2 > 0) {
            double scale = Math.min(dx2 / dx1, dy2 / dy1);
            tscale = Transform.scale(scale, scale);
        }
        // je ramène 0 au centre  du rectangle 2
        Transform ttrans2 = Transform.translate(cx2, cy2);
        Transform res = ttrans2.createConcatenation(tscale).createConcatenation(ttrans1);
        return res;
    }

    /**
     * @return the xMin
     */
    public double getxMin() {
        return xMin;
    }

    /**
     * @return the xMax
     */
    public double getxMax() {
        return xMax;
    }

    /**
     * @return the yMin
     */
    public double getyMin() {
        return yMin;
    }

    /**
     * @return the yMax
     */
    public double getyMax() {
        return yMax;
    }

    /**
     * @param xMin the xMin to set
     */
    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    /**
     * @param xMax the xMax to set
     */
    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    /**
     * @param yMin the yMin to set
     */
    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    /**
     * @param yMax the yMax to set
     */
    public void setyMax(double yMax) {
        this.yMax = yMax;
    }



}
