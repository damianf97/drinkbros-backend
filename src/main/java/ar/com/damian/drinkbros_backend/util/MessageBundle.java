package ar.com.damian.drinkbros_backend.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageBundle {

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
}
