# Query API Service

**A fully functional Spring Boot API to query call detail records with flexible filters — clean code, proper DTOs, and ready for immediate testing.**

---

## Project Structure

```
Query-api-service
├── src
│   └── main
│       └── java
│           └── com/example/queryApi
│               ├── controller
│               │   └── CallDetailRecordController.java
│               ├── dto
│               │   ├── QueryRequest.java
│               │   └── CallDetailRecordResponse.java
│               ├── model
│               │   └── CallDetailRecord.java
│               ├── repository
│               │   └── CallDetailRecordRepository.java
│               └── QueryApiApplication.java
└── src/main/resources
    └── application.properties
```

---

## Features

* Query `call_detail_records` table using date range and optional MSISDN/IMSI filters.
* Returns results as JSON arrays using CallDetailRecordResponse.
* Spring Boot + Spring Data JPA for seamless database integration.
* PostgreSQL support.
* Open for testing without authentication.

---

## Tech Stack

* Backend: Spring Boot 17
* Database: PostgreSQL
* JPA: Spring Data JPA
* Language: Java 17

---

## Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/akram3855/Query-api-service.git
cd Query-api-service
```

### 2. Set Up PostgreSQL

1. Create a PostgreSQL database (default: querydb).
2. Ensure the `call_detail_records` table exists:

   * id (Primary Key, auto-increment)
   * record\_date (timestamp, not null)
   * msisdn (string)
   * imsi (string)
3. Spring Boot can auto-create the table if `spring.jpa.hibernate.ddl-auto=update` is enabled.
4. Insert sample records for testing.

### 3. Configure Database Connection

Edit `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/querydb
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL
```

### 4. Run the Application

```
mvn spring-boot:run
```

* The application will start at [http://localhost:8080](http://localhost:8080).

---

## DTOs (Data Transfer Objects)

### 1. QueryRequest

Defines input parameters for the API.

| Field               | Type   | Required | Description                  |
| ------------------- | ------ | -------- | ---------------------------- |
| record\_date\_start | String | Yes      | Start date/time of the query |
| record\_date\_end   | String | Yes      | End date/time of the query   |
| msisdn              | String | No       | Optional MSISDN filter       |
| imsi                | String | No       | Optional IMSI filter         |

### 2. CallDetailRecordResponse

Defines the output structure returned by the API.

| Field      | Type   | Description               |
| ---------- | ------ | ------------------------- |
| recordDate | String | Timestamp of the record   |
| msisdn     | String | MSISDN of the call record |
| imsi       | String | IMSI of the call record   |

---

## API Usage

Endpoint: POST /api/query
Content-Type: application/json

### Example Requests

1. Date range only

```
{
    "record_date_start": "2023-08-18 10:30:00",
    "record_date_end": "2023-08-18 10:31:00"
}
```

2. Date range + MSISDN

```
{
    "record_date_start": "2023-08-18 10:30:00",
    "record_date_end": "2023-08-18 10:31:00",
    "msisdn": "573228550000"
}
```

3. Date range + IMSI

```
{
    "record_date_start": "2023-08-18 10:30:00",
    "record_date_end": "2023-08-18 10:31:00",
    "imsi": "1234567890"
}
```

4. Date range + MSISDN + IMSI

```
{
    "record_date_start": "2023-08-18 10:30:00",
    "record_date_end": "2023-08-18 10:31:00",
    "msisdn": "573228550000",
    "imsi": "1234567890"
}
```

---

### Sample Response

```
[
  {
    "recordDate": "2023-08-18T10:30:15",
    "msisdn": "573228550000",
    "imsi": "1234567890"
  },
  {
    "recordDate": "2023-08-18T10:30:45",
    "msisdn": "573228550001",
    "imsi": "1234567891"
  }
]
```

---

## Testing Instructions

1. Clone the repository.
2. Set up PostgreSQL database and table.
3. Configure application.properties with your database credentials.
4. Run the application:

```
mvn spring-boot:run
```

5. Use cURL or any HTTP client to send POST requests to [http://localhost:8080/api/query](http://localhost:8080/api/query).
6. Test different scenarios: date range only, date range + MSISDN, date range + IMSI, date range + MSISDN + IMSI.
7. Verify returned JSON matches expected filtered records.

---


