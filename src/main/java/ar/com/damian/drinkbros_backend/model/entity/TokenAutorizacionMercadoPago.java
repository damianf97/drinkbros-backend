package ar.com.damian.drinkbros_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token_autorizacion_mercado_pago")
public class TokenAutorizacionMercadoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token_autorizacion_mercado_pago")
    private Long idTokenAutorizacionMercadoPago;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "id_usuario_mercado_pago")
    private Long idUsuarioMercadoPago;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "segundos_expira")
    private Long segundosExpira;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_expira")
    private LocalDateTime fechaExpira;

}
