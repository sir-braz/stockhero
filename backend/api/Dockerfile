# Define a imagem base a ser usada
FROM openjdk:11


RUN apt-get update && apt-get install -y maven


# Copia o arquivo .jar da aplicação para dentro da imagem
COPY target/backend-0.0.1-SNAPSHOT.jar /backend-0.0.1-SNAPSHOT.jar.original


# Define o comando para executar a aplicação quando o container for iniciado
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar.original"]