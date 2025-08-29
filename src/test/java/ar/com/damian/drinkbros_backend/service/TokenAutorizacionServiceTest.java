package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.client.MercadoPagoClient;
import ar.com.damian.drinkbros_backend.model.entity.TokenAutorizacionMercadoPago;
import ar.com.damian.drinkbros_backend.model.request.mercado_pago.OauthTokenMpRequest;
import ar.com.damian.drinkbros_backend.model.response.mercado_pago.OauthTokenMPResponse;
import ar.com.damian.drinkbros_backend.repository.TokenAutorizacionMercadoPagoRepository;
import ar.com.damian.drinkbros_backend.service.mercado_pago.TokenAutorizacionMercadoPagoService;
import ar.com.damian.drinkbros_backend.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

class TokenAutorizacionServiceTest {

    @InjectMocks
    private TokenAutorizacionMercadoPagoService systemUnderTest;

    @Mock
    private TokenAutorizacionMercadoPagoRepository tokenAutorizacionMercadoPagoRepositoryMock;

    @Mock
    private MercadoPagoClient mercadoPagoClientMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(systemUnderTest, "mercadoPagoClientId", "5391999996149444");
        ReflectionTestUtils.setField(systemUnderTest, "mercadoPagoClientSecret", "secret");
    }

    @Test
    void obtenerTokenValidoRenovando() throws IOException {
        LocalDateTime fechActual = LocalDateTime.now();

        TokenAutorizacionMercadoPago vencido = new TokenAutorizacionMercadoPago();
        vencido.setIdTokenAutorizacionMercadoPago(56L);
        vencido.setIdUsuarioMercadoPago(7L);
        vencido.setFechaExpira(fechActual.minusDays(2));
        vencido.setRefreshToken("refreshToken");
        when(tokenAutorizacionMercadoPagoRepositoryMock.findByIdUsuarioMercadoPago(7L)).thenReturn(Optional.of(vencido));

        OauthTokenMpRequest body = new OauthTokenMpRequest();
        body.setClientId("5391999996149444");
        body.setClientSecret("secret");
        body.setGrantType(Constants.STRING_REFRESH_TOKEN);
        body.setRefreshToken("refreshToken");

        Call<OauthTokenMPResponse> callToken = mock(Call.class);
        when(mercadoPagoClientMock.oauthToken(refEq(body))).thenReturn(callToken);

        Response<OauthTokenMPResponse> responseToken = mock(Response.class);
        when(callToken.execute()).thenReturn(responseToken);

        when(responseToken.isSuccessful()).thenReturn(true);

        OauthTokenMPResponse response1 = new OauthTokenMPResponse();
        response1.setAccessToken("accessToken1");
        response1.setRefreshToken("refreshToken1");
        response1.setExpiresIn(50L);
        when(responseToken.body()).thenReturn(response1);

        TokenAutorizacionMercadoPago renovado = new TokenAutorizacionMercadoPago();
        renovado.setIdTokenAutorizacionMercadoPago(56L);
        renovado.setIdUsuarioMercadoPago(7L);
        renovado.setAccessToken("accessToken1");
        renovado.setRefreshToken("refreshToken1");
        renovado.setSegundosExpira(50L);
        when(tokenAutorizacionMercadoPagoRepositoryMock.save(refEq(renovado, "fechaRegistro", "fechaExpira"))).thenReturn(renovado);

        TokenAutorizacionMercadoPago resultado = systemUnderTest.obtenerTokenValido(7L);

        assertNotNull(resultado);
        assertEquals("accessToken1", resultado.getAccessToken());

        verify(tokenAutorizacionMercadoPagoRepositoryMock).save(refEq(renovado, "fechaRegistro", "fechaExpira"));
    }
}
