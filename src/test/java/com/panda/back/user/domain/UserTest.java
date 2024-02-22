package com.panda.back.user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.panda.back.common.exception.user.UnAuthorizedUserException;
import com.panda.back.user.application.port.in.dto.CreateUserDto;
import com.panda.back.user.application.port.in.dto.UpdateUserDto;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void User는_CreateUserDto로_생성_할_수_있다() {
    //given
    CreateUserDto createUserDto = CreateUserDto.builder()
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .build();

    //when
    User user = User.from(createUserDto);

    //then
    assertThat(user.getId()).isNull();
    assertThat(user.getEmail()).isEqualTo("test1234@test.com");
    assertThat(user.getPassword()).isEqualTo("this is test pass");
    assertThat(user.getNickname()).isEqualTo("test_nick");
    assertThat(user.getProfileImgUrl()).isNull();
    assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    assertThat(user.getRole()).isEqualTo(UserRole.NORMAL);
    assertThat(user.getCreatedAt()).isNull();
  }

  @Test
  void ACTIVE_유저의_정보는_UpdateUserDto로_수정_할_수_있다() {
    //given
    User user = User.builder()
        .id(1L)
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .profileImgUrl("this_is_image_url")
        .status(UserStatus.ACTIVE)
        .role(UserRole.NORMAL)
        .createdAt(123456789L)
        .build();

    UpdateUserDto updateUserDto = UpdateUserDto.builder()
        .profileImgUrl("changed_url")
        .nickname("changed_nick")
        .build();

    //when
    user = user.modify(updateUserDto);

    //then
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getEmail()).isEqualTo("test1234@test.com");
    assertThat(user.getPassword()).isEqualTo("this is test pass");
    assertThat(user.getNickname()).isEqualTo("changed_nick");
    assertThat(user.getProfileImgUrl()).isEqualTo("changed_url");
    assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    assertThat(user.getRole()).isEqualTo(UserRole.NORMAL);
    assertThat(user.getCreatedAt()).isEqualTo(123456789);
  }

  @Test
  void INACTIVE_유저의_정보는_UpdateUserDto로_수정_불가능하다() {
    //given
    User user = User.builder()
        .id(1L)
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .profileImgUrl("this_is_image_url")
        .status(UserStatus.INACTIVE)
        .role(UserRole.NORMAL)
        .createdAt(123456789L)
        .build();

    UpdateUserDto updateUserDto = UpdateUserDto.builder()
        .profileImgUrl("changed_url")
        .nickname("changed_nick")
        .build();

    //when
    //then
    assertThatThrownBy(() -> user.modify(updateUserDto))
        .isInstanceOf(UnAuthorizedUserException.class);
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getEmail()).isEqualTo("test1234@test.com");
    assertThat(user.getPassword()).isEqualTo("this is test pass");
    assertThat(user.getNickname()).isEqualTo("test_nick");
    assertThat(user.getProfileImgUrl()).isEqualTo("this_is_image_url");
    assertThat(user.getStatus()).isEqualTo(UserStatus.INACTIVE);
    assertThat(user.getRole()).isEqualTo(UserRole.NORMAL);
    assertThat(user.getCreatedAt()).isEqualTo(123456789);
  }

  @Test
  void ACTIVE_상태의_유저는_이메일이_동일하면_삭제가능하다() {
    //given
    User user = User.builder()
        .id(1L)
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .profileImgUrl("this_is_image_url")
        .status(UserStatus.ACTIVE)
        .createdAt(123456789L)
        .build();
    //when
    user = user.delete("test1234@test.com");

    //then
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getEmail()).isEqualTo("test1234@test.com");
    assertThat(user.getPassword()).isEqualTo("this is test pass");
    assertThat(user.getNickname()).isEqualTo("test_nick");
    assertThat(user.getProfileImgUrl()).isEqualTo("this_is_image_url");
    assertThat(user.getStatus()).isEqualTo(UserStatus.INACTIVE);
    assertThat(user.getCreatedAt()).isEqualTo(123456789);
  }

  @Test
  void ACTIVE_상태의_유저는_이메일이_동일하지않으면_권한이_없어_삭제_불가능하다() {
    //given
    User user = User.builder()
        .id(1L)
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .profileImgUrl("this_is_image_url")
        .status(UserStatus.ACTIVE)
        .createdAt(123456789L)
        .build();

    //when
    //then
    assertThatThrownBy(() -> user.delete("diff1234@test.com"))
        .isInstanceOf(UnAuthorizedUserException.class);
    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getEmail()).isEqualTo("test1234@test.com");
    assertThat(user.getPassword()).isEqualTo("this is test pass");
    assertThat(user.getNickname()).isEqualTo("test_nick");
    assertThat(user.getProfileImgUrl()).isEqualTo("this_is_image_url");
    assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    assertThat(user.getCreatedAt()).isEqualTo(123456789);

  }

  @Test
  void INACTIVE_상태의_유저는_삭제불가능하다() {
    //given
    User user = User.builder()
        .id(1L)
        .email("test1234@test.com")
        .password("this is test pass")
        .nickname("test_nick")
        .profileImgUrl("this_is_image_url")
        .status(UserStatus.INACTIVE)
        .createdAt(123456789L)
        .build();

    //when
    //then
    assertThatThrownBy(() -> user.delete("test1234@test.com"))
        .isInstanceOf(UnAuthorizedUserException.class);
    assertThat(user.getStatus()).isEqualTo(UserStatus.INACTIVE);
  }

}