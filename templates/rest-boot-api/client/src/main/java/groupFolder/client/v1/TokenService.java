package ${group}.client.v1;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.FormEncodingBuilder;

import java.io.IOException;

public class TokenService {

  private Gson gson;
  private OkHttpClient httpClient;
  private String baseUrl;

  public TokenService(OkHttpClient httpClient, Gson gson, String baseUrl) {
    this.gson = gson;
    this.httpClient = httpClient;
    this.baseUrl = baseUrl;
  }

  OAuthToken login(String username,
               String password,
               String grantType,
               String scope,
               String clientId,
               String clientSecret) {

    String credential = Credentials.basic(clientId, clientSecret);

    RequestBody formBody = new FormEncodingBuilder()
      .add("username", username)
      .add("password", password)
      .add("grant_type", grantType)
      .add("scope", scope)
      .add("client_id", clientId)
      .add("client_secret", clientSecret)
      .build();

    Request request = new Request.Builder()
      .header("Authorization", credential)
      .url(baseUrl + "/oauth/token")
      .post(formBody)
      .build();

    try {
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful())  {
          throw new ApiException("Cannot login with request data");
        }
        return gson.fromJson(response.body().charStream(), OAuthToken.class);
    } catch(IOException ex) {
        throw new ApiException(ex.getMessage(), null, ex);
    }
  }


}
