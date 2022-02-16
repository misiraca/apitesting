<!-- GETTING STARTED -->
## Getting Started

Simple example of testing API web services with RestAssured and TestNG framework. 
### Prerequisites
<ul>
        <li>JDK 8</li>
        <li>Maven</li>
        <li>IntelliJ (Recommended)</li>
        <li>Lombok plugin for IntelliJ</li>
  </ul>

### IMPORTANT

There are just a few examples. Please note that this is not complete framework and
there are a lot of things to be upgraded. Not all responses are covered with assertions.

### Test running

To run tests with a "smoke" tag in a single thread, enter <code>mvn test -PSmoke</code><br>
To run tests with a "smoke" tag in a multiple threads, enter <code> mvn test -PParallel</code><br><br>
To run test class VerifyCountriesFiltering enter <code> mvn -Dtest=VerifyCountriesFiltering test</code> <b>Test should fail, because API filtering is not valid</b>
### Report
Report can be found in the <b>target/surefire-reports