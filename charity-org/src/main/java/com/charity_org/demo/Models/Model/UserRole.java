package com.charity_org.demo.Models.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

@Table(
        name = "user_role",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "roleId"})
)
public class UserRole extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    public UserRole (User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
