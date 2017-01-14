package com.roxiemobile.androidcommons.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Base64;

import com.roxiemobile.androidcommons.diagnostics.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;

public final class IOUtils
{
// MARK: - Construction

    private IOUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * TODO
     */
    public static byte[] loadAsset(@NonNull Context context, @NonNull String path) {
        expectNotNull(context, "context is null");
        expectNotEmpty(path, "path is empty");

        InputStream stream = null;
        byte[] content = null;

        try {
            stream = context.getAssets().open(path);
            if (stream != null) {
                content = toByteArray(stream);
            }
        }
        catch (IOException ex) {
            Logger.w(TAG, ex);
        }
        finally {
            IOUtils.closeQuietly(stream);
        }

        // Done
        return content;
    }

    /**
     * TODO
     */
    public static String loadAssetAsString(@NonNull Context context, @NonNull String path, Charset charset) {
        String content = null;

        byte[] buffer = loadAsset(context, path);
        if (buffer != null) {
            content = new String(buffer, charset);
        }

        // Done
        return content;
    }

    /**
     * Get the contents of an {@code InputStream} as a {@code byte[]}.
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    /**
     * Copy bytes from a {@code InputStream} to an {@code OutputStream}.
     */
    public static long copy(InputStream input, OutputStream output) throws IOException {
        expectNotNull(input, "input is null");
        expectNotNull(output, "output is null");

        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long totalCount = 0;

        int bytesRead;
        while (EOF != (bytesRead = input.read(buffer))) {
            output.write(buffer, 0, bytesRead);
            totalCount += bytesRead;
        }

        // Done
        return totalCount;
    }

    /**
     * Unconditionally close a {@code Closeable}.
     * <p>
     * Equivalent to {@link Closeable#close()}, except any exceptions will be ignored.
     * This is typically used in finally blocks.
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException ex) {
            Logger.w(TAG, ex);
        }
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String encodeObject(@NonNull Serializable object, String defaultValue) {
        expectNotNull(object, "object is null");

        ObjectOutputStream oos = null;
        String str = defaultValue;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            str = Base64.encodeToString(baos.toByteArray(), Base64.NO_PADDING | Base64.NO_WRAP | Base64.URL_SAFE);
        }
        catch (IOException ex) {
            Logger.w(TAG, ex);
        }
        finally {
            IOUtils.closeQuietly(oos);
        }
        return str;
    }

    public static String encodeObject(@NonNull Serializable obj) {
        return encodeObject(obj, null);
    }

    /**
     * Read the object from Base64 string.
     */
    public static Object decodeObject(@NonNull String string, Object defaultObject) {
        expectNotEmpty(string, "string is empty");

        ObjectInputStream ois = null;
        Object obj = defaultObject;
        try {
            byte[] data = Base64.decode(string, Base64.URL_SAFE);
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            obj = ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex) {
            Logger.w(TAG, ex);
        }
        finally {
            IOUtils.closeQuietly(ois);
        }
        return obj;
    }

    public static Object decodeObject(@NonNull String str) {
        return decodeObject(str, null);
    }

// MARK: - Constants

    public static final String TAG = IOUtils.class.getSimpleName();

    /**
     * TODO
     */
    private static final int EOF = -1;

    /**
     * The default buffer size ({@value}) to use for {@link #copy(InputStream, OutputStream)}.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

}