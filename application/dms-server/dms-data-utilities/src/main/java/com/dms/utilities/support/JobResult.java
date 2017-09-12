package com.dms.utilities.support;

/**
 * Created by boxfox on 2017-03-04.
 */
public class JobResult {
    private String message;
    private boolean success;
    private Object[] args;

    public JobResult(boolean success) {
        this.success = success;
    }


    public JobResult(boolean success, String message, Object... args) {
        this.success = success;
        this.message = message;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object... args) {
        this.args = args;
    }
}
