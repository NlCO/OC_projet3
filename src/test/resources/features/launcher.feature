Feature: Launcher

 # En tant que joueur je veux choisir un jeu parmi les jeux dispos

 Scenario: Afficher les jeux disponibles
    Given les jeux "Mastermind", "Recherche +/-" et "Locked" sont disponibles
    When j'appelle la liste des jeux
    Then j'obtiens une liste de 3 jeux : "Mastermind", "Recherche +/-" et "Locked"

 Scenario: Il n'y a pas de jeux disponibles
    Given aucun jeu n'est disponible
    When j'appelle la liste des jeux
    Then La liste contient 0 jeu

 #  En tant que joueur je veux avoir la liste des modes de jeu disponible afin d'initialiser la partie

 Scenario: Afficher les modes de Jeu
    Given un fois le jeu choisi
    When j'affiche les modes jeu disponibles
    Then les modes CHALLENGER, DEFENSEUR et DUEL doivent être proposés

 Scenario Outline: Roles pour un joueur
    Given pour un joueur et son adversaire
    When le joueur choisi le mode <gameMode>
    Then le statut de ses roles est <joueurDecodeur>, <joueurCodeur> et celui de son adversaire <adversaieDecodeur>, <adversaireCodeur>

 Examples:
    |gameMode  |joueurDecodeur|joueurCodeur|adversaieDecodeur|adversaireCodeur|
    |CHALLENGER|true          |false       |false            |true            |
    |DEFENSEUR |false         |true        |true             |false           |
    |DUEL      |true          |true        |true             |true            |


