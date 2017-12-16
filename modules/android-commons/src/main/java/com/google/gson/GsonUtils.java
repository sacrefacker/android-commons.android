package com.google.gson;

import com.roxiemobile.androidcommons.diagnostics.Guard;
import com.roxiemobile.androidcommons.util.ParseUtils;

import java.util.NoSuchElementException;

public final class GsonUtils
{
// MARK: - Construction

    private GsonUtils() {
        // Do nothing
    }

// MARK: - Methods

    public static JsonElement getJsonElement(JsonObject jsonObj, String key) throws NoSuchElementException {
        JsonElement result = null;

        if (jsonObj != null) {
            result = jsonObj.get(key);
        }

        if (result == null) {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static JsonElement getJsonElement(JsonObject jsonObj, String key, JsonElement defaultVal) {
        JsonElement result = defaultVal;

        try {
            result = getJsonElement(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }


    public static JsonObject getJsonObject(JsonObject jsonObj, String key) throws NoSuchElementException {
        JsonElement result = getJsonElement(jsonObj, key);

        if (result == null || !result.isJsonObject()) {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return (JsonObject) result;
    }

    public static JsonObject getJsonObject(JsonObject jsonObj, String key, JsonObject defaultObj) {
        JsonObject result = defaultObj;

        try {
            result = getJsonObject(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static JsonArray getJsonArray(JsonObject jsonObj, String key) throws NoSuchElementException {
        JsonElement result = getJsonElement(jsonObj, key);

        if (result == null || !result.isJsonArray()) {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return (JsonArray) result;
    }

    public static JsonArray getJsonArray(JsonObject jsonObj, String key, JsonArray defaultArr) {
        JsonArray result = defaultArr;

        try {
            result = getJsonArray(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static String getString(JsonObject jsonObj, String key) throws NoSuchElementException {
        String result = null;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isString()) {
                result = elem.getAsString();
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static String getString(JsonObject jsonObj, String key, String defaultVal) {
        String result = defaultVal;

        try {
            result = getString(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static int getInt(JsonObject jsonObj, String key) throws NoSuchElementException {
        int result = 0;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isNumber()) {
                result = elem.getAsInt();
            }
            else
            if (value.isString()) {
                String string = elem.getAsString();

                if (!ParseUtils.tryParseInt(string)) {
                    throw new NoSuchElementException("Key not found: "+ key);
                }

                result = Integer.parseInt(string);
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static int getInt(JsonObject jsonObj, String key, int defaultVal) {
        int result = defaultVal;

        try {
            result = getInt(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static long getLong(JsonObject jsonObj, String key) throws NoSuchElementException {
        long result = 0;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isNumber()) {
                result = elem.getAsLong();
            }
            else
            if (value.isString()) {
                String string = elem.getAsString();

                if (!ParseUtils.tryParseLong(string)) {
                    throw new NoSuchElementException("Key not found: "+ key);
                }

                result = Long.parseLong(string);
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static long getLong(JsonObject jsonObj, String key, long defaultVal) {
        long result = defaultVal;

        try {
            result = getLong(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static float getFloat(JsonObject jsonObj, String key, float defaultVal) {
        float result = defaultVal;

        try {
            result = getFloat(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static float getFloat(JsonObject jsonObj, String key) throws NoSuchElementException {
        float result = 0;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isNumber()) {
                result = elem.getAsFloat();
            }
            else
            if (value.isString()) {
                String string = elem.getAsString();

                if (!ParseUtils.tryParseFloat(string)) {
                    throw new NoSuchElementException("Key not found: "+ key);
                }

                result = Float.parseFloat(string);
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static double getDouble(JsonObject jsonObj, String key, double defaultVal) {
        double result = defaultVal;

        try {
            result = getDouble(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static double getDouble(JsonObject jsonObj, String key) throws NoSuchElementException {
        double result = 0;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isNumber()) {
                result = elem.getAsDouble();
            }
            else
            if (value.isString()) {
                String string = elem.getAsString();

                if (!ParseUtils.tryParseDouble(string)) {
                    throw new NoSuchElementException("Key not found: "+ key);
                }

                result = Double.parseDouble(string);
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static boolean getBoolean(JsonObject jsonObj, String key) throws NoSuchElementException {
        boolean result = false;

        JsonElement elem = getJsonElement(jsonObj, key);
        if (elem.isJsonPrimitive())
        {
            JsonPrimitive value = elem.getAsJsonPrimitive();
            if (value.isBoolean()) {
                result = elem.getAsBoolean();
            }
            else
            if (value.isString()) {
                result = Boolean.parseBoolean(elem.getAsString());
            }
            else {
                throw new NoSuchElementException("Key not found: "+ key);
            }
        }
        else {
            throw new NoSuchElementException("Key not found: "+ key);
        }

        // Done
        return result;
    }

    public static boolean getBoolean(JsonObject jsonObj, String key, boolean defaultVal) {
        boolean result = defaultVal;

        try {
            result = getBoolean(jsonObj, key);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static JsonElement toJsonElement(String jsonStr) {
        JsonElement value = null;

        if (jsonStr != null) {
            value = sParser.parse(jsonStr);
        }

        // Done
        return value;
    }

    public static JsonElement toJsonElement(String jsonStr, JsonElement defaultObj) {
        JsonElement result = defaultObj;

        try {
            result = toJsonElement(jsonStr);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static JsonObject toJsonObject(String jsonStr) {
        JsonElement jsonObj = toJsonElement(jsonStr);

        if (jsonObj != null && !jsonObj.isJsonObject()) {
            jsonObj  = null;
        }

        // Done
        return (JsonObject) jsonObj;
    }

    public static JsonObject toJsonObject(String jsonStr, JsonObject defaultObj) {
        JsonObject result = defaultObj;

        try {
            result = toJsonObject(jsonStr);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static JsonArray toJsonArray(String jsonStr) {
        JsonElement jsonArr = toJsonElement(jsonStr);

        if (jsonArr != null && !jsonArr.isJsonArray()) {
            jsonArr  = null;
        }

        // Done
        return (JsonArray) jsonArr;
    }

    public static JsonArray toJsonArray(String jsonStr, JsonArray defaultArr) {
        JsonArray result = defaultArr;

        try {
            result = toJsonArray(jsonStr);
        }
        catch (NoSuchElementException e) {
            // Do nothing
        }

        // Done
        return result;
    }

    public static boolean isEmpty(JsonObject object) {
        return (object == null) || object.entrySet().size() < 1;
    }

    public static boolean isEmpty(JsonArray array) {
        return (array == null) || array.size() < 1;
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> T deepCopy(T object) {
        Guard.notNull(object, "object is null");
        return (T) object.deepCopy();
    }

// MARK: - Variables

    private static final JsonParser sParser = new JsonParser();
}
