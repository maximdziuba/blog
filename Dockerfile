FROM gradle:7.2.0-jdk17
COPY sql/init.sh /docker-entrypoint-initdb.d/
ADD build/libs/blog-0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]