# car-catalog

A REST API that manages a car catalogue. The API allows cars to be viewed, inserted, modified and deleted from the 
catalogue.

## How to start and test the application

Before starting the application, create an ```application.properties``` file in the ```src/main/resources``` folder, and 
specify the properties required for H2 database:

- ```spring.datasource.url```
- ```spring.datasource.driverClassName```
- ```spring.datasource.username```
- ```spring.datasource.password```
- ```spring.jpa.database-platform```

FOR THE H2 DATABASE USED FOR TESTING:

- ```spring.datasource.test.url```
- ```spring.datasource.test.driverClassName```
- ```spring.datasource.test.username```
- ```spring.datasource.test.password```
- ```spring.jpa.test.hibernate.ddl-auto```

Ensure to exclude this properties file by specifying it in the 
```.gitignore``` file.

### Testing the endpoints directly using Postman

The application can be started in two different ways:

- In IntelliJ IDEA, Open the ```src/main/java/com/autoxy/car_catalog/CarCatalogApplication.java```, click on one of the 
  two "play" buttons, and choose the "Run" or the "Debug" option in the dropdown menu.
- or, alternatively, Open the terminal by clicking on the "Terminal" button on the left side toolbar, then type 
  ```mvn spring-boot:run``` and press Enter.

After that, once the application is started, you can begin to test the API endpoints with Postman; import the cURLs that 
are shown below. The port number to refer to is what you have specified in the ```server.port``` property in the 
```application.properties``` file (or ```8080``` by default, if you have not specified it).
The values in the request bodies and the path variables/request parameters in the URLs can be modified.

If the values in the request body or the path variables/request parameters in the URL are absent or sintactically 
incorrect, the API will respond with an appropriate error message.

Below you will find the cURLs for making the API calls via Postman, you can copy & paste them.

#### POST /cars
```
curl --location --request POST 'http://localhost:8080/cars' \
--header 'Content-Type: application/json' \
--data '{
"brand":"Peugeot",
"model":"208",
"yearOfProduction":"2002",
"price":5000.00,
"status":"available"
}'
```

#### GET /cars/{id}
```
curl --location --request GET 'http://localhost:8080/cars/1' \
--header 'Content-Type: application/json'
```

#### GET /cars
```
curl --location --request GET 'http://localhost:8080/cars' \
--header 'Content-Type: application/json'
```

#### PUT /cars/{id}
```
curl --location --request PUT 'http://localhost:8080/cars/1' \
--header 'Content-Type: application/json' \
--data '{
    "brand":"Peugeot",
    "model":"208",
    "yearOfProduction":"2025",
    "price":5000.00,
    "status":"sold"
}'
```

#### DELETE /cars/{id}
```
curl --location --request DELETE 'http://localhost:8080/cars/1' \
--header 'Content-Type: application/json'
```

#### GET /cars/brand/{brand}
```
curl --location --request GET 'http://localhost:8080/cars/brand/Toyota' \
--header 'Content-Type: application/json'
```

#### GET /cars/byPriceRange?fromPrice=value1&toPrice=value2
```
curl --location --request GET 'http://localhost:8080/cars/byPriceRange?fromPrice=1000&toPrice=10000' \
--header 'Content-Type: application/json'
```

#### GET /cars/status/{status}
```
curl --location --request GET 'http://localhost:8080/cars/status/available' \
--header 'Content-Type: application/json'
```

#### WARNINGS ON PASSING THE REQUEST VALUES

As mentioned earlier, if incorrect values are passed in the request body (in the POST and PUT requests) or in the URL, 
the API will respond with an error message.

- The fields of a Car (```brand```, ```model```, ```yearOfProduction```, ```price```, ```status```) are all MANDATORY, 
  so if you omit any one of these fields, or you set any one of these fields to null, the API will return an error 
  message.
```
{
  "message": "Car attributes cannot be null!"
}
```

- The ```brand``` and the ```model``` fields are both strings, but can be alternatively passed as a number. In that 
  case, they will be automatically converted in strings.


- The ```yearOfProduction``` is a string, but can be alternatively passed as a number, as long as it represents a valid 
  year, without any decimal point. In that case, it will be automatically converted in a string. If you pass it as a 
  string, it cannot have any alphanumeric characters different from numeric digits.
```
{
  "message":"The year of production must be a valid year! It can contain only digits."
}
```

- The ```price``` is a decimal number, but can be alternatively passed as a string, as long as it represents a number, 
  integer or decimal, and it cannot have any alphanumeric characters different from numeric digits.
```
{
  "message":"Car price must be a valid number! It cannot contain letters."
}
```

- The ```status``` must be passed necessarily as a string. It can have only two possible values: ```available``` or 
  ```sold```. If you specify any another value, the API will respond with an error message.
```
{
  "message":"Car status can be only \"available\" or \"sold\"!"
}
```

- In the ```GET /cars/{id}``` and in the ```DELETE /cars/{id}```, if you specify the id of a Car that does not exist, 
  the API will respond with an error message.
```
{
  "message":"the Car with the specified id doesn't exist!"
}
```

- In the ```GET /cars```, ```GET /cars/brand/{brand}```, ```GET /cars/byPriceRange?fromPrice=value1&toPrice=value2```, 
  and ```GET /cars/status/{status}```, if there are no Cars, the API will respond with an error message.
```
{
  "message":"No Car exists at the moment!"
}
```

- In the ```GET /cars/byPriceRange?fromPrice=value1&toPrice=value2```, if the maximum price is less than the minimum 
  price, the API will respond with an error message.
```
{
  "message":"The maximum price cannot be less than the minimum price!"
}
```
and if one or both the specified prices are omitted or invalid, the API will respond with an error message.
```
{
  "message":"Please specify a valid number in your request!"
}
```

- In every API call that requires a parameter in the URL, if the parameter is omitted, the API will respond with an 
  error message.
```
{
  "message":"Please specify a not empty parameter in the URL!"
}
```

- any other field arbitrarily specified in the request body that is different from those specified above (```brand```, 
  ```model```, ```yearOfProduction```, ```price```, ```status```) will be ignored.

### Execute the Unit Tests

- There are implemented some unit tests for the Repository layer (CarRepository), in the 
  ```src/test/java/com/autoxy/car_catalog/CarRepositoryUnitTests.java```. You can execute these unit tests by clicking 
  on the play buttons (in IntelliJ IDEA), the play button on top allows you to execute all unit tests (not disabled), 
  the other play buttons allow you to execute a single unit test.

- Some test methods are disabled by default with the ```@Disabled``` annotation, so remove this annotation to execute 
  these tests.