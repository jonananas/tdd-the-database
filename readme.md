# TDD the database

## Rules
* First create a test, then just enough to pass the test
* Try to take baby steps, create the simplest next test possible
* Do not forget to refactor 

Steps

1. Create a Person with id, and name using a factory method.
2. Two persons should only be equal when id matches. There can be two John Doe without them being the same person.
3. Create a PersonRepository interface and a PersonRepositoryJDBC that allows you to store a person using H2.
4. You should be able to find a person by id.
5. You should be able to filter persons by name. If John Doe and Jane Doe exists, search for do should return both. Searching for john should return one.
6. The repository should be able to returned a sorted list on name. Both ascending and descending.
7. You should be able to specify number of returned persons using a maximum limit.
8. You should be able to specify starting person as well, to enable paging.
9. Add gender to a person.
10. You should be able to sort on only name, only gender or both.