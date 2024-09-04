# PaymentService

## Clone Repository
First, clone the repository on your local machine:
```
git clone https://github.com/EBayego/PaymentService.git
cd payment-service
```

## Configuring the database with Docker
The project is configured to use PostgreSQL as database. You can raise a PostgreSQL instance using Docker with the following command:
```
docker run --name my-postgres -e POSTGRES_PASSWORD=pass -d -p 5432:5432 -v /route/to/V1__create_payment_table.sql:/docker-entrypoint-initdb.d/V1__create_payment_table.sql postgres
```
You will have to replace the path with the path of your local computer where the project is located. The sql file is in "src\main\resources\db".

This command will raise a PostgreSQL container on port 5432 with the credentials defined in the docker-compose.yml file.

## Configuring the database in local
Download PostgreSQL and run the script file located at "src\main\resources\db\V1__create_payment_table.sql".

In your IDE, you will need to set an environment variable. In eclipse you should go to Run Configurations -> Spring Boot App -> 
Create a new one from this project -> Go to the Environment tab -> Add this variable: DATABASE_URL=jdbc:postgresql://localhost:5432/payment_db.

## Configure application properties
The application properties are located in the src/main/resources/application.yml file. Here you can configure the database connection:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/payment_db
    username: postgres
    password: postgres
```

## Explore the API
API documentation is available at http://localhost:8080/swagger-ui.html. From here, you can interact with the API endpoints.
