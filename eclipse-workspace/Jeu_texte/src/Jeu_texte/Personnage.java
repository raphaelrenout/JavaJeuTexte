package Jeu_texte;

import java.util.*;

/**
 * <b>Personnage est la classe représentant un héro qui joue au jeu.</b>
 * <p>Un héro est caractérisé par les informations suivantes :</p>
 * <ul>
 * <li>Un nombre de pièces</li>
 * <li>Un nombre de points de vie</li>
 * <li>Un nombre de clés</li>
 * <li>Une position sur la carte</li>
 * <li>Un choix de direction (rentrée par l'utilisateur)</li>
 * </ul>
 * <p>
 * De plus, un héro est rattaché à une carte (qui sera créée lors de la création du héro, donc chaque héro créé aura une carte spécifique).
 * </p>
 * 
 * @see Carte
 * 
 * @author RaphaelRenout
 * @version 1.0
 */
public class Personnage {
	
    /**
     * <p>La création d'une carte dont est rattaché l'héro.</p>
     * 
     * @see Carte#initialiserCarte()
     */
    Carte map;
    
    /**
     *  <p>On va créer une variable pour envoyer un message sous la carte.
     *  Ce message pourra indiquer qu'on gagne une pièce, perd un pv, ...
     *  Ou les messages d'erreurs : saisie clavier incorrect ou sortie de la carte, ...</p>
     */
    private String mess;
    
    /**
     * <p>Le nombre de pièces, il est possible d'en gagner.</p>
     * 
     * @see Personnage#getSous()
     * @see Personnage#setSous(int)
     */
    private int pieces;
    
    /**
     * <p>Le nombre de points de vie, il est possible d'en perdre.</p>
     * 
     * @see Personnage#getPv()
     * @see Personnage#setPv(int)
     */
    private int pv;
    
    /**
     * <p>Le nombre de clés, il est possible d'en gagner.</p>
     * 
     * @see Personnage#getCles()
     * @see Personnage#setCles(int)
     */
    private int cles;   
    
    /**
     * <p> Il s'agit ici de la position en abscisse du héro sur la carte qui va bien entendu bouger en fonction du choix de l'utilisateur.</p>
     * 
     * @see Personnage#getX()
     * @see Personnage#setX(int)
     */
    private int posx;
    
    /**
     * <p> Il s'agit ici de la position en ordonnée du héro sur la carte qui va bien entendu bouger en fonction du choix de l'utilisateur.</p>
     * 
     * @see Personnage#getY()
     * @see Personnage#setY(int)
     */
    private int posy;
    
    /**
     * <p> Le choix du clavier qui sera demandé à chaque fois à l'utilisateur.</p>
     * 
     * @see Personnage#getChoixclavier()
     */
    private int choixclavier;
    
    /**
     * <b>Constructeur du personnage.</b>
     * <p>A la création d'un personnage on va :</p>
     * <ul>
     * <li>Créer une carte.</li>
     * <li>Mettre le personnage à la position 0,0.</li>
     * <li>Mettre le nombre de pièces et de clés à 0 et les points de vie à 10.</li>
     * <li>Mettre également le choix du clavier à 1 (et pas à 0 qui correspondrait à quitter le jeu).</li>
     * <li>Mettre un champ vide pour le message (afin de ne rien afficher au lieu d'afficher "null" si rien n'est à afficher).</li>
     * </ul>
     * 
     * @see Personnage#map
     * @see Personnage#posx
     * @see Personnage#posy
     * @see Personnage#pieces
     * @see Personnage#cles
     * @see Personnage#pv
     * @see Personnage#choixclavier
     * @see Personnage#mess
     */
    public Personnage()
    {
        this.map = new Carte();
        this.posx = 0;
        this.posy = 0;
        this.pieces = cles = 0;
        this.pv = 10;
        this.choixclavier = 1;
        this.mess="";
    }
    
