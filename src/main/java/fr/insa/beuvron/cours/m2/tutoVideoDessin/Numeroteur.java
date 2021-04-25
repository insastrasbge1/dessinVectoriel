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

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author francois
 */
public class Numeroteur<TO> {
    
    private TreeMap<Integer,TO> idVersObjet;
    private Map<TO,Integer> objetVersId;
    
    private int prochainID;
    
    public Numeroteur() {
        this(0);
    }
    
    private Numeroteur(int prochainID) {
        this.prochainID = prochainID;
        this.idVersObjet = new TreeMap<>();
        this.objetVersId = new HashMap<>();
    }
    
    public int creeID(TO obj) {
        if(this.objetVersId.containsKey(obj)) {
            throw new Error("objet " + obj + " déjà dans le numéroteur");
        }
        this.idVersObjet.put(this.prochainID, obj);
        this.objetVersId.put(obj, this.prochainID);
        this.prochainID ++;
        return this.prochainID - 1;
    }
    
    public boolean objExist(TO obj) {
        return this.objetVersId.containsKey(obj);
    }
    
    public int getID(TO obj) {
        if (this.objExist(obj)) {
            return this.objetVersId.get(obj);
        } else {
            throw new Error("Objet" + obj + " inconnu dans numéroteur");
        }
    }

    public int getOuCreeID(TO obj) {
        if (this.objExist(obj)) {
            return this.objetVersId.get(obj);
        } else {
            return this.creeID(obj);
        }
    }
    
    public TO getObj(int id) {
        if (! this.idExist(id)) {
            throw new Error("identificateur non existant");
        }
        return this.idVersObjet.get(id);
    }
    
    public boolean idExist(int id) {
        return this.idVersObjet.containsKey(id);
    }
    
    public void associe(int id,TO obj) {
        if (this.idExist(id)) {
            throw new Error("identificateur existant");
        }
        this.idVersObjet.put(id, obj);
        this.objetVersId.put(obj, id);
    }

    
    
}
