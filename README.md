Topjava graduate project by Pavel Lezin
----
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and **README.md with API documentation and curl commands for based use-cases to test it..**

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

-----------------------------

**View model:**

**Regular user** receives a list of restaurants with today's menu. Restaurants are sorted by rating and name. User can rate the restaurant. User can't edit any other data.

**Admin** receives a full list of restaurants (even without menu) and can edit restaurant's details and can edit today's menu. 

## Install:

    git clone https://github.com/pavellezin/menu-voting

## Run (from project directory)

    $ mvn spring-boot:run

or

    $ mvn clean package
    $ java -Dfile.encoding=UTF8 -jar target/menu-voting.jar

- <a href="http://localhost:8082/">H2 console</a>
- User: `sa`, no password
- JDBC URL: `jdbc:h2:mem:voting`
- Remote connection URL: `jdbc:h2:tcp://localhost:9092/mem:voting`

## <a href="http://localhost:8080/api">The HAL Browser</a>

        User login: user@gmail.com
          password: password
    "Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ="

       Admin login: admin@javaops.ru
          password: admin
    "Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=="

### SoapUI project

From project directory: `menu-voting-soapui-project.xml`
    
### User handling

    403:Forbidden
    curl 'http://localhost:8080/api/users' -i -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ='

- <a href="http://localhost:8080/api/users">Users list</a>
- <a href="http://localhost:8080/api/users/0">Users 1</a>
- <a href="http://localhost:8080/api/users/search/by-email?email=user@gmail.com">Users by email: user@gmail.com</a>

CURL:

     curl 'http://localhost:8080/api/users' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/users/1' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/users/search/by-email?email=user@gmail.com' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/users' -i -d'{"first_name":"New_First_Name","last_name":"New_Last_Name","email":"new@mail.ru","password":"123456","roles":["USER"]}' -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
 
 
### Restaurant handling

- <a href="http://localhost:8080/api/restaurants">Restaurant list</a>
- <a href="http://localhost:8080/api/restaurants/1">Restaurant 1</a>
- <a href="http://localhost:8080/api/restaurants/search/by-name?name=Don">Restaurant by name: name=Ba</a>

CURL:

     curl 'http://localhost:8080/api/restaurants' -i -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ='
     curl 'http://localhost:8080/api/restaurants/1' -i -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ='
     curl 'http://localhost:8080/api/restaurants/search/by-name?name=Ba' -i -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ='

Modification (Access denied for User):

     curl 'http://localhost:8080/api/restaurants' -i -d'{"name" : "McDonalds"}' -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ=' -H'Content-Type: application/json'

Modification (Access allowed for Admin):

     curl 'http://localhost:8080/api/restaurants' -i -d'{"name" : "McDonalds"}' -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/restaurants/3' -i -X DELETE -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'

### Menu handling

- <a href="http://localhost:8080/api/menus">Menu list</a>
- <a href="http://localhost:8080/api/menus/1">Menu 1</a>
- <a href="http://localhost:8080/api/menus/search/by-date?date=2021-01-29">Menu for date 2021-01-29</a>
- <a href="http://localhost:8080/api/menus/search/by-restaurant?restaurant=http://localhost:8080/api/restaurants/1">Menu for restaurant 1</a>

CURL:

     curl 'http://localhost:8080/api/menus' -i -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/menus/1' -i -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/menus' -i -d'{"date" : "2021-01-30", "restaurant":"http://localhost:8080/api/restaurants/1"}' -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/menus/2' -i -X PUT -d'{"date" : "2021-01-30"}' -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/menus/search/by-date?date=2021-01-29' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/menus/search/by-restaurant?restaurant=http://localhost:8080/api/restaurants/1' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='

### Lunch handling
- <a href="http://localhost:8080/api/lunches">Lunch list</a>
- <a href="http://localhost:8080/api/lunches/1">Lunch 1</a>
- <a href="http://localhost:8080/api/lunches/search/by-date?date=2021-01-29">Lunch for date 2021-01-29</a>
- <a href="http://localhost:8080/api/lunches/search/by-menu?menu=http://localhost:8080/api/menus/1">Lunch for menu 1</a>

CURL:

     curl 'http://localhost:8080/api/lunches' -i -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/lunches/1' -i -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/lunches' -i -d'{"name" : "Desert", "price": 85, "menu":"http://localhost:8080/api/menus/1"}' -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/lunches' -i -d'{"name" : "Desert", "menu":"http://localhost:8080/api/menus/2"}' -H'Authorization: Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg==' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/lunches/search/by-date?date=2021-01-29' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='
     curl 'http://localhost:8080/api/lunches/search/by-menu?menu=http://localhost:8080/api/menus/1' -i -H'Authorization:Basic YWRtaW5AamF2YW9wcy5ydTphZG1pbg=='

## Voting
- <a href="http://localhost:8080/api/vote">Current Vote</a>

Vote for menu 1: 

     curl 'http://localhost:8080/api/vote/1' -i -X POST -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ=' -H'Content-Type: application/json'
Vote for menu 2: 

     curl 'http://localhost:8080/api/vote/2' -i -X POST -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ=' -H'Content-Type: application/json'
     
Check current vote:

     curl 'http://localhost:8080/api/vote' -i -H'Authorization: Basic dXNlckBnbWFpbC5jb206cGFzc3dvcmQ='
