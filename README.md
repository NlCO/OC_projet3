# Projet 3 du parcours développeur d'application Java d'OPENCLASSROOMS


Description
---
Mettre à disposition une application en java permettant de jouer à deux jeux de décodage de combinaison secrète à l'aide d'indications fournies à chaque tentative et avant l'épuisement du nombre d'essais permis.

Les jeux sont :
- **Recherche +/-** : découvrir la combinaison à x chiffres de l'adversaire à partir d'indications +, - ou = pour chaque chiffre de la combinaison proposée.

*exemple :*

    (Combinaison secrète : 1234)
    
    Proposition : 4278 -> Réponse : -=--
    Proposition : 2214 -> Réponse : -=+=
    ...

    
- **Mastermind** : découvrir la combinaison à x symboles de l'adversaire à partir des informations du nombres de symboles bien placés ou présents à la mauvaise position.

*exemple :*

    Combinaison secrète : 1234)
    
    Proposition : 4278 -> Réponse : 1 présent, 1 bien placé
    Proposition : 6274 -> Réponse : 2 bien placés
    ...

3 modes de jeux doivent être proposés :
- **Mode challenger** 

L'utilisateur doit trouver la combinaison secrète de l'ordinateur

- **Mode défenseur**

L'ordinateur de trouver la combinaison secrète de l'utilisateur

- **Mode duel**

L'ordinateur et l'utilisateur jouent tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.


L'application doit de plus pendre en charge :
- au lancement de l'application un mode "développeur" via un paramètre. Ce mode permet d'afficher la combinaison secrète au début de la partie afin de tester le bon comportement des jeux.
- un fichier de configuration des paramètres de jeu :
    * pour chaque jeu :
        * le nombre de cases de la combinaison secrète
        * le nombre d'essais possibles
    * pour le Mastermind :
        * le nombre de symboles utilisables (de 4 à 10)
- un fichier de configuration des logs de l'application gérées avec _Apache Log4J_

Prérequis
---
L'application a été développée en java avec le JDK 8 et l'outil de gestion maven en version 3.6.

Les commandes fournies par la suite supposent que le chemin vers les binaires "java.exe" et "mvn.exe" soit définis dans le PATH du système.


Création du package
---
A la racine du REPO (cloné), tapez la commande suivante :
```cmd
mvn clean package
```

Un répertoire **_target_** doit être créer et contenir au moins :

- un fichier **_projet3-1.0-SNAPSHOT-jar-with-dependencies.jar_**
- un sous-répertoire **_conf_** contenant un fichier **_config.properties_** et un fichier **_log4j2.xml_**


Lancement de l'application
---
La ligne de commande suivante permet de lancer le jeu.
```
java [-Dlog4j.configurationFile={chemin du xml}log4j.xml] -jar [chemin du jar]projet3-1.0-SNAPSHOT-jar-with-dependencies.jar [-DevMode]
```

> Options :
> * **-Dlog4j.configurationFile** permet de prendre en compte un fichier externe de configuration pour log4j de type XML en spécifiant son chemin
> * **-DevMode** permet d'activer le mode "développeur"

L'application prend par défaut  le fichier config.properties dans le jar sauf si un fichier .\conf\config.properties est présent dans le chemin où la commande java est lancée (voir fichiers "conf" dans target généré au moment du "packaging").  
> paramètres par défaut :
> * longueur combinaison : 4
> * nombre d'essai : 10
> * nombre de symbole : 10

Exemples :

En se plaçant dans le répertoire target (suite à la création du package)
```cmd
java -jar projet3-1.0-SNAPSHOT-jar-with-dependencies.jar
```

En restant à la racine du REPO et activant le mode "dev"
```cmd
java -jar .\target\projet3-1.0-SNAPSHOT-jar-with-dependencies.jar -DevMode
```
