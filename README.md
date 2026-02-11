# Stockwatch
[![Java CI](https://github.com/likhithgp/Stockwatch/actions/workflows/maven.yml/badge.svg)](https://github.com/likhithgp/Stockwatch/actions/workflows/maven.yml)
![Static Badge](https://img.shields.io/badge/3.1.5-likhith?style=plastic&logo=springboot&logoColor=green&label=Springboot)
![Static Badge](https://img.shields.io/badge/17-likhith?style=plastic&label=Java&labelColor=orange&color=black)

This Project Contains an API to fetch Stock Details of the Company with Customization.

API Infromation
------------------------
It Consist of 12 API's including one API to fetch Health of Application.

+ POST API to add new Stockdetails.
+ Eight GET API to get Details from DB based on customization. [Get details by Company name,Company Stock Symbol,sorting order, Prefered column name sorting, Pagination,FilterByPagination based on current price]
+ PUT API to update stock details.
+ DELETE API to delete stock details.
+ It has also one actuator API implicity.

Documenation
--------------------
Added Swagger for documenation
+ Access swagger UI at http://localhost:8085/swagger-ui/index.html#/

Added actuator, You can configure to get all metrics and health releated data.
+ You can access actuator http://localhost:8085/actuator

Postman collection 
-------------------------

```jason
{
  "info": {
    "_postman_id": "0e0ac8c2-e392-4b89-beab-66835f6fe52e",
    "name": "Stockwatch",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Stock Detail",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \r\n    \"stockName\": \"Likhith\",\r\n    \"stockSymbol\": \"IAS\",\r\n    \"currencyType\": \"Peso\",\r\n    \"currentPrice\": 4899.02,\r\n    \"highOf52weeks\": 2649.4,\r\n    \"lowOf52weeks\": 2780.91\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8085/stockwatch/api/v1/stock",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "stockwatch",
            "api",
            "v1",
            "stock"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get ALL",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": ""
        }
      },
      "response": []
    },
    {
      "name": "GET stock By Name",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": ""
        }
      },
      "response": []
    },
    {
      "name": "Get STock by Symbol",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/actuator",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "actuator"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Pagenation",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/stockwatch/api/v1/stocks/Pagenation/0/100",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "stockwatch",
            "api",
            "v1",
            "stocks",
            "Pagenation",
            "0",
            "100"
          ]
        }
      },
      "response": []
    },
    {
      "name": "PagenationAndsort",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/stockwatch/api/v1/stocks/Pagenation/0/100",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "stockwatch",
            "api",
            "v1",
            "stocks",
            "Pagenation",
            "0",
            "100"
          ]
        }
      },
      "response": []
    },
    {
      "name": "DeleteStockDetails",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/stockwatch/api/v1/stock/Likhith",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "stockwatch",
            "api",
            "v1",
            "stock",
            "Likhith"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get All By Sort",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/stockwatch/api/v1/stocks/order/stockName",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "stockwatch",
            "api",
            "v1",
            "stocks",
            "order",
            "stockName"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Update",
      "request": {
        "method": "PUT",
        "header": [],
        "url": {
          "raw": ""
        }
      },
      "response": []
    },
    {
      "name": "actuator/health",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8085/actuator/health",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8085",
          "path": [
            "actuator",
            "health"
          ]
        }
      },
      "response": []
    }
  ]
}
```




# Design Decisions

## Package Structure & Separation of Concerns

To ensure maintainability, scalability, and clear ownership across multiple engineers, the project follows a clean layered architecture:

- **controller**
  - Contains all REST endpoint classes.
  - Responsible only for handling HTTP requests/responses.
  - Delegates business logic to the service layer.

- **service**
  - Contains business logic and core application rules.
  - Keeps controllers thin and focused on transport concerns.
  - Improves testability and separation of concerns.

- **model**
  - Contains POJO classes (DTOs) for request and response payloads.
  - Separates API contracts from internal implementation.
  - Makes the system easier to extend and refactor.

- **exception**
  - Centralized exception handling using `@ControllerAdvice`.
  - Maps exceptions to appropriate HTTP status codes.
  - Returns only required error information.
  - Prevents internal stack traces from leaking to clients.

- **configuration**
  - Contains configuration-related classes (e.g., OpenAPI config, filters, custom beans).
  - Keeps setup logic isolated and organized.

- **constants**
  - Stores reusable constant values in a single place.
  - Avoids duplication of hard-coded values.
  - Ensures easier updates and better maintainability.

---

## API Documentation

- Integrated **Swagger / OpenAPI** documentation.
- Provides clear request and response models.
- Improves developer onboarding.
- Enables easy testing through Swagger UI.
- Encourages consistent API standards across services.

---

## Validation

- Implemented input validation using **Jakarta Validation** (`@Valid`, `@NotNull`, etc.).
- Ensures invalid data is rejected at the boundary.
- Returns meaningful validation error messages.
- Improves reliability and protects business logic from bad inputs.

---

## Exception Handling Strategy

- Global exception handler ensures:
  - Proper HTTP status codes.
  - Clean, standardized error responses.
  - No exposure of internal implementation details.
  - Easy extensibility for domain-specific exceptions.

---

## Containerization

- Docker image provided for:
  - Environment consistency across dev/test/prod.
  - Simplified CI/CD integration.
  - Easy deployment to cloud/Kubernetes environments.
  - Horizontal scalability in high-traffic production systems.

- The service is designed to be **stateless**, making it suitable for scaling behind a load balancer.

---

## Production-Oriented Considerations

Even though the functional scope is minimal, the structure reflects production-readiness:

- Clear separation of concerns.
- Centralized error handling.
- Input validation.
- API documentation.
- Docker support.
- Clean and extensible package structure.

This ensures the service can serve as a reliable **base template** for future microservices within the organization.
