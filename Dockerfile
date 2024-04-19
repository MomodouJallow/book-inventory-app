
FROM openjdk:17-jdk-slim
COPY /target/jallow-books-0.0.1-SNAPSHOT.jar jallow-books.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","jallow-books.jar"]
