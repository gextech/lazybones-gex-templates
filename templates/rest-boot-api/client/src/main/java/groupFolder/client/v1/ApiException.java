package ${group}.client.v1;

import retrofit.client.Response;

public class ApiException extends RuntimeException {

  private String debugInfo;

  private Response response;

  private int statusCode;

  public ApiException() {
    super();
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, Response response, Throwable cause) {
    super(message, cause);
    this.response = response;
    if(response != null) {
      this.statusCode = response.getStatus();
    }
  }

}
