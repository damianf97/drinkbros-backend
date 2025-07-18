package ar.com.damian.drinkbros_backend.controller.mercado_pago.imp;

import ar.com.damian.drinkbros_backend.controller.mercado_pago.MercadoPagoWebhookControlerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MercadoPagoWebhookController implements MercadoPagoWebhookControlerApi {

    @Override
    public ResponseEntity<String> handleWebhook(Map<String, Object> payload, Map<String, String> headers) {
        // Opcional: imprimir para debug
        System.out.println("Webhook recibido:");
        System.out.println("Headers: " + headers);
        System.out.println("Payload: " + payload);

        // Aquí puedes procesar el evento, por ejemplo:
        String type = (String) payload.get("type"); // payment, plan, subscription, etc.
        String dataId = ((Map<String, Object>) payload.get("data")).get("id").toString();

        // Lógica para manejar el evento según tipo e ID...

        return ResponseEntity.ok("Received");
    }
}
