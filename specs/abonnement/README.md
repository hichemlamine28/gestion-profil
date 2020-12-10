# Présentation
les abonnements permettent de procurer aux utilisateurs des fonctionnalités(action) en plus.
Chaque abonnement est considéré comme un groupe d'actions.
Si un utilisateur achète un abonnement, il sera attibué un groupe d'action qu'il pourra faire.


# Implémentation technique

## les actions
Dans LDAP, nous aurons un ou=groups qui contiendra deux groupes :
* Action : qui contiendra la liste des actions possibles. exemple:
  * search
  * post_annonce
  * post_annonce_illimite
* Abonnements : qui contiendra la liste des abonnements possibles. exemple :
  * Freemium
  * Premium   

Tous les exemples (search,Freemium ....) donnés ci-dessus sont des groups qui contiendront des valeurs.

* la liste dans Freemium ou ses soeurs contiendra les utilisateurs qui ont droit à cet abonnement. exemple :
  * premium :
    * userA,UserB,userC, etc...
    * cette liste sera récupérée par le biais de groupOfUrls. pour cet exemple : tous les utilisateurs qui ont un champ ou=premium.
* la liste dans post_annonce ou ses soeurs contiendra:
  * les abonnements qui appliqueront ces actions. exemple :
    * Freemium,Premium
  * le nom technique de l'action. exemple :
    * ac_post_annonce
    * ac_post_annonce_v2
  * toutes ces valeurs sont des champs "ou" du group.


## Exemple
          Linkinnov_LDAP
          └── ou=groups
             ├──ou=action
             │   ├──ou=search
             │   │   +ou=Freemium
             │   │   +ou=Premium
             │   │   +ou=ac_search
             │   │   +ou=ac_search_v2
             │   │   +cn=SEARCH
             │   └──ou=poster
             │       +ou=Premium
             │       +ou=ac_pster
             │       +ou=ac_search_v2
             │       +cn=POSTER
             └──ou=abonnement
                 ├──ou=Freemium
                 │   +groupOfUrls=...
                 └──ou=Premium
                     +groupOfUrls=...




## Assigner des actions
### LDAP
Pour assigner à l'utilsateur des actions, il y a deux possibilités. nous lui rajoutons un champ "ou" qui contiendra soit  :
* le nom technique de l'abonnement (ab_"nom_abonnement")
* le nom technique de l'action (ac_"nom_de_l_action")
exemple :
UserA :
* ou=ab_premium
* ou=ac_post_annonce
### Spring
Côté spring :
1. nous récupèrons l'utilsateur avec les abonnements et les actions.
2. nous parsons les abonnements (les "ou" commençant par "ab_") et nous récupérons les actions de cet abonnement.
3. nous parsons les actions (les "ou" commençant par "ac_") et nous récupérons les actions.
4. nous mettons la liste de toutes les actions récupérée dans le JWT. cette liste ne doit pas contenir de doublon.

### Sécurité WS

les WS seront seront sécurisés par les actions.


