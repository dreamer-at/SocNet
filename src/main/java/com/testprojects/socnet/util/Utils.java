package com.testprojects.socnet.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Provides class of utils
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

public class Utils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHH");

    public static String generateUniqueId() {
        return DATE_FORMAT.format(new Date())
                + UUID.randomUUID().toString().replaceAll("-", "");
    }
}
