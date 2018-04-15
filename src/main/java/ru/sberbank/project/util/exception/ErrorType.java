package ru.sberbank.project.util.exception;

public enum ErrorType {
    APP_ERROR("Ошибка приложения"),
    DATA_NOT_FOUND("Данные не найдены");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}