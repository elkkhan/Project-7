package tos.common.api.connection;

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ConnectionResponse {

    private final String finalRedirectUrl;
    private final String responseContent;
    private final Integer responseCode;

    ConnectionResponse(@Nullable String finalRedirectUrl,
        @Nullable String responseContent,
        @Nullable Integer responseCode) {
        this.finalRedirectUrl = finalRedirectUrl;
        this.responseContent = responseContent;
        this.responseCode = responseCode;
    }


    @Nullable
    public String getFinalRedirectUrl() {
        return finalRedirectUrl;
    }

    @Nullable
    public String getResponseContent() {
        return responseContent;
    }

    @Nullable
    public List<String> getResponseContentAsList() {
        if (responseContent == null) {
            return null;
        }
        return Arrays.asList(responseContent.split("\r\n|\r|\n"));
    }

    @Nullable
    public Integer getResponseCode() {
        return responseCode;
    }
}

