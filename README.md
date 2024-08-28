# Employee Management System Backend

This is the backend of an Employee Management System built with Java Spring Boot. It provides RESTful APIs for managing users, roles, and employees. The system includes JWT authentication for secure login and role-based access control.

## Features

- **JWT Authentication**: Secure login system using JSON Web Tokens.
- **Role-Based Logins**: Access control based on user roles.
- **User Management**: CRUD operations for users and their accounts.
- **Role Management**: CRUD operations for roles.
- **Employee Management**: CRUD operations for employees, including specific employee retrieval by ID.

## Technologies Used

- Java Spring Boot
- JWT for Authentication
- Spring Security for Role-Based Access Control
- MySQL (or your preferred database)
- Maven

## API Endpoints

### Users / Accounts

- **Create a User**  
  `POST /users`  
  Adds a new user to the system.

- **Get All Users**  
  `GET /users`  
  Retrieves a list of all users.

- **Update a User**  
  `PUT /users`  
  Updates an existing user.

### Roles

- **Create a Role**  
  `POST /roles`  
  Adds a new role to the system.

- **Get All Roles**  
  `GET /roles`  
  Retrieves a list of all roles.

- **Update a Role**  
  `PUT /roles`  
  Updates an existing role.

### Employee

- **Create an Employee**  
  `POST /api/employee`  
  Adds a new employee to the system.

- **Get All Employees**  
  `GET /api/employee`  
  Retrieves a list of all employees.

- **Get an Employee by ID**  
  `GET /api/employee/{ID}`  
  Retrieves a specific employee by their ID.

- **Update an Employee**  
  `PUT /api/employee`  
  Updates an existing employee.

- **Delete an Employee by ID**  
  `DELETE /api/employee/{ID}`  
  Deletes an employee from the system by their ID.

### Authentication

- **Login**  
  `POST /auth/login`  
  Authenticates a user and returns a JWT for accessing secured endpoints.

## Setup and Installation

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/Adikumaw/EMS.git
   cd EMS
   ```

2. **Configure the Application**  
   Update the `application.properties` file with your database configuration and no need to configure JWT secret key ( Automaticaly Generates after running the server ).

3. **Build the Project**  
   ```bash
   mvn clean install
   ```

4. **Run the Application**  
   ```bash
   mvn spring-boot:run
   ```

5. **Access the APIs**  
   The application will be running at `http://localhost:8080/`. You can use tools like Postman to interact with the APIs.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
