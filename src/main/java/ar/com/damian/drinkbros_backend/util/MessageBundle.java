package ar.com.damian.drinkbros_backend.util;

public final class MessageBundle {

    private MessageBundle() {
        //default
    }

    public static final String RESOURCE_NOT_FOUND = "Resource not found.";
    public static final String USER_PASSWORD_INCORRECT = "The username or password is incorrect.";
    public static final String ACCOUNT_LOKECD = "The account is locked.";
    public static final String UNAUTHORIZED = "You are not authorized to access this resource.";
    public static final String JWT_INVALID = "The JWT signature is invalid.";
    public static final String JWT_TOKEN_EXPIRED = "The JWT token has expired.";
    public static final String INTERNAL_ERROR = "Unknown internal server error.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String DRINK_NOT_FOUND = "Drink not found.";
    public static final String WAREHOUSE_NOT_FOUND = "Warehouse not found.";

    public static final String TOKEN_VENCIDO_MP = "Debe sincronizar la cuenta con Mercado Pago nuevamente, su token venci\u00F3.";
    public static final String USUARIO_MP_NO_EXISTE = "El usuario de Mercado Pago no existe.";
    public static final String DISPOSITIVO_MP_NO_EXISTE = "El Dispositivo de Mercado Pago no existe.";
    public static final String ORDEN_PAGO_NO_EXISTE = "La orden de pago no existe.";

    public static final String ERROR_DEVICE_OWNER_MP = "No se pudo verificar el propietario del dispositivo.";
    public static final String ERROR_AMOUNT_MP = "El monto a cobrar debe ser igual o mayor a 15.";
    public static final String ERROR_QUEUED_INTENT_MP = "Ya hay una intención en cola para el dispositivo.";
    public static final String ERROR_NOT_FOUND_INTENT_MP = "No se encontro la intencion de pago, ya debe estar Finalizada o Cancelada.";
    public static final String ERROR_CANNOT_CANCEL_ORDER = "La orden no se puede cancelar. Si ya se encuentra en la terminal, deberás hacerlo desde la misma.";
    public static final String ERROR_ORDER_ALREADY_CANCELED = "La orden ya fue cancelada.";
    public static final String ERROR_ALREADY_QUEUED_ORDER = "Ya hay una orden pendiente para la terminal.";
    public static final String ERROR_PROPERTY_VALUE = "El monto debe ser mayor o igual a 15";
}
