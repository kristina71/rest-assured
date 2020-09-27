package tests;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.request.CreateUser;
import models.request.RegisterUser;
import models.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReqresInTests {
    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(new AllureRestAssured().setRequestTemplate("request.ftl").setResponseTemplate("response.ftl"));
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @DisplayName("Create user test")
    public void createUserTest(){

        final String name="morpheus";
        final String leader="leader";

        CreateUser data = CreateUser.builder().name(name).leader(leader).build();

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/users/2")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", is(name)).body("leader",is(leader));
    }

    @Test
    @DisplayName("Register user success test")
    public void registerUserSuccessTest(){
        final String email="eve.holt@reqres.in";
        final String password="pistol";

        RegisterUser data = RegisterUser.builder().email(email).password(password).build();

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .log().body()
                .body("token", notNullValue());
    }

    @Test
    @DisplayName("Delete user test")
    public void deleteUserTest(){
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Single resource not found test")
    public void singleResourceNotFoundTest(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void registerUserUnSuccessTest(){
        final String email="sydney@fife";
        final String errorMessage="Missing password";

        RegisterUser data = RegisterUser.builder().email(email).build();

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/register")
                .then()
                .statusCode(400).body("error", is(errorMessage));
    }

    @Test
    public void usersListTest(){
        UsersList response = given()
                .when()
                .queryParam("page", "2")
                .get("/users").getBody().as(UsersList.class);

        List<UserData> usersData = response.getData();
        usersData.stream().forEach(userItem->{
            assertThat(userItem.getAvatar(),notNullValue());
            assertThat(userItem.getEmail(),notNullValue());
            assertThat(userItem.getFirstName(),notNullValue());
            assertThat(userItem.getLastName(),notNullValue());
        });
    }

    @Test
    @DisplayName("Single resource test")
    public void singleResourceTest(){
        final Integer id=2;
        final String name="fuchsia rose";
        final Integer year = 2001;
        final String color="#C74375";
        final String pantone="17-2031";

        SingleResource singleResource = SingleResource.builder().data(
                SingleResourceData.builder()
                        .id(id)
                        .name(name)
                        .year(year)
                        .color(color)
                        .pantoneValue(pantone).build()).ad(SingleResourceAd.builder().build()).build();

        SingleResource response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/unknown/2")
                 .getBody().as(SingleResource.class);

        assertThat(singleResource, is(response));
    }
}
