package com.chat.chat_server.entity;

import java.util.Date;
import com.chat.chat_server.enum_constant.Gender;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends  BaseEntity {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private Role role;

}
