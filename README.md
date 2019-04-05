# Spring boot project. Star Wars Api
Application shows Star Wars characters compared to others.
You can input a name of character and get the result that contains one character same race that inputted one , and one different race.  Service use external StarWarsAPI (swapi.co) to get information about characters.
## Getting Started

### Prerequisites

In order to run this application you will need

```
Git
JDK 8 or later
Maven 3.6.0 or later
```

### Clone

At first you should clone project using URL

```
https://github.com/kvasnytsyaira/StarWarsSpringBoot.git
```

### Running the tests

After configuration you should run all tests
```
mvn test
```

### Build an executable JAR

You can run the application from the command line using:

```
mvn spring-boot:run
```
Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources with:
```
mvn clean package
```
Then you can run the JAR file with:
```
java -jar target/*.jar
```

## Author

* **Iryna Kvasnytsya** - [GitHub link](https://github.com/kvasnytsyaira)

##Additional Info
In src/main/resources is one more README that explains how application works.

