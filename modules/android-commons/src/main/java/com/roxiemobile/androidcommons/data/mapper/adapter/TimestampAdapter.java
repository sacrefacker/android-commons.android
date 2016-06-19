package com.roxiemobile.androidcommons.data.mapper.adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.roxiemobile.androidcommons.data.DateKeys.Format;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimestampAdapter extends TypeAdapter<Timestamp>
{
// MARK: - Methods

    @Override
    public Timestamp read(JsonReader in) throws IOException {
        Timestamp timestamp = null;

        // Create object from JSON string
        if (in.peek() != JsonToken.NULL) {
            try {
                // Java 6 date is not fully compatible with ISO 8601 and doesn't recognize 'Z' marker as +00:00 timezone.
                // @link http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html#timezone
                Date date = FORMATTER.get().parse(in.nextString().replace("Z", "+0000"));
                timestamp = new Timestamp(date.getTime());
            }
            catch (ParseException e) {
                throw new JsonSyntaxException(e);
            }
        }
        else {
            in.nextNull();
        }

        // Done
        return timestamp;
    }

    @Override
    public void write(JsonWriter writer, Timestamp timestamp) throws IOException {
        writer.value(timestamp != null ? FORMATTER.get().format(timestamp) : null);
    }

// MARK: - Inner Types

    private static final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Format.TIMESTAMP_ISO8601, Locale.ENGLISH);
        }
    };

}
