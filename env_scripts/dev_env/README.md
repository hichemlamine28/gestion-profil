# Intro

### démarrer les containers
````
docker-compose -f {chemin_vers_le_yaml_de_configuration} up -d
````
 exemple:
````
docker-compose -f  ./env_scripts/dev_env/docker-compose.yaml up -d
````

### éteindre les containers
````
docker-compose -f {chemin_vers_le_yaml_de_configuration} down
````
 exemple:
````
docker-compose -f  ./env_scripts/dev_env/docker-compose.yaml down
````

# Mongo DB

localhost:35001

# Elastic Search

## node
localhost:35011
## cluster
localhost:35012

# Elastic search HQ (Elastic search viewer)

<http://localhost:35013>

* pour accéder au cluster fournir l'adresse suivante :
    * <http://es:9200>


# Neo4j

* http
    * localhost:35021
* https 
    * localhost:35022
* bolt 
    * localhost:35023

## Visualiseur de base de données Neo4j

http://localhost:35022/browser

* Url de connexion à fournir:
    * bolt://localhost:35023
* username/password
    * neo4j
    * admin

# LDAP

* http :
  * localhost:35031
* https :
  * localhost:35032

## PHP LDAP Admin (viewer LDAP)

<https://localhost:35033>

* username/password
  * cn=admin,dc=linkinnov,dc=com
  * admin


## RabbitMQ

<http://localhost:35042/#/>

* username/password
  * rabbitmqAdmin
  * rabbitmqPassword

