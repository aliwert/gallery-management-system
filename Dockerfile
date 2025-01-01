FROM openjdk:17-oracle
COPY target/*.jar auto-showroom.jar
EXPOSE 8089
ENTRYPOINT ["java", "jar", "auto-showroom.jar"]