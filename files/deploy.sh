#!/bin/bash
# authors: henryfisi08@gmail.com
# Run All sh

rpath=$(pwd)/examen/

function compile_source() {
  if [[ -z "$rpath" ]] ; then echo "error on pull source..."; exit 1; fi
  echo "SOURCE_PATH : $rpath"
  cd $rpath/root-backend && mvn clean install -DskipTests -U
  echo "compile source ok..."
}

function generate_test_source() {
  cd $rpath/root-backend/ms-evaluacion-web
  mvn clean test
  if [[ "$?" -ne 0 ]] ; then
    echo 'could not perform tests'; exit $rc
  fi
    echo "end tests evaluation controller..."
}

function generate_docker_image() {
  cd $rpath/ms-evaluacion-web
  if [[ -z "$rpath/Dockerfile" ]] ; then echo "don't exists Dockerfile ..."; exit 1; fi
  docker build --no-cache -t ms-evaluacion-web-0.0.1-SNAPSHOT-EVA.jar:1.0 .
  echo "end generate image..."
}

function generate_docker_container() {
  cd $rpath/ms-evaluacion-web/target/
  if [[ -z "$rpath/*.jar" ]] ; then echo "don't exists jar file ..."; exit 1; fi
  docker run -d -p:8082:8082 -e TZ=America/Lima -v /root/external/properties/:/root/external/properties/ ms-evaluacion-web:1.0
  echo "end generate image..."
}

compile_source
generate_test_source
#generate_docker_image
#generate_docker_container
exit;
