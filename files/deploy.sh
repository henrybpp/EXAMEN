#!/bin/bash
# authors: henryfisi08@gmail.com
# Run All sh

rpath=$(pwd)/EXAMEN

function compile_source() {
  if [[ -z "$rpath" ]] ; then echo "error on pull source..."; exit 1; fi
  echo "SOURCE_PATH : $rpath"
  cd $rpath/root-backend && mvn clean install -DskipTests -U
  echo "compile source ok..."
}

function generate_test_source() {
  cd $rpath/root-backend/ms-evaluacion-web
  mvn clean test package
  if [[ "$?" -ne 0 ]] ; then
    echo 'could not perform tests'; exit $rc
  fi
    echo "end tests evaluation controller..."
}

function generate_docker_image() {
  cd $rpath/root-backend/ms-evaluacion-web
  if [[ -z "Dockerfile" ]] ; then echo "don't exists Dockerfile ..."; exit 1; fi
  if [[ -z "target/*.jar" ]] ; then echo "don't exists jar file ..."; exit 1; fi
  docker build --no-cache -t ms-evaluacion-web:1.0 .
  echo "end generate image..."
}

function generate_docker_container() {
  docker rm -f evaluacion-container
  docker run -d -p:8082:8082 -e TZ=America/Lima -v /external/properties/:/external/properties/ --name evaluacion-container ms-evaluacion-web:1.0
  echo "end generate container..."
}

compile_source
generate_test_source
generate_docker_image
generate_docker_container
exit;
