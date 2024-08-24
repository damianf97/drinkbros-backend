package ar.com.damian.drinkbros_backend.util;

public class CommonFunctions {

    private CommonFunctions(){
        //default
    }

    public static String prepareStringToSearch(String stringToSearch) {
        if (stringToSearch == null || stringToSearch.isEmpty() || stringToSearch.trim().isEmpty()) {
            return null;
        }

        return "%" + stringToSearch + "%";
    }
}
