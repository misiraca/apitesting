package tests;

import client.requests.ClientRequest;
import models.requests.user.User;
import models.requests.users.Datum;
import models.requests.users.Users;
import models.responses.user.UserResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static client.requests.Config.REQRES_IN_ENDPOINT;
import static client.requests.Config.USERS_PATH;

public class TestExample extends BaseTest{

    @Test(groups = {"smoke"})
    public void getListUsersSoftAssertionExample() {
        Users expectedUsersDataResponse =
                Users.builder().
                        page(2).perPage(6).total(12).totalPages(2).
                        build();
        Datum expectedDatumDataResponse =
                Datum.builder().
                        id(7).email("michael.lawson@reqres.in").firstName("Michael").lastName("Lawson").avatar("https://reqres.in/img/faces/7-image.jpg").
                        build();


        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "2");
        Users users = new ClientRequest().createGetRequest(REQRES_IN_ENDPOINT + USERS_PATH, queryParams, Users.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(users.page).as("page").isEqualTo(expectedUsersDataResponse.page);
        softAssertions.assertThat(users.perPage).as("perPage").isEqualTo(expectedUsersDataResponse.perPage);
        softAssertions.assertThat(users.total).as("total").isGreaterThanOrEqualTo(expectedUsersDataResponse.total);
        softAssertions.assertThat(users.totalPages).as("totalPages").isEqualTo(expectedUsersDataResponse.totalPages);

        softAssertions.assertThat(users.data.get(0).id).as("data[0]").isEqualTo(expectedDatumDataResponse.id);
        softAssertions.assertThat(users.data.stream().anyMatch((s) -> s.firstName.startsWith("M"))).isTrue();
        softAssertions.assertAll();

    }

    @Test(groups = {"smoke"})
    public void postRequestExampleCreateUser() {
        User userRequestBody = User.builder().
                name("Dragutin").
                job("QA").
                build();

        UserResponse userResponse = new ClientRequest().createPostRequest(REQRES_IN_ENDPOINT + USERS_PATH, userRequestBody, UserResponse.class);

        Assertions.assertThat(userResponse.name).isEqualTo("Dragutin");
        Assertions.assertThat(userResponse.job).isEqualTo("QA");
        System.out.println("User created");
    }

    @Test(groups = {"smoke"}, dependsOnMethods = {"postRequestExampleCreateUser"})
    public void patchRequestExample() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "2");
        User userRequestBody = User.builder().
                name("John").
                build();
        UserResponse userResponse = new ClientRequest().createPatchRequest(REQRES_IN_ENDPOINT + USERS_PATH, userRequestBody, queryParams, UserResponse.class);

        Assertions.assertThat(userResponse.name).isEqualTo("John");
        Assertions.assertThat(userResponse.job).isEqualTo("QA");// will throw null because reqres.in doesn't store the data

    }


    @Test(groups = {"smoke"})
    public void deleteUserRequest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "2");

        new ClientRequest().createDeleteRequest(REQRES_IN_ENDPOINT + USERS_PATH, queryParams, 200);
    }

    @Test(groups = {"smoke"})
    public void putRequestExample() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "2");
        User userRequestBody = User.builder().
                name("Dragutin").
                job("QA engineer").
                build();
        UserResponse userResponse = new ClientRequest().createPutRequest(REQRES_IN_ENDPOINT + USERS_PATH, userRequestBody, queryParams, UserResponse.class);
        System.out.println(userResponse.name);
    }
}
