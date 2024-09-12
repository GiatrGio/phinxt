# PHINXT API

API with a single endpoint to calculate the movement of a cleaning robotic hoover in a room.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Build the Project](#build-the-project)
    - [Run the Application](#run-the-application)
- [API Endpoints](#api-endpoints)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 8 or higher installed.
- Apache Maven installed (for building the project).

## Getting Started

Follow these steps to set up and run the project locally.

### Clone the Repository

```bash
git clone https://github.com/GiatrGio/phinxt.git
cd phinxt
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

### Run all Tests
```bash
mvn test
```

The application will be accessible at `http://localhost:8080`.

Swagger UI will be accessible at http://localhost:8080/swagger-ui.html

## API Endpoints

### Movement calculation endpoint

- Get final position and cleaning spots endpoint: POST `http://localhost:8080/hoover/cleanRoom`