package org.boxfox.dms.utilities.actions.support;

/**
 * Created by boxfox on 2017-03-04.
 */
public class JobResult {
    private String message;
    private boolean success;

    public JobResult(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}
