package com.sda.company.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Entity
// este recomandat sa folosesti (name = "") pentru denumirea tabelelor
@Table(name = "company")
public class Company {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "registration_number")
    private Long registrationNumber;

    @Column
    private String email;

    // == Relationships ==
    @OneToMany(mappedBy = "company",
            targetEntity = Employee.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonIgnoreProperties("company")
    private List<Employee> employeeSetCompany;

    //== getters and setters ==
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Employee> getEmployeeSetCompany() {
        return employeeSetCompany;
    }

    public void setEmployeeSetCompany(List<Employee> employeeSetCompany) {
        this.employeeSetCompany = employeeSetCompany;
    }
}
