package ar.com.damian.drinkbros_backend.model.request.mercado_pago;

import lombok.Data;

@Data
public class OauthTokenMpRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String grantType;
    private String refreshToken;
    private String redirectUri;
    private boolean testToken;
}
