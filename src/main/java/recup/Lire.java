package recup;

import java.io.*;

/**
version améliorée par Raphaël Schruoffeneger ; MIQ2 INSA Strasbourg 2013 
*/

public class Lire
{    
    //-------------------------------------------------------
    public static String S() // lire une chaine de caracteres
    {
        String x;
	char c;
	boolean erreur;
		
	do
	{
            x = "";
            erreur = false;
            
            try
            {
                while((c = (char)System.in.read()) != '\n')
		{
                    if (c != '\r')
                    {
                        x += c;
                    }
                }
            }
            catch(IOException e)
            {
                System.out.print(" > Veuillez entrer une chaine de caracteres : ");
                erreur = true;
            }
	} while(erreur);
		
	return x;
    } // fin de String S()


    //----------------------------------------------------
    public static byte b() // lire un nombre entier (byte)
    {
        byte x;
        boolean erreur;
        
        do
        {
            x = 0;
            erreur = false;
            
            try
            {
                x = Byte.parseByte(S());
            }
            catch(NumberFormatException e)
            {
                System.out.println(" > Veuillez entrer un nombre entier (byte) : ");
                erreur = true;
            }
        } while(erreur);
        
        return x;
    } // fin de byte b()

    
    //------------------------------------------------------
    public static short s() // lire un nombre entier (short)
    {
        short x;
        boolean erreur;
        
        do
        {
            x = 0;
            erreur = false;
            
            try
            {
                x = Short.parseShort(S());
            }
            catch(NumberFormatException e)
            {
                System.out.println(" > Veuillez entrer un nombre entier (short) : ");
                erreur = true;
            }
        } while(erreur);
    
    return x;
    } // fin de short s()
     
    
    //--------------------------------------------------
    public static int i() // lire un nombre entier (int)
    {
        String chaine;
        int x;
        boolean erreur;
 	 	
        do
        {
            x = 0;
            chaine = S();
            erreur = false;

            try
            {
                x = Integer.parseInt(chaine);
            }
            catch(NumberFormatException e)
            {
                if(chaineEstUnEntier(chaine))
                {
                    System.out.print(" > Votre nombre depasse les limites de stockage du format (int) !\n"
                        + " > Veuillez entrer un nombre compris entre "
                        + separer(Integer.MIN_VALUE) + " et "
                        + separer(Integer.MAX_VALUE) + " : ");
                }
                else
                {
                    System.out.print(" > Veuillez entrer un nombre entier (int) : ");
                }

                erreur = true;
            }	
        } while(erreur);
		
        return x;
    } // fin de int i()
        
    
    //----------------------------------------------------
    public static long l() // lire un nombre entier (long)
    {
        long x;
        boolean erreur;
 	
        do
        {
            x = 0;
            erreur = false;
            
            try
            {
                x = Long.parseLong(S());
            }
            catch(NumberFormatException e)
            {
                System.out.println(" > Veuillez entrer un nombre entier (long) : ");
                erreur = true;
            }
        } while(erreur);
        
        return x;
    } // fin de long l()
    
    
    //------------------------------------------------------
    public static double d() // lire un nombre reel (double)
    {
        double x;
        boolean erreur;
	  	
	do
	{
            x = 0;
            erreur = false;
	 		
            try
            {
                x = Double.parseDouble(S());
            }
            catch(NumberFormatException e)
            {
                System.out.print(" > Veuillez entrer un nombre reel (double) : ");
                erreur = true;
            }
        } while(erreur);
	 	 	
        return x ;
    } // fin de double d()
        
    
    //----------------------------------------------------
    public static float f() // lire un nombre reel (float)
    {
        float x;
        boolean erreur;
        
        do
        {
            x = 0;
            erreur = false;
            
            try
            {
                x = Float.parseFloat(S());
            }
            catch(NumberFormatException e)
            {
                System.out.println(" > Veuillez entrer un nombre reel (float) : ");
                erreur = true;
            }
        } while(erreur);
	
        return x;
    } // fin de float f()

    
    //-----------------------------------------
    public static char c() // lire un caractere
    {
        String chaine;
        
        chaine = S();
        
        if (chaine.length() == 0)
        {
            return '\n';
        }
        else
        {
            return chaine.charAt(0);
        }
    } // fin de char c()
        
    
    //----------------------------------------------------
    public static int choix(int nbrChoix) // lire un choix
    {
        int x;
        boolean erreur;
        
        do
        {
            x = 0;
            erreur = false;

            try
            {
                x = Integer.parseInt(S());
            }
            catch(NumberFormatException e)
            {
                System.out.print(" > Veuillez taper un numero compris entre 1 et " + nbrChoix + " : ");
                erreur = true;
            }

            if(!erreur && (x < 1 || x > nbrChoix))
            {
                System.out.print(" > Veuillez taper un numero compris entre 1 et " + nbrChoix + " : ");
                erreur = true;
            }
        } while(erreur);

        return x;
    } // fin de int choix(int nbrChoix)


