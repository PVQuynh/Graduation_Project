package com.example.hust_learning_server.entity;

import com.example.hust_learning_server.constant.enum_constant.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity implements UserDetails {

    @Email(message = "Email isn't valid")
    @Column(unique = true,nullable = false)
    private  String email;

    private String password;

    @Column(nullable = false)
    private  String name;

    private String address;

    private  String phoneNumber;

    private  String avatarLocation;

    private  boolean isOauth2;

    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isApproved;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "code")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Định nghĩa các authority của ROLE đó khi sử dụng hasAuthority
        // Mặc định nếu không phần quyền hạn cụ thể thì cứ phân quyền theo role
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(role.getCode().toUpperCase()));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
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
        return true;
    }
}
