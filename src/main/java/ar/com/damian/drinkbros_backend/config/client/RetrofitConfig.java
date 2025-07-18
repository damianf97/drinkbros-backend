package ar.com.damian.drinkbros_backend.config.client;

import ar.com.damian.drinkbros_backend.client.MercadoPagoClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class RetrofitConfig {

    @Value("${mercadopago.connect.timeout}")
    private Integer mercadopagoConnectTimeout;
    @Value("${mercadopago.read.timeout}")
    private Integer mercadoPagoReadTimeout;

    @Value("${mercadoPago.url}")
    private String mercadoPagoUrl;

    @Bean
    public MercadoPagoClient mercadoPagoClient(ObjectMapper objectMapper) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(mercadoPagoReadTimeout, TimeUnit.SECONDS)
                .connectTimeout(mercadopagoConnectTimeout, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        final OkHttpClient client = builder.build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(mercadoPagoUrl).client(client)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper)).build();

        return retrofit.create(MercadoPagoClient.class);
    }
}
