package Jeu_texte;

/**
 * <b>Carte est la classe qui génère une map lors de la création d'un héro.</b>
 * <p>Une carte est caractérisée par les informations suivantes :</p>
 * <ul>
 * <li>Un tableau à deux dimensions qui va servir à l'afficher dans la console.</li>
 * <li>Un tableau de contenu à une dimension avec le nombre d'éléments à rajouter dans le tableau.</li>
 * </ul>
 * <p>
 * Pour le moment la taille de la carte n'est pas modifiable (20x20) mais on peut envisager l'aggrandir ou la diminuer au besoin pour créer des niveaux.
 * De plus, une autre erreur est apparue :
 * Si on créé une carte et que l'utilisateur est bloqué (par exemple un arbre sous et à droite de lui) alors on doit quitter et relancer le jeu.
 * </p>
 * 
 * @see Personnage
 * 
 * @author RaphaelRenout
 * @version 1.0
 */
public class Carte {    
	/**
     * <p>Un tableau à deux dimensions (de taille 20 x 20) qui va générer la map qu'on affiche dans la console.</p>
     * 
     * @see Carte#Carte()
     * @see Carte#getValCase(int, int)
     * @see Carte#setValCase(int posx, int posy, String val)
     */
    String carte[][] = new String [20][20];
    
    /**
     * <b>On créé un second tableau qui comporte ici le nombre d'objets différents que l'utilisateur va rencontrer.</b>
     * <p>(herbe, fleur, monstre, pièces, ...)
     * Ici, il y en a 9 (de 0 à 8).</p>
     */
    int contenu[]= new int [9];
    
    /**
     * <b>Constructeur de la carte.</b>
     * <p>A la création d'une carte on va remplir le tableau de 0 (ce qui correpsond à de l'herbe.
     * Puis on va mettre notre héro à la position 0,0 (en haut à gauche).
     * Et on va ensuite lancer la méthode initialiserCarte() qui va nous permettre de remplir le contenu du tableau.</p>
     * 
     * @see Carte#initialiserCarte()
     */
    public Carte(){
        // On commence d'abord par remplir tout le tableau avec des 0 partout (Herbe)
        for (int i=0; i<20; i++)
        {
            for (int j=0; j<20; j++)
            {
                carte[i][j]="0";
            }
        }
        // On ajoute ensuite le personnage à la pos 0,0
        carte[0][0]="X";
        // Va permettre de remplir avec les différents contenus
        initialiserCarte();
    }
    
    /**
     * <b>Cette fonction permet de remplir le contenu (avec les différents objets) de la map.</b>
     * <p>On va ainsi tout d'abord dire qu'il va exister tant de fleurs, tant d'arbres, ... de manière aléatoire.
     * Le calcul est fait entre le minimum demandé et 2x le minimum.
     * Ainsi nous aurons par exemple 8 fleurs à mettre.</p>
     * <p>On va ensuite tirer une case au hasard et si c'est un 0, alors on va rentrer une fleur à la place (ce qui correspond à un 1).
     * Ainsi de suite jusqu'à ce qu'il n'y ai plus de fleurs à rentrer.
     * Et on reprend ce même principe pour chaque objet.
     * </p>
     * <p>Le tableau "contenu" va ainsi nous permettre de sauvegarder le nombre d'éléments à rajouter concernant le chiffre symbolisant l'objet.
     * Exemple : 1 correspond à la fleur, si on a 8 fleurs à rentrer, on aura donc : contenu[1]=8.
     * Et ainsi de suite pour tous les objets.
     */
    public void initialiserCarte()
    {
        // On va maintenant remplir notre tableau avec les bonnes valeurs
        // L'index correspond à la valeur de la case
        // Et son contenu (int) correspond au nb de fois qu'il doit apparaitre
        contenu[1] = (int) (5 + (Math.random() * (11 - 5))); // Fleur
        contenu[2] = (int) (5 + (Math.random() * (11 - 5))); // obstacle : Arbre
        contenu[3] = (int) (10 + (Math.random() * (21 - 10))); // obstacle : Rocher
        contenu[4] = contenu[6] = (int) (3 + (Math.random() * (7 - 3))); // Clé et Coffre (le même nombre pour pouvoir ouvrir tous les coffres)
        contenu[5] = (int) (20 + (Math.random() * (41 - 20))); // Sous
        contenu[7] = (int) (10 + (Math.random() * (21 - 10))); // Piege
        contenu[8] = (int) (15 + (Math.random() * (31 - 15))); // Monstre
        
        // On fait ensuite une boucle for de la table de notre tableau
        for (int index=1; index<=8; index++)
        {
            int i=0;
            int j=0;
            
            // Fait l'intérieur tant que le contenu n'est pas égal à 0 c'est à dire qu'on a pas encore rempli le nb attendu
            while(contenu[index] > 0)
            {
                // On fait un ramdom pour avoir une position aléatoire dans notre carte
                i=(int) (Math.random() * (20));
                j=(int) (Math.random() * (20));
                
                // Si la case est un 0, alors on peut modifier notre case pour ajouter notre contenu
                if ("0".equals(carte[i][j]))
                {
                    // On rajoute le contenu en mode String
                    carte[i][j]=("" + index);
                    // Et on enlève une valeur dans notre tableau car on a rajouté une case
                    contenu[index]--;
                }
            }  
        }
    }
    
    /**
     * Retourne la valeur de la case aux positions posx et posy renseignées.
     * 
     * @param posx
     * 			position en abscisse
     * @param posy
     * 			position en ordonnée
     * @return la valeur de la position (de 0 à 8)
     */
    public String getValCase(int posx, int posy)
    {
        return carte[posx][posy];
    }
    
    /**
     * Permet de modifier la valeur d'une case aux positions posx et posy renseignées par la valeur val donnée.
     * 
     * @param posx
     * 			position en abscisse
     * @param posy
     * 			position en ordonnée
     * @param val
     * 			nouvelle valeur de la case
     */
    public void setValCase(int posx, int posy, String val)
    {
        carte[posx][posy]=val;
    }
    
    /**
     * <b>Méthode qui va permettre d'afficher la carte</b>
     * <p>Dans cette méthode, on va afficher case par case l'élément pour la ligne en laissant un espace à chaque fois afin que la lecture soit le plus claire possible
     * pour l'utilitsateur. </p>
     * <p>On répète cette opération pour chaque ligne afin d'obtenir toute la carte (en sautant une case à chaque nouvelle ligne).
     * </p>
     */
    public void afficherCarte()
    {
        // On affiche ici case par case avec un espace entre chaque case
        // Et on répète le nombre de fois qu'il y a de lignes cette même opération
        int i=0;
        while (i < 20)
        {
          int j = 0;
          while(j < 20)
          {
            System.out.print(carte[i][j]);
            System.out.print(" ");
            j++;
          }
          System.out.println("");
          i++;
        }
    }
    
}
