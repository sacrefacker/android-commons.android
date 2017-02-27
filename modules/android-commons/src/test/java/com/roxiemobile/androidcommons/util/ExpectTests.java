package com.roxiemobile.androidcommons.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.roxiemobile.androidcommons.data.mapper.DataMapper;
import com.roxiemobile.androidcommons.data.model.ParkingModel;
import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.diagnostics.ExpectationException;
import com.roxiemobile.androidcommons.logging.Logger;
import com.roxiemobile.androidcommons.logging.Logger.LogLevel;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectFalse;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectTrue;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectValid;
import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public final class ExpectTests
{
// MARK: - Tests

    @Test
    public void testExpectTrue()
    {
        expectThrowsException("expectTrue", () -> {
            expectTrue(1 > 2);
        });

        expectNotThrowsException("expectTrue", () -> {
            expectTrue(2 > 1);
        });
    }

    @Test
    public void testExpectFalse()
    {
        expectThrowsException("expectFalse", () -> {
            expectFalse(2 > 1);
        });

        expectNotThrowsException("expectFalse", () -> {
            expectFalse(1 > 2);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsException("expectEqual", () -> {
            expectEqual(1, 2);
        });
        expectThrowsException("expectEqual", () -> {
            expectEqual(string, nilString);
        });

        expectNotThrowsException("expectEqual", () -> {
            expectEqual(2, 2);
        });
        expectNotThrowsException("expectEqual", () -> {
            expectEqual(string, string);
        });
    }

    @Test
    public void testExpectNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsException("expectNotEqual", () -> {
            expectNotEqual(2, 2);
        });
        expectThrowsException("expectNotEqual", () -> {
            expectNotEqual(string, string);
        });

        expectNotThrowsException("expectNotEqual", () -> {
            expectNotEqual(1, 2);
        });
        expectNotThrowsException("expectNotEqual", () -> {
            expectNotEqual(string, nilString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectSame()
    {
        String string = "value";
        String otherString = "otherValue";

        expectThrowsException("expectSame", () -> {
            expectSame(string, otherString);
        });

        expectNotThrowsException("expectSame", () -> {
            expectSame(string, string);
        });
    }

    @Test
    public void testExpectNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        expectThrowsException("expectNotSame", () -> {
            expectNotSame(string, string);
        });

        expectNotThrowsException("expectNotSame", () -> {
            expectNotSame(string, otherString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNull()
    {
        expectThrowsException("expectNull", () -> {
            expectNull(2);
        });

        expectNotThrowsException("expectNull", () -> {
            expectNull(null);
        });
    }

    @Test
    public void testExpectNotNull()
    {
        expectThrowsException("expectNotNull", () -> {
            expectNotNull(null);
        });

        expectNotThrowsException("expectNotNull", () -> {
            expectNotNull(2);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        expectThrowsException("expectEmpty", () -> {
            expectEmpty(string);
        });

        expectNotThrowsException("expectEmpty", () -> {
            expectEmpty(nilString);
        });
        expectNotThrowsException("expectEmpty", () -> {
            expectEmpty(emptyString);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        expectThrowsException("expectEmpty_Array", () -> {
            expectEmpty(array);
        });

        expectNotThrowsException("expectEmpty_Array", () -> {
            expectEmpty(nilArray);
        });
        expectNotThrowsException("expectEmpty_Array", () -> {
            expectEmpty(emptyArray);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        expectThrowsException("expectEmpty_Collection", () -> {
            expectEmpty(list);
        });

        expectNotThrowsException("expectEmpty_Collection", () -> {
            expectEmpty(nilList);
        });
        expectNotThrowsException("expectEmpty_Collection", () -> {
            expectEmpty(emptyList);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        expectThrowsException("expectEmpty_Map", () -> {
            expectEmpty(map);
        });

        expectNotThrowsException("expectEmpty_Map", () -> {
            expectEmpty(nilMap);
        });
        expectNotThrowsException("expectEmpty_Map", () -> {
            expectEmpty(emptyMap);
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


        expectThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(toArray(string));
        });
        expectThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(toArray(nilString, string));
        });
        expectThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(toArray(emptyString, string));
        });

        expectNotThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(array);
        });
        expectNotThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(nilArray);
        });
        expectNotThrowsException("expectAllEmpty", () -> {
            expectAllEmpty(emptyArray);
        });
    }

    @Test
    public void testExpectNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        expectThrowsException("expectNotEmpty", () -> {
            expectNotEmpty(nilString);
        });
        expectThrowsException("expectNotEmpty", () -> {
            expectNotEmpty(emptyString);
        });

        expectNotThrowsException("expectNotEmpty", () -> {
            expectNotEmpty(string);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(nilArray);
        });
        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(emptyArray);
        });

        expectNotThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(array);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        expectThrowsException("expectNotEmpty_Collection", () -> {
            expectNotEmpty(nilList);
        });
        expectThrowsException("expectNotEmpty_Collection", () -> {
            expectNotEmpty(emptyList);
        });

        expectNotThrowsException("expectNotEmpty_Collection", () -> {
            expectNotEmpty(list);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        expectThrowsException("expectNotEmpty_Map", () -> {
            expectNotEmpty(nilMap);
        });
        expectThrowsException("expectNotEmpty_Map", () -> {
            expectNotEmpty(emptyMap);
        });

        expectNotThrowsException("expectNotEmpty_Map", () -> {
            expectNotEmpty(map);
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


        expectThrowsException("expectAllNotEmpty", () -> {
            expectAllNotEmpty(toArray(string, nilString));
        });
        expectThrowsException("expectAllNotEmpty", () -> {
            expectAllNotEmpty(toArray(string, emptyString));
        });

        expectNotThrowsException("expectAllNotEmpty", () -> {
            expectAllNotEmpty(array);
        });
        expectNotThrowsException("expectAllNotEmpty", () -> {
            expectAllNotEmpty(nilArray);
        });
        expectNotThrowsException("expectAllNotEmpty", () -> {
            expectAllNotEmpty(emptyArray);
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


        expectThrowsException("expectBlank", () -> {
            expectBlank(string);
        });

        expectNotThrowsException("expectBlank", () -> {
            expectBlank(nilString);
        });
        expectNotThrowsException("expectBlank", () -> {
            expectBlank(emptyString);
        });
        expectNotThrowsException("expectBlank", () -> {
            expectBlank(whitespaceString);
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


        expectThrowsException("expectAllBlank", () -> {
            expectAllBlank(toArray(string));
        });
        expectThrowsException("expectAllBlank", () -> {
            expectAllBlank(toArray(nilString, string));
        });
        expectThrowsException("expectAllBlank", () -> {
            expectAllBlank(toArray(emptyString, string));
        });
        expectThrowsException("expectAllBlank", () -> {
            expectAllBlank(toArray(whitespaceString, string));
        });

        expectNotThrowsException("expectAllBlank", () -> {
            expectAllBlank(array);
        });
        expectNotThrowsException("expectAllBlank", () -> {
            expectAllBlank(nilArray);
        });
        expectNotThrowsException("expectAllBlank", () -> {
            expectAllBlank(emptyArray);
        });
    }

    @Test
    public void testExpectNotBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        expectThrowsException("expectNotBlank", () -> {
            expectNotBlank(nilString);
        });
        expectThrowsException("expectNotBlank", () -> {
            expectNotBlank(emptyString);
        });
        expectThrowsException("expectNotBlank", () -> {
            expectNotBlank(whitespaceString);
        });

        expectNotThrowsException("expectNotBlank", () -> {
            expectNotBlank(string);
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


        expectThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(toArray(nilString));
        });
        expectThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(toArray(emptyString));
        });
        expectThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(toArray(whitespaceString));
        });
        expectThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(toArray(string, whitespaceString));
        });

        expectNotThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(array);
        });
        expectNotThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(nilArray);
        });
        expectNotThrowsException("expectAllNotBlank", () -> {
            expectAllNotBlank(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("expectValid", () -> {
            expectValid(notValidObject);
        });

        expectNotThrowsException("expectValid", () -> {
            expectValid(validObject);
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


        expectThrowsException("expectAllValid", () -> {
            expectAllValid(toArray(notValidObject));
        });
        expectThrowsException("expectAllValid", () -> {
            expectAllValid(toArray(validObject, nilObject));
        });
        expectThrowsException("expectAllValid", () -> {
            expectAllValid(toArray(validObject, notValidObject));
        });

        expectNotThrowsException("expectAllValid", () -> {
            expectAllValid(array);
        });
        expectNotThrowsException("expectAllValid", () -> {
            expectAllValid(nilArray);
        });
        expectNotThrowsException("expectAllValid", () -> {
            expectAllValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("expectNotValid", () -> {
            expectNotValid(validObject);
        });

        expectNotThrowsException("expectNotValid", () -> {
            expectNotValid(notValidObject);
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


        expectThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(toArray(validObject));
        });
        expectThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(toArray(nilObject));
        });
        expectThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(toArray(notValidObject, validObject));
        });

        expectNotThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(array);
        });
        expectNotThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(nilArray);
        });
        expectNotThrowsException("expectAllNotValid", () -> {
            expectAllNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("expectNullOrValid", () -> {
            expectNullOrValid(notValidObject);
        });

        expectNotThrowsException("expectNullOrValid", () -> {
            expectNullOrValid(validObject);
        });
        expectNotThrowsException("expectNullOrValid", () -> {
            expectNullOrValid(nilObject);
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


        expectThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(toArray(notValidObject));
        });
        expectThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(toArray(validObject, notValidObject));
        });
        expectThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(toArray(nilObject, notValidObject));
        });

        expectNotThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(array);
        });
        expectNotThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(nilArray);
        });
        expectNotThrowsException("expectAllNullOrValid", () -> {
            expectAllNullOrValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("expectNullOrNotValid", () -> {
            expectNullOrNotValid(validObject);
        });

        expectNotThrowsException("expectNullOrNotValid", () -> {
            expectNullOrNotValid(nilObject);
        });
        expectNotThrowsException("expectNullOrNotValid", () -> {
            expectNullOrNotValid(notValidObject);
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


        expectThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(toArray(validObject));
        });
        expectThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(toArray(nilObject, validObject));
        });
        expectThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(toArray(notValidObject, validObject));
        });

        expectNotThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(array);
        });
        expectNotThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(nilArray);
        });
        expectNotThrowsException("expectAllNullOrNotValid", () -> {
            expectAllNullOrNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testIsValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_valid_vehicles_in_array");
        assertNotNull("Could not parse JSON from file", jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException e) {
            Assert.fail("isValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("isValidModel: Unknown exception is thrown");
        }

        assertNotNull(parking);
        assertTrue(parking.isValid());
    }

    @Test
    public void testIsNotValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_one_non_valid_vehicle_in_array");
        assertNotNull("Could not parse JSON from file", jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException e) {
            Assert.fail("isNotValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("isNotValidModel: Unknown exception is thrown");
        }

        assertNotNull(parking);
        assertFalse(parking.isValid());
    }

// MARK: - Private Methods

    private void expectThrowsException(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        Exception cause = null;
        try {
            task.run();
        }
        catch (ExpectationException e) {
            cause = e;
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }

        if (cause == null) {
            Assert.fail(method + ": Method not thrown an exception");
        }
    }

    private void expectNotThrowsException(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (ExpectationException e) {
            Assert.fail(method + ": Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }
    }

    private JsonObject loadJson(String filename)
    {
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

// MARK: - Inner Types

    private static class ValidModel implements Validatable {
        @Override
        public boolean isValid() {
            return true;
        }
    }

    private static class NotValidModel implements Validatable {
        @Override
        public boolean isValid() {
            return false;
        }
    }
}
