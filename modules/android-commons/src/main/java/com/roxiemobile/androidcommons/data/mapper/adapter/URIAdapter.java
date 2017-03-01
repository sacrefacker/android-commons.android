package com.roxiemobile.androidcommons.data.mapper.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.net.URI;

public class URIAdapter extends TypeAdapter<URI>
{
// MARK: - Methods

    @Override
    public void write(JsonWriter writer, URI uri) throws IOException {
        writer.value(uri.toString());
    }

    @Override
    public URI read(JsonReader reader) throws IOException {
        return URI.create(reader.nextString());
    }
}
