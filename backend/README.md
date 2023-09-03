## Project Description

The EstoqueAPI is an API for inventory management developed in Java with Spring Boot. It allows the management of products and categories, enabling the creation, updating, listing, and deletion of these records.

## Prerequisites

Before running the project, make sure you have the following installed on your machine:

- Java JDK 11
- Docker and Docker Compose (optional, only for containerized execution)

## Database Configuration

The EstoqueAPI uses the MySQL database to store product and category information. Before running the application, you need to configure the database.

### Option 1: Run MySQL with Docker (recommended)

If you have Docker and Docker Compose installed, you can use the provided `docker-compose.yml` file to set up and run the MySQL database. Simply run the following command in the project's root directory:

```bash
docker-compose up -d
```

This will create a Docker container containing the MySQL database and run it in the background.

### Option 2: Manual MySQL Configuration

If you prefer to set up MySQL manually, you can do so through MySQL Workbench or another MySQL client. Create a database named `inventorydb` and set the username and password as specified in the environment variables in the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventorydb?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=admin123
```

## Compiling and Running the Project

To compile and run the project, you can use Apache Maven. Simply execute the following command in the project's root directory:

```bash
mvn spring-boot:run
```

This will compile the project and start the application, which will be available at `http://localhost:8080`.

## API Endpoints

The EstoqueAPI offers the following endpoints for interaction:

### Categories

- `POST /api/categorias`: Creates a new category in the system.
- `GET /api/categorias`: Lists all registered categories.
- `GET /api/categorias/{id}`: Retrieves details of a specific category based on ID.
- `PUT /api/categorias/{id}`: Updates data of an existing category based on ID.
- `DELETE /api/categorias/{id}`: Deletes a category from the system based on ID.

### Products

- `POST /api/produtos`: Creates a new product in the system.
- `GET /api/produtos`: Lists all registered products.
- `GET /api/produtos/{id}`: Retrieves details of a specific product based on ID.
- `PUT /api/produtos/{id}`: Updates data of an existing product based on ID.
- `DELETE /api/produtos/{id}`: Deletes a product from the system based on ID.

## Relationship between Categories and Products

The `Product` entity has a relationship with the `Category` entity, where each product is associated with a specific category. The relationship is implemented through a foreign key in the products table, which stores the ID of the corresponding category.

When a product is returned by the API, it contains a `categoria` object representing the product's category. This object contains the category's `id` and `nomeCategoria`. This way, you can identify the product's category directly in the JSON response.

Example JSON response containing a product object with category information:

```json
{
  "id": 2,
  "category": {
    "id": 1,
    "categoryName": "Electronics"
  },
  "productName": "Test Product 2",
  "productDescription": "Description of Test Product 2",
  "price": 19.99,
  "quantity": 100,
  "unitOfMeasurement": "unit",
  "creationDate": "2023-07-31",
  "usageDate": "2023-08-31"
}

```

In this example, product with ID 2 is associated with category with ID 1, which is the "Electronics" category.

## Application Dockerization

If you prefer to run the application in a Docker container, a Dockerfile is already configured in the project's root directory. To create a Docker image of the application, run the following command:

```bash
docker build -t estoqueapi .
```

Then, you can run the application in a Docker container with the following command:

```bash
docker run -p 8080:8080 estoqueapi
```

This will start the application in the container and make it available at `http://localhost:8080`.

## Tests

Unit tests have been successfully executed using [JUnit](https://junit.org/) and [Mockito](https://site.mockito.org/).

### Running Tests

Ensure you have all dependencies installed and configured correctly. Then, run the unit tests using the following command:

```bash
./mvnw test
```

## To-Do

- [x] Create base project with Spring Initializr
- [x] Configure project with necessary dependencies
- [x] Implement entity layer (Category, Product)
- [x] Implement relationship between (Category, Product)
- [x] Implement unit tests in ProdutosController
- [x] Implement unit tests in CategoriaProdutoController
- [x] Create controller, service, and repository layers
- [ ] Integrate authentication service with JWT (if necessary)
- [ ] Implement authorization filters to protect endpoints (if necessary)
- [x] Implement error and exception handling
- [x] Complete documentation in README.md
- [x] Dockerize the application with Dockerfile and Docker Compose