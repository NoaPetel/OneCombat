# Journal de bord


## Jeudi 22




| Nom       |                                                                                   À faire | Bilan                                                                                                                    |
| --------- | -----------------------------------------------------------------------------------------:| ------------------------------------------------------------------------------------------------------------------------ |
| Titouan   |                                                                                           |                                                                                                                          |
| Lilian    |                                                                                           |                                                                                                                          |
| Alexandre | Faire fonctionner plateformes avec toutes les actions et mouvements ensemble + régler bug des actions des plateformes                                                                                          | PlatformBump avec Tom                                                                                                    |
| Joseph    |                     Modification TileManager pour correspondre au nouvelle implementation | TileManager ok                                                                                                           |
| Noa       |                                                                                           |                                                                                                                          |
| Valentin  | Ajout d'une nouvelle entité "Ronces" et ajout des animations de hit/death pour les bots. |                                                                                                                          |
| Tom       |                                        Plateforme blessant le joueur et le faisant sauter | PlatformDamage, PlatformSpring, PlatformExplosion implémentées, PlatformBump avec Alexandre, merge de plusieurs branches | 
## Mercredi 21




| Nom       | À faire                                                                                                                                                                       | Bilan                                                                  |
| --------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| Titouan   | Finir e=les attack specials des joueurs, regler quelque bugs, et faire les barres de vie et d'enregie                                                                         | Test, tout ok                                                          |
| Lilian    |                                                                                                                                                                               |                                                                        |
| Alexandre | Finir collision action plateforme + rajouter pourcentage pour visiteur + mettre en place nouvelle conception des classess plateforme + rajouter dernières actions plateformes |   Pourcentage visiteur fonctionne, conception classe faite a implemente + manque plateforme qui rend de l'énergie + platforme merge et split OK                                                                     |
| Joseph    | Changement Bot + BotManager (spawn aléatoire)                                                                                                                                 | BotManager fonctionnel                                                 |
| Noa       | Ajout du fonctionnalité camera (caméra s'étends vers item qui apparait) + création GameManager                                                                                | Caméra qui s'étends OK + Doit revoir avec Tit pour le GameManager      | 
| Valentin  | Ajout d'un délai entre les téléportations des portails pour éviter les bugs sur les mouvements des entités.                                                                   |          Tout est OK.                                                              |
| Tom       | Suite des plateformes                                                                                                                                                         | Ajout des méthodes pour les plateformes et merge de plusieurs branches |
## Mardi 20




| Nom       | À faire                                                                                                                                       | Bilan                                                                                                                                                                                                   |
| --------- | --------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Titouan   | Tester les premiers automates avec Lilian avec les joueurs et finir la totalité des comportements des joueurs avec les sons                   | Tests tout ok, automates foncionnels et joueur complètement fini                                                                                                                                        |
| Lilian    | Corriger les erreurs dans l'algo de pathfinding et merge la partie automate avec Titouan                                                      | Algo de pathfinding opérationel                                                                                                                                                                         |
| Alexandre | Plateforme heal + plateforme egg quand collision entre deux plateforme + plateforme merge + revoir conception de certaines classes du visitor | Plateforme heal OK. Plateforme OK Collision en bas OK mais à changer pour faire 1 seul hitbox de merge autour. Correction parametre de Key dans Visiteur. Plateforme merge OK à rajouter le déclencheur |
| Joseph    | regler le problème de hitbox + finition des réglages des bots faibles et medium (damage le terrain + medium action heal)                      | hitbox ok + reglage ok                                                                                                                                                                                  |
| Noa       | Reglage de problemes de merge + début implémentation CameraZoom objet + Spawn aléatoire                                                       | Probleme merge reglé + Spawn aléatoire commencé (Caméra zoom objet pas eu le temps)                                                                                                                                                         |
| Valentin  | Animation portails + Ajouts gros portails sur les côtés de la carte.                                                                          | Animation OK, gros portail trop gros, compliqué a intégrer pour le moment.                                                                                                                              |
| Tom       | Interfaces des plateformes pour les différents types & merge avec Alexandre                                                                   | Gravité des plateformes opérationnnelle. Merge de branches. Création des interfaces des plateformes                                                                                                     |



## Lundi 19




| Nom       | À faire                                                                                                       | Bilan                                                                                                                                                                                                          |
|:--------- | ------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Tom       | Gravité sur les plateformes                                                                                   | Merge de plusieurs branches; mise à jour des actions automates afin de lier avec le Visitor; Déplacement des plateformes fonctionnel avec Joseph. Constructeurs pour la fusion et le découpage des plateformes | 
| Joseph    | Finition des bots faibles (chien) et attack bot medium        |    Foncionne avec juste un problème de hitbox                                                                                                                                                                                                            |
| Alexandre | Rajouter les pourcentages dans le visiteur et faire la méthode egg qui divise une platforme en deux a la mort | On a decider pour le moment que pourcentages dans visiteur pas priorité + methode egg qui fonctionne mais bug, les deux platformes se tp un peu vers la droite                                                 |
| Valentin  | Création des portails de téléportation.                                                                       | Manque plus que l'animation.                                                                                                                                                                                   |
| Noa       | Correction ItemManager, ajout sprites items,                                                                  | ItemManager OK + Sprites OK                                                                                                                                                                                    |
| Titouan   | Amélioration du joueur, update le system et commmencer le premier menu et voir pour les IEntity               |                                                                          test ok, reglade de plusieurs bug, ajout du son                                                                                                                                      |
| Lilian    |Avancer sur l'algorithme de pathfinding et création de l'algo du bot fort                                                                                                         |     C'est ok                                                                                                                                                                                                           |


## Samedi 17 & Dimanche 18 



| Nom       | À faire                                                                                             | Bilan                                                                                        |
| --------- | --------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| Joseph    | fin animation Medium Bot +animation missile                                                         | Medium bot + missile fonctionne, debut de travail sur le bot faible (chien)                  |
| Tom       | Déplacement des plateformes                                                                         | Déplacement latéral des plateformes mais problème avec la fréquence de changement, merge git |
| Noa       | Création de l'ItemManager, pour les items qui sont fixes tout au long de la partie (respawn etc...) | Manager crée, réussi à créer les items mais bug pour le respawn/despawn                      |
| Titouan   | Continuer les mécanisme du joueur                                                                   | test ok, joueur casiment fini                                                                |
| Lilian    | Travail sur Visitor avec Alexandre  et nouveau Sprite                                                                               |  Bientôt fini visitor                                                                                           |
| Alexandre | Finir visiteur + mettre en place lien Automate et Java                                              | Lien Automate et Java en place, Visiteur fini mais avec un bug                               |
| Valentin  | fin animation MediumBot + animation Missile.                                                        | Tout est OK                                                                                  |



## Vendredi 16



| Nom       | À faire                                                                                                        | Bilan                                                                                                                               |
| --------- |:-------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| Joseph    | Merge de git avec Tom + gérer la destruction des blocs de la map + reprise du travail d'Alexandre (Bot faible) | Merge git solved + destruction ok, manque de temps pour bot faible (à faire demain)                                                 |
| Alexandre | Finir le visiteur                                                                                              | Probleme avec FunCall sinon tout a l'air OK                                                                                         |
| Tom       | Automates des plateformes pour prendre en compte les contraintes + aide sur le merge avec Joseph               | Merge de plusieurs branches et début d'automates + complets                                                                         |
| Titouan   |        Regler un maximum de bug sur la physics et le system du jeu, et commencer a faire le joueur principal                                                                                                        |                                                                          Test ok                                                           |
| Valentin  | MediumBot qui bouge L/R, le projectile est tiré dans la bonne direction.                                       | Ok + début animation                                                                                                                |
| Noa       | Implémentation des différents items : interfaces, enum, behavior et objects                                    | interface, enum, behavior, object OK, powerUp Speed et Jump faites mais pas les autres (Energie, Power, Health) car pas encore définies |
| Lilian    | Graphismes du menu et assets à faire et conception de l'algo du bot fort                                           | Tout est ok.                                                                                                                        |


## Jeudi 15 



| Nom       | À faire                                                                        | Bilan                                                                                |
| --------- | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------ |
| Joseph    | Merger le travail sur master + texturing tile                | Merge réalisé +    texturing tile completed   |
| Tom       | Lien avec le Java pour les actions et conditions et amélioration des automates | Interfaces d'actions & conditions implémentées + nouveaux automates                  |
| Lilian    | Travail sur visitor et début des graphismes                                                                               |                       Toujours des problèmes sur visitor, Titouan et Alexandre m'aident. Les graphisme des personnages ok.                                                               |
| Valentin  | Entité MediumBot qui lance des entités Missiles.                               | Nouvelles entités missiles lancées/crées par MediumBot mais pas encore détruites.            |
| Titouan   | Faire les premiers mouvements et  actions du joueur                              |         Début des premiers tests avec le jouer fait et debug mode et addition de collision matrice                                                                             |
| Alexandre | Ajouter les animations aux joueur+bots + debug le jump                         |  Jump corrigé, anim attack ajoutée, ajout sprite weakBot, Visiteur fonctionne pas                                                                                    |
| Noa       | Basculer la caméra de l'ancienne physique à la nouvelle physique               | Caméra basculée + réorganisation code de la caméra + ajout de smoothness à la caméra | 


## Mercredi 14

| Nom       | À faire aujourd'hui                                                                                   | Bilan fin de journée                                                 |
| --------- | ----------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| Joseph    | Création de map à partir d'une matrice                                                                | Fonctionne (lag un peu si beaucoup de blocs)                         |
| Tom       | Continuation de la création d'automates                                                               | Automates  fonctionnels ( parsables ) et ajout des touches.          |
| Lilian    | Continuer à travailler sur les automates et les parseurs                                              | ça avance, toujours des problèmes avec visitor                                                           |
| Valentin  |    Test d'implémentation d'une entité WeakBot dans  le projet.                                                                                                   |  Affichage de l'entité OK.                                                                    |
| Titouan   |    Implémenter le moteur physics dans le system, et creer un animator pour toute les entity afin d'avoir des animations                                                                                                   |                                                                 Branche mergée, moteur physique et animation marche bien     |
| Alexandre | Merger les branches sur Git. Retravailler les actions avec nouvelle physique + travailler sur attaque | Branche mergée, actions move et jump qui fonctionne avec la physique |
| Noa       | Implémentation d'un dash sur l'ancien moteur physique                                                 | Dash OK, mais l'entité garde un momentum malgré tout                 | 
## Mardi 13

Attente de finalisation du contrat. Attribution des différentes tâches.
Premier push du code fait en amont.

| Nom       | À faire aujourd'hui                                                     | Bilan fin de journée                                                                                                                    |
| --------- | ----------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| Joseph    | Début de réalisation des blocs pour la map                              | Bloc de map fonctionnel OK                                                                                                              |
| Tom       | Réalisation des premiers automates et vérification d'exécution          | Création des automates des bots & plateformes mais incomplets                                                                           |
| Lilian    | Réalisation des premiers automates et vérification d'exécution          | Fonctionnalités basiques qui marche                                                                                                                                |
| Valentin  | Test d'affichage d'une entité avec quelques mouvements basiques dans un petit projet à part.                              |  Code pas très propre mais fonctionnel.                                                                                                                                       |
| Titouan   | Tentative de réalisation du moteur physique réel                        | Génération d'entités, physique et collisions OK                                                                                         |
| Alexandre | Début de réalisation des actions des bots faibles (move).               | Move qui part dans une direction aléatoire en attendant automate, jump fonctionnel mais paramètres a régler, plateformes	 avec des bugs |
| Noa       | Implémentation de la caméra en fonction de la position des deux joueurs | Caméra + zoom/dézoom si caméra trop loin/près  OK                                                                                       |



