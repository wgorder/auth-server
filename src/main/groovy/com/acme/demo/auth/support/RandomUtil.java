package com.acme.demo.auth.support;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 *
 * @author William Gorder
 * @since 12/30/14
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
}