    //----------------------------------------------------------------------------------------------
    public static int entierSupEgal(int min) // lire un nombre entier superieur ou egal a un minimun
    {
        String chaine;
        int x;
        boolean erreur;
        
        do
        {
            x = 0;
            chaine = S();
            erreur = false;

            try
            {
                x = Integer.parseInt(chaine);
            }
            catch(NumberFormatException e)
            {
                if(chaineEstUnEntier(chaine))
                {
                    System.out.print(" > Votre nombre depasse les limites de stockage du format (int) !\n"
                        + " > Veuillez entrer un nombre compris entre "
                        + separer(min) + " et " + separer(Integer.MAX_VALUE) + " : ");
                }
                else
                {
                    System.out.print(" > Veuillez entrer un nombre entier superieur ou egal a " + separer(min) + " : ");
                }

                erreur = true;
            }

            if(!erreur && x < min)
            {
                erreur = true;
                System.out.print(" > Veuillez entrer un nombre entier superieur ou egal a " + separer(min) + " : ");
            }
        } while(erreur);

        return x;
    } // fin de int entierSupEgal(int min)
    
    
    //----------------------------------------------------------------------------------------------
    public static int entierInfEgal(int max) // lire un nombre entier inferieur ou egal a un maximum
    {
        String chaine;
        int x;
        boolean erreur;
        
        do
        {
            x = 0;
            chaine = S();
            erreur = false;

            try
            {
                x = Integer.parseInt(chaine);
            }
            catch(NumberFormatException e)
            {
                if(chaineEstUnEntier(chaine))
                {
                    System.out.print(" > Votre nombre depasse les limites de stockage du format (int) !\n"
                        + " > Veuillez entrer un nombre compris entre "
                        + separer(Integer.MIN_VALUE) + " et " + separer(max) + " : ");
                }
                else
                {
                    System.out.print(" > Veuillez entrer un nombre entier inferieur ou egal a " + separer(max) + " : ");
                }

                erreur = true;
            }

            if(!erreur && x > max)
            {
                erreur = true;
                System.out.print(" > Veuillez entrer un nombre entier inferieur ou egal a " + separer(max) + " : ");
            }
        } while(erreur);

        return x;
    } // fin de int entierInfEgal(int max)
    
    
    //---------------------------------------------------------------------------------------------------------
    public static int entierCompris(int min, int max) // lire un nombre entier compris dans un intervalle ferme
    {
        String chaine;
        int x;
        boolean erreur;
        
        do
        {
            x = 0;
            chaine = S();
            erreur = false;

            try
            {
                x = Integer.parseInt(chaine);
            }
            catch(NumberFormatException e)
            {
                System.out.print(" > Veuillez entrer un nombre entier compris entre " + separer(min) + " et " + separer(max) + " : ");
                erreur = true;
            }

            if(!erreur && (x < min || x > max))
            {
                erreur = true;
                System.out.print(" > Veuillez entrer un nombre entier compris entre " + separer(min) + " et " + separer(max) + " : ");
            }
        } while(erreur);

        return x;
    } // fin de int entierCompris(int min, int max)


