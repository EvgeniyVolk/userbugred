package userbugred_api;

import db_connection.UserQueries;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateTask {
    UserQueries addTaskToDB = new UserQueries();

    @Test
    private void createTask() throws SQLException {
//        CreateResponse response = new CreateResponse();
        CreateRequest request = new CreateRequest();
        Specification specification = new Specification();

        request.setTask_title("Simple sample task");
        request.setTask_description("Test creation a new task");
        request.setEmail_owner("apitest1@rest.com");
        request.setEmail_assign("apitest3@rest.com");

        Specification.response = given()
                .spec(specification.setupSpecification())
                .body(request)
                .when().post(Specification.createTask)
                .then().log().all()
                .assertThat()
                .body("type", equalTo("success"))
                .extract().body().as(CreateResponse.class);

//    Добавить в базу данных task id
        if (Specification.response.getId_task() != null) {
            String insertTaskQuery = "insert into userbugred.tasks (task_title, task_description, email_owner, email_assign, id_task) " +
                    "VALUES (" + "'Simple sample task'" + "," + "'Test creation a new task'" + "," + "'apitest1@rest.com'" + ","
                    + "'apitest3@rest.com'" + "," + "'" + Specification.response.getId_task() + "'" + ")";
            addTaskToDB.dbQuery(insertTaskQuery);
        }
    }
}
