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

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectFalse;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotWhiteSpace;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrWhiteSpace;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectTrue;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectValid;
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


        expectThrowsException("expectNullOrEmpty", () -> {
            expectNullOrEmpty(string);
        });

        expectNotThrowsException("expectNullOrEmpty", () -> {
            expectNullOrEmpty(nilString);
        });
        expectNotThrowsException("expectNullOrEmpty", () -> {
            expectNullOrEmpty(emptyString);
        });
    }

    @Test
    public void testExpectNullOrEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = new String[]{nilString, emptyString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{string});
        });
        expectThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{nilString, string});
        });
        expectThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{emptyString, string});
        });
        expectThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(nilArray);
        });

        expectNotThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(array);
        });
        expectNotThrowsException("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(emptyArray);
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
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(new String[]{nilString});
        });
        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(new String[]{string, emptyString});
        });
        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(nilArray);
        });
        expectThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(emptyArray);
        });

        expectNotThrowsException("expectNotEmpty_Array", () -> {
            expectNotEmpty(array);
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


        expectThrowsException("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(string);
        });

        expectNotThrowsException("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(nilString);
        });
        expectNotThrowsException("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(emptyString);
        });
        expectNotThrowsException("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(whitespaceString);
        });
    }

    @Test
    public void testExpectNullOrWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = new String[]{nilString, emptyString, whitespaceString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{string});
        });
        expectThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{nilString, string});
        });
        expectThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{emptyString, string});
        });
        expectThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{whitespaceString, string});
        });
        expectThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(nilArray);
        });

        expectNotThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(array);
        });
        expectNotThrowsException("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(emptyArray);
        });
    }

    @Test
    public void testExpectNotWhiteSpace()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        expectThrowsException("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(nilString);
        });
        expectThrowsException("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(emptyString);
        });
        expectThrowsException("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(whitespaceString);
        });

        expectNotThrowsException("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(string);
        });
    }

    @Test
    public void testExpectNotWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{nilString});
        });
        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{emptyString});
        });
        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{whitespaceString});
        });
        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{string, whitespaceString});
        });
        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(nilArray);
        });
        expectThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(emptyArray);
        });

        expectNotThrowsException("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(array);
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

        final Validatable[] array = new Validatable[]{validObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectValid_Array", () -> {
            expectValid(new Validatable[]{notValidObject});
        });
        expectThrowsException("expectValid_Array", () -> {
            expectValid(new Validatable[]{validObject, nilObject});
        });
        expectThrowsException("expectValid_Array", () -> {
            expectValid(new Validatable[]{validObject, notValidObject});
        });
        expectThrowsException("expectValid_Array", () -> {
            expectValid(nilArray);
        });

        expectNotThrowsException("expectValid_Array", () -> {
            expectValid(array);
        });
        expectNotThrowsException("expectValid_Array", () -> {
            expectValid(emptyArray);
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

        final Validatable[] array = new Validatable[]{notValidObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectNotValid_Array", () -> {
            expectNotValid(new Validatable[]{validObject});
        });
        expectThrowsException("expectNotValid_Array", () -> {
            expectNotValid(new Validatable[]{nilObject});
        });
        expectThrowsException("expectNotValid_Array", () -> {
            expectNotValid(nilArray);
        });
        expectThrowsException("expectNotValid_Array", () -> {
            expectNotValid(emptyArray);
        });

        expectNotThrowsException("expectNotValid_Array", () -> {
            expectNotValid(array);
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

        final Validatable[] array = new Validatable[]{validObject, nilObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{notValidObject});
        });
        expectThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{validObject, notValidObject});
        });
        expectThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(nilArray);
        });

        expectNotThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{validObject, nilObject});
        });
        expectNotThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(array);
        });
        expectNotThrowsException("expectNullOrValid_Array", () -> {
            expectNullOrValid(emptyArray);
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

        final Validatable[] array = new Validatable[]{nilObject, notValidObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(new Validatable[]{validObject});
        });
        expectThrowsException("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(nilArray);
        });
        expectThrowsException("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(emptyArray);
        });

        expectNotThrowsException("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(array);
        });
    }

// MARK: - Tests

    @Test
    public void testNotThrowIfValidModel() {
        Logger.instance().logLevel(LogLevel.Suppress);
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
        Logger.instance().logLevel(LogLevel.Suppress);
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
