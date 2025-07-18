package ar.com.damian.drinkbros_backend.util;

public final class Constants {

    private Constants() {
        //default
    }

    public static final String STRING_DESCRIPTION = "description";

    public static final String STRING_REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_ERROR_TIMESTAMP = "timestamp";
    public static final String CLIENT_ERROR_CODE = "error_code";
    public static final String CLIENT_ERROR_MESSAGE = "message";
    public static final String CLIENT_ERROR_VIOLATED_FIELDS = "violated_fields";
    public static final String CLIENT_ERROR_DESCRIPTION = "description";
    public static final String CLIENT_ERROR_DETAIL = "detail";

    public static final String ERROR_DEVICE_OWNER_MP = "Forbidden Error checking device owner";
    public static final String ERROR_AMOUNT_MP = "message -> amount: Must be greater than or equal to 1500";
    public static final String ERROR_QUEUED_INTENT_MP = "There is already a queued intent for the device";
    public static final String ERROR_CANNOT_CANCEL_ORDER = "cannot_cancel_order";
    public static final String ERROR_ORDER_ALREADY_CANCELED = "order_already_canceled";
    public static final String ERROR_ALREADY_QUEUED_ORDER = "already_queued_order_on_terminal";
    public static final String ERROR_PROPERTY_VALUE = "property_value";

    public static final Character CHAR_S = 'S';
    public static final Character CHAR_N = 'N';
}
