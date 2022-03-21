# city-list-app (server)

## Server startup

Open a command line (or terminal) and navigate to the folder where you have the project files. One can build and run the
application by issuing the following command:

**MacOS/Linux**

```
./mvnw spring-boot:run
```

**Windows**

```
mvnw spring-boot:run
```

## Users

There are two users in app:

| username | password | roles                            |
|----------|----------|----------------------------------|
| reader   | reader   | ROLE_ALLOW_READ                  |
| editor   | editor   | ROLE_ALLOW_READ, ROLE_ALLOW_EDIT |

Authorization is done using **basic authentication**.

## REST API

Server on default runs on **http://localhost:8080/**. Therefore, API is available on **http://localhost:8080/api**.
Available endpoints:

* **/cities**
    * description: Get list of cities in pagable format.
    * method: **GET**
    * params:
        * __page__ - the offset to be taken according to the underlying __page__ and __size__.
        * __size__ - the number of items to be returned.
    * example request:
        * **http://localhost:8080/api/cities?page=0&size=2**
    * example response:

```javascript
{
    "content": [
        {
            "id": 1,
            "name": "Tokyo",
            "photoUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg"
        },
        {
            "id": 2,
            "name": "Jakarta",
            "photoUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Jakarta_Pictures-1.jpg/327px-Jakarta_Pictures-1.jpg"
        }
    ],
        "pageable": {
        "sort": {
            "empty": true,
                "sorted": false,
                "unsorted": true
        },
        "offset": 0,
            "pageSize": 2,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
    },
    "last": false,
        "totalElements": 1000,
        "totalPages": 500,
        "size": 2,
        "number": 0,
        "sort": {
        "empty": true,
            "sorted": false,
            "unsorted": true
    },
    "first": true,
        "numberOfElements": 2,
        "empty": false
}
```

* **/cities/{id}**
    * description: Edit city.
    * **id** - id of the requested city
    * method: **PUT**
    * body: DTO in JSON format, ex.

```javascript
{
    "name": "put new name here", 
    "photoUrl": "put new photo url here"
}
```

*
    * response: DTO in JSON format, ex.

```javascript
{
    "id": 32,
        "name": "London",
        "photoUrl": "https://new.url.com"
}
```

* **/cities/{name}**
    * description: Get list of cities that starts with prefix __name__.
    * method: **GET**
    * example request:
        * **http://localhost:8080/api/cities/Lond**
    * example response:

```javascript
[
    {
        "id": 32,
        "name": "London",
        "photoUrl": "https://new.url.com"
    },
    {
        "id": 927,
        "name": "Londrina",
        "photoUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Lago_Igap%C3%B3_Londrina.jpg/500px-Lago_Igap%C3%B3_Londrina.jpg"
    }
]
```