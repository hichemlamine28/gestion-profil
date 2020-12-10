# Jenkins

## configuration

la configuration à mettre en place pour un nouveau jenkins

* Ajouter les credentials user/mdp svn arkeup.
l'identifiant du credentials doit être  : jenkins
* Installer le plugin ```Subversion Plugin```
* Installer le plugin ```Pipeline Utility Steps```
* Installer le plugin ```Lockable Resources Plugin```
* Installer le plugin ```Google OAuth Credentials``` si nécessaire.
* Installer ```Docker``` sur la machine contenant le jenkins
* Installer ```docker-compose``` sur la machine contenant le jenkins

* Donner les droits au user ```jenkins``` d'utiliser docker
```
sudo usermod -a -G docker jenkins
``` 
puis redémarrer Jenkins
* Créer un projet multibranch pipeline pointant sur <https://tools.arkeup.com/svn/com.arkeup.docker>  et buildant les branches suivantes : branches/\*java\*

* Installer le plugin ```SonarQube Scanner```
* aller à ```Manage Jenkins > Configure System > SonarQube configuration``` et ajouter une configuration sonarQube. pour la config arkeup :
    * Name: ```SONARQUBE_ARKEUP```
    * Server URL : ```https://tools.arkeup.com/sonar_new/```
    * Server authentication token : générer un token sur le serveur SonarQube et le coller ici
        * pour générer le token, aller sur le serveur sonarqube en tant qu'administrateur:
            * aller sur ```Administration > security > users```
                * aller sur l'utilisateur Administrator, ```colonne tokens > update Tokens```
                    * dans le champ Generate Tokens, entrer le nom du jenkins (exemple : ```JENKINS_{PROJECT_NAME}``` )

## Configuration Optionnelle

### repository nexus arkeup
 SEULEMENT DANS LE CAS OÙ LE PROJET UTILISE LE NEXUS D'ARKEUP!!!!
* installer le plugin ```Config File Provider```
* ajouter la configuration suivante dans la section ```Manage Jenkins > Managed files > Add a new Config ``` de Jenkins
    * Type : ```Maven settings.xml```
    * ID : ```maven_custom_settings```
    * Name : ```maven_custom_settings```
```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  
  <profiles>
    <profile>
      <id>togethup_repo</id>
      <repositories>
        <repository>
          <id>togethup_public_repository</id>
          <name>Togethup Public Maven Repository Group</name>
          <url>http://tools.arkeup.com/nexus/content/groups/public/</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <activeProfiles>
    <activeProfile>togethup_repo</activeProfile>
  </activeProfiles>
</settings>
```



## Build 

* créer un Job jenkins pointant sur le SVN du projet
    * spécifier que le jenkins file se trouve dans ```./build_scripts/Jenkinsfile``` dans le champ ```Script Path```
* **ATTENTION**: le projet pourrait ne pas fonctionner les premières fois dû à l'erreur suivante :
```
Scripts not permitted to use method {}. Administrators can decide whether to approve or reject this signature.
```
Approuver les méthodes qui bloquent le build en cliquant sur le lien remonté dans l'erreur ```Administrators can decide whether to approve or reject this signature```:
* ```Approve method org.apache.maven.model.Model getArtifactId```
* ```method org.jenkinsci.plugins.workflow.steps.FlowInterruptedException getCauses```
* ```method org.jenkinsci.plugins.workflow.support.steps.input.Rejection getUser```
* ```method hudson.scm.SubversionSCM getLocations```
* ```field hudson.scm.SubversionSCM$ModuleLocation remote```
* ```method hudson.scm.SubversionSCM$ModuleLocation getSVNURL```
* ```method hudson.scm.SubversionSCM$ModuleLocation getURL```



 