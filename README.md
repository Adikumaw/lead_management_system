# Lead Management System - Backend

## Overview

The **Lead Management System** backend is a structural and robust solution tailored for travel agencies to manage and monitor leads efficiently. It facilitates tracking the journey of a lead through various stages, from the initial inquiry to post-trip feedback, ensuring seamless service delivery and optimal customer satisfaction.

## Features

### Core Functionality

1. **Lead Management**
   - Track and manage leads through various stages (e.g., `hot`, `warm`, `converted to clients`).
   - Record customer inquiries such as flight bookings, hotel bookings, and sightseeing requests.
   - Schedule and monitor follow-ups, meetings, and interactions.

2. **Quotation System**
   - Generate quotations using a pool of predefined itineraries.
   - Customize quotations based on client requirements.

3. **Hot Deals**
   - Convert potential leads into hot deals to boost conversions.

4. **Invoice Management**
   - Generate invoices including GST, taxes, and the final package price.
   - Maintain financial records for transparency and compliance.

5. **Customer Management**
   - Create customer profiles only after receiving an advance payment.
   - Track trip progress and ensure all promised features are delivered.
   - Collect post-trip feedback for service improvement.

6. **Authentication & Authorization**
   - User authentication using JWT tokens.
   - Refresh tokens for session management.
   - Account locking after multiple failed login attempts.

### API Endpoints

#### Authentication APIs
- **Sign In**: Authenticate users and provide access tokens.
- **Sign Up**: Register new users with email verification.
- **Token Management**: Refresh access tokens and handle token expiry.
- **Password Management**: Request and confirm password resets, update passwords.
- **Logout**: Invalidate user sessions securely.

#### Lead Management APIs
- Create, retrieve, update, and delete leads.
- Fetch leads by ID or retrieve all leads.
- Manage leads through various statuses (e.g., `active`, `closed`, `proposal sent`).
- Delete leads selectively or in bulk.

#### Itinerary APIs
- Create, retrieve, update, and delete itineraries.
- Fetch itinerary details by ID or retrieve all itineraries.

### Data Flow

1. **Lead Creation**: A lead is created with details like inquiry type, planned travel date, and budget.
2. **Quotation**: Generate quotations based on predefined itineraries.
3. **Hot Deal Conversion**: Leads can be converted into hot deals for quick follow-up.
4. **Invoice Generation**: Generate invoices for confirmed leads, including tax calculations.
5. **Customer Creation**: Once a lead pays an advance, a customer profile is created, and trip details are recorded.
6. **Feedback Collection**: Post-trip feedback ensures continuous improvement.

## Technologies Used

- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Tokens)
- **Tools**: Postman for API testing
- **Version Control**: Git, GitHub

## Setup and Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd lead-management-system
   ```

2. **Configure Database**
   - Update the `application.properties` file with your database credentials.

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **API Documentation**
   - Swagger documentation is available for detailed API specifications.

## Future Enhancements

- Integration with third-party APIs for real-time booking updates.
- Enhanced analytics and reporting dashboards.
- Mobile application for on-the-go lead management.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For inquiries or support, please reach out to [Aditya Kumawat](mailto:kumawataditya105@gmail.com).
