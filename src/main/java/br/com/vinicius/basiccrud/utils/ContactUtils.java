package br.com.vinicius.basiccrud.utils;

public class ContactUtils {

    public String capitalizeFirstLetter(String field) {
        if (field == null || field.isEmpty()) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
