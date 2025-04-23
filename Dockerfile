FROM openjdk:23
WORKDIR /app
COPY build/libs/JournalApp-0.0.1-SNAPSHOT.jar /app/JournalApp-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","JournalApp-0.0.1-SNAPSHOT.jar"]