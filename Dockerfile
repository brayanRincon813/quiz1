FROM eclipse-temurin:21-jre-jammy
COPY "./target/quiz1-1.jar" "app.jar"
EXPOSE 8136
ENTRYPOINT ["java", "-jar", "app.jar"]
