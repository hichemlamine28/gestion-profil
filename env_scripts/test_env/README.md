# Intro

### démarrer les containers
````
docker-compose -f {chemin_vers_le_yaml_de_configuration} up -d
````
 exemple:
````
docker-compose -f  ./env_scripts/test_env/docker-compose.yaml up -d
````

### éteindre les containers
````
docker-compose -f {chemin_vers_le_yaml_de_configuration} down
````
 exemple:
````
docker-compose -f  ./env_scripts/test_env/docker-compose.yaml down
````

# Mongo DB

localhost:37001

# Elastic Search

## node
localhost:37011
## cluster
localhost:37012

# Elastic search HQ (Elastic search viewer)

<http://localhost:37013>

* pour accéder au cluster fournir l'adresse suivante :
    * <http://es:9200>


# Neo4j

* http
    * localhost:37021
* https 
    * localhost:37022
* bolt 
    * localhost:37023

## Visualiseur de base de données Neo4j

<http://localhost:37022/browser>

* Url de connexion à fournir:
    * bolt://localhost:37023
* username/password
    * neo4j
    * admin

# LDAP

* http :
  * localhost:37031
* https :
  * localhost:37032

## PHP LDAP Admin (viewer LDAP)

<https://localhost:37033>

* username/password
  * cn=admin,dc=linkinnov,dc=com
  * admin

## RabbitMQ

<http://localhost:37042/#/>

* username/password
  * rabbitmqAdmin
  * rabbitmqPassword


