package com.db.bts.enums;

public enum TransactionStatus {

    INACTIVE(-1),
    DEFAULT (0),
    ACTIVE(1),
    CANCELLED(2);

    private int value;

    TransactionStatus(int value) {
        this.value = value;
    }

    public static TransactionStatus valueOf(int value) {
        for (TransactionStatus status : TransactionStatus.values()) {
            if(value == status.value) {
                return status;
            }
        }
        return TransactionStatus.DEFAULT;
    }

    public int getValue() {
        return value;
    }
}