    /**
     * <b>Choix de déplacer le personnage d'une case à une autre.</b>
     * <p>On va d'abord vérifier que l'utilisateur n'a ni gagné (pieces = 10) ni perdu le jeu (pv =0)
     * Sinon on va alors récupérer le choix de l'utilisateur via la méthode getInteger()</p>
     * <p>En fonction du choix récupérer nous allons soit monter, descendre, aller à gauche ou à droite.
     * Si aucun choix ne correspond, un message d'erreur est indiqué</p>
     * 
     * @see Personnage
     * @see Personnage#getInteger()
     * @see Personnage#changement(int, int)
     */
    public void deplacerpersonnage() 
    {        
        /* On vérifie d'abord que le joueur n'a pas gagné (pièces = 10)
		Si oui, on met un message et on quitte le jeu (avec choixclavier=0 pour sortir du "tant que" dans la classe Test
        */
        if (this.pieces == 10)
        {
            System.out.println("Bravo vous avez gagné le jeu !");
            this.choixclavier=0;
        }
        /* Sinon on vérifie aussi qu'il n'a pas perdu s'il n'a pas plus de pv
        Même principe : Message + choixclavier=0 pour quitter le jeu
        */
        else if (this.pv == 0 )
        {
            System.out.println("Vous avez perdu ! Retentez votre chance");
            this.choixclavier=0;
        }
        // Sinon on peut lancer le choix du clavier
        else
        {
            // Cette fonction va nous permettre de récupérer le choix du clavier seulement si l'utilisateur rentre un entier (voir tout en bas la méthode)
            getInteger();

            // Et on va vérifier si l'utilisateur veut :
            switch (choixclavier)
            {
                case 2: // Descendre : Dans ce cas on incrémente d'1 la ligne (pour descendre dans notre tableau)
                    changement(this.posx+1, this.posy);
                    break;
                case 4: // A gauche : Dans ce cas on décrémente d'1 la colonne (pour aller à gauche dans notre tableau)
                    changement(this.posx, this.posy-1);
                    break;
                case 8: // Monter : Dans ce cas on décrémente d'1 la ligne (pour monter dans notre tableau)
                    changement(this.posx-1, this.posy);
                    break;
                case 6: // A droite : Dans ce cas on incrémente d'1 la colonne (pour aller à droite dans notre tableau)
                    changement(this.posx, this.posy+1);
                    break;
                case 0: // On quitte le jeu (donc on ne fait rien)
                    break;
                default: // On met un message d'erreur si le choix ne convient pas
                		this.mess="Merci de choisir un choix valide (8 : Haut, 6 : Droit, 2 : Bas, 4 : Gauche, 0 : Quitter)";
                		break;
            }
        }
    }
    
