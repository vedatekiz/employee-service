package com.demo.microservice.employeeservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee",
        uniqueConstraints = @UniqueConstraint(name = "email_uc"
                , columnNames = "email"))
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_hobbies",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "hobby_id", referencedColumnName = "id")}
    )
    private List<Hobby> hobbies;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
