package com.pmvyas.emailconsultation.util;

import java.util.UUID;

public class AcknowledgementNumber {
    /**
     * Generates a new unique acknowledgment number each time it's called
     * @return A new unique ACK number using UUID
     */
    public static String generateNewAcknowledgementNumber() {
        return "ACK-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }

    @Deprecated
    public static String ackNumber = UUID.randomUUID().toString().substring(0, 10);
}
