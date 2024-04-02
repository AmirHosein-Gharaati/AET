# Asset and Expense Tracker(AET)

This project's main focus is on learning and using spring boot as the backend.

You can add and track your assets and check the total amount, total cost and 
calculate gain/loss.


## API Documentation

You can check swagger UI for APIs here:

http://localhost:8080/aet/v1/swagger-ui/index.html

## Usage

**Requirements**:
 - Java OpenJDK v21
 - MongoDB v5
 - Docker
 - Maven



### Local

#### Docker Compose
If you are using Intellij, run the project using maven profiles and check
`compose` profile.


Or before starting the project, load the services by docker compose:

```bash
docker compose up -d
```

If wanted to stop the services:
```bash
docker compose down
```
#### properties
Use Intellij or another IDE to specify the properties to `application-local.properties`
