The application runs with a minimun version of java 17. It Uses Spring 3.0.2

Donwload the repository and run 
*mvnw spring-boot:run*

The application would connect to an external Postgres database. application.properties declare the **spring.datasource.url=** on
*jdbc:postgresql://cloud.090771.dhatek.com:5566/easygo*
The database model map image is located on ./src/main/resources.

For review all the API endpoints with schemas, got to:

http://localhost:8080/swagger-ui/index.html



