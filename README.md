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

## Use case example

In the following example, we track the activity of 5 users.
```
1609459200,1
1609459200,2
1609459200,3
1609459200,4
1609459260,1
1609545600,1
1609545600,3
1609632000,1
1609632000,2
1609632000,3
1609718400,1
1609718400,2
1609804800,1
1609804800,5
```

The previous input file should print the following output to stdout:

```
1,2,0,1,0,1,0,0,0,0,0,0,0,0,0
2,0,0,0,0,0,0,0,0,0,0,0,0,0,0
3,0,1,0,0,0,0,0,0,0,0,0,0,0,0
4,0,0,0,0,0,0,0,0,0,0,0,0,0,0
5,1,0,0,0,0,0,0,0,0,0,0,0,0,0
6,0,0,0,0,0,0,0,0,0,0,0,0,0,0
7,0,0,0,0,0,0,0,0,0,0,0,0,0,0
8,0,0,0,0,0,0,0,0,0,0,0,0,0,0
9,0,0,0,0,0,0,0,0,0,0,0,0,0,0
10,0,0,0,0,0,0,0,0,0,0,0,0,0,0
11,0,0,0,0,0,0,0,0,0,0,0,0,0,0
12,0,0,0,0,0,0,0,0,0,0,0,0,0,0
13,0,0,0,0,0,0,0,0,0,0,0,0,0,0
14,0,0,0,0,0,0,0,0,0,0,0,0,0,0
```

Four users began streaks on day 1: Users 2 and 4 had a one day streak, User 3 had a
three day streak, and User 1 had a five day streak. That means that the first line
of the output should be

```
1,2,0,1,0,1,0,0,0,0,0,0,0,0,0
```

No users began a streak on day 2, so the second line in the output should be

```
2,0,0,0,0,0,0,0,0,0,0,0,0,0,0
```

User 2 started a two day streak on day 3, and they were the only user that started
a streak for that day, yielding the following line:

```
3,0,1,0,0,0,0,0,0,0,0,0,0,0,0
```

No users began a streak on day 4, so the fourth line is

```
4,0,0,0,0,0,0,0,0,0,0,0,0,0,0
```

User 5 began a single day streak on day 5, so the fifth line is

```
5,1,0,0,0,0,0,0,0,0,0,0,0,0,0
```

There was no further activity afterwards, so the rest of the output has zeroes.
