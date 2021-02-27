package org.superhelt.performance.om.responses;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("token_type")
    private final String tokenType;

    @SerializedName("access_token")
    private final String accessToken;

    public TokenResponse(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
