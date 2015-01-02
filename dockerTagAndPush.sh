#!/bin/bash

PWD="`pwd`"
echo "Using docker file in: ${PWD}"
TAG="$@"
if [[ -z "$TAG" ]]
then
  TAG="latest"
fi
 echo "Building docker image, setting tag: ${TAG}"
docker build -t "wgorder/auth-server:${TAG}" ${PWD}
echo "Docker pushing image to dockerhub..."
docker push "wgorder/auth-server"
