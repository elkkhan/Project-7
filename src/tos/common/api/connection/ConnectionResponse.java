package tos.common.api.connection;

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConnectionResponse {

  private final String finalRedirectUrl;
  private final String responseContent;
  private final Integer responseCode;

  ConnectionResponse(
      @Nullable String finalRedirectUrl,
      @NotNull String responseContent,
      @Nullable Integer responseCode) {
    this.finalRedirectUrl = finalRedirectUrl;
    this.responseContent = responseContent;
    this.responseCode = responseCode;
  }

  /**
   * @return final redirect URL in case GET request performs redirection
   */
  @Nullable
  public String getFinalRedirectUrl() {
    return finalRedirectUrl;
  }

  /**
   * @return full content of GET request response
   */
  @NotNull
  public String getResponseContent() {
    return responseContent;
  }

  /**
   * @return full content of GET request response parsed into a List<String> line-by-line
   */
  @NotNull
  public List<String> getResponseContentAsList() {
    return Arrays.asList(responseContent.split("\r\n|\r|\n"));
  }

  /**
   * {@see REST API status code documentation}
   *
   * @return status code returned from the GET request
   */
  @Nullable
  public Integer getResponseCode() {
    return responseCode;
  }
}

