package api.dataentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"status", "data", "message"})
public class Employees {

    private String status;
    private List<Employee> data;
    private String message;

    public Employees() {
        this.status = "";

        Employee employee = new Employee();
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);

        this.data = employeesList;
        this.message = "message";
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("data")
    public List<Employee> getData() {
        return data;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
