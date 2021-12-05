package com.db.bts.enums;

public enum PaymentStatus {

    PENDING(0),
    ACCEPTED (1),
    REJECTED(2);

    private int value;

    PaymentStatus(int value) {
        this.value = value;
    }

    public static PaymentStatus valueOf(int value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if(value == status.value) {
                return status;
            }
        }
        return PaymentStatus.PENDING;
    }

    public int getValue() {
        return value;
    }
}
