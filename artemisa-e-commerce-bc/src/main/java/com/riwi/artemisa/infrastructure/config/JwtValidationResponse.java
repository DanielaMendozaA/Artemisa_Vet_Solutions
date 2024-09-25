package com.riwi.artemisa.infrastructure.config;

public class JwtValidationResponse {
    private int statusCode;
    private String message;
    private Data data;

    // Getters y setters

    public static class Data {
        private boolean valid;

        // Getter y setter para valid
        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }

    // Getters y setters para statusCode, message y data
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}













