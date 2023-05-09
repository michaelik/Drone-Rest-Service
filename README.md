## Author
#### Name: Ikechukwu Michael, Email: mikeikechi3@gmail.com
# Introduction🖖

This project explores the potential of drones to revolutionize transportation infrastructure. With the ability to deliver small items to remote and urgent locations, drones have the potential to change the way we think about transportation and logistics. Through this project, I aim to showcase the benefits of drone technology and how it can be used to improve our daily lives. From delivering medical supplies to remote regions to facilitating emergency response, the possibilities of drones are limitless.

---

### Step One - Tools and Technologies used 🎼

- Spring Boot(V2.7.3)
- Spring Data JPA
- Lombok (To get rid of boilerplate code by providing encapsulations support)
- JDK 1.8
- Embedded Tomcat
- Mysql Database(Mysql Workbench)
- Maven
- Java IDE (Intellij)

---

### Step Two - Steps to Run the project Locally ⚙️

[MySQL Workbench](https://www.mysql.com/products/workbench) was used to run the database locally. Navigate to Project application.yml and modify the database credential per your Database server requirement such as `username` and `password`

## Installation

* Clone this repo:

```bash
git clone https://github.com/michaelik/Drone-Rest-Service.git
```
* Navigate to the root directory of the project.
* Build the application
```bash
./mvnw clean install
```
* Run the application
```bash
./mvnw spring-boot:run
```
---

## Usage 🧨

>REST APIs for Drone Service Resource

| HTTP METHOD |                 ROUTE                  | STATUS CODE |                  DESCRIPTION                   |
|:------------|:--------------------------------------:|:------------|:----------------------------------------------:|
| POST        |          `/api/auth/register`          | 200         |                Create new user                 |
| POST        |           `/api/auth/login`            | 200         |                Login new user                 |
| POST        |        `/api/v1/drone/register`        | 201         |                Create new drone                |
| POST        |        `/api/v1/drone/battery`         | 200         |            Get drone battery level             |
| GET         |       `/api/v1/drone/available`        | 200         |            Get all available drones            |
| POST        |          `/api/v1/drone/load`          | 201         |          Insert medication into drone          |
| GET         | `/api/v1/drone/details/{serialNumber}` | 200         | Get single medication details by serial number |
| POST        |        `/api/v1/drone/deliver`         | 200         |           Get drone delivery status            |

---

### The Client should be able to:

**SignUp**

The payload will have the following field:

- `name`:
- `email`:
- `password`
- `age`:
- `gender`:

Request

```
curl -X POST http://localhost:8080/api/auth/register \
-H 'Content-type: application/json' \
-d '{
    "name": "Ikechukwu Michael",
    "email": "mikeikechi3@gmail.com",
    "password": "12345",
    "age": 20,
    "gender": "M"
}'
```
**SignIn**

The payload will have the following field:
- `userName`:
- `password`:

Request

```
curl -X POST http://localhost:8080/api/auth/register \
-H 'Content-type: application/json' \
-d '{
    "userName": "mikeikechi3@gmail.com",
    "password": "12345"
}'
```

Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8",
  "user": {
    "id": 1,
    "name": "Ikechukwu Michael",
    "email": "mikeikechi3@gmail.com",
    "age": 20,
    "gender": "M",
    "password": "$2a$10$BAoIxNDNVTKV/0xr5Vb34eHJ9N0mROjl/8BWeQ7DkJT3wEQlZobsW",
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "username": "mikeikechi3@gmail.com",
    "accountNonLocked": true
  }
}
```

**Register New Drone**

The payload will have the following fields

- `serialNumber` is the unique serial number for the drone being loaded
- `model` is the unique name representing the drone (Lightweight, Middleweight, Cruiser weight, Heavyweight)
- `weight` represent the weight of the drone
- `battery` is the current battery level of the drone
- `state`  represent the current state of the drone (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING)

Request

```
curl -X POST http://localhost:8080/api/v1/drone/register \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
-d '{
    "serialNumber": "IK15690241",
    "model": "Lightweight",
    "weight": "430.0",
    "battery": "0.98",
    "state": "IDLE"
}'
```

Response

```json
{
    "message": "drone created successfully"
}
```

**Check Drone battery level for a given drone**

The payload will have the following field

- `serialNumber` is the unique serial number for identifying drone battery level

Request

```
curl -X GET http://localhost:8080/api/v1/drone/battery \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
-d '{
    "serialNumber": "IK15690241"
}'
```

Response

```json
{
    "serialNumber": "IK15690241",
    "battery": 0.98,
    "timestamp": "2023-05-09T11:11:51.5043857"
}
```

**Checking available drones for loading**

The payload will not accept any field or URL parameter

Request

```
curl -X GET http://localhost:8080/api/v1/drone/available \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
```

Response

```json
{
    "drones": [
        {
            "serialNumber": "IK15690241",
            "model": "Lightweight",
            "weight": 430.0,
            "battery": 0.98,
            "state": "IDLE"
        }
    ],
    "createdAt": "2023-05-09T11:13:22.6235369"
}
```

**Loading a drone with medication items**

The payload will have the following fields

- `serialNumber` is the unique serial number for the drone being loaded
- `code` is the unique code for the medication item being loaded to the drone
- `source` is the point of loading
- `destination` is the end goal of the load

The Medication items `code` to be loaded for testing: 04-5673, 04-6792, 047651, 04-5762, 04-6738

The destination and the source are any places

Request

```
curl -X POST http://localhost:8080/api/v1/drone/load \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
-d '{
    "serialNumber": "IK15690241",
    "code": "04.5673",
    "source": "Canada",
    "destination": "India"    
}'
```

Response

```json
{
    "message": "drone loaded successfully"
}
```

**Checking loaded medication items for a given drone**

- The payload will accept URL parameter request which is basically the specific drone serial number.

Request

```
curl -X GET http://localhost:8080/api/v1/drone/details/IK15690241 \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
```

Response

```json
{
    "serialNumber": "IK15690241",
    "medication": {
        "code": "04.5673",
        "name": "Abilify",
        "weight": 100.0,
        "image": "/Abilify_IMG"
    },
    "createdAt": "2023-05-09T11:15:34.351517"
}
```

**Delivery of medication item**

The payload will have the following field

- `serialNumber` is the unique serial number for identifying drone delivery status

Request

```
curl -X GET http://localhost:8080/api/v1/drone/deliver \
-H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9VU0VSIl0sInN1YiI6Im1pa2Vpa2VjaGkzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYyNTQwMiwiZXhwIjoxNjg0OTIxNDAyfQ.lUhKrUAynF2lkEZj2wCs0jmEpc8Qra1m83RKjIm0xb8' \
-d '{
    "serialNumber": "IK15690241"
}'
```

Response

```json
{
    "status": "LOADED",
    "createdAt": "2023-05-09T11:16:40.1959317"
}
```
