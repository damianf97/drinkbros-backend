package ar.com.damian.drinkbros_backend.client;

import ar.com.damian.drinkbros_backend.model.request.mercado_pago.OauthTokenMpRequest;
import ar.com.damian.drinkbros_backend.model.response.mercado_pago.OauthTokenMPResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface MercadoPagoClient {

    @POST("/oauth/token")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Call<OauthTokenMPResponse> oauthToken(@Body OauthTokenMpRequest body);

//    @POST("/v1/orders")
//    @Headers({"Accept: application/json", "Content-Type: application/json"})
//    Call<TmpResponseOrder> crearOrdenPoint(@Header("Authorization") String accessToken, @Header("X-Idempotency-Key") String idempotency, @Body TmpBodyOrder body);
//
//    @POST("/v1/orders/{order_id}/cancel")
//    @Headers({"Accept: application/json", "Content-Type: application/json"})
//    Call<TmpResponseOrder> cancelarOrdenPoint(@Header("Authorization") String accessToken, @Header("X-Idempotency-Key") String idempotency, @Path("order_id") String orderId);
//
//    @GET("/v1/orders/{order_id}")
//    @Headers({"Accept: application/json", "Content-Type: application/json"})
//    Call<TmpResponseOrder> buscarOrden(@Header("Authorization") String accessToken, @Path("order_id") String orderId);
}
