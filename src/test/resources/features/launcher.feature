Feature: Launcher

 # En tant que joueur je veux choisir un jeu parmi les jeux proposés

 Scenario: Afficher les 2 jeux disponibles
    Given le lancement du programme
    When j'appelle la liste des jeux
    Then j'obtiens une liste de 2 jeux contenant Mastermind et "Recherche +/-"

 #  En tant que joueur je veux avoir la liste des modes de jeu disponible afin d'initialiser la partie

 Scenario: Afficher les modes de Jeu
    Given un fois le jeu choisi
    When j'affiche les modes jeu disponibles
    Then les modes CHALLENGER, DEFENSEUR et DUEL doivent être proposés

 Scenario Outline: Détermination des roles des joueurs
    Given pour une liste de joueurs
    When le joueur choisi le mode <gameMode>
    Then le statut de ses roles est <joueurDecodeur>, <joueurCodeur> et celui de son adversaire <adversaieDecodeur>, <adversaireCodeur>

 Examples:
    |gameMode  |joueurDecodeur|joueurCodeur|adversaieDecodeur|adversaireCodeur|
    |CHALLENGER|true          |false       |false            |true            |
    |DEFENSEUR |false         |true        |true             |false           |
    |DUEL      |true          |true        |true             |true            |