    /**
     * <b>Réaliser le déplacement une fois choisi</b>
     * <p>
     * La méthode changement va permettre de réaliser le déplacement du personnage sur la carte une fois le choix de l'utilisateur enregistré.
     * Ainsi en fonction de ce que l'on va rencontrer sur la case où l'on souhaite aller, différentes actions vont se réaliser
     * </p>
     * 
     * @param absc
     * 			La potentielle nouvelle abscisse du personnage
     * @param ordo
     * 			La potentielle nouvelle ordonnée du personnage
     * 
     * @throws ArrayIndexOutOfBoundsException Si jamais le choix de l'utilisateur l'amène à sortir de la carte
     * 
     * @see Carte#setValCase(int, int, String)
     */
    public void changement(int absc, int ordo)
    {
        try {
            // Herbe : On va sur la case et rien ne se passe
            if ("0".equals(this.map.getValCase(absc,ordo)))
            {
                // Change la case où était le joueur pour mettre de l'herbe
                map.setValCase(this.posx, this.posy, "0");
                // Change la position du personnage sur la nouvelle case
                map.setValCase(absc, ordo, "X");
                // Enregistre les nouvelles coordonnées du héro
                setX(absc);
                setY(ordo);
                // On remet à vide le message (afin de ne pas répéter deux fois la même chose si on a rien à dire (ex : on va sur une case herbe rien à dire mais il faut que le message soit vide)
                mess="";
            }

            // Fleur : On va sur la case et rien ne se passe (similaire à herbe)
            if ("1".equals(this.map.getValCase(absc,ordo)))
            {
                map.setValCase(this.posx, this.posy, "0");
                map.setValCase(absc, ordo, "X");
                setX(absc);
                setY(ordo);
                // On remet à vide le message
                mess="";
            }

            // Arbre : Case bloquée, on ne peut pas y aller
            // Rien ne se passe mais message pour prévenir
            if ("2".equals(this.map.getValCase(absc,ordo)))
            {
                mess="Mouvement impossible, un arbre bloque le passage !";
            }

            // Rocher : Case bloquée, on ne peut pas y aller
            // Rien ne se passe mais message pour prévenir
            if ("3".equals(this.map.getValCase(absc,ordo)))
            {
                mess="Mouvement impossible, un rocher bloque le passage !";
            }

            // Clés : On va sur la case et rajoute une clé dans l'inventaire, notre case précédente devient de l'herbe
            // On rajoute un message pour prévenir le joueur
            if ("4".equals(this.map.getValCase(absc,ordo)))
            {
                map.setValCase(this.posx, this.posy, "0");
                map.setValCase(absc, ordo, "X");
                setX(absc);
                setY(ordo);
                setCles(1);
                mess="Bravo, vous venez de gagner une clé, trouvez vite un coffre à ouvrir !";
            }

            // Piece : On va sur la case et rajoute une piece dans l'inventaire, notre case précédente devient de l'herbe
            // On rajoute un message pour prévenir le joueur
            if ("5".equals(this.map.getValCase(absc,ordo)))
            {
                map.setValCase(this.posx, this.posy, "0");
                map.setValCase(absc, ordo, "X");
                setX(absc);
                setY(ordo);
                setSous(1);
                mess="Bravo, vous venez de gagner une pièce, vous en avez désormais : " + getSous() + " sous";
            }

            // Cadenas : Si on a une clé, on va sur la case et on rajoute les pièces, notre case précédente devient de l'herbe
            // Sinon la case est bloquée et on ne peut y accéder
            // Dans les deux cas : on rajoute un message pour prévenir le joueur
            if ("6".equals(this.map.getValCase(absc,ordo)))
            {
                // On vérifie si on a une clé pour ajouter les pièces et aller sur la case
                if (cles > 0)
                {
                    map.setValCase(this.posx, this.posy, "0");
                    map.setValCase(absc, ordo, "X");
                    setX(absc);
                    setY(ordo);
                    setSous(2);
                    setCles(-1);
                    mess="Bravo, vous venez d'ouvrir un coffre, vous en avez désormais : " + getSous() + " sous"; 
                }
                // Sinon on est bloqué donc on ne fait rien
                else 
                {
                    mess="Vous n'avez pas de clés, retentez votre chance avec une clé !";
                }

            }

            // Piege : On va sur la case et on perd un pv, notre case précédente devient de l'herbe
            // On rajoute un message pour prévenir le joueur
            if ("7".equals(this.map.getValCase(absc,ordo)))
            {
                map.setValCase(this.posx, this.posy, "0");
                map.setValCase(absc, ordo, "X");
                setX(absc);
                setY(ordo);
                setPv(-1);
                mess="Aie ! Vous venez de tomber dans un piège, vous perdez un point de vie, vous avez maintenant : " + getPv() + " points de vie";
            }

            // Monstre : On va sur la case et on perd 2 pv, notre case précédente devient de l'herbe
            // On rajoute un message pour prévenir le joueur
            if ("8".equals(this.map.getValCase(absc,ordo)))
            {
                map.setValCase(this.posx, this.posy, "0");
                map.setValCase(absc, ordo, "X");
                setX(absc);
                setY(ordo);
                setPv(-2);
                mess="Aie ! Vous venez de tomber sur un monstre, vous perdez deux points de vie, vous avez maintenant : " + getPv() + " points de vie";
            }
            
        // Exception qui permet ici de vérifier qu'on reste dans le tableau (sinon affiche un message d'erreur mais ne quitte pas le jeu)
        } catch (ArrayIndexOutOfBoundsException e) {
            mess="Vous sortez de la carte, merci de prendre une autre direction";
        }
    }
    
    /**
     * Sert à récupérer la carte de la classe Carte.
     * 
     * @see Carte#afficherCarte()
     */
    public void getAfficherCarte()
    {
        map.afficherCarte();
    }
    
