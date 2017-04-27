package com.roxiemobile.androidcommons.data.mapper.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.roxiemobile.androidcommons.data.model.PostValidatable;
import com.roxiemobile.androidcommons.diagnostics.ExpectationException;

import java.io.IOException;

public class PostValidatableObjectTypeAdapterFactory implements TypeAdapterFactory
{
// MARK: - Methods

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        return new TypeAdapter<T>()
        {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                T obj = delegate.read(in);

                if (obj instanceof PostValidatable) {
                    PostValidatable instance = (PostValidatable) obj;
                    if (instance.isShouldPostValidate()) {
                        try {
                            instance.validate();
                        }
                        catch (ExpectationException e) {
                            throw new JsonSyntaxException(e.getMessage(), e);
                        }
                    }
                }
                return obj;
            }
        };
    }
}
