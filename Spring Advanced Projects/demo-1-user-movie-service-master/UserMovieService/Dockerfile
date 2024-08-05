FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/UserMovieService-0.0.1-SNAPSHOT.jar /usr/src/UserMovieService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserMovieService-0.0.1-SNAPSHOT.jar"]
