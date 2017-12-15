package Jeu_texte;
/**
 * <b>Test est la classe qui nous permet de lancer le jeu.</b>
 * <p>Lorsqu'on lance la classe test, elle va :</p>
 * <ul>
 * <li>Générer un personnage.</li>
 * <li>Rentrer dans une boucle tant que pour vérifier que l'utilisateur ne quitte pas le jeu (choix différent de 0).</li>
 * <li>On affiche la carte.</li>
 * <li>On propose à l'utilisateur de déplacer son personnage.</li>
 * </ul>
 * <p>
 * La carte sera créée lors de la création du héro.
 * </p>
 * 
 * @see Carte
 * @see Personnage
 * 
 * @author RaphaelRenout
 * @version 1.0
 */
public class Test {
	
    /**
     * <b>Méthode main de la classe test.</b>
     * <p>C'est ici qu'on va créer le personnage et réaliser l'affichage de la carte ainsi que proposer
     * à l'utilisateur de déplacer son personnage tant que l'utilitsateur ne décide pas de quitter le jeu.
     * </p>
     * @param args
     * 			Paramètre de la classe main 			
     */
    public static void main(String[] args)
    {
        // Lorsqu'on lance le jeu on créé un Personnage
        Personnage hero = new Personnage();
        // La création du personnage s'occupera de créer une carte
        
        // Tant que le choix rentrer au clavier n'est pas égale à 0 (sortie du jeu), alors :
        while (hero.getChoixclavier() != 0)
        {
            // On affiche la carte
            hero.getAfficherCarte();
            // Et on propose de déplacer le personnage
            hero.deplacerpersonnage();
        }
        System.out.println("Le jeu est terminé");
    } 
}
