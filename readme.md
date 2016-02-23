# TDD the database

## Rules
* First create a test, then just enough to pass the test
* Try to take baby steps, create the simplest next test possible
* Do not forget to refactor

## Before starting
The files below are already created for you to give you a jumping start. Before starting you should have a look at each of them to familiarize yourself.

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
│   │                   ├── JDBCRunner.java             Helper class for running querys.
│   │                   ├── Person.java                 Minimal implementation, you will edit it!
│   │                   ├── PersonRepository.java       Minimal implementation, you will edit it!
│   │                   ├── PersonRepositoryJDBC.java   Minimal implementation, you will edit it!
│                       └── SQLFunction.java            Interface used to specify JDBC Runner.
│   └── test
│       └── java
│           └── se
│               └── jonananas
│                   └── tdd
│                       ├── H2TestDataSource.java           Sets up a H2 in-memory database
│                       ├── JDBCRunnerTest.java             Tests the JDBC Runner
│                       ├── PersonRepositoryJDBCTest.java   Minimal implementation, you will edit it!
│                       └── PersonTest.java                 Minimal implementation, you will edit it!
</pre>

## The kata
Steps marked DONE are done to give you a head start. You should not edit 

1. Add name to Person, updating the factory method.
2. Two persons should only be equal when id matches. There can be two John Doe without them being the same person.
3. Create a PersonRepository interface and a PersonRepositoryJDBC that allows you to store a person using H2. DONE!
4. You should be able to find a person by id. DONE!
5. You should be able to filter persons by name. If John Doe and Jane Doe exists, search for do should return both. Searching for john should return one.
6. The repository should be able to returned a sorted list on name. Both ascending and descending.
7. You should be able to specify number of returned persons using a maximum limit.
8. You should be able to specify starting person as well, to enable paging.
9. Add gender to a person.
10. You should be able to sort on only name, only gender or both.

## Ending notes
- As you might recognize, this kata is DDD-influenced. If you do not know what DDD is, you should do yourself a favor and look it up.
- All classes are in same package for convenience. They really shouldn't be.
- It's ok to throw RuntimException, but you should define specific exceptions.
- We are using UUID as ID for Person, but we could as well use an alternative, like a database sequenced id.
