package userbugred_api;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;

@Setter
public class CreateRequest {

    public ArrayList<String> company_users;
    public String company_name;
    public String company_type;
    public String email_owner;
    public String task_id;
    public String task_title;
    public String task_description;
    public String email_assign;
    public String name;
    public String email;
    public String password;
    public String name1;
    public String surname1;
    public String fathername1;
    public String hobby;
    public String cat;
    public String dog;
    public String parrot;
    public String cavy;
    public String hamster;
    public String squirrel;
    public String phone;
    public Long inn;
    public String adres;
    public String gender;
    public String birthday;
    public String date_start;
}
