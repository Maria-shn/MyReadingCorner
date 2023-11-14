# My Reading Corner

## Overview

My Reading Corner is a simple web application for managing your reading list. It allows users to add, update, and delete books, as well as organize them based on their reading status ("Want To Read," "Currently Reading," "Finished"). The backend is built with Spring Boot, using Spring Data JPA for data persistence, and the frontend is a basic HTML, CSS and JavaScript interface.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Adding a Book](#adding-a-book)
  - [Updating Book Status](#updating-book-status)
  - [Deleting a Book](#deleting-a-book)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 17
- MySQL database
- Maven
- Node.js (for frontend development)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/MyReadingCorner.git
   ```

2. Set up the database:

   - Create a MySQL database named `db_example`.
   - Update the `application.properties` file in the `src/main/resources` directory with your database connection details.

3. Build and run the application:

   ```bash
   mvn spring-boot:run
   ```

   The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Usage

### Adding a Book

1. Open the web application in your browser.
2. Fill in the book details (Title, Author, Status) in the "Add Book" form.
3. Click the "Add Book" button.

### Updating Book Status

1. Open the web application in your browser.
2. Fill in the book ID and the new status in the "Update Status" form.
3. Click the "Update Status" button.

### Deleting a Book

#### By ID

1. Open the web application in your browser.
2. Fill in the book ID in the "Delete Book By ID" form.
3. Click the "Delete Book By ID" button.

#### By Title and Author

1. Open the web application in your browser.
2. Fill in the book title and author in the "Delete Book By Title And Author" form.
3. Click the "Delete Book By Title And Author" button.

## Project Structure

- **`src/main/java/com/example/MyReadingCorner`**: Contains the Java source code, including entities, repositories, and the main application class.
- **`src/main/resources`**: Contains application properties and database schema files.
- **`src/main/resources/static`**: Contains the HTML, CSS and JavaScript files for the frontend.
- **`pom.xml`**: Maven project configuration file.

## Technologies Used

- **Spring Boot**: Framework for building Java-based enterprise applications.
- **Spring Data JPA**: Part of the larger Spring Data project, simplifying data access in Spring applications.
- **MySQL**: Database management system.
- **HTML and JavaScript**: Frontend for user interaction.
- **Maven**: Build automation and project management.

## Configuration

- Database configuration is done in the `src/main/resources/application.properties` file.
- Frontend scripts are in the `src/main/resources/static` directory.

## Contributing

Feel free to contribute to the project. Fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
