# README #

This README would normally document whatever steps are necessary to get your application up and running.

### clonar el proyecto ###

* git clone https://github.com/henrybpp/EXAMEN.git

### copiar el archivo de configuración en la ruta /external/properties ###

* cp EXAMEN/files/backend-dev.properties /external/properties

### ejecutar las sentencias al mismo nivel que el repositorio ###

* cp EXAMEN/files/deploy.sh deploy.sh
* chmod u+x deploy.sh
* bash -x deploy.sh

### Comandos de prueba rest api ###

* curl -X POST -d '{"nombres":"test20","correo":"test20@gmail.com","telefono":"957898100","puntuacion":10,"comentarios": [{"comentario": "comentario20.1"}]}' -H 'Content-Type: application/json' http://192.168.1.20:8082/evaluacion/creacion

* curl -X PUT -d '{"id":1,"nombres":"test1","correo":"test@gmail.com","telefono":"953258100","comentarios": [{"comentario": "comentario20.1"}],"fecha":"21/07/2020 21:44:49"}' -H 'Content-Type: application/json' http://192.168.1.20:8082/evaluacion/modificacion

* curl -X POST -d '{"fechaDesde":"23/07/2020 21:44:49","fechaHasta":"24/07/2020 21:44:49"}' -H 'Content-Type: application/json' http://192.168.1.20:8082/evaluacion/lista/fecha

* curl -X GET -H 'Content-Type: application/json' http://192.168.1.20:8082/evaluacion/lista

### SWAGGER-UI ###
* http://192.168.1.20:8082/swagger-ui.html

### MONGO DB ###
* http://192.168.1.20:8081/
