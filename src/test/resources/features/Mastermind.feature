Feature: Mastermind

  Background: le joueur a choisi le jeux du Mastermind et le mode Challengeur

  Scenario: Définir une combinaison du jeu Mastermind
    Given Un joueur de Mastermind ayant le statut codeur
    When il génère une combinaison de symbole
    Then cette combinaison de symboles est associée à son adversaire et contient seulement les symboles autorisés

  Scenario: Evaluer une proposition du jeu Mastermind
    Given un joueur ayant généré une combinaison de symbole
    When l'adversaire propose une combinaison de symbole
    Then Le resultat de l'evaluation est deux nombre séparés par une virgule