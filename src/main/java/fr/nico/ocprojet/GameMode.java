package fr.nico.ocprojet;


/**
 * Liste des modes de jeu disponibles
 */
public enum GameMode {
    CHALLENGER("Challenger",true, false),
    DEFENSEUR("Défenseur", false, true),
    DUEL("Duel", true, true);

    private final String label;
    protected final boolean decodeur;
    protected final boolean codeur;

    GameMode(String label, boolean decodeur, boolean codeur){
        this.label = label;
        this.decodeur = decodeur;
        this.codeur = codeur;
    }

    @Override
    public String toString() {
        return label;
    }
}
