package com.springblog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoProfile {

  @JsonProperty("id")
  public Long id;
  @JsonProperty("connected_at")
  public String connectedAt;
  @JsonProperty("properties")
  public Properties properties;
  @JsonProperty("kakao_account")
  public KakaoAccount kakaoAccount;

  @Data
  public static class KakaoAccount {

    @JsonProperty("profile_nickname_needs_agreement")
    public Boolean profileNicknameNeedsAgreement;
    @JsonProperty("profile")
    public Profile profile;
    @JsonProperty("has_email")
    public Boolean hasEmail;
    @JsonProperty("email_needs_agreement")
    public Boolean emailNeedsAgreement;
    @JsonProperty("is_email_valid")
    public Boolean isEmailValid;
    @JsonProperty("is_email_verified")
    public Boolean isEmailVerified;
    @JsonProperty("email")
    public String email;

  }

  @Data
  public static class Profile {

    @JsonProperty("nickname")
    public String nickname;

  }

  @Data
  public static  class Properties {

    @JsonProperty("nickname")
    public String nickname;

  }
}


