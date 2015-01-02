auth-server
===========

A shared authentication service


### Build
```
./gradlew build
```

### Run only api-gateway
~~./gradlew :bootRun~~
wont work due to this bug -- https://github.com/spring-cloud/spring-cloud-netflix/issues/60

Instead do it the old fashion way...

```
java -jar auth-server.jar
```

### Run All the applications
See [spring-cloud-examples](https://github.com/wgorder/spring-cloud-examples)

