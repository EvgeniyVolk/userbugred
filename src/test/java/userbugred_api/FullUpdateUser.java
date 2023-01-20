package userbugred_api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class FullUpdateUser {
    String name = "ApiUser3";
    String name1 = "Billy";
    String surname1 = "surname1";
    String fathername1 = "Michael";
    String email = "apitest3@rest.com";
    String hobby = "Python";
    String cat = "cat";
    String dog = "dog";
    String parrot = "parrot";
    String cavy = "cavy";
    String hamser = "hamster";
    String squirrel = "squirrel";
    String phone = "5(43)12-342-65";
    Long inn = 123456789012L;
    String adres = "homeless";
    String gender = "m";
    String birthday = "15.07.1983";
    String date_start = "1.01.2023";

    @Test
    public void changeUser() {
        Specification specification = new Specification();
        CreateRequest request = new CreateRequest();

        request.setName(name);
        request.setName1(name1);
        request.setSurname1(surname1);
        request.setFathername1(fathername1);
        request.setEmail(email);
        request.setHobby(hobby);
        request.setCat(cat);
        request.setDog(dog);
        request.setParrot(parrot);
        request.setCavy(cavy);
        request.setHamster(hamser);
        request.setSquirrel(squirrel);
        request.setPhone(phone);
        request.setInn(inn);
        request.setAdres(adres);
        request.setGender(gender);
        request.setBirthday(birthday);
        request.setDate_start(date_start);

        Response response = given()
                .spec(specification.setupSpecification())
                .body(request)
                .when().post(Specification.fullupdateuser)
                .then().log().all()
                .assertThat()
                .extract().response();

        assertEquals(200, response.getStatusCode());
    }
}
