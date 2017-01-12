package com.roxiemobile.androidcommons.util;

import junit.framework.Assert;

/**
 * Thrown when an {@link com.roxiemobile.androidcommons.util.Expect#expectEqual(Object, Object) expectEqual(String, String)} fails.
 * Create and throw a <code>ComparisonFailure</code> manually if you want to show users
 * the difference between two complex strings.
 */
public class ComparisonFailure extends ExpectationError
{
// MARK: - Construction

    /**
     * Constructs a comparison failure.
     *
     * @param message the identifying message or null
     * @param expected the expected string value
     * @param actual the actual string value
     */
    public ComparisonFailure(String message, String expected, String actual) {
        super(message);

        // Init instance variables
        mExpected = expected;
        mActual = actual;
    }

// MARK: - Methods

    /**
     * Returns "..." in place of common prefix and "..." in place of common suffix between expected and actual.
     *
     * @see Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return new ComparisonCompactor(MAX_CONTEXT_LENGTH, mExpected, mActual).compact(super.getMessage());
    }

    /**
     * Returns the actual string value
     *
     * @return the actual string value
     */
    public String getActual() {
        return mActual;
    }

    /**
     * Returns the expected string value
     *
     * @return the expected string value
     */
    public String getExpected() {
        return mExpected;
    }

// MARK: - Inner Types

    private static class ComparisonCompactor {
        private static final String ELLIPSIS = "...";
        private static final String DIFF_END = "]";
        private static final String DIFF_START = "[";

        /**
         * The maximum length for <code>expected</code> and <code>actual</code> strings to show. When
         * <code>contextLength</code> is exceeded, the Strings are shortened.
         */
        private final int contextLength;
        private final String expected;
        private final String actual;

        /**
         * @param contextLength the maximum length of context surrounding the difference between the compared strings.
         * When context length is exceeded, the prefixes and suffixes are compacted.
         * @param expected the expected string value
         * @param actual the actual string value
         */
        public ComparisonCompactor(int contextLength, String expected, String actual) {
            this.contextLength = contextLength;
            this.expected = expected;
            this.actual = actual;
        }

        public String compact(String message) {
            if (expected == null || actual == null || expected.equals(actual)) {
                return Expect.format(message, expected, actual);
            } else {
                DiffExtractor extractor = new DiffExtractor();
                String compactedPrefix = extractor.compactPrefix();
                String compactedSuffix = extractor.compactSuffix();
                return Expect.format(message,
                        compactedPrefix + extractor.expectedDiff() + compactedSuffix,
                        compactedPrefix + extractor.actualDiff() + compactedSuffix);
            }
        }

        private String sharedPrefix() {
            int end = Math.min(expected.length(), actual.length());
            for (int i = 0; i < end; i++) {
                if (expected.charAt(i) != actual.charAt(i)) {
                    return expected.substring(0, i);
                }
            }
            return expected.substring(0, end);
        }

        private String sharedSuffix(String prefix) {
            int suffixLength = 0;
            int maxSuffixLength = Math.min(expected.length() - prefix.length(),
                    actual.length() - prefix.length()) - 1;
            for (; suffixLength <= maxSuffixLength; suffixLength++) {
                if (expected.charAt(expected.length() - 1 - suffixLength)
                        != actual.charAt(actual.length() - 1 - suffixLength)) {
                    break;
                }
            }
            return expected.substring(expected.length() - suffixLength);
        }

        private class DiffExtractor {
            private final String sharedPrefix;
            private final String sharedSuffix;

            /**
             * Can not be instantiated outside {@link com.roxiemobile.androidcommons.util.ComparisonFailure.ComparisonCompactor}.
             */
            private DiffExtractor() {
                sharedPrefix = sharedPrefix();
                sharedSuffix = sharedSuffix(sharedPrefix);
            }

            public String expectedDiff() {
                return extractDiff(expected);
            }

            public String actualDiff() {
                return extractDiff(actual);
            }

            public String compactPrefix() {
                if (sharedPrefix.length() <= contextLength) {
                    return sharedPrefix;
                }
                return ELLIPSIS + sharedPrefix.substring(sharedPrefix.length() - contextLength);
            }

            public String compactSuffix() {
                if (sharedSuffix.length() <= contextLength) {
                    return sharedSuffix;
                }
                return sharedSuffix.substring(0, contextLength) + ELLIPSIS;
            }

            private String extractDiff(String source) {
                return DIFF_START + source.substring(sharedPrefix.length(), source.length() - sharedSuffix.length())
                        + DIFF_END;
            }
        }
    }

// MARK: - Constants

    /**
     * The maximum length for expected and actual strings. If it is exceeded, the strings should be shortened.
     *
     * @see ComparisonCompactor
     */
    private static final int MAX_CONTEXT_LENGTH = 20;
    private static final long serialVersionUID = 1L;

// MARK: - Variables

    /*
     * We have to use the m prefix until the next major release to ensure
     * serialization compatibility.
     * See https://github.com/junit-team/junit/issues/976
     */
    private String mExpected;
    private String mActual;

}
