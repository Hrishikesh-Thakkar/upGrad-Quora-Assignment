package com.upgrad.quora.service.entity;

import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@NamedQueries(
        {
                @NamedQuery(name = "userByUuid", query = "select u from UsersEntity u where u.uuid = :uuid"),
                @NamedQuery(name = "userByEmail", query = "select u from UsersEntity u where u.email =:email"),
                @NamedQuery(name = "userByUsername", query = "select u from UsersEntity u where u.username =:username")
        }
)


public class UsersEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 200)
    private String uuid;

    @Column(name = "EMAIL")
    @NotNull
    @Size(max = 200)
    private String email;

    @ToStringExclude
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRSTNAME")
    @NotNull
    @Size(max = 30)
    private String firstName;

    @Column(name = "LASTNAME")
    @NotNull
    @Size(max = 30)
    private String lastName;

    @Column(name = "USERNAME")
    @NotNull
    @Size(max = 30)
    private String username;

    @Column(name = "CONTACTNUMBER")
    @NotNull
    @Size(max = 30)
    private String contactNumber;

    @Column(name = "COUNTRY")
    @NotNull
    @Size(max = 30)
    private String country;

    @Column(name = "ABOUTME")
    @NotNull
    @Size(max = 30)
    private String aboutMe;

    @Column(name = "SALT")
    @NotNull
    @Size(max = 200)
    @ToStringExclude
    private String salt;

    @Column(name = "ROLE")
    @NotNull
    @Size(max = 30)
    private String role;

    @Column(name = "DOB")
    @NotNull
    @Size(max = 30)
    private String dob;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<AnswerEntity> answerEntities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<QuestionEntity> questionEntities = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<AnswerEntity> getAnswerEntities() {
        return answerEntities;
    }

    public void setAnswerEntities(List<AnswerEntity> answerEntities) {
        this.answerEntities = answerEntities;
    }

    public List<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }
}
