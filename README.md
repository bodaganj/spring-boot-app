# spring-boot-app

### TODO list
* monitor/version endpoint is available through http when the app is started locally
* consuming from kafka (which is started separately within the docker)
* write to Mongo DB (which is started separately within the docker)
* tested with embedded kafka

## REST endpoints
* http://localhost:8080/monitor/version - returns hardcoded string
* http://localhost:8080/actuator/info - returns java/application related info
* http://localhost:8080/actuator/health - return application status (UP/DOWN)
* http://localhost:8080/person/names - return the list of names within Mongo DB

## Preconditions
Run the following command to start Mongo DB, ZK and Kafka in docker
````
docker-compose -f dependencies-compose.yaml up
````
Run the following test to send some test data to kafka:
````
com.bodaganj.spring.boot.app.precondition.PrepareEnvTest
````

## App flow
1. Read kafka
2. Update mongo
3. Provide some data from Mongo via rest

## Test flow
1. Start docker dependencies
2. Run test to create kafka topic
3. Start app
4. Run test to feed kafka with some data
5. Check app consumes data from kafka
6. Check app writes data to Mongo
7. Check data is available via rest