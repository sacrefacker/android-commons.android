package com.roxiemobile.androidcommons.diagnostics;

/**
 * Thrown when an {@link Expect#expectEqual(Object, Object) expectEqual(String, String)} fails.
 * Create and throw a <code>ComparisonFailure</code> manually if you want to show users
 * the difference between two complex strings.
 */
public class ComparisonFailure extends ExpectationException
{
// MARK: - Construction

    /**
     * Constructs a comparison failure.
     *
     * @param message  The identifying message or null
     * @param expected The expected string value
     * @param actual   The actual string value
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
     * @return The actual string value
     */
    public String getActual() {
        return mActual;
    }

    /**
     * Returns the expected string value
     *
     * @return The expected string value
     */
    public String getExpected() {
        return mExpected;
    }

// MARK: - Inner Types

    private static class ComparisonCompactor
    {
        private static final String ELLIPSIS = "...";
        private static final String DIFF_END = "]";
        private static final String DIFF_START = "[";

        /**
         * The maximum length for <code>expected</code> and <code>actual</code> strings to show. When
         * <code>contextLength</code> is exceeded, the Strings are shortened.
         */
        private final int mContextLength;
        private final String mExpected;
        private final String mActual;

        /**
         * @param contextLength The maximum length of context surrounding the difference between the compared
         *                      strings. When context length is exceeded, the prefixes and suffixes are compacted.
         * @param expected      The expected string value
         * @param actual        The actual string value
         */
        public ComparisonCompactor(int contextLength, String expected, String actual) {
            mContextLength = contextLength;
            mExpected = expected;
            mActual = actual;
        }

        public String compact(String message) {
            if (mExpected == null || mActual == null || mExpected.equals(mActual)) {
                return Expect.format(message, mExpected, mActual);
            }
            else {
                DiffExtractor extractor = new DiffExtractor();
                String compactedPrefix = extractor.compactPrefix();
                String compactedSuffix = extractor.compactSuffix();
                return Expect.format(message,
                        compactedPrefix + extractor.expectedDiff() + compactedSuffix,
                        compactedPrefix + extractor.actualDiff() + compactedSuffix);
            }
        }

        private String sharedPrefix() {
            int end = Math.min(mExpected.length(), mActual.length());
            for (int i = 0; i < end; i++) {
                if (mExpected.charAt(i) != mActual.charAt(i)) {
                    return mExpected.substring(0, i);
                }
            }
            return mExpected.substring(0, end);
        }

        private String sharedSuffix(String prefix) {
            int suffixLength = 0;
            int maxSuffixLength = Math.min(mExpected.length() - prefix.length(), mActual.length() - prefix.length()) - 1;
            for (; suffixLength <= maxSuffixLength; suffixLength++) {
                if (mExpected.charAt(mExpected.length() - 1 - suffixLength) != mActual.charAt(mActual.length() - 1 - suffixLength)) {
                    break;
                }
            }
            return mExpected.substring(mExpected.length() - suffixLength);
        }

        private class DiffExtractor
        {
            private final String mSharedPrefix;
            private final String mSharedSuffix;

            /**
             * Can not be instantiated outside {@link ComparisonFailure.ComparisonCompactor}.
             */
            private DiffExtractor() {
                mSharedPrefix = sharedPrefix();
                mSharedSuffix = sharedSuffix(mSharedPrefix);
            }

            public String expectedDiff() {
                return extractDiff(mExpected);
            }

            public String actualDiff() {
                return extractDiff(mActual);
            }

            public String compactPrefix() {
                if (mSharedPrefix.length() <= mContextLength) {
                    return mSharedPrefix;
                }
                return ELLIPSIS + mSharedPrefix.substring(mSharedPrefix.length() - mContextLength);
            }

            public String compactSuffix() {
                if (mSharedSuffix.length() <= mContextLength) {
                    return mSharedSuffix;
                }
                return mSharedSuffix.substring(0, mContextLength) + ELLIPSIS;
            }

            private String extractDiff(String source) {
                return DIFF_START + source.substring(mSharedPrefix.length(), source.length() - mSharedSuffix.length()) + DIFF_END;
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

    /**
     * We have to use the m prefix until the next major release to ensure
     * serialization compatibility.
     * See https://github.com/junit-team/junit/issues/976
     */
    private String mExpected;
    private String mActual;
}
