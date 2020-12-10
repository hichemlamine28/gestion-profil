#!/usr/bin/env bash


./dc-stop-env.sh $1
sleep 1
./dc-start-env.sh $1

