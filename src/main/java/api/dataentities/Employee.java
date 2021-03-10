package api.dataentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "employee_name", "employee_salary", "employee_age", "profile_image"})
public class Employee {

    private final int id;
    private final String employee_name;
    private final int employee_salary;
    private final int employee_age;
    private final String profile_image;

    public Employee() {
        this.id = 0;
        this.employee_name = "Carlos";
        this.employee_salary = 0;
        this.employee_age = 0;
        this.profile_image = "";
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("employee_name")
    public String getEmployee_name() {
        return employee_name;
    }

    @JsonProperty("employee_salary")
    public int getEmployee_salary() {
        return employee_salary;
    }

    @JsonProperty("employee_age")
    public int getEmployee_age() {
        return employee_age;
    }

    @JsonProperty("profile_image")
    public String getProfile_image() {
        return profile_image;
    }
}
