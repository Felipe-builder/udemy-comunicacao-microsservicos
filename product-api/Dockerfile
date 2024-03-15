# Use uma imagem base do Maven para construir o projeto
FROM maven:3.9-eclipse-temurin-21 as build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o pom.xml e as dependências para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Construa o projeto
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK para executar o aplicativo
FROM openjdk:21-jdk-oracle

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR construído do estágio de build
COPY --from=build /app/target/product-api-0.0.1-SNAPSHOT.jar /app/product-api.jar

# Exponha a porta que o aplicativo usa
EXPOSE 8080

# Comando para iniciar o aplicativo
CMD ["java", "-jar", "/app/product-api.jar"]