    //-------------------------------------------------------
    public static int choixBinaire() // lire un choix binaire
    {
        int x;
        boolean erreur;
        
        do
        {
            x = 0;
            erreur = false;

            try
            {
                x = Integer.parseInt(S());
            }
            catch(NumberFormatException e)
            {
                System.out.print(" > Veuillez taper '1' ou '2' ! ");
                erreur = true;
            }

            if(!erreur && x!= 1 && x!= 2)
            {
                System.out.print(" > Veuillez taper '1' ou '2' ! ");
                erreur = true;
            }
        } while(erreur);

        return x;
    } // fin de int choixBinaire()


    //----------------------------------------------------------------------------------------
    public static boolean chaineEstUnEntier(String chaine) // test si une chaine est un entier
    {
        int i;
        boolean estUnEntier;

        if(chaine.length() > 0)
        {
            if((chaine.charAt(0) < '0' || chaine.charAt(0) > '9') && chaine.charAt(0) != '-' || chaine.equals("-"))
            {
                estUnEntier = false;
            }
            else
            {
                i = 1;
                estUnEntier = true;

                while(i < chaine.length() && estUnEntier)
                {
                    if(chaine.charAt(i) < '0' || chaine.charAt(i) > '9')
                    {
                        estUnEntier = false;
                    }

                    i++;
                }
            }
        }
        else
        {
            estUnEntier = false;
        }

        return estUnEntier;
    } // fin de boolean chaineEstUnEntier(String chaine)
    
    
    //-------------------------------------------------------------------------------------------------------------------
    public static boolean chaineEstUnEntierPositif(String chaine) // test si une chaine est un entier strictement positif
    {
        int i;

        if(chaineEstLEntierNul(chaine))
        {
            return false;
        }
        
        if(chaine.length() > 0)
        {
            i = 0;

            while(i < chaine.length())
            {
                if(chaine.charAt(i) < '0' || chaine.charAt(i) > '9')
                {
                    return false;
                }

                i++;
            }
            
            return true;
        }
        else
        {
            return false;
        }
    } // fin de boolean chaineEstUnEntierPositif(String chaine)
    
    
    //-------------------------------------------------------------------------------------------------------------------
    public static boolean chaineEstUnEntierNegatif(String chaine) // test si une chaine est un entier strictement negatif
    {
        int i;

        if(chaineEstLEntierNul(chaine))
        {
            return false;
        }
        
        if(chaine.length() > 1)
        {
            if(chaine.charAt(0) != '-')
            {
                return false;
            }
            
            i = 1;

            while(i < chaine.length())
            {
                if(chaine.charAt(i) < '0' || chaine.charAt(i) > '9')
                {
                    return false;
                }

                i++;
            }
            
            return true;
        }
        else
        {
            return false;
        }
    } // fin de boolean chaineEstUnEntierNegatif(String chaine)
    
    
    //---------------------------------------------------------------------------------------------
    public static boolean chaineEstLEntierNul(String chaine) // test si une chaine est l'entier nul
    {
        int i;

        if(chaine.length() > 0)
        {
            if(chaine.charAt(0) == '-')
            {
                if(chaine.length() == 1)
                {
                    return false;
                }
                
                i = 1;
            }
            else
            {
                i = 0;
            }

            while(i < chaine.length())
            {   
                if(chaine.charAt(i) != '0')
                {
                    return false;
                }

                i++;
            }
            
            return true;
        }
        else
        {
            return false;
        }
    } // fin de boolean chaineEstLEntierNul(String chaine)
    
    
    //---------------------------------------------------------------------------------------------------------------
    public static String separer(int nombre) // separe les grands nombres par groupes de 3 chiffres avec des virgules
    {
        String converti, resultat;
        int i, k;
        
        converti = Integer.toString(nombre);
        resultat = "";
        k = 0;

        for(i = converti.length()-1; i >= 0; i--)
        {
            resultat = converti.charAt(i) + resultat;
            k++;
            
            if(k % 3 == 0 && i != 0 && !(i == 1 && converti.charAt(0) == '-'))
            {
                resultat = ',' + resultat;
            }
        }

        return resultat;
    } // fin de String separer(int nombre)
}
