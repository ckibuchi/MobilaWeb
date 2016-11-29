package com.mobila.utils;

/**
 * Created by ckibuchi on 11/2/2016.
 */
public enum  Status {
    IN("IN"),
    EXITED("EXITED"),
    DELETED("DELETED");
    private final String status;

    /**
     * @param status
     */
    private Status(final String status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.status;
    }
}
