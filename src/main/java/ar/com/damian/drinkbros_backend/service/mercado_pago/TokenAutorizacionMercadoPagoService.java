package ar.com.damian.drinkbros_backend.service.mercado_pago;

import ar.com.damian.drinkbros_backend.client.MercadoPagoClient;
import ar.com.damian.drinkbros_backend.exception.ResourceNotFoundException;
import ar.com.damian.drinkbros_backend.model.entity.TokenAutorizacionMercadoPago;
import ar.com.damian.drinkbros_backend.model.request.mercado_pago.OauthTokenMpRequest;
import ar.com.damian.drinkbros_backend.model.response.mercado_pago.OauthTokenMPResponse;
import ar.com.damian.drinkbros_backend.repository.TokenAutorizacionMercadoPagoRepository;
import ar.com.damian.drinkbros_backend.service.ClientMercadoPagoRequestService;
import ar.com.damian.drinkbros_backend.util.Constants;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenAutorizacionMercadoPagoService extends ClientMercadoPagoRequestService {

    private final TokenAutorizacionMercadoPagoRepository tokenAutorizacionMercadoPagoRepository;
    private final MercadoPagoClient mercadoPagoClient;

    @Value("${mercadopago.client-secret}")
    private String mercadoPagoClientSecret;

    @Value("${mercadopago.client-id}")
    private String mercadoPagoClientId;

    public TokenAutorizacionMercadoPago obtenerTokenValido(Long idUsuarioMercadoPago) {
        TokenAutorizacionMercadoPago tokenAutorizacionMercadoPago = tokenAutorizacionMercadoPagoRepository.findByIdUsuarioMercadoPago(idUsuarioMercadoPago)
                .orElseThrow(() -> new ResourceNotFoundException(MessageBundle.TOKEN_VENCIDO_MP));

        if (tokenAutorizacionMercadoPago.getFechaExpira().isBefore(LocalDateTime.now())) {
            OauthTokenMpRequest body = new OauthTokenMpRequest();
            body.setClientId(mercadoPagoClientId);
            body.setClientSecret(mercadoPagoClientSecret);
            body.setGrantType(Constants.STRING_REFRESH_TOKEN);
            body.setRefreshToken(tokenAutorizacionMercadoPago.getRefreshToken());

            Call<OauthTokenMPResponse> tokenCall = mercadoPagoClient.oauthToken(body);
            OauthTokenMPResponse response = call(tokenCall);

            LocalDateTime fecha = LocalDateTime.now();
            tokenAutorizacionMercadoPago.setAccessToken(response.getAccessToken());
            tokenAutorizacionMercadoPago.setRefreshToken(response.getRefreshToken());
            tokenAutorizacionMercadoPago.setSegundosExpira(response.getExpiresIn());
            tokenAutorizacionMercadoPago.setFechaRegistro(fecha);
            tokenAutorizacionMercadoPago.setFechaExpira(fecha.plusSeconds(response.getExpiresIn()));
            tokenAutorizacionMercadoPago = tokenAutorizacionMercadoPagoRepository.save(tokenAutorizacionMercadoPago);
        }
        return tokenAutorizacionMercadoPago;
    }
}
