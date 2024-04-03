package com.app.user.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should be atleast 2 characters")
    //@JsonProperty("user_name")
    private String name;

    @Past(message = "Birth Date should be not the past")
    //@JsonProperty("birth_date")
    private LocalDate birthDate;

    //single user can have many posts
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
}
