package userbugred_api;

import db_connection.UserQueries;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteUser {
    UserQueries delete = new UserQueries();

    @Test
    private void deleteUser() throws SQLException {
        int users = 3;
        Specification specification = new Specification();
//        CreateRequest request = new CreateRequest();

        for (Integer i = 1; i <= users; i++) {
            Map<String, String> request = new HashMap<>();
//            request.setEmail("apitest" + i.toString() + "@rest.com");
//            request.setPassword("passwordapi" + i.toString());
//            request.setName("ApiUser" + i.toString());

            request.put("name", "ApiUser" + i.toString());
            request.put("email", "apitest" + i.toString() + "@rest.com");
            request.put("password", "passwordapi" + i.toString());

            Response response = given()
                    .spec(specification.setupSpecification())
                    .body(request)
                    .when().post(Specification.deleteUser)
                    .then().log().all()
                    .assertThat()
                    .extract().response();

            String deleteUserQuery =  "delete from users where name = " + "'" + "ApiUser" + i.toString() + "'" ;
            delete.dbQuery(deleteUserQuery);

            assertEquals(200, response.getStatusCode());
        }
    }
}