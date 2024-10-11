#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY src .
#
#RUN apt-get install maven -y
#RUN mvn clean install
#
#FROM openjdk:17-jdk-slim
#
#EXPOSE 8080
#
#COPY --from=build /target/youtan-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT [ "java", "-jar", "app.jar" ]

# Etapa de build
FROM ubuntu:latest AS build

# Atualiza o sistema e instala o JDK e Maven
RUN apt-get update && apt-get install openjdk-17-jdk maven -y

# Define o diretório de trabalho para o build
WORKDIR /app

# Copia o arquivo pom.xml e o diretório src para o container
COPY pom.xml .
COPY src ./src

# Executa o Maven para compilar e construir o projeto
RUN mvn clean install

# Etapa final
FROM openjdk:17-jdk-slim

# Exponha a porta 8080 para o tráfego externo
EXPOSE 8080

# Copia o arquivo JAR gerado na etapa de build
COPY --from=build /app/target/youtan-0.0.1-SNAPSHOT.jar app.jar

# Define o ponto de entrada para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
