package fr.nico.ocprojet;


import com.sun.istack.internal.NotNull;

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

    public static GameMode modeFromCode(@NotNull String code) {
        Map<String, GameMode> map = new HashMap<>();
        for (GameMode mode: GameMode.values()) {
            map.put(mode.getCode(), mode);
        }
        return map.get(code);
    }

    public String getRoleadversaire() {
        return roleadversaire;
    }

    public String getCode(){
        return code;
    }

    @Override
    public String toString() {
        return label;
    }
}
