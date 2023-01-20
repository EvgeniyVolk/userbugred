package userbugred_api;

import db_connection.UserQueries;
import org.testng.annotations.Test;

import java.sql.SQLException;
import static db_connection.SetupJdbcConnection.result;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeleteTask {
    UserQueries deleteTaskRecord = new UserQueries();

    @Test
    private void deleteTask() throws SQLException {
        Specification specification = new Specification();

        CreateRequest createRequest = new CreateRequest();
        createRequest.setEmail_owner("apitest1@rest.com");

        //удаляю с сайта
        String getTaskQuery = "SELECT MAX(id_task) FROM tasks";
        deleteTaskRecord.selectQuery(getTaskQuery);
        createRequest.setTask_id(String.valueOf(result.getInt(1))); //подставить значение из базы данны

        Specification.response = given()
                .spec(specification.setupSpecification())
                .body(createRequest)
                .when().post(Specification.deleteTask)
                .then().log().all()
                .extract().body().as(CreateResponse.class);

        //удаляю из db
        String deleteTaskQuery = "DELETE FROM TASKS WHERE id_task = " + result.getInt(1);
        deleteTaskRecord.dbQuery(deleteTaskQuery);
    }
}
