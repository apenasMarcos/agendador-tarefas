# Use uma imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo JAR gerado pelo Gradle para dentro do container
COPY build/libs/br.com.marcos-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exponha a porta em que a aplicação estará rodando
EXPOSE 8080
