package com.roxiemobile.androidcommons.data.mapper.adapter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.roxiemobile.androidcommons.data.CommonKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumTypeAdapterFactory implements TypeAdapterFactory
{
// MARK: - Methods

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();

        if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
            return null;
        }

        // Handle anonymous subclasses
        if (!rawType.isEnum()) {
            rawType = rawType.getSuperclass();
        }

        //noinspection unchecked
        return (TypeAdapter<T>) new CustomEnumTypeAdapter(rawType);
    }

// MARK: - Inner Types

    public static final class CustomEnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T>
            implements EnumStringConverter<T>
    {
        private CustomEnumTypeAdapter(Class<T> classOfT) {
            try {
                for (T constant : classOfT.getEnumConstants()) {
                    String name = constant.name();

                    SerializedName annotation = classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (annotation != null) {
                        name = annotation.value();

                        for (String alias : annotation.alternate()) {
                            mNameToConstant.put(alias, constant);
                        }
                    }

                    mNameToConstant.put(name, constant);
                    mConstantToName.put(constant, name);
                }

                mUnknownTypeValue = mNameToConstant.get(CommonKeys.State.UNDEFINED);
            }
            catch (NoSuchFieldException e) {
                throw new IllegalStateException("Missing field in " + classOfT.getName(), e);
            }
        }

        @Override
        public T read(JsonReader in) throws IOException {
            T value = null;

            // Read enum value
            if (in.peek() != JsonToken.NULL) {
                value = getValueForKey(in.nextString());
                if (value == null) {
                    value = mUnknownTypeValue;
                }
            }
            else {
                in.nextNull();
            }

            // Done
            return value;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            out.value(value != null ? mConstantToName.get(value) : null);
        }

        @Override
        public T getValueForKey(@NonNull String key) {
            return mNameToConstant.get(key);
        }

        @Override
        public List<T> getValuesForKeys(Collection<String> keys) {
            List<T> results = new ArrayList<>(keys.size());

            //noinspection Convert2streamapi
            for (String key : keys) {
                results.add(getValueForKey(key));
            }
            return results;
        }

        @Override
        public String getKeyForValue(T value) {
            return mConstantToName.get(value);
        }

        @Override
        public List<String> getKeysForValues(Collection<T> values) {
            List<String> results = new ArrayList<>(values.size());

            //noinspection Convert2streamapi
            for (T value : values) {
                results.add(getKeyForValue(value));
            }
            return results;
        }

        private final Map<String, T> mNameToConstant = new HashMap<>();
        private final Map<T, String> mConstantToName = new HashMap<>();
        private final T mUnknownTypeValue;
    }

}