    /**
     * Retourne le choix saisi au clavier.
     * 
     * @return Le choix du clavier
     */
    public int getChoixclavier()
    {
        return this.choixclavier;
    }
    
    /**
     * Retourne la position en abscisse.
     * 
     * @return la position en abscisse
     */
    public int getX()
    {
        return this.posx;
    }
    
    /**
     * Retourne la position en ordonnée.
     * 
     * @return la position en ordonnée
     */
    public int getY()
    {
        return this.posy;
    }
    
    /**
     * Modifie la position en abscisse du personnage.
     * 
     * @param x
     * 			La nouvelle position en abscisse
     */
    public void setX(int x)
    {
        this.posx = x;
    }
    
    /**
     * Modifie la position en ordonnée du personnage.
     * 
     * @param y
     * 			La nouvelle position en ordonnée
     */
    public void setY(int y)
    {
        this.posy = y;
    }
    
    /**
     * Retourne le nombre de points de vie.
     * 
     * @return le nombre de points de vie.
     */
    public int getPv()
    {
        return pv;
    }
    
    /**
     * Modifie le nombre de points de vie.
     * 
     * @param val
     * 			Le nombre de pv à rajouter.
     */
    public void setPv(int val)
    {
        this.pv += (val);     
    }
    
    /**
     * Retourne le nombre de clés.
     * 
     * @return le nombre de clés.
     */
    public int getCles()
    {
        return this.cles;
    }
    
    /**
     * Modifie le nombre de clés :
     * <ul>
     * <li>A rajouter si on en trouve une.</li>
     * <li>A enlever si on ouvre un coffre</li>
     * </ul>
     * 
     * @param val
     * 			Le nombre de clés à rajouter ou à enlever.
     */
    public void setCles(int val)
    {
        this.cles += (val);
    }
    
    /**
     * Retourne le nombre de pièces.
     * 
     * @return le nombre de pièces.
     */
    public int getSous()
    {
        return this.pieces;
    }
    
    /**
     * Modifie le nombre de pièces.
     * 
     * @param val
     * 			Le nombre de pièces.
     */
    public void setSous(int val)
    {
        this.pieces += (val);
    }
    
    /**
     * <b>Retourne le choix rentré au clavier par l'utilisateur.</b>
     * <p>Dans un premier temps, on affiche le nb de pièces, de sous et de clés qu'on possède et le message s'il y en a un.
     * On va ensuite enregistrer ce que rentre l'utilisateur et vérifier qu'il s'agit bien d'un entier à l'aide d'une exception.
     * Tant que ce n'est pas un entier, on continue de lui demander de rentrer un entier.
     * </p>
     * 
     * @throws InputMismatchException Si jamais le choix de l'utilisateur n'est pas un entier.
     * 
     * @return Le choix du clavier.
     */
    public int getInteger(){
        // Initialisation de la saisie du clavier
        Scanner sc = new Scanner(System.in);
        // On affiche d'abord les informations pv, pièces et clés pour que l'utilisateur sache où il en est
        System.out.println();
        System.out.print("Point de vie : " + getPv());
        System.out.print(" ; Pièces : " + getSous());
        System.out.println(" ; Clés : " + getCles());
        System.out.println(this.mess);
        // On va ensuite faire un test de vérification tant que test == false
        boolean test = false;
        while(test == false)
        {
            try{
	            // On affiche ensuite les différentes possibilités de mouvement
	            System.out.println("8 : Haut, 6 : Droit, 2 : Bas, 4 : Gauche, 0 : Quitter");
	            
	            // on enregistre le choix
	            this.choixclavier= sc.nextInt();
	            
	            // Si le chiffre renré est un bien un int alors on met test à true pour sortir du tant que et on retourne le résultat
	            test = true;
	            return this.choixclavier;
            }
            //Vérification pour tester si l'entier est un Int, sinon on met un message d'erreur et l'utilisateur est invité à retaper son choix
            catch(InputMismatchException e)
            {
            		System.out.println("Merci de rentrer un chiffre valide");
            	}
            // On oubli pas de passer à la prochaine saisie sinon on reste dans une boucle infinie
            sc.nextLine();
        }
        // Sert juste pour avoir un return en dehors des boucles (pas de wardings)
        return this.choixclavier;
    }
}
