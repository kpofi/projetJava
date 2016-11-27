package io;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import elements.Carte;
import elements.Case;
import elements.Incendie;
import elements.NatureTerrain;
import robots.Robot;
import robots.RobotAChenille;
import robots.RobotAPatte;
import robots.RobotARoue;
import robots.RobotDrone;
import simulation.DonneesSimulation;



/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des méthodes, inspirées de celles présentes
 * (ou non), qui CREENT les objets au moment adéquat pour construire une
 * instance de la classe DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues:
 *    public static DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui lisent les données,
 * créent les objets adéquats et les ajoutent ds l'instance de
 * DonneesSimulation.
 */
public class LecteurDonnees {


    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     * @param fichierDonnees nom du fichier à lire
     */
    public static void lire(String fichierDonnees)
        throws FileNotFoundException, DataFormatException {
        System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        lecteur.lireCarte();
        lecteur.lireIncendies();
        lecteur.lireRobots();
        scanner.close();
        System.out.println("\n == Lecture terminee");
    }

    /**
     * Créer la carte et les éléments de la carte
     * (robots et incendies).
     * @param fichierDonnees nom du fichier à lire
     */

    public static DonneesSimulation creerDonnees(String fichierDonnees)
        throws FileNotFoundException, DataFormatException {
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);

        Carte c = lecteur.creerCarte();
        Incendie i[] = lecteur.creerIncendies(c);
        Robot r[] = lecteur.creerRobots(c);
        int p;
        /* Création du graphe accessible pour chaque robot */
        for (p=0; p<r.length; p++) {
        	r[p].setGraphe(c);
        }
        DonneesSimulation s = new DonneesSimulation(c, i, r);
        scanner.close();
        return s;

    }

/**************************************************************************/
/*                            CREATION                                    */
/**************************************************************************/


    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     * @throws ExceptionFormatDonnees
     */
    private void lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m
            System.out.println("Carte " + nbLignes + "x" + nbColonnes
                    + "; taille des cases = " + tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    lireCase(lig, col);
                }
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }

    /**
     * Lit et affiche les donnees d'une case.
     */
    private void lireCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Case (" + lig + "," + col + "): ");
        String chaineNature = new String();
        //		NatureTerrain nature;

        try {
            chaineNature = scanner.next();
            // si NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            //			NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();

            System.out.print("nature = " + chaineNature);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }

        System.out.println();
    }


    /**
     * Lit et affiche les donnees des incendies.
     */
    private void lireIncendies() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                lireIncendie(i);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    private void lireIncendie(int i) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Incendie " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

            System.out.println("position = (" + lig + "," + col
                    + ");\t intensite = " + intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    private void lireRobots() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            System.out.println("Nb de robots = " + nbRobots);
            for (int i = 0; i < nbRobots; i++) {
                lireRobot(i);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private void lireRobot(int i) throws DataFormatException {
        ignorerCommentaires();
        System.out.print("Robot " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.print("position = (" + lig + "," + col + ");");
            String type = scanner.next();

            System.out.print("\t type = " + type);


            // lecture eventuelle d'une vitesse du robot (entier)
            System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

            if (s == null) {
                System.out.print("valeur par defaut");
            } else {
                int vitesse = Integer.parseInt(s);
                System.out.print(vitesse);
            }
            verifieLigneTerminee();

            System.out.println();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
    }




    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }

/**************************************************************************/
/*                            CREATION                                    */
/**************************************************************************/


    /**
    * Lit et créer la carte.
    * @throws ExceptionFormatDonnees
    */
    private Carte creerCarte() throws DataFormatException {
        ignorerCommentaires();
        Carte c = null;
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();
            if (tailleCases < 1000 && tailleCases >= 100) {
            	tailleCases /= 1.8f;
            } else if (tailleCases >= 1000) {
            	tailleCases /= 100;
            } 
            
            
            c = new Carte(nbLignes, nbColonnes, tailleCases);
            /* On boucle pour créer les cases */
            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    c.setCaseCarte(lig, col, creerCase(lig, col));
                }
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
            + "Attendu: nbLignes nbColonnes tailleCases");
          }
          return c;
          // une ExceptionFormat levee depuis lireCase est remontee telle quelle
        }

    /**
    * Lit et affiche les donnees d'une case.
    */
    private Case creerCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        Case ca  = null;
        String chaineNature = new String();

        try {
            chaineNature = scanner.next();
            NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

            ca = new Case(lig, col, nature);

            verifieLigneTerminee();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
            + "Attendu: nature altitude [valeur_specifique]");
        }
        return ca;
    }

    /**
     * Lit et affiche les donnees des incendies.
     */
    private Incendie[] creerIncendies(Carte c) throws DataFormatException {
        ignorerCommentaires();
        Incendie tabIn[] = null;
        try {
            int nbIncendies = scanner.nextInt();
            tabIn = new Incendie[nbIncendies];
            for (int i = 0; i < nbIncendies; i++) {
                tabIn[i] = creationIncendie(i, c);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
        return tabIn;
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    private Incendie creationIncendie(int i, Carte c) throws DataFormatException {
        ignorerCommentaires();
        Incendie inc = null;
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();
            inc = new Incendie(c.getCase(lig,col), intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
        return inc;
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    private Robot[] creerRobots(Carte c) throws DataFormatException {
        Robot r[] = null;
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            r = new Robot[nbRobots];
            for (int i = 0; i < nbRobots; i++) {
                r[i] = creerRobot(i, c);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
        return r;
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private Robot creerRobot(int i, Carte c) throws DataFormatException {
        ignorerCommentaires();
        Robot r = null;
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            String t = scanner.next();

            // lecture eventuelle d'une vitesse du robot (entier)

            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            if (s == null) {
            	if (t.equals("ROUES")) {
            		r = new RobotARoue(c.getCase(lig, col),i);
            	}
            	if (t.equals("DRONE")) {
            		r = new RobotDrone(c.getCase(lig, col),i);
            	}
            	if (t.equals("PATTES")) {
            		r = new RobotAPatte(c.getCase(lig, col),i);
            	}
            	if (t.equals("CHENILLES")) {
            		r = new RobotAChenille(c.getCase(lig, col),i);
            	}
            } else {
                int vitesse = Integer.parseInt(s);
            	if (t.equals("ROUES")) {
            		r = new RobotARoue(c.getCase(lig, col), vitesse, i);
            	}
            	if (t.equals("DRONE")) {
            		r = new RobotDrone(c.getCase(lig, col), vitesse, i);
            	}
            	if (t.equals("PATTES")) {
            		r = new RobotAPatte(c.getCase(lig, col), vitesse, i);
            	}
            	if (t.equals("CHENILLES")) {
            		r = new RobotAChenille(c.getCase(lig, col), vitesse, i);
            	}
            }
            verifieLigneTerminee();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
        return r;
    }
}
