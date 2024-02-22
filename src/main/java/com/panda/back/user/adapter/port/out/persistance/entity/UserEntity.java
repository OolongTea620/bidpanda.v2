package com.panda.back.user.adapter.port.out.persistance.entity;

import com.panda.back.user.domain.User;
import com.panda.back.user.domain.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = "user",
    uniqueConstraints = @UniqueConstraint(columnNames = {"id","email"})
)
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "image_url")
  private String profileImgUrl;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @CreatedDate
  private Long createdAt;

  public static UserEntity fromModel(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.id = user.getId();
    userEntity.email = user.getEmail();
    userEntity.password = user.getPassword();
    userEntity.nickname = user.getNickname();
    userEntity.profileImgUrl = user.getProfileImgUrl();
    userEntity.status = user.getStatus();
    userEntity.createdAt = user.getCreatedAt();
    return userEntity;
  }

  public User toModel() {
    return User.builder()
        .id(id)
        .email(email)
        .password(password)
        .nickname(nickname)
        .profileImgUrl(profileImgUrl)
        .createdAt(createdAt)
        .build();
  }
}