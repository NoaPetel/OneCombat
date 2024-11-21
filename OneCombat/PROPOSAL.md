# CONTRAT DE JEU - Groupe H
# Jeu de combat avec bots

### Phase de jeu classique : `jeu de combat` : 
- [ ] 2 joueurs + des bots
- [ ] HitBox : x,y, hauteur, largeur

##### Gravité 
- [ ] les objets, les joueurs et les bots y sont soumis
- [ ] pas la carte ni la plateforme. 

2 options 
- [ ] Lois physiques adaptées au jeu
- [ ] Lois physiques réelles appliquées au jeu (densité, volume, vitesse, accélération de pesanteur)


### But du jeu : 
- Être le dernier survivant sur la carte (bot normal et faible non compris)

### Caméra

Vue en 2D 
- [ ] `Zoom automatique` en fonction de la position des deux joueurs:
- [ ] Joueurs proches = vue “zoomée”
- [ ] Joueurs éloignés = vue “large”


### Map

Grande map (:question:) vue en 2D : on ne verra pas l'entièreté de la map, mais le maximum affichable en fonction de la position relative des joueurs
- [ ] Construit avec des cubes destructibles
- [ ] détection des collisions `optimisée` par des régions.
- [ ] Univers torique 
    - [ ] Passage du bas vers le haut et inversement (tomber en bas fait réapparaitre au dessus) 
    - [ ] Passage de la droite vers la gauche et inversement à l'aide de portails

