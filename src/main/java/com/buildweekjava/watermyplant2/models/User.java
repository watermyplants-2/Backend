package com.buildweekjava.watermyplant2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"roles", "useremails"})
public class User
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
            unique = true)
    @Email
    private String primaryemail;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Useremail> useremails = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Plant> plants = new ArrayList<>();


    public User()
    {
    }


    public User(
            String username,
            String password,
            String primaryemail)
    {
        setUsername(username);
        setPassword(password);
        this.primaryemail = primaryemail;
    }


    public long getUserid()
    {
        return userid;
    }


    public void setUserid(long userid)
    {
        this.userid = userid;
    }


    public String getUsername()
    {
        return username;
    }


    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }


    public String getPrimaryemail()
    {
        return primaryemail;
    }


    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail.toLowerCase();
    }


    public String getPassword()
    {
        return password;
    }


    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }


    public List<Useremail> getUseremails()
    {
        return useremails;
    }


    public void setUseremails(List<Useremail> useremails)
    {
        this.useremails = useremails;
    }

    public Set<UserRoles> getRoles()
    {
        return roles;
    }

    public List<Plant> getPlants() {return plants; }
    public void setPlants(List<Plant> plant) {this.plants = plant; }

    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }


    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.roles)
        {
            String myRole = "ROLE_" + r.getRole()
                    .getName()
                    .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
