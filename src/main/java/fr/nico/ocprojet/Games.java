package fr.nico.ocprojet;


/**
 * Liste des jeux disponibles : Recherche +/- et Mastermind
 *
 */
public enum Games {

    R("Recherche +/-", "R"),
    M("Mastermind", "M");

    private final String gameName;
    private final String abbreviation;

    Games(String gameName, String abbreviation) {
        this.gameName = gameName;
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return gameName;
    }
}
