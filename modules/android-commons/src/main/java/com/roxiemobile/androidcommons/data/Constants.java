package com.roxiemobile.androidcommons.data;

import java.nio.charset.Charset;

public interface Constants
{
// MARK: - Constants

    interface Charsets
    {
        // Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
        Charset US_ASCII = Charset.forName("US-ASCII");

        // ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
        Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

        // Eight-bit UCS Transformation Format
        Charset UTF_8 = Charset.forName("UTF-8");

        // Sixteen-bit UCS Transformation Format, big-endian byte order
        Charset UTF_16BE = Charset.forName("UTF-16BE");

        // Sixteen-bit UCS Transformation Format, little-endian byte order
        Charset UTF_16LE = Charset.forName("UTF-16LE");

        // Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark
        Charset UTF_16 = Charset.forName("UTF-16");
    }

    interface DateFormat
    {
        String DATE = "yyyy-MM-dd";
        String TIMESTAMP_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
    }
}
