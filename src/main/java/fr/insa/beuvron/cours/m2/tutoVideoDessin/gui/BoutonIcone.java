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

import java.io.InputStream;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author francois
 */
public class BoutonIcone extends Button {

    public BoutonIcone(String relPathOfImageFile, double sizeX, double sizeY) {
        // chargement des icones : la systaxe 
        // this.getClass().getResourceAsStream(path) permet de retrouver 
        // un fichier en indiquant son chemin relatif par rapport au répertoire
        // contenant la classe de this.
        // Cela à condition que les fichiers correspondants aient bien été
        // copiés !! Pour cela, voir le tag <ressources> dans le fichier .pom
        InputStream is = this.getClass().getResourceAsStream(relPathOfImageFile);
        if (is == null) {
            this.setText("?? " + relPathOfImageFile);
        } else {
            Image img = new Image(is, sizeX, sizeY, false, true);
            this.setGraphic(new ImageView(img));

        }

    }
}
