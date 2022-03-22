package se.nilsedgar.accessy.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonResponse {
    public class ErrorDetails {
        private long status;
        private String message;

        public ErrorDetails(long status, String message) {
            this.status = status;
            this.message = message;
        }

        public long getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails error;

    public CommonResponse(Object payload) {
        this.payload = payload;
        this.success = true;
        this.error = null;
    }

    public CommonResponse(long status, String message) {
        this.payload = null;
        this.success = false;
        this.error = new ErrorDetails(status, message);
    }

    public Object getPayload() {
        return payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ErrorDetails getError() {
        return error;
    }
}
