package userbugred_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CreateResponse {

    String type;
    Integer id_task;
    String message;
    String name;
    String email;
    String password;
    Integer id_company;
}
