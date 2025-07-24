# docker build -t app-todos . ------------------> esto construye la imagen
# docker build -t anddyqg/app-todos .   ------------------> esto construye la imagen y la etiqueta
# docker push anddyqg/app-todos         ------------------> esto sube la imagen al repositorio

#desde donde este el dockercomposeyml
#docker compose up -d

# Etapa única basada en la imagen JRE 21 de Eclipse Temurin
FROM eclipse-temurin:21.0.7_6-jre-noble

# Crea el directorio de la aplicación
RUN mkdir /app
WORKDIR /app

# Copia los artefactos generados por Quarkus
COPY build/quarkus-app/lib ./lib
COPY build/quarkus-app/quarkus ./quarkus
COPY build/quarkus-app/app ./app
COPY build/quarkus-app/quarkus-run.jar app.jar

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]
