#Deployment of Springbooot App

#Folder Structure
- /backend/Dockerfile
- /backend/.dockerignore
- /frontend/Dockerfile
- /frontend/.dockerignore
- /frontend/nginx.conf
- /root/docker-compose.yml
- root/.env
- root/.gitignore

###/backend/
DockerFile
```
#----------Build stage----------
FROM maven :3.8.6-openjdk-17-slim AS build      
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline  -B 
COPY src ./src
RUN mvn clean  package -DskipTests -B

#----------Run stage----------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```
#backend/.dockerignore
```
target
.git
,idea
*.iml
```
###frontend/DockerFile
```
#-----Run stage------
FROM nginx:alpine
COPY nginx.conf /etc/nginx /conf.d/default.com
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
```
###frontend/.dockerignore
```node modules
dist
.git
```