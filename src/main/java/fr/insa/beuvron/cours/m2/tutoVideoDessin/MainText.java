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
package fr.insa.beuvron.cours.m2.tutoVideoDessin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import recup.Lire;

/**
 *
 * @author francois
 */
public class MainText {
    
    public static void menuInitial() {
                int rep = -1;
        while (rep != 0) {
            System.out.println("Initialisation du Groupe de figure");
            System.out.println("----------------------------------");
            System.out.println("1) commencer avec un groupe vide");
            System.out.println("2) commencer avec le groupe de test");
            System.out.println("3) lire dans un fichier");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                Groupe g = new Groupe();
                g.menuTexte();
            } else if (rep == 2) {
                Groupe g = Groupe.groupeTest();
                g.menuTexte();
            } else if (rep == 3) {
                System.out.println("répertoire courant : ");
                System.out.println(System.getProperty("user.dir"));
                System.out.println("entrez le chemin du fichier contenant le groupe de figure : ");
                String path = Lire.S();
                File fin = new File(path);
                try {
                    Figure fLue = Figure.lecture(fin);
                    if (fLue instanceof Groupe) {
                        Groupe gLu = (Groupe) fLue;
                        gLu.menuTexte();
                    } else {
                        System.out.println("la figure dans le fichier " + fin.getAbsolutePath() + " n'est pas un groupe");
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("fichier non trouvé : " + fin.getAbsolutePath());;
                } catch (IOException ex) {
                    System.out.println("problème de lecture : " + ex.getLocalizedMessage());;
                }
            } 
        }

    }
    
    public static void main(String[] args) {
        menuInitial();
    }
    
}
