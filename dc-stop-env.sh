#!/usr/bin/env bash

source dc-conf-env.sh
docker-compose -f ${DOCKER_COMPOSE_PATH} -p ${COMPOSE_NETWORK_NAME} down
