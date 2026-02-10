FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/bff-agendadordetarefas-0.0.1-SNAPSHOT.jar /app/bff-agendadordetarefas.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/bff-agendadordetarefas.jar"]
