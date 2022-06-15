# User Retention App

![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)

This is a Java Gradle application to analyze an application's user retention as we compute the number of users that use the application for a number of consecutive days. A jar file it's created and process csv files.
## How to build the application

1. Install [Java 15 or plus](https://www.azul.com/downloads/?package=jdk)

2. Install Gradle 7.1

3. Download the [User Retention](https://github.com/jcostamagna/user-Retention) GitHub repository

4. Go to the project's root and run it in your terminal


`./gradlew clean build jar`


## How to run the application

1. After build:

`java -jar build/libs/user-retention-embrace-1.0.0.jar <absolute_path_to_csv_file>`


