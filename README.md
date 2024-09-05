# PaymentService

## Clone Repository
First, clone the repository on your local machine:
```
git clone https://github.com/EBayego/PaymentService.git
cd PaymentService
```

## Configuring with Docker

### Configuring the database with Docker
The project is configured to use PostgreSQL as database. You can raise a PostgreSQL instance using Docker with the following commands:
```
docker pull postgres
docker run --name my-postgres -e POSTGRES_PASSWORD=pass -d -p 5432:5432 -v /route/to/V1__create_payment_table.sql:/docker-entrypoint-initdb.d/V1__create_payment_table.sql postgres
```
You will have to replace the path of the .sql file with the path of your local computer where the project is located. The sql file is in "src\main\resources\db".
This command will raise a PostgreSQL container on port 5432 with the credentials defined in the docker-compose.yml file.

### Configuring the repository with Docker
To compile and generate the .jar file of the project, you must execute this command in the root directory of the project:
```
mvn clean package
```
For it to work, you must have maven installed (Java is also required, but it is assumed that it is already installed):
- For Windows: you must download the [Maven Binary Zip](https://maven.apache.org/download.cgi) file. Then, you must extract it in the directory where you 
want to have it saved, and copy the path that leads to the 'bin' folder of the newly downloaded directory. Once this is done, you should go to Windows Finder 
-> search for Environment Variables -> Environment Variables -> User Variables -> Path -> Edit -> New -> and finally paste the previously copied path.

- For Linux: you will need to do the following:
```
$ wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
$ tar -xvf apache-maven-3.6.3-bin.tar.gz
$ mv apache-maven-3.6.3 /opt/
```
Change the version to the newest version [at this link](https://maven.apache.org/download.cgi). Then, to add it to the Path run the following:
```
M2_HOME='/opt/apache-maven-3.6.3'
PATH="$M2_HOME/bin:$PATH"
export PATH
```
Once the file has been generated in 'target' from the root directory of the project, we can create a copy of the generated .jar file, or rename it, removing the version, to be called “paymentservice.jar”. 

Now, from the root directory, we execute the following commands:
```
docker build -t PaymentService .
docker run -p 8080:8080 PaymentService
```

## Configuring in Local
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
