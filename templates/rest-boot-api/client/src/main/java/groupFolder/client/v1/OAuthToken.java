package ${group}.client.v1;

public class OAuthToken {
  private String access_token;
  private String token_type;
  private String refresh_token;
  private Long expires_in;
  private String scope;

  public String getAccessToken() {
    return access_token;
  }

  public String getTokenType() {
    return token_type;
  }

  public String getRefreshToken() {
    return refresh_token;
  }

  public Long getExpiresIn() {
    return expires_in;
  }

  public String getScope() {
    return scope;
  }

}
