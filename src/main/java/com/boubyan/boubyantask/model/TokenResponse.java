package com.boubyan.boubyantask.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {

    String accessToken;
    Long expiresIn;
    String tokenType;
}
