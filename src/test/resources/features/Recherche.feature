Feature: Recherche +/-

  Background: le joueur a choisi le jeux du Recherche +/- et le mode Challengeur

  Scenario: Définir une combinaison du jeu Recherche
    Given Un joueur de recherche ayant le statut codeur
    When il génère une combinaison de chiffres
    Then cette combinaison chiffrée est associée à son adversaire

  Scenario: Evaluer une proposition du jeu recherche
    Given un joueur ayant généré une combinaison de chiffres
    When l'adversaire propose une combinaison de chiffres
    Then Le resultat de l'evaluation est de la bonne longeur et contient seulement les opérateurs autorisés