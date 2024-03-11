package br.com.vinicius.basiccrud.exceptions;

public class DuplicateDataException extends RuntimeException{

    public DuplicateDataException(String message) {
        super(message);
    }
}
