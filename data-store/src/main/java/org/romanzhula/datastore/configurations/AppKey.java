package org.romanzhula.datastore.configurations;

import java.util.Objects;

public class AppKey {

    private final static String defaultPrefix = "app";
    private static String prefix = null;

    public static void setPrefix(String keyPrefix) {
        prefix = keyPrefix;
    }

    public static String getKey(String key) {
        String prefix = getPrefix();

        return getPrefix() + ":" + key;
    }

    public static String getPrefix() {
        return Objects.requireNonNullElse(prefix, defaultPrefix);
    }

}
