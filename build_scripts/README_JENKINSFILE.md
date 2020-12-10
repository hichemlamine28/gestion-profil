# builder les jobs Jenkins

le Jenkinsfile génère un job jenkins configuré pour faire ce qui suit:
* ```Test/Build```
* ```Release/Tag```

le job s'exécutera à chaque changement détecté  dans le gestionnaire de versions (SVN).
L'utilisateur peut choisir si le job devrait builder ou tagguer une nouvelle version, il a pour cela 30s pour prendre une décision.
pour pouvoir faire un choix, il devrait :
* cliquer sur le numéro de build qui vient d'apparaitre. ceci va l'envoyer vers une nouvelle page.
* cliquer sur  ```Paused for Input``` se trouvant dans la liste à gauche.
* choisir dans la liste déroulante ce qu'il souhaite faire

## ATTENTION : procédure de tag
**Il ne faut pas pas choisir la procédure de tag sur un build qui a été démarré automatiquement sur Jenkins** (à la suite d'un commit par exemple.).

La procédure de tag devrait exclusivement:
* être démarré manuellement en appuyant sur le bouton ```Build Now```.
* être lancé après un job ```Test/Build``` réussi

**PS : Pour éviter des tags corrompus, tous les dévs devrait être mis au courant et aucun commit ne devrait être fait tant que la procédure de tag n'est pas terminé.**


# Description Test/Build

 * Démarre un environnement de test
 * Test l'application unitairement
 * Crée un package Java
 * Envoie les analyses sonar au serveur sonar si la branche buildé est trunk
 * Crée une image docker contenant toutes les données nécessaires pour démarrer l'application dans un container docker.
 le tag de l'image sera :
    * ```latest``` si on build le trunk
    * Numéro de version si on build un tag
 * Envoie l'image vers le serveur adéquat et supprime l'image.
 * Eteint l'environnement de test
 * Supprimer l'espace de travail

# Description Release/Tag

  * Démarre un environnement de test
  * Test l'application unitairement
  * Crée un tag de la version actuelle et incrémente la version
  * Eteint l'environnement de test
  * Supprimer l'espace de travail
