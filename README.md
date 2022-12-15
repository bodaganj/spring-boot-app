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