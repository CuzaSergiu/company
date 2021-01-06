package com.sda.company.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

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
    private Set<Employee> employeeSetDepartment = new HashSet<>();

    // == Getters and setters ==

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

    public Set<Employee> getEmployeeSetDepartment() {
        return employeeSetDepartment;
    }

    public void setEmployeeSetDepartment(Set<Employee> employeeSetDepartment) {
        this.employeeSetDepartment = employeeSetDepartment;
    }
}
