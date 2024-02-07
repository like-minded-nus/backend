# LikeMinded - Java Spring Backend

This project is a Java Spring Boot application designed to serve as a backend service for LikeMinded.

## Getting Started

These instructions will get your copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed on your system:

-   OpenJDK 21
-   Maven (if you prefer not using the included Maven wrapper)

### Installing

First, clone the repository to your local machine:

```bash
git clone https://github.com/like-minded-nus/backend.git
cd your-project-directory
```

### Running the application

To start the server, you can use the provided Maven wrapper. In the project root directory, run the following command:

```bash
./mvnw spring-boot:run
```

For Windows users, use the `mvnw.cmd` command instead:

```cmd
mvnw spring-boot:run
```

This command will start the Spring Boot application on the default port (usually 8080). Access the application at `http://localhost:8080`.

## Built With

-   [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
