# Blog application #

## Instructions ##
### In app is OAuth2 with Google is impemented###
At first you have to create app in https://console.cloud.google.com/ and create OAuth2 Client in Credentianls and paste these credentials in application.yaml


You can run app with Docker or with jar-file locally
1. Clone the repository 
 ```git clone https://github.com/maximdziuba/blog.git```
2. Open the project folder in the terminal or command line
3. ```docker-compose up```
4. At first Docker will download all required packages and than app will start.
You can access it on the ```localhost:8080```

Also you can start app with jar or Intellij IDEA.
Postgres is required to start the app. You can specify datasource settings
in ```application.yml``` in ```src/main/resources/```. Then you have to build jar ```./gradlew clean bootJar``` in project root folder
or just run app ```./gradlew bootRun```.
