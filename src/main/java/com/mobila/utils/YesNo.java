package com.mobila.utils;

/**
 * Created by ckibuchi on 11/18/2016.
 */
public enum  YesNo {
    NO("NO"),
    YES("YES");
    private final String yesno;

    /**
     * @param yesno
     */
    private YesNo(final String yesno) {
        this.yesno = yesno;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.yesno;
    }
}
