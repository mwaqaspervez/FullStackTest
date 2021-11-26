package com.demo.assessment.model.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DeliveryStatus {
    ORDER_RECEIVED,
    ORDER_PREPARING,
    ORDER_PICKED_UP,
    ORDER_DELIVERED;

    public static List<DeliveryStatus> getInProgressStatus() {
        return new ArrayList<>(Arrays.asList(ORDER_RECEIVED, ORDER_PREPARING, ORDER_PICKED_UP));
    }
}
