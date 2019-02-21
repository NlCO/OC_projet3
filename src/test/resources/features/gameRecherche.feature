Feature: Game Recherche +/-

  Scenario: Définir une combinaison du jeu
    Given Un joueur ayant le statut codeur et le paramètre taille
    When il génère une combinaison
    Then une combinaison avec les parametres