# simple-redirect-service

A simple URL shorten/redirect service

This project is released and distributed under the [Apache License, v2.0](http://www.apache.org/licenses/LICENSE-2.0)

## Features

- Quick and simple URL shortener/redirector service
- Multiple choices of redirection (HTTP301/HTTP302/Plain HTTP/JavaScript)
- Easy database setup

## Required Software

- Java 8 or newer
- SQLite database management tools

## Usage

### Database Setup

To set up the SQLite database, create a `.db`/`.sqlite` file and create a table named `redirection` using your favorite SQLite database management tool.

The table can be created using the following script:
```SQL
CREATE TABLE "redirection" (
	"id"	INTEGER NOT NULL UNIQUE,
	"resource_id"	TEXT NOT NULL UNIQUE,
	"redirect_url"	TEXT NOT NULL,
	"redirect_type"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
)
```

### Redirection Management

Add records of URLs you wished to redirect to the table `redirection` using your favorite SQLite database management tool. 

**Redirections should be defined in the database before starting up service.**

A redirection record is defined as followed:

|Column|Type|Definition|
|-|-|-|
|id|INTEGER|auto index|
|resource_id|TEXT|the identifier of link to be redirected|
|redirect_url|TEXT|destination URL|
|redirect_type|TEXT|redirect by `http301`, `http302`, `plainhttp` or `javascript`|

### Modify Application Configuration

Modify the port number and SQLite database URL. Here is an example of `application.yml`:

```YAML
server:
  port: <port number>

spring:
  datasource:
    url: jdbc:sqlite:<path to SQLite database file>
    driver-class-name: org.sqlite.JDBC
```

### Start Service

Start the service by running:

```Shell
java -jar simple-redirect-service.jar --spring.config.location=<path to application.yml>
```

Then you can access and redirect to a URL by visiting `http://hostname:port/resource_id`.

Using Apache HTTP Server or Nginx to reverse proxy the service would improve user experience.

## Build

To build the project, simply run `mvn package`. Skipping tests while building the project is recommended, as tests in this project are not finished yet.