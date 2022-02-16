package tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static client.requests.Config.REST_COUNTRIES_IN_ENDPOINT;
import static io.restassured.RestAssured.given;

public class VerifyCountriesFiltering extends BaseTest {

    @Test(description = "Verify that API returns all countries that start with united in /name/ path")
    public void verifyListCountriesNameFiltering()
    {
        //Test should fail, because API is not working fine
        String param="united";
        List<String> allCountries =
                given()
                        .get(REST_COUNTRIES_IN_ENDPOINT +"/all")
                        .then()
                        .statusCode(200)
                        .extract().path("name.official");

        List<String> filteredCountries =
                given()
                        .get(REST_COUNTRIES_IN_ENDPOINT +"/name/" + param)
                        .then()
                        .statusCode(200)
                        .extract().path("name.official");

        List<String> local= allCountries.parallelStream().filter(s -> s.toLowerCase().startsWith(param)).collect(Collectors.toList());
        Assertions.assertThat(filteredCountries).isEqualTo(local);
    }

}