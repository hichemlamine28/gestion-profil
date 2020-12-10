#!/usr/bin/env bash

DOCKER_COMPOSE_PATH="./env_scripts/dev_env/docker-compose.yaml"
COMPOSE_NETWORK_NAME=$(basename $(pwd))

if [[ $1 -eq 1 ]]; then
    DOCKER_COMPOSE_PATH="./env_scripts/dev_env/docker-compose.yaml"
    COMPOSE_NETWORK_NAME=${COMPOSE_NETWORK_NAME}+"_dev"
elif [[ $1 -eq 2 ]]; then
    DOCKER_COMPOSE_PATH="./env_scripts/test_env/docker-compose.yaml"
    COMPOSE_NETWORK_NAME=${COMPOSE_NETWORK_NAME}+"_test"
elif [[ $1 -eq 3 ]]; then
    DOCKER_COMPOSE_PATH="./env_scripts/test_jenkins_env/docker-compose.yaml"
    COMPOSE_NETWORK_NAME=${COMPOSE_NETWORK_NAME}+"_test_jenkins"
fi

