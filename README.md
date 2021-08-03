# stay.tui.com automation-test
QA technical automation test for stay.tui.com
# pre
JAVA 11+ and maven are needed to build the project. 
# simple usage
The project is built with Maven. On a terminal:
```
mvn clean install
```
By default, it will execute all tests.

# report
As a result an index.html cucumber report will be generated, located at target/generated-report/

# specify scenarios
If a single scenario is needed to be tested, modify the "tag" value in the runnerTest file located at src/test/java/runner.
Example:
tags = "@reception"

# available features

| Feature | Tag |
| :---: | :---: |
| Reception | @reception |
| search - recommended | @hotel |
| search | @destination |
| search actions - Order by price | @price |
| search actions - Order by rating | @rating |
| search actions - Order by stars | @stars |
| Booking | @booking |
