# Intro

Cet environnement est à destination de jenkins, il ne contient pas les viewer des différentes BDD

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

## RabbitMQ

<http://localhost:37042/#/>

* username/password
  * rabbitmqAdmin
  * rabbitmqPassword


