package com.demo.assessment.model.types;

import java.util.List;

public enum DeliveryStatus {
    ORDER_RECEIVED,
    ORDER_PREPARING,
    ORDER_PICKED_UP,
    ORDER_DELIVERED;

    public static List<DeliveryStatus> getInProgressStatus() {
        return List.of(ORDER_RECEIVED, ORDER_PREPARING, ORDER_PICKED_UP);
    }
}
