Feature: Launcher

  """
  En tant que joueur je veux choisir un jeu parmi les jeux dispos
  """

  Scenario: Afficher les jeux disponibles
    Given les jeux "Mastermind", "Recherche +/-" et "Locked" sont disponibles
    When j'appelle la liste des jeux
    Then j'obtiens une liste de 3 jeux : "Mastermind", "Recherche +/-" et "Locked"

  Scenario: Il n'y a pas de jeux disponibles
    Given aucun jeu n'est disponible
    When j'appelle la liste des jeux
    Then j'obtiens une liste vide de 0 jeu

  """
  En tant que joueur je veux avoir la liste des modes de jeu disponible afin d'initialiser la partie
  """

  Scenario: Afficher les modes de Jeu
    Given un fois le jeu choisi
    When j'affiche les modes jeu disponibles
    Then les modes CHALLENGER, DEFENSEUR et DUEL doivent être proposés

  Scenario Outline: Roles pour un joueur
    Given pour un joueur
    When je choisi le <gameMode>
    Then le statut des roles est <décodeur> et <codeur>

  Examples:
    |gameMode  |décodeur|codeur|
    |Challenger|true    |false |
    |Défenseur |false   |true  |
    |Duel      |true    |true  |


