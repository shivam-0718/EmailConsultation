package com.pmvyas.emailconsultation.util;

import java.util.UUID;

public class AckNumber {
    public static String ackNumber = UUID.randomUUID().toString().substring(0, 10);
}
