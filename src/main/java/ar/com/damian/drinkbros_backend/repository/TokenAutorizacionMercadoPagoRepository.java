package ar.com.damian.drinkbros_backend.repository;

import ar.com.damian.drinkbros_backend.model.entity.TokenAutorizacionMercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenAutorizacionMercadoPagoRepository extends JpaRepository<TokenAutorizacionMercadoPago, Long> {

    Optional<TokenAutorizacionMercadoPago> findByIdUsuarioMercadoPago(Long idUsuarioMercadoPago);
}
