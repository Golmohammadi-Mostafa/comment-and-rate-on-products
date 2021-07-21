package com.egc.shopping.dto;

import com.egc.shopping.domain.Comment;
import com.egc.shopping.domain.Rate;
import com.egc.shopping.domain.Role;
import com.egc.shopping.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private UserStatus status;

    private Set<Role> roles;

    private List<Comment> comments = new ArrayList<>();

    private List<Rate> rates = new ArrayList<>();

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String username, String password, UserStatus status, Set<Role> roles, List<Comment> comments, List<Rate> rates) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.comments = comments;
        this.rates = rates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                ", comments=" + comments +
                ", rates=" + rates +
                '}';
    }
}
