package org.mbonilla.wallet.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    public static final long ROLE_ADMIN_ID = 1L;

    public static final long ROLE_USER_ID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
