package com.roxiemobile.androidcommons.diagnostics;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.roxiemobile.androidcommons.data.mapper.DataMapper;
import com.roxiemobile.androidcommons.data.model.NotValidModel;
import com.roxiemobile.androidcommons.data.model.ParkingModel;
import com.roxiemobile.androidcommons.data.model.ValidModel;
import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.logging.Logger;
import com.roxiemobile.androidcommons.logging.Logger.LogLevel;
import com.roxiemobile.androidcommons.util.StringUtils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public final class CheckTests
{
// MARK: - Tests

    @Test
    public void testExpectTrue() {
        String method = "Check.isTrue";

        checkThrowsException(method, () -> {
            Check.isTrue(1 > 2);
        });

        checkNotThrowsException(method, () -> {
            Check.isTrue(2 > 1);
        });
    }

    @Test
    public void testExpectFalse() {
        String method = "Check.isFalse";

        checkThrowsException(method, () -> {
            Check.isFalse(2 > 1);
        });

        checkNotThrowsException(method, () -> {
            Check.isFalse(1 > 2);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectEqual()
    {
        final String string = "value";
        final String nilString = null;


        checkThrowsException("equal", () -> {
            Check.equal(1, 2);
        });
        checkThrowsException("equal", () -> {
            Check.equal(string, nilString);
        });

        checkNotThrowsException("equal", () -> {
            Check.equal(2, 2);
        });
        checkNotThrowsException("equal", () -> {
            Check.equal(string, string);
        });
    }

    @Test
    public void testExpectNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        checkThrowsException("notEqual", () -> {
            Check.notEqual(2, 2);
        });
        checkThrowsException("notEqual", () -> {
            Check.notEqual(string, string);
        });

        checkNotThrowsException("notEqual", () -> {
            Check.notEqual(1, 2);
        });
        checkNotThrowsException("notEqual", () -> {
            Check.notEqual(string, nilString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectSame()
    {
        String string = "value";
        String otherString = "otherValue";

        checkThrowsException("same", () -> {
            Check.same(string, otherString);
        });

        checkNotThrowsException("same", () -> {
            Check.same(string, string);
        });
    }

    @Test
    public void testExpectNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        checkThrowsException("notSame", () -> {
            Check.notSame(string, string);
        });

        checkNotThrowsException("notSame", () -> {
            Check.notSame(string, otherString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNull()
    {
        checkThrowsException("isNull", () -> {
            Check.isNull(2);
        });

        checkNotThrowsException("isNull", () -> {
            Check.isNull(null);
        });
    }

    @Test
    public void testExpectNotNull()
    {
        checkThrowsException("notNull", () -> {
            Check.notNull(null);
        });

        checkNotThrowsException("notNull", () -> {
            Check.notNull(2);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        checkThrowsException("empty", () -> {
            Check.empty(string);
        });

        checkNotThrowsException("empty", () -> {
            Check.empty(nilString);
        });
        checkNotThrowsException("empty", () -> {
            Check.empty(emptyString);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        checkThrowsException("empty_Array", () -> {
            Check.empty(array);
        });

        checkNotThrowsException("empty_Array", () -> {
            Check.empty(nilArray);
        });
        checkNotThrowsException("empty_Array", () -> {
            Check.empty(emptyArray);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        checkThrowsException("empty_Collection", () -> {
            Check.empty(list);
        });

        checkNotThrowsException("empty_Collection", () -> {
            Check.empty(nilList);
        });
        checkNotThrowsException("empty_Collection", () -> {
            Check.empty(emptyList);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        checkThrowsException("empty_Map", () -> {
            Check.empty(map);
        });

        checkNotThrowsException("empty_Map", () -> {
            Check.empty(nilMap);
        });
        checkNotThrowsException("empty_Map", () -> {
            Check.empty(emptyMap);
        });
    }

    @Test
    public void testExpectAllEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = toArray(nilString, emptyString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        checkThrowsException("allEmpty", () -> {
            Check.allEmpty(toArray(string));
        });
        checkThrowsException("allEmpty", () -> {
            Check.allEmpty(toArray(nilString, string));
        });
        checkThrowsException("allEmpty", () -> {
            Check.allEmpty(toArray(emptyString, string));
        });

        checkNotThrowsException("allEmpty", () -> {
            Check.allEmpty(array);
        });
        checkNotThrowsException("allEmpty", () -> {
            Check.allEmpty(nilArray);
        });
        checkNotThrowsException("allEmpty", () -> {
            Check.allEmpty(emptyArray);
        });
    }

    @Test
    public void testExpectNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        checkThrowsException("notEmpty", () -> {
            Check.notEmpty(nilString);
        });
        checkThrowsException("notEmpty", () -> {
            Check.notEmpty(emptyString);
        });

        checkNotThrowsException("notEmpty", () -> {
            Check.notEmpty(string);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        checkThrowsException("notEmpty_Array", () -> {
            Check.notEmpty(nilArray);
        });
        checkThrowsException("notEmpty_Array", () -> {
            Check.notEmpty(emptyArray);
        });

        checkNotThrowsException("notEmpty_Array", () -> {
            Check.notEmpty(array);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        checkThrowsException("notEmpty_Collection", () -> {
            Check.notEmpty(nilList);
        });
        checkThrowsException("notEmpty_Collection", () -> {
            Check.notEmpty(emptyList);
        });

        checkNotThrowsException("notEmpty_Collection", () -> {
            Check.notEmpty(list);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        checkThrowsException("notEmpty_Map", () -> {
            Check.notEmpty(nilMap);
        });
        checkThrowsException("notEmpty_Map", () -> {
            Check.notEmpty(emptyMap);
        });

        checkNotThrowsException("notEmpty_Map", () -> {
            Check.notEmpty(map);
        });
    }

    @Test
    public void testExpectAllNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        checkThrowsException("allNotEmpty", () -> {
            Check.allNotEmpty(toArray(string, nilString));
        });
        checkThrowsException("allNotEmpty", () -> {
            Check.allNotEmpty(toArray(string, emptyString));
        });

        checkNotThrowsException("allNotEmpty", () -> {
            Check.allNotEmpty(array);
        });
        checkNotThrowsException("allNotEmpty", () -> {
            Check.allNotEmpty(nilArray);
        });
        checkNotThrowsException("allNotEmpty", () -> {
            Check.allNotEmpty(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        checkThrowsException("blank", () -> {
            Check.blank(string);
        });

        checkNotThrowsException("blank", () -> {
            Check.blank(nilString);
        });
        checkNotThrowsException("blank", () -> {
            Check.blank(emptyString);
        });
        checkNotThrowsException("blank", () -> {
            Check.blank(whitespaceString);
        });
    }

    @Test
    public void testExpectAllBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = toArray(nilString, emptyString, whitespaceString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        checkThrowsException("allBlank", () -> {
            Check.allBlank(toArray(string));
        });
        checkThrowsException("allBlank", () -> {
            Check.allBlank(toArray(nilString, string));
        });
        checkThrowsException("allBlank", () -> {
            Check.allBlank(toArray(emptyString, string));
        });
        checkThrowsException("allBlank", () -> {
            Check.allBlank(toArray(whitespaceString, string));
        });

        checkNotThrowsException("allBlank", () -> {
            Check.allBlank(array);
        });
        checkNotThrowsException("allBlank", () -> {
            Check.allBlank(nilArray);
        });
        checkNotThrowsException("allBlank", () -> {
            Check.allBlank(emptyArray);
        });
    }

    @Test
    public void testExpectNotBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        checkThrowsException("notBlank", () -> {
            Check.notBlank(nilString);
        });
        checkThrowsException("notBlank", () -> {
            Check.notBlank(emptyString);
        });
        checkThrowsException("notBlank", () -> {
            Check.notBlank(whitespaceString);
        });

        checkNotThrowsException("notBlank", () -> {
            Check.notBlank(string);
        });
    }

    @Test
    public void testExpectAllNotBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        checkThrowsException("allNotBlank", () -> {
            Check.allNotBlank(toArray(nilString));
        });
        checkThrowsException("allNotBlank", () -> {
            Check.allNotBlank(toArray(emptyString));
        });
        checkThrowsException("allNotBlank", () -> {
            Check.allNotBlank(toArray(whitespaceString));
        });
        checkThrowsException("allNotBlank", () -> {
            Check.allNotBlank(toArray(string, whitespaceString));
        });

        checkNotThrowsException("allNotBlank", () -> {
            Check.allNotBlank(array);
        });
        checkNotThrowsException("allNotBlank", () -> {
            Check.allNotBlank(nilArray);
        });
        checkNotThrowsException("allNotBlank", () -> {
            Check.allNotBlank(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        checkThrowsException("valid", () -> {
            Check.valid(notValidObject);
        });

        checkNotThrowsException("valid", () -> {
            Check.valid(validObject);
        });
    }

    @Test
    public void testExpectAllValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        checkThrowsException("allValid", () -> {
            Check.allValid(toArray(notValidObject));
        });
        checkThrowsException("allValid", () -> {
            Check.allValid(toArray(validObject, nilObject));
        });
        checkThrowsException("allValid", () -> {
            Check.allValid(toArray(validObject, notValidObject));
        });

        checkNotThrowsException("allValid", () -> {
            Check.allValid(array);
        });
        checkNotThrowsException("allValid", () -> {
            Check.allValid(nilArray);
        });
        checkNotThrowsException("allValid", () -> {
            Check.allValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        checkThrowsException("notValid", () -> {
            Check.notValid(validObject);
        });

        checkNotThrowsException("notValid", () -> {
            Check.notValid(notValidObject);
        });
    }

    @Test
    public void testExpectAllNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        checkThrowsException("allNotValid", () -> {
            Check.allNotValid(toArray(validObject));
        });
        checkThrowsException("allNotValid", () -> {
            Check.allNotValid(toArray(nilObject));
        });
        checkThrowsException("allNotValid", () -> {
            Check.allNotValid(toArray(notValidObject, validObject));
        });

        checkNotThrowsException("allNotValid", () -> {
            Check.allNotValid(array);
        });
        checkNotThrowsException("allNotValid", () -> {
            Check.allNotValid(nilArray);
        });
        checkNotThrowsException("allNotValid", () -> {
            Check.allNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        checkThrowsException("nullOrValid", () -> {
            Check.nullOrValid(notValidObject);
        });

        checkNotThrowsException("nullOrValid", () -> {
            Check.nullOrValid(validObject);
        });
        checkNotThrowsException("nullOrValid", () -> {
            Check.nullOrValid(nilObject);
        });
    }

    @Test
    public void testExpectAllNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject, nilObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        checkThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(toArray(notValidObject));
        });
        checkThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(toArray(validObject, notValidObject));
        });
        checkThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(toArray(nilObject, notValidObject));
        });

        checkNotThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(array);
        });
        checkNotThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(nilArray);
        });
        checkNotThrowsException("allNullOrValid", () -> {
            Check.allNullOrValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        checkThrowsException("nullOrNotValid", () -> {
            Check.nullOrNotValid(validObject);
        });

        checkNotThrowsException("nullOrNotValid", () -> {
            Check.nullOrNotValid(nilObject);
        });
        checkNotThrowsException("nullOrNotValid", () -> {
            Check.nullOrNotValid(notValidObject);
        });
    }

    @Test
    public void testExpectAllNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(nilObject, notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        checkThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(toArray(validObject));
        });
        checkThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(toArray(nilObject, validObject));
        });
        checkThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(toArray(notValidObject, validObject));
        });

        checkNotThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(array);
        });
        checkNotThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(nilArray);
        });
        checkNotThrowsException("allNullOrNotValid", () -> {
            Check.allNullOrNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testIsValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        final ParkingModel[] parking = {null};

        JsonObject jsonObject = loadJson("test_parking_model_with_valid_vehicles_in_array");
        assertNotNull("Could not parse JSON from file", jsonObject);

        checkNotThrowsException("validModel", JsonSyntaxException.class, () -> {
            parking[0] = DataMapper.fromJson(jsonObject, ParkingModel.class);
        });

        assertNotNull(parking[0]);
        assertTrue(parking[0].isValid());
    }

    @Test
    public void testIsNotValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        final ParkingModel[] parking = {null};

        JsonObject jsonObject = loadJson("test_parking_model_with_one_non_valid_vehicle_in_array");
        assertNotNull("Could not parse JSON from file", jsonObject);

        checkThrowsException("notValidModel", JsonSyntaxException.class, () -> {
            parking[0] = DataMapper.fromJson(jsonObject, ParkingModel.class);
        });

        assertNull(parking[0]);
    }

// MARK: - Private Methods

    private <T> void checkThrowsException(String method, Class<T> classOfT, Runnable task) {
        checkArgument(StringUtils.isNotEmpty(method), "method is empty");
        checkArgument(classOfT != null, "classOfT is null");
        checkArgument(task != null, "task is null");

        Throwable cause = null;
        try {
            task.run();
        }
        catch (Throwable t) {
            cause = t;
        }

        if (cause != null)
        {
            if (cause.getClass().equals(classOfT)) {
                // Do nothing
            }
            else {
                Assert.fail(method + ": Unknown exception is thrown");
            }
        }
        else {
            Assert.fail(method + ": Method not thrown an exception");
        }
    }

    private void checkThrowsException(String method, Runnable task) {
        checkThrowsException(method, CheckException.class, task);
    }

// --

    private <T> void checkNotThrowsException(@NonNull String method, @NonNull Class<T> classOfT, @NonNull Runnable task) {
        checkArgument(StringUtils.isNotEmpty(method), "method is empty");
        checkArgument(classOfT != null, "classOfT is null");
        checkArgument(task != null, "task is null");

        Throwable cause = null;
        try {
            task.run();
        }
        catch (Throwable t) {
            cause = t;
        }

        if (cause != null)
        {
            if (cause.getClass().equals(classOfT)) {
                Assert.fail(method + ": Method thrown an exception");
            }
            else {
                Assert.fail(method + ": Unknown exception is thrown");
            }
        }
        else {
            // Do nothing
        }
    }

    private void checkNotThrowsException(String method, Runnable task) {
        checkNotThrowsException(method, CheckException.class, task);
    }

// --

    private JsonObject loadJson(@NonNull String filename) {
        checkArgument(StringUtils.isNotEmpty(filename), "filename is empty");

        ClassLoader loader = this.getClass().getClassLoader();
        JsonObject jsonObject = null;

        try {
            InputStream in = loader.getResourceAsStream(filename + ".json");
            String jsonString = StringUtils.streamToString(in);
            jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        }
        catch (IOException e) {
            Assert.fail("Could not load file: " + filename + ".json");
        }
        return jsonObject;
    }

    private static void checkArgument(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
