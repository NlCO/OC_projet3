package fr.nico.ocprojet;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste des modes de jeu disponibles : Challenger, Défenseur et Duel
 *
 */
public enum GameMode {
    CHALLENGER("Challenger","C",true, false, "DEFENSEUR"),
    DEFENSEUR("Défenseur","D", false, true, "CHALLENGER"),
    DUEL("Duel","VS", true, true, "DUEL");

    private final String label;
    private final String code;
    protected final boolean decodeur;
    protected final boolean codeur;
    private final String roleadversaire;

    GameMode(String label, String code, boolean decodeur, boolean codeur, String roleadversaire){
        this.label = label;
        this.code = code;
        this.decodeur = decodeur;
        this.codeur = codeur;
        this.roleadversaire = roleadversaire;
    }

    /**
     * Recupération de l'enum à partir de sa valeur code
     * La methode est static pour pouvoir etre appelée en utilisant la class enum :
     *  "non-static method modeFromCode(java.lang.String) cannot be referenced from a static context"
     *
     * @param code valeur du paramètre code à rechercher
     * @return l'enum corespondant à la valeur passée en paramètre (null si absente)
     */
    public static GameMode modeFromCode(String code) {
        Map<String, GameMode> map = new HashMap<>();
        for (GameMode mode: GameMode.values()) {
            map.put(mode.getCode(), mode);
        }
        return map.get(code);
    }

    public GameMode getRoleadversaire() {
        return GameMode.valueOf(roleadversaire);
    }

    public String getCode(){
        return code;
    }

    @Override
    public String toString() {
        return label;
    }
}
