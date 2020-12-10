#!/usr/bin/env bash

docker-compose -f ./env_scripts/test_jenkins_env/docker-compose.yaml down
docker-compose -f ./env_scripts/test_jenkins_env/docker-compose.yaml up -d

echo "sleeping for 20s to let docker images start up"
sleep 20

mvn clean compile test org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar

docker-compose -f ./env_scripts/test_jenkins_env/docker-compose.yaml down