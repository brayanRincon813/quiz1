FROM openjdk:21
COPY "./target/quiz1-1.jar.original" "app.jar"
EXPOSE "8136"
ENTRYPOINT [ "java", "-jar", "app.jar" ] 