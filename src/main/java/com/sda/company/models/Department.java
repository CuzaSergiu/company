package com.sda.company.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    //== Relationships ==
    @OneToMany(mappedBy = "department",
            targetEntity = Employee.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonIgnoreProperties("department")
    private List<Employee> employeeSetDepartment;

    // == getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeSetDepartment() {
        return employeeSetDepartment;
    }

    public void setEmployeeSetDepartment(List<Employee> employeeSetDepartment) {
        this.employeeSetDepartment = employeeSetDepartment;
    }
}
