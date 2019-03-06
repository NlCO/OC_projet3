Feature: Recherche +/-

  Background: le joueur a choisi le jeux du Recherche +/- et le mode Challengeur

  Scenario: Définir une combinaison du jeu
    Given Un joueur ayant le statut codeur
    When il génère une combinaison
    Then cette combinaison est associée à son adversaire

  Scenario: Evaluer une proposition
    Given un joueur joueur ayant généré une combinaison
    When l'adversaire propose une combinaison
    Then Le resultat de l'evaluation est de la bonne longeur et contient seulement des symboles autorisés