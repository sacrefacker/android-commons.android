package com.roxiemobile.androidcommons.data.mapper;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import com.roxiemobile.androidcommons.data.Constants.Charsets;
import com.roxiemobile.androidcommons.data.Constants.DateFormat;
import com.roxiemobile.androidcommons.data.mapper.adapter.DateAdapter;
import com.roxiemobile.androidcommons.data.mapper.adapter.EnumStringConverter;
import com.roxiemobile.androidcommons.data.mapper.adapter.EnumTypeAdapterFactory;
import com.roxiemobile.androidcommons.data.mapper.adapter.TimestampAdapter;
import com.roxiemobile.androidcommons.data.mapper.adapter.URIAdapter;
import com.roxiemobile.androidcommons.data.model.ValidatableModel;
import com.roxiemobile.androidcommons.diagnostics.ExpectationException;
import com.roxiemobile.androidcommons.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ServiceLoader;

public final class DataMapper
{
// MARK: - Construction

    private DataMapper() {
        // Do nothing
    }

// MARK: - Methods: JSON to POJO

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return validateObject(GsonHolder.shared().fromJson(json, classOfT));
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return validateObject(GsonHolder.shared().fromJson(json, typeOfT));
    }

    public static <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
        return validateObject(GsonHolder.shared().fromJson(json, classOfT));
    }

    public static <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        return validateObject(GsonHolder.shared().fromJson(json, typeOfT));
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return validateObject(GsonHolder.shared().fromJson(json, classOfT));
    }

    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return validateObject(GsonHolder.shared().fromJson(json, typeOfT));
    }

// MARK: - Methods: POJO to JSON

    public static String toJson(Object src, Type typeOfSrc) {
        return GsonHolder.shared().toJson(src, typeOfSrc);
    }

    public static String toJson(Object src) {
        return GsonHolder.shared().toJson(src);
    }

    public static JsonElement toJsonTree(Object src, Type typeOfSrc) {
        return GsonHolder.shared().toJsonTree(src, typeOfSrc);
    }

    public static JsonElement toJsonTree(Object src) {
        return GsonHolder.shared().toJsonTree(src);
    }

// MARK: - Methods: JSON to ByteArray

    public static byte[] toByteArray(JsonElement element) {
        byte[] result = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, Charsets.UTF_8);
        JsonWriter jsonWriter = new JsonWriter(streamWriter);
        try {
            jsonWriter.setLenient(true);
            Streams.write(element, jsonWriter);
            jsonWriter.close();
            result = outputStream.toByteArray();
        }
        catch (IOException ex) {
            throw new AssertionError(ex);
        }
        finally {
            IOUtils.closeQuietly(jsonWriter);
            IOUtils.closeQuietly(outputStream);
        }

        return result;
    }

// MARK: - Methods

    @SuppressWarnings("unchecked")
    public static <T> EnumStringConverter<T> getEnumStringConverter(Class<T> enumClass) {
        return (EnumStringConverter<T>) GsonHolder.shared().getAdapter(enumClass);
    }

// MARK: - Private Methods

    private static <T> T validateObject(T object)
    {
        if (object instanceof ValidatableModel) {
            try {
                ((ValidatableModel) object).validate();
            }
            catch (ExpectationException e) {
                throw new JsonSyntaxException(e);
            }
        }
        return object;
    }

// MARK: - Inner Types

    private static class GsonHolder
    {
        public static Gson shared() {
            return GsonHolder.SingletonHolder.SHARED_INSTANCE;
        }

        private static class SingletonHolder {
            private static final Gson SHARED_INSTANCE = newInstance();
        }

        private static Gson newInstance() {
            GsonBuilder builder = new GsonBuilder();

            // Init Gson builder
            builder.enableComplexMapKeySerialization();
            builder.setDateFormat(DateFormat.TIMESTAMP_ISO8601);
            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

            // Register an adapters
            for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class, DataMapper.class.getClassLoader())) {
                builder.registerTypeAdapterFactory(factory);
            }

            builder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
            builder.registerTypeAdapter(URI.class, new URIAdapter());
            builder.registerTypeAdapter(Date.class, new DateAdapter());
            builder.registerTypeAdapter(Timestamp.class, new TimestampAdapter());

            // Done
            return builder.create();
        }
    }
}
