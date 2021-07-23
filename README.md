# user-service

* with docker-compose *
- mvn clean package -DskipTests
- docker build --tag=hedwig-user-service:latest .
- cd src/main/resources
- docker-compose up

* without docker *
- docker run --name postgresqldb -e POSTGRES_USER=hedwig -e POSTGRES_PASSWORD=hedwig -p 5432:5432 -d postgres
- docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

