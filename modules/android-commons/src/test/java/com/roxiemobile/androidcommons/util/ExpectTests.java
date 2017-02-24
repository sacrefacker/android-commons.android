package com.roxiemobile.androidcommons.util;

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
    public void testExpectNullOrEmpty()
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
    }

    @Test
    public void testExpectNullOrEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = toArray(nilString, emptyString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectAllEmpty_Array", () -> {
            expectAllEmpty(toArray(string));
        });
        expectThrowsException("expectAllEmpty_Array", () -> {
            expectAllEmpty(toArray(nilString, string));
        });
        expectThrowsException("expectAllEmpty_Array", () -> {
            expectAllEmpty(toArray(emptyString, string));
        });

        expectNotThrowsException("expectAllEmpty_Array", () -> {
            expectAllEmpty(array);
        });
        expectNotThrowsException("expectAllEmpty_Array", () -> {
            expectAllEmpty(nilArray);
        });
        expectNotThrowsException("expectAllEmpty_Array", () -> {
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
    }

    @Test
    public void testExpectNotEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectAllNotEmpty_Array", () -> {
            expectAllNotEmpty(toArray(nilString));
        });
        expectThrowsException("expectAllNotEmpty_Array", () -> {
            expectAllNotEmpty(toArray(string, emptyString));
        });
        expectThrowsException("expectAllNotEmpty_Array", () -> {
            expectAllNotEmpty(nilArray);
        });
        expectThrowsException("expectAllNotEmpty_Array", () -> {
            expectAllNotEmpty(emptyArray);
        });

        expectNotThrowsException("expectAllNotEmpty_Array", () -> {
            expectAllNotEmpty(array);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNullOrWhiteSpace()
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
    public void testExpectNullOrWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = toArray(nilString, emptyString, whitespaceString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(toArray(string));
        });
        expectThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(toArray(nilString, string));
        });
        expectThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(toArray(emptyString, string));
        });
        expectThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(toArray(whitespaceString, string));
        });

        expectNotThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(array);
        });
        expectNotThrowsException("expectAllBlank_Array", () -> {
            expectAllBlank(nilArray);
        });
        expectNotThrowsException("expectAllBlank_Array", () -> {
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
    public void testExpectNotBlank_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(toArray(nilString));
        });
        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(toArray(emptyString));
        });
        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(toArray(whitespaceString));
        });
        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(toArray(string, whitespaceString));
        });
        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(nilArray);
        });
        expectThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(emptyArray);
        });

        expectNotThrowsException("expectAllNotBlank_Array", () -> {
            expectAllNotBlank(array);
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
    public void testExpectValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectAllValid_Array", () -> {
            expectAllValid(toArray(notValidObject));
        });
        expectThrowsException("expectAllValid_Array", () -> {
            expectAllValid(toArray(validObject, nilObject));
        });
        expectThrowsException("expectAllValid_Array", () -> {
            expectAllValid(toArray(validObject, notValidObject));
        });

        expectNotThrowsException("expectAllValid_Array", () -> {
            expectAllValid(array);
        });
        expectNotThrowsException("expectAllValid_Array", () -> {
            expectAllValid(nilArray);
        });
        expectNotThrowsException("expectAllValid_Array", () -> {
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
    public void testExpectNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectAllNotValid_Array", () -> {
            expectAllNotValid(toArray(validObject));
        });
        expectThrowsException("expectAllNotValid_Array", () -> {
            expectAllNotValid(toArray(nilObject));
        });
        expectThrowsException("expectAllNotValid_Array", () -> {
            expectAllNotValid(nilArray);
        });
        expectThrowsException("expectAllNotValid_Array", () -> {
            expectAllNotValid(emptyArray);
        });

        expectNotThrowsException("expectAllNotValid_Array", () -> {
            expectAllNotValid(array);
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
    public void testExpectNullOrValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject, nilObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectAllNullOrValid_Array", () -> {
            expectAllNullOrValid(toArray(notValidObject));
        });
        expectThrowsException("expectAllNullOrValid_Array", () -> {
            expectAllNullOrValid(toArray(validObject, notValidObject));
        });

        expectNotThrowsException("expectAllNullOrValid_Array", () -> {
            expectAllNullOrValid(toArray(validObject, nilObject));
        });
        expectNotThrowsException("expectAllNullOrValid_Array", () -> {
            expectAllNullOrValid(array);
        });
        expectNotThrowsException("expectAllNullOrValid_Array", () -> {
            expectAllNullOrValid(nilArray);
        });
        expectNotThrowsException("expectAllNullOrValid_Array", () -> {
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
    public void testExpectNullOrNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(nilObject, notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectAllNullOrNotValid_Array", () -> {
            expectAllNullOrNotValid(toArray(validObject));
        });
        expectThrowsException("expectAllNullOrNotValid_Array", () -> {
            expectAllNullOrNotValid(nilArray);
        });
        expectThrowsException("expectAllNullOrNotValid_Array", () -> {
            expectAllNullOrNotValid(emptyArray);
        });

        expectNotThrowsException("expectAllNullOrNotValid_Array", () -> {
            expectAllNullOrNotValid(array);
        });
    }

// MARK: - Tests

    @Test
    public void testNotThrowIfValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_valid_vehicles_in_array");
        assertNotNull(jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException e) {
            Assert.fail("notThrowIfValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("notThrowIfValidModel: Unknown exception is thrown");
        }

        assertNotNull(parking);
        assertTrue(parking.isValid());
    }

    @Test
    public void testThrowIfNotValidModel() {
        Logger.shared().logLevel(LogLevel.Suppress);
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_one_non_valid_vehicle_in_array");
        assertNotNull(jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException e) {
            Assert.fail("throwIfNotValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("throwIfNotValidModel: Unknown exception is thrown");
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
