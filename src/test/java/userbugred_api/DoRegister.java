package userbugred_api;

import db_connection.UserQueries;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DoRegister {

    UserQueries insert = new UserQueries();

    @Test
    private void doRegister() throws SQLException {
        SoftAssertions softAssertions = new SoftAssertions();
        Specification specification = new Specification();
        CreateRequest request = new CreateRequest();
        int users = 3;

        try {
            for (Integer i = 1; i <= users; i++) {

                request.setName("ApiUser" + i.toString());
                request.setEmail("apitest" + i.toString() + "@rest.com");
                request.setPassword("passwordapi" + i.toString());

                CreateResponse response = given()
                        .spec(specification.setupSpecification())
                        .body(request)
                        .when().post(Specification.doRegester)
                        .then().log().all()
                        .assertThat()
                        .extract().body().as(CreateResponse.class);

                if (response.getMessage() != null) {
                    softAssertions.fail("Error: user " + "ApiUser" + i.toString() + " was not created.");
                }

                if (response.getName() != null) {
                    String insertUserQuery =  "insert into userbugred.users (name, email, password) " +
                            "VALUES (" + "'" + response.getName() + "'" + "," + "'" + response.getEmail() +
                            "'" + "," + "'" + "passwordapi" + i.toString() + "'" + ")";
//                    insert.dbQuery(insertUserQuery, response.getName(), response.getEmail(), "passwordapi" + i.toString());
                    insert.dbQuery(insertUserQuery);

                }
            }
            softAssertions.assertAll();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}