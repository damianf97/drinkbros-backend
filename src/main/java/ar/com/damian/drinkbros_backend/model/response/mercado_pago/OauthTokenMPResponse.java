package ar.com.damian.drinkbros_backend.model.response.mercado_pago;

import lombok.Data;

@Data
public class OauthTokenMPResponse {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private String scope;
    private Long userId;
    private String refreshToken;
}
