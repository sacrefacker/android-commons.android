package com.roxiemobile.androidcommons.data.mapper.adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.roxiemobile.androidcommons.data.DateKeys.Format;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAdapter extends TypeAdapter<Date>
{
// MARK: - Methods

    @Override
    public Date read(JsonReader in) throws IOException {
        Date date = null;

        // Create object from JSON string
        if (in.peek() != JsonToken.NULL) {
            try {
                date = FORMATTER.get().parse(in.nextString());
            }
            catch (ParseException e) {
                throw new JsonSyntaxException(e);
            }
        }
        else {
            in.nextNull();
        }

        // Done
        return date;
    }

    @Override
    public void write(JsonWriter writer, Date date) throws IOException {
        writer.value(date != null ? FORMATTER.get().format(date) : null);
    }

// MARK: - Inner Types

    private static final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Format.DATE, Locale.ENGLISH);
        }
    };

}
