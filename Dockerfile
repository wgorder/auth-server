FROM wgorder/java8-jce
MAINTAINER Bill Gorder <william.gorder>

# Add the application to the container
ADD build/libs/auth-server.jar /data/auth-server.jar

# Expose
EXPOSE  8080

# Run
CMD ["java", "-jar", "auth-server.jar"]
