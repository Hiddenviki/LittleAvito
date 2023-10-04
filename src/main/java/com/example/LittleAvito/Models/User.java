package com.example.LittleAvito.Models;

import com.example.LittleAvito.Models.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="name")
    private String name;
    @Column(name="active")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;
    @Column(name="password") //можно еще прописать length = 1000
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType. EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn (name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>() ;
    private LocalDateTime dateofCreation;

    @PrePersist
    private void init(){
        dateofCreation = LocalDateTime.now();
    }

    //security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
