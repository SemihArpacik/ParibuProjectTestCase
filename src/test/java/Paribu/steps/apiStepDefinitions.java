package Paribu.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class apiStepDefinitions {

    private Response response;
    private static String BASE_URL = "https://dummyjson.com";
    public static String accessToken; //
    public static String accessTokens;
    private int firstProductId;
    private String username;
    private String password;

    @Given("The user sends a POST request to {string} with username {string} and password {string}")
    public void sendPostRequest(String endpoint, String username, String password) {
        this.username = username;
        this.password = password;

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();

        System.out.println("➡️ Request: " + requestBody);
        System.out.println("⬅️ Response: " + response.asString());
    }

    @Then("The status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals("❌ Status code beklenenle uyuşmuyor!", expectedStatusCode, actualStatusCode);
        System.out.println("✅ Status code doğrulandı: " + actualStatusCode);
    }

    @Then("The response should contain token equals {string}")
    public void verifyTokenPresence(String expectToken) {
        boolean shouldHaveToken = Boolean.parseBoolean(expectToken);
        String responseBody = response.getBody().asString();

        if (shouldHaveToken) {
            JSONObject jsonResponse = new JSONObject(responseBody);

            // Değişiklik burada 👇
            assertTrue("❌ Response içinde 'accessToken' yok!", jsonResponse.has("accessToken"));

            accessToken = jsonResponse.getString("accessToken");
            Assert.assertFalse("❌ accessToken boş geldi!", accessToken.isEmpty());

            System.out.println("✅ Access token bulundu: " + accessToken);
        } else {
            Assert.assertFalse("❌ accessToken hatalı response içinde olmamalı!", responseBody.contains("accessToken"));
            System.out.println("✅ Token bulunmadı (beklenen durum).");
        }
    }


    @Given("The user successfully logs in.")
    public void theUserSuccessfullyLogsIn() {
        accessTokens = given()

                .contentType("application/json")
                .body("{\"username\":\"emilys\",\"password\":\"emilyspass\"}")
                .when()
                .post("https://dummyjson.com/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken"); // <-- tek satırda accessToken’a set ediliyor

    }



    @Given("The user has a valid access token")
    public void theUserHasAValidAccessToken() {



        System.out.println(" Access token " + accessTokens);
        assertNotNull("❌ Access token mevcut değil, önce login olmalısınız!", accessTokens);
    }

    @When("The user sends a GET request to {string} using the saved access token")
    public void theUserSendsAGETRequestToUsingTheSavedAccessToken(String endpoint) {

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessTokens)
                .get(BASE_URL + endpoint);
        System.out.println("➡️ GET Request sent with access token");
        System.out.println("⬅️ Response: " + response.getBody().asString());
    }

    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals("❌ Status code eşleşmiyor!", expectedStatusCode, response.getStatusCode());
        System.out.println("✅ Status code doğrulandı: " + expectedStatusCode);

    }

    @And("The response should contain a {string} array")
    public void theResponseShouldContainAArray(String arrayName) {

        assertTrue("❌ Response içinde '" + arrayName + "' array yok!",
                response.jsonPath().getList(arrayName).size() >= 0);
        System.out.println("✅ Response içinde '" + arrayName + "' array bulundu");


    }

    @And("The number of products should match the {string} value")
    public void theNumberOfProductsShouldMatchTheValue(String limitField) {

        int limit = response.jsonPath().getInt(limitField);
        int productsSize = response.jsonPath().getList("products").size();
        assertEquals("❌ Ürün sayısı limit ile eşleşmiyor!", limit, productsSize);
        System.out.println("✅ Ürün sayısı limit ile eşleşiyor: " + productsSize);
    }


    @When("The user gets the list of products")
    public void theUserGetsTheListOfProducts() {
        Response response = given()
                .header("Authorization", "Bearer " + accessTokens)
                .when()
                .get("https://dummyjson.com/auth/products")
                .then()
                .statusCode(200)
                .extract().response();

        List<Object> products = response.jsonPath().getList("products");
        assertTrue("Product list should not be empty", products.size() > 0);

        // İlk ürünün ID'sini alıyoruz
        firstProductId = response.jsonPath().getInt("products[0].id");

    }

    @And("The user updates the first product's name to {string}")
    public void theUserUpdatesTheFirstProductSNameTo(String newName) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", newName);

        Response response = given()
                .header("Authorization", "Bearer " + accessTokens)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .put("https://dummyjson.com/auth/products/" + firstProductId)
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals("Product name should be updated", newName, response.jsonPath().getString("title"));
    }

    @And("The user deletes the updated product")
    public void theUserDeletesTheUpdatedProduct() {

        given()
                .header("Authorization", "Bearer " + accessTokens)
                .when()
                .delete("https://dummyjson.com/auth/products/" + firstProductId)
                .then()
                .statusCode(200);

    }

    @Then("The deleted product should not exist in the product list")
    public void theDeletedProductShouldNotExistInTheProductList() {

        Response response = given()
                .header("Authorization", "Bearer " + accessTokens)
                .when()
                .get("https://dummyjson.com/auth/products")
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productIds = response.jsonPath().getList("products.id");
        assertFalse("Deleted product should not exist", productIds.contains(firstProductId));
    }
}
