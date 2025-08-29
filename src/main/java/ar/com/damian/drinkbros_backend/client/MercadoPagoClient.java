package ar.com.damian.drinkbros_backend.client;

import ar.com.damian.drinkbros_backend.model.request.mercado_pago.OauthTokenMpRequest;
import ar.com.damian.drinkbros_backend.model.response.mercado_pago.OauthTokenMPResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface MercadoPagoClient {

    @POST("/oauth/token")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<OauthTokenMPResponse> oauthToken(@Body OauthTokenMpRequest body);
}
