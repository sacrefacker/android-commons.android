package com.roxiemobile.androidcommons.util;

public final class CharacterUtils
{
// MARK: - Construction

    private CharacterUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Indicates whether the specified character is a ASCII letter.
     *
     * @param c The character to check.
     * @return {@code true} if {@code c} is a letter; {@code false} otherwise.
     */
    public static boolean isAsciiLetter(char c) {
        return isAsciiLetter((int) c);
    }

    /**
     * Indicates whether the specified code point is a ASCII letter.
     *
     * @param codePoint The code point to check.
     * @return {@code true} if {@code codePoint} is a letter; {@code false} otherwise.
     */
    public static boolean isAsciiLetter(int codePoint) {
        return ('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z');
    }

    /**
     * Indicates whether the specified character is a ASCII letter or a digit.
     *
     * @param c The character to check.
     * @return {@code true} if {@code c} is a letter or a digit; {@code false} otherwise.
     */
    public static boolean isAsciiLetterOrDigit(char c) {
        return isAsciiLetterOrDigit((int) c);
    }

    /**
     * Indicates whether the specified code point is a ASCII letter or a digit.
     *
     * @param codePoint The code point to check.
     * @return {@code true} if {@code codePoint} is a letter or a digit; {@code false} otherwise.
     */
    public static boolean isAsciiLetterOrDigit(int codePoint) {
        return ('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z') || ('0' <= codePoint && codePoint <= '9');
    }
}
