package ar.com.damian.drinkbros_backend.exception;

/**
 * Esta excepción se lanza cuando ocurre un error inesperado en el servidor, como una
 * excepción no capturada o un error en la lógica de la aplicación. Se utiliza para
 * manejar errores internos del servidor que no están relacionados directamente con
 * la solicitud enviada por el cliente.
 */
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}