package ar.com.damian.drinkbros_backend.service;


import ar.com.damian.drinkbros_backend.exception.ClientException;
import ar.com.damian.drinkbros_backend.exception.ErrorDetail;
import ar.com.damian.drinkbros_backend.exception.InternalServerErrorException;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import ar.com.damian.drinkbros_backend.util.Constants;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;

public class ClientMercadoPagoRequestService {

    //para adapatar el manejo de los mensajes de error de MP
    protected <T> T call(Call<T> call) {
        try {
            Response<T> response = call.execute();

            if (!response.isSuccessful()) {
                ErrorDetail errorResponse = this.parseError(response.errorBody());
                throw new ClientException(HttpStatus.valueOf(response.code()),
                        errorResponse.getTimestamp(), errorResponse.getErrorCode(),
                        errorResponse.getMessage(), errorResponse.getViolatedFields(),
                        errorResponse.getDescription());
            }

            return response.body();
        } catch (IOException ex) {
            throw new InternalServerErrorException(MessageBundle.INTERNAL_ERROR);
        }
    }

    private ErrorDetail parseError(ResponseBody response) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> errorMap = objectMapper.readValue(response.string(), Map.class);

            LocalDateTime timestamp = errorMap.containsKey(Constants.CLIENT_ERROR_TIMESTAMP) ?
                    LocalDateTime.parse((String) errorMap.get(Constants.CLIENT_ERROR_TIMESTAMP)) : LocalDateTime.now();

            int errorCode = errorMap.containsKey(Constants.CLIENT_ERROR_CODE) ?
                    (int) errorMap.get(Constants.CLIENT_ERROR_CODE) : 0;

            String message = null;
            if (errorMap.containsKey(Constants.CLIENT_ERROR_MESSAGE))
                message = (String) errorMap.get(Constants.CLIENT_ERROR_MESSAGE);
            if (errorMap.containsKey(Constants.CLIENT_ERROR_DETAIL))
                message = (String) errorMap.get(Constants.CLIENT_ERROR_DETAIL);

            message = traducirMensajeMP(message);

            if (errorMap.containsKey("errors")) {
                List<Map<String, String>> errors = (List<Map<String, String>>) errorMap.get("errors");
                StringBuilder messageBuilder = new StringBuilder();
                if (CommonFunctions.hasData(message)) {
                    messageBuilder.append(message);
                }
                for (Map<String, String> error : errors) {
                    messageBuilder.append(traducirMensajeMP(error.get("code")));
                }
                message = String.valueOf(messageBuilder);
            }

            String violatedFields = errorMap.containsKey(Constants.CLIENT_ERROR_VIOLATED_FIELDS) ?
                    (String) errorMap.get(Constants.CLIENT_ERROR_VIOLATED_FIELDS) : null;

            String description = errorMap.containsKey(Constants.CLIENT_ERROR_DESCRIPTION) ?
                    (String) errorMap.get(Constants.CLIENT_ERROR_DESCRIPTION) : null;

            return new ErrorDetail(timestamp, errorCode, message, violatedFields, description);
        } catch (Exception ex) {
            return new ErrorDetail(null, 0, null, null, null);
        }
    }

    private String traducirMensajeMP(String message) {
        if (CommonFunctions.isEmptyOrNull(message)) return null;

        if (message.contains("Not found The intent")) return MessageBundle.ERROR_NOT_FOUND_INTENT_MP;

        return switch (message) {
            case Constants.ERROR_DEVICE_OWNER_MP -> MessageBundle.ERROR_DEVICE_OWNER_MP;
            case Constants.ERROR_AMOUNT_MP -> MessageBundle.ERROR_AMOUNT_MP;
            case Constants.ERROR_QUEUED_INTENT_MP -> MessageBundle.ERROR_QUEUED_INTENT_MP;
            case Constants.ERROR_CANNOT_CANCEL_ORDER -> MessageBundle.ERROR_CANNOT_CANCEL_ORDER;
            case Constants.ERROR_ORDER_ALREADY_CANCELED -> MessageBundle.ERROR_ORDER_ALREADY_CANCELED;
            case Constants.ERROR_ALREADY_QUEUED_ORDER -> MessageBundle.ERROR_ALREADY_QUEUED_ORDER;
            case Constants.ERROR_PROPERTY_VALUE -> MessageBundle.ERROR_PROPERTY_VALUE;
            default -> message;
        };
    }

}
