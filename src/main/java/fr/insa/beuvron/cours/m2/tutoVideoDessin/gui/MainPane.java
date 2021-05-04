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

import fr.insa.beuvron.cours.m2.tutoVideoDessin.Groupe;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author francois
 */
public class MainPane extends BorderPane {

    /**
     * multiplicateur pour l'espace de départ : pour ne pas que les bords de la
     * figure soit confondus avec les bords de la fenêtre, on considère que l'on
     * veut que la fenêtre puisse contenir une figure MULT_POUR_FIT_ALL fois
     * plus grande que la figure réelle. Par exemple, si MULT_POUR_FIT_ALL = 2,
     * la figure complète n'occupera en fait qu'environ la moitié de la fenetre
     * graphique.
     */
    private static double MULT_POUR_FIT_ALL = 1.1;

    private Groupe model;
    private Controleur controleur;

    private Stage inStage;
    private File curFile;

    private RadioButton rbSelect;
    private RadioButton rbPoints;
    private RadioButton rbSegments;

    private Button bGrouper;
    private ColorPicker cpCouleur;

    private Button bZoomDouble;
    private Button bZoomDemi;
    private Button bZoomFitAll;
 
    private DessinCanvas cDessin;
    private RectangleHV zoneModelVue;

    private MainMenu menu;

    public MainPane(Stage inStage) {
        this(inStage, new Groupe());
    }

    public MainPane(Stage inStage, Groupe model) {
        this(inStage, null, model);
    }

    public MainPane(Stage inStage, File fromFile, Groupe model) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.model = model;
        this.fitAll();
        this.controleur = new Controleur(this);

        this.rbSelect = new RadioButton("Select");
        this.rbSelect.setOnAction((t) -> {
            this.controleur.boutonSelect(t);
        });
        this.rbPoints = new RadioButton("Points");
        this.rbPoints.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
        this.rbSegments = new RadioButton("Segments");
        this.rbSegments.setOnAction((t) -> {
            this.controleur.boutonSegments(t);
        });

        ToggleGroup bgEtat = new ToggleGroup();
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbPoints.setToggleGroup(bgEtat);
        this.rbSegments.setToggleGroup(bgEtat);
        this.rbPoints.setSelected(true);

        VBox vbGauche = new VBox(this.rbSelect, this.rbPoints, this.rbSegments);
        this.setLeft(vbGauche);

        this.bGrouper = new Button("Grouper");
        this.bGrouper.setOnAction((t) -> {
            this.controleur.boutonGrouper(t);
        });
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.controleur.changeColor(this.cpCouleur.getValue());
        });

        this.bZoomDouble = new Button("Zoom x2");
        this.bZoomDouble.setOnAction((t) -> {
            this.controleur.zoomDouble();
        });
        this.bZoomDemi = new Button("Zoom /2");
        this.bZoomDemi.setOnAction((t) -> {
            this.controleur.zoomDemi();
        });
        this.bZoomFitAll = new Button("Zoom Fit All");
        this.bZoomFitAll.setOnAction((t) -> {
            this.controleur.zoomFitAll();
        });
        VBox vbZoom = new VBox(this.bZoomDouble, this.bZoomDemi, this.bZoomFitAll);
        vbZoom.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        VBox vbDroit = new VBox(this.bGrouper, this.cpCouleur, vbZoom);
        this.setRight(vbDroit);

        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);

        this.menu = new MainMenu(this);
        this.setTop(this.menu);

        this.controleur.changeEtat(20);

    }

    public void fitAll() {
        this.zoneModelVue = new RectangleHV(this.model.minX(),
                this.model.maxX(), this.model.minY(), this.model.maxY());
        this.zoneModelVue = this.zoneModelVue.scale(MULT_POUR_FIT_ALL);
    }

    public void redrawAll() {
        this.cDessin.redrawAll();
    }

    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @return the rbSelect
     */
    public RadioButton getRbSelect() {
        return rbSelect;
    }

    /**
     * @return the rbPoints
     */
    public RadioButton getRbPoints() {
        return rbPoints;
    }

    /**
     * @return the rbSegments
     */
    public RadioButton getRbSegments() {
        return rbSegments;
    }

    /**
     * @return the bGrouper
     */
    public Button getbGrouper() {
        return bGrouper;
    }

    /**
     * @return the cpCouleur
     */
    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }

    /**
     * @return the cDessin
     */
    public DessinCanvas getcDessin() {
        return cDessin;
    }

    /**
     * @return the inStage
     */
    public Stage getInStage() {
        return inStage;
    }

    /**
     * @return the curFile
     */
    public File getCurFile() {
        return curFile;
    }

    /**
     * @param curFile the curFile to set
     */
    public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    /**
     * @return the MULT_POUR_FIT_ALL
     */
    public static double getMULT_POUR_FIT_ALL() {
        return MULT_POUR_FIT_ALL;
    }

    /**
     * @return the bZoomDouble
     */
    public Button getbZoomDouble() {
        return bZoomDouble;
    }

    /**
     * @return the bZoomDemi
     */
    public Button getbZoomDemi() {
        return bZoomDemi;
    }

    /**
     * @return the bZoomFitAll
     */
    public Button getbZoomFitAll() {
        return bZoomFitAll;
    }

    /**
     * @return the zoneModelVue
     */
    public RectangleHV getZoneModelVue() {
        return zoneModelVue;
    }

    /**
     * @return the menu
     */
    public MainMenu getMenu() {
        return menu;
    }

    /**
     * @param zoneModelVue the zoneModelVue to set
     */
    public void setZoneModelVue(RectangleHV zoneModelVue) {
        this.zoneModelVue = zoneModelVue;
    }

}
