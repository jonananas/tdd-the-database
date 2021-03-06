# TDD the database

## Installation
Import as a maven project into your favorite IDE and you should be up and running!
You need JDK1.8 to build. JDK9 DOES NOT WORK! If you are using JAVA_HOME, make sure it is pointing at a JDK1.8. 

## Rules
* Take baby steps:
* Create the simplest test possible.
* Then the minimal implementation to pass the test.
* Refactor.
* Refactor test code as well!

## Before starting
The files and structure below are already created for you. Before starting you should have a look at each of them to familiarize yourself.

<pre>
── create_db.sql                                        This is the database specification, you will edit it!
├── pom.xml                                             Project specification
├── readme.md                                           This file
├── src
│   ├── main
│   │   └── java
│   │       └── se
│   │           └── jonananas
│   │               └── tdd
│   │                   ├── JDBCRunner.java             Helper class for running queries. Needs no edits.
│   │                   ├── Person.java                 Minimal implementation, you will edit it!
│   │                   ├── PersonRepository.java       Minimal implementation, you will edit it!
│   │                   ├── PersonRepositoryJDBC.java   Minimal implementation, you will edit it!
│                       └── SQLFunction.java            Interface used to specify JDBC Runner. Needs no edits.
│   └── test
│       └── java
│           └── se
│               └── jonananas
│                   └── tdd
│                       ├── H2TestDataSource.java           Sets up a H2 in-memory database. Needs no edits.
│                       ├── JDBCRunnerTest.java             Tests the JDBC Runner. Needs no edits.
│                       ├── PersonRepositoryJDBCTest.java   Minimal implementation, you will edit it!
│                       └── PersonTest.java                 Minimal implementation, you will edit it!
</pre>

## The kata
Steps marked DONE are implemented already to give you a head start. Feel free to remove all classes if you think it's to easy!

1. Two persons should only be equal when id matches. There can be two John Doe without them being the same person. DONE!
2. Create a PersonRepository interface and a PersonRepositoryJDBC that allows you to store a person using H2. DONE!
3. You should be able to find a person by id. DONE!
4. Add name to Person, updating the factory method. (Do not update repository just yet - take baby steps!) 
5. A persons name should be stored in database. Will force you to update schema!
   NOTE: Step 1 made person.equals only check for id, so you cannot use Person.equals
5. You should be able to filter persons by name. If John Doe and Jane Doe exists, filtering for "do" should return both. Filtering for john should return one.
6. The repository should be able to return a sorted list on name. Both ascending and descending.
7. You should be able to specify number of returned persons using a maximum limit.
8. You should be able to specify starting person as well, to enable paging.
9. Add gender to a person.
10. You should be able to sort on only name, only gender or both.

## Branches/Implementations
The master branch contains both JPA, JDBC and an in-memory implementation!
If you only want to perform the kata on one at a time, just add an @Ignore annotation to the corresponding test class that you want to ignore.
You can also delete code of course!

There is also a git branch called jdbc-only that you can checkout.

## Ending notes
- You might recognize that patterns in this kata comes from Domain Driven Design. If you do not know what DDD is, [read up on it](http://www.amazon.com/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215)! It takes time, but is worth it! 
- All classes are in same package for convenience. They really shouldn't be in a real project.
- RuntimeException is a good thing, but even better is defining your own and throw those for clarity.
- We are using UUID as ID for Person, but we could as well use an alternative, like a database sequenced id.
- Tools like [LiquiBase](http://www.liquibase.org/) and [Flyway](https://flywaydb.org/getstarted/how) are great for evolving your database together with your code.

## TODO
- Support Java 9