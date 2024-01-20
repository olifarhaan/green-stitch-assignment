
# Green Stitch Backend Development Assignment


Welcome to the Green Stitch Backend Development Assignment! This project aims to create a secure and efficient backend for a login and signup REST API, integrating essential security features and utilizing JSON Web Tokens (JWT) for authentication. The H2 database will be employed for data storage, ensuring a seamless integration with the Spring Boot framework.



## Task Details
My task was to design and implement a robust backend system that facilitates user authentication and authorization. 


## Url & endpoints for testing

Base Url: https://green-stitch-assignment.onrender.com/

Public Auth endpoints: `/auth/login` , `/auth/signup`

Private Auth endpoints: `/hello`


Sign Up Request Body: 
```
{
    "name": "user1",
    "email": "user1@gmail.com",
    "password": "123456"
}
```

Sign In Request Body:
```
{
    "email": "user1@gmail.com",
    "password": "123456"
}
```
Post login you will get Bearer token
```
{
    "jwtToken": "eyJhbGciOiJIUzUxMIgdlTNlE6fw6-Ot-Ak7bmZ-some_random_token_6o21F_Jl5Cw4975cndYw",
    "username": "user@gmail.com"
}
```

Now try to access the `/hello` endpoint with the header `[Authorization: Bearer your_jwt_token]`

Wallah, you accessed the private endpoint of Spring Security and got a message `Hello from GreenStitch`


## How to deploy

Simply use the Dockerfile to deploy and don't forget to add the application.properties as environment variables.


## Technologies Used
Spring Boot Framework

Spring Security

H2 Database

JSON Web Tokens (JWT)

## How to get started with the project

Clone the repository.

Configure the application using your own application.properties file.

Demo application.properties file below:
```

server.port=8081
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:h2:file:/yourdatabasename_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

```

Execute the Maven build to install the dependencies

Build and run the project.


## Feedback

If you have any feedback, please reach out to me at alifarhan231087@gmail.com