### Objets sur la carte (Apparition unique au début du jeu) : 
- [ ] Coeurs (redonne des points de vie au joueur puis disparaît)
- [ ] Boules de viande (redonne des points d'énergie puis disparaît)

### Plateformes 
- [ ] Tremplins : type de plateforme qui en contact avec un joueur, le propulse verticalement (toujours avec la même force sans prendre en compte la vitesse d'arrivée) 

- [ ] Ronces (en contact avec un joueur, il fait perdre de la vie au joueur, ne bloque pas le joueur)

- [ ] Tous les blocs sont destructibles 
- [ ] sauf la couche la plus basse (afin d'éviter qu'il n'y ait plus de map). 
:question: `Comment passer vers le bas et revenir en haut dans ce cas ?` : Le bas de la map sera composé sur certains segments de portails et sur les autres d'un mur ( idem pour le haut et les bords )


- [ ] Toutes les attaques des entités (bot moyen, fort, joueur) peuvent détruire les blocs
- [ ] occasionnent une vibration de la caméra
- [ ] et une animation)  _(Voir section explosion)_


### Joueurs

Attributs
- [ ] Une barre de vie
- [ ] Une barre d'énergie
- [ ] Mort si la barre de vie atteint 0.

Actions 
- [ ] Se déplacer (droite/gauche)
- [ ] Sauter
- [ ] Dash: déplacement rapide dans la direction de regard du joueur. Le joueur est protégé des attaques pendant le dash.
- [ ] Attaque corps-à-corps simple
- [ ] Attaque spéciale (consomme de l'énergie): 
   - [ ] légère secousse de la caméra
   - [ ] nuage de fumée
   - [ ] destruction de la map à proximité
- [ ] Grosse attaque spéciale (consomme plus d'énergie que l'attaque spéciale)
   - [ ] destruction de la map à proximité

### Bots
- [ ] 3 types (faible, moyen, fort)
- Nombre limité sur la carte (une limite par types)
- [ ] Possède une barre de vie

Bots faible
- [ ] Déplacement aléatoire (droite/gauche).
- [ ] Lors de sa mort, (il fait apparaître une boule de viande avec l'action Egg et meurt ensuite = `état ()`).
- [ ] Réapparition aléatoire sur la carte (limitée à une/minute)
- Pas d'autre action possible :question: `Comment empêcher les autres actions puisqu'on pourra modifier l'automate` : même si elles sont appelées, elles n'auront pas de méthode associée dans la classe Java, donc pas d'effet
- [ ] Fait perdre de la vie si le joueur le touche

Bot moyen
- Bouge aléatoirement 
- Lors de sa mort, une boule de viande ou un coeur apparaît (avec l'action Egg), le bot disparaît de la map.
- Réapparition aléatoire sur la carte (limité à une/minute)
- [ ] Comme bot faible mais 2 actions possibles : 
- [ ] lancer un projectile (`action = ?` : faisant des dégâts au joueur)
- [ ] récupérer une partie de sa barre de vie (`action=?`)
    
Bot fort
- [ ] Va dans la direction du joueur le plus proche
- [ ] Lors de sa mort, un object bonus apparaît celui-ci donne un avantage permanent au tueur : vitesse plus élevée, sauter plus haut, etc..
- [ ] Doivent être tous tués pour gagner la partie
- [ ] Pas de réapparition
- [ ] Possède les mêmes attributs que les joueurs :
    - barre de vie
    - barre d'énergie
    - une attaque simple (`action=?`)
    - une attaque spéciale (`action=?`)
    - une grosse attaque spéciale (`action=?`)
    - un dash (`action=?`)

## Originalité

### Plateformes, non soumises à la gravité

- [ ] Déplacement aléatoire à droite ou à gauche
 
4 types de plateformes qui impactent les joueurs entrant en collision avec celles-ci :
- [ ] redonner des points de vie ou de l'énergie
- [ ] augmente la vitesse
- [ ] tremplin
- [ ] explosion

- [ ] Les plateformes qui redonnent de l'énergie et des points de vie rendent __périodiquement__ des points de vie tant que l'entité est en contact sur celle-ci.


Si deux plateformes se touchent :
- [ ]  soit elles se repoussent 
- [ ]  soit elles fusionnent en une seule plateforme (dont le type sera choisi de manière aléatoire)


### Explosion

- [ ] Chaque bloc de la carte possède une barre de vie :question: `VISIBLE dans le jeu` : Nombre sur le bloc 
- [ ] Cette barre de vie diminue avec les impacts reçus par celle-ci (attaque de joueurs, explosion )
- [ ] Quand la barre de vie atteint 0 le bloc est détruit
- [ ] Une fois un trou créé, les collisions `en tiennent compte`, il est possible pour les entités et les joueurs d'aller dans le trou.


### Définition des actions Pop et Wizz



| Entité             | POP                      | PROTECT | EGG                                        | WIZZ                                |
| ------------------ | ------------------------ | ------- | ------------------------------------------ | ----------------------------------- |
| Plateforme         | fusion inter-plateformes |         | Division en 2 plateformes                  | impacte le joueur ( perte de vie, énergie ou autre )                   |
| Joueur  & Bot fort | attaque spéciale         | dash    | Apparition d'un bonus                      | grosse attaque spéciale             |
| Bot moyen          | lancer d'un projectile   |         | Apparition d'un objet ( coeur ou viande  ) | récupération d'une partie de la vie |
| Coeur              |                          |         |                                            | donne des points de vie             |
| Boule de viande    |                          |         |                                            | donne des points d'énergie          |
| Bonus              |                          |         |                                            | donne un avantage permanent         |

## Actions

- [ ] L'action dash, fait charger ( déplacement rapide ) le personnage en avant (non affecté par la gravité). Il charge toujours à la même vitesse et cette charge a toujours la même portée.

- `déjà dit` L'attaque spéciale consomme de l'énergie. Toutes les entités touchées à l'exception des plateformes, c'est-à-dire le terrain, les décors (arbres), les différents bots et les joueurs sont touchés, tout comme les attaques normales. Elle est plus puissante qu'une attaque normale et a une plus large zone d'effet.

- [ ] Pour les bonus, les coeurs et les boules de viandes, après leur action WIZZ, disparaissent de la carte `=état()`.


## `HORS CONTRAT` Idées supplémentaires à l'étude

* Les joueurs peuvent choisir leurs pouvoirs (début du jeu)
* Plateformes pouvant tourner (avec tout les problèmes qui vont avec)
* Nouveaux bonus/malus pour les plateformes
* Ajout de mine qui dégraderaient le terrain
* Faire un menu
* Le joueur a un pouvoir qui permet de contrôler une plateforme (difficile donc pas trop)
* Missile téléguidé ?
* Autre mode de jeu : un seul joueur solo, combat uniquement contre des bots

## Point important

- [x] Les méthodes ( dans la classe Java ) associées aux actions WIZZ de Plateforme et EGG du bot normal doivent décider de ce qui apparaît ( parmi les options du tableau ) avec une probabilité définie à l'avance.

