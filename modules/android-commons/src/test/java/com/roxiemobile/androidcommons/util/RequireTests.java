package com.roxiemobile.androidcommons.util;

import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.diagnostics.RequirementError;

import org.junit.Assert;
import org.junit.Test;

import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllBlank;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireAllValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireBlank;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireEqual;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireFalse;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotSame;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNull;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireSame;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireTrue;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireValid;
import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public final class RequireTests
{
// MARK: - Tests

    @Test
    public void testRequireTrue()
    {
        requireThrowsError("requireTrue", () -> {
            requireTrue(1 > 2);
        });

        requireNotThrowsError("requireTrue", () -> {
            requireTrue(2 > 1);
        });
    }

    @Test
    public void testRequireFalse()
    {
        requireThrowsError("requireFalse", () -> {
            requireFalse(2 > 1);
        });

        requireNotThrowsError("requireFalse", () -> {
            requireFalse(1 > 2);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireEqual()
    {
        final String string = "value";
        final String nilString = null;


        requireThrowsError("requireEqual", () -> {
            requireEqual(1, 2);
        });
        requireThrowsError("requireEqual", () -> {
            requireEqual(string, nilString);
        });

        requireNotThrowsError("requireEqual", () -> {
            requireEqual(2, 2);
        });
        requireNotThrowsError("requireEqual", () -> {
            requireEqual(string, string);
        });
    }

    @Test
    public void testRequireNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        requireThrowsError("requireNotEqual", () -> {
            requireNotEqual(2, 2);
        });
        requireThrowsError("requireNotEqual", () -> {
            requireNotEqual(string, string);
        });

        requireNotThrowsError("requireNotEqual", () -> {
            requireNotEqual(1, 2);
        });
        requireNotThrowsError("requireNotEqual", () -> {
            requireNotEqual(string, nilString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectSame()
    {
        String string = "value";
        String otherString = "otherValue";

        requireThrowsError("expectSame", () -> {
            requireSame(string, otherString);
        });

        requireNotThrowsError("expectSame", () -> {
            requireSame(string, string);
        });
    }

    @Test
    public void testExpectNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        requireThrowsError("expectNotSame", () -> {
            requireNotSame(string, string);
        });

        requireNotThrowsError("expectNotSame", () -> {
            requireNotSame(string, otherString);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNull()
    {
        requireThrowsError("requireNull", () -> {
            requireNull(2);
        });

        requireNotThrowsError("requireNull", () -> {
            requireNull(null);
        });
    }

    @Test
    public void testRequireNotNull()
    {
        requireThrowsError("requireNotNull", () -> {
            requireNotNull(null);
        });

        requireNotThrowsError("requireNotNull", () -> {
            requireNotNull(2);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        requireThrowsError("requireEmpty", () -> {
            requireEmpty(string);
        });

        requireNotThrowsError("requireEmpty", () -> {
            requireEmpty(nilString);
        });
        requireNotThrowsError("requireEmpty", () -> {
            requireEmpty(emptyString);
        });
    }

    @Test
    public void testRequireNullOrEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = toArray(nilString, emptyString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(toArray(string));
        });
        requireThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(toArray(nilString, string));
        });
        requireThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(toArray(emptyString, string));
        });

        requireNotThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(array);
        });
        requireNotThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(nilArray);
        });
        requireNotThrowsError("requireAllEmpty_Array", () -> {
            requireAllEmpty(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        requireThrowsError("requireNotEmpty", () -> {
            requireNotEmpty(nilString);
        });
        requireThrowsError("requireNotEmpty", () -> {
            requireNotEmpty(emptyString);
        });

        requireNotThrowsError("requireNotEmpty", () -> {
            requireNotEmpty(string);
        });
    }

    @Test
    public void testRequireNotEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllNotEmpty_Array", () -> {
            requireAllNotEmpty(toArray(nilString));
        });
        requireThrowsError("requireAllNotEmpty_Array", () -> {
            requireAllNotEmpty(toArray(string, emptyString));
        });
        requireThrowsError("requireAllNotEmpty_Array", () -> {
            requireAllNotEmpty(nilArray);
        });
        requireThrowsError("requireAllNotEmpty_Array", () -> {
            requireAllNotEmpty(emptyArray);
        });

        requireNotThrowsError("requireAllNotEmpty_Array", () -> {
            requireAllNotEmpty(array);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrWhiteSpace()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        requireThrowsError("requireBlank", () -> {
            requireBlank(string);
        });

        requireNotThrowsError("requireBlank", () -> {
            requireBlank(nilString);
        });
        requireNotThrowsError("requireBlank", () -> {
            requireBlank(emptyString);
        });
        requireNotThrowsError("requireBlank", () -> {
            requireBlank(whitespaceString);
        });
    }

    @Test
    public void testRequireNullOrWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = toArray(nilString, emptyString, whitespaceString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(toArray(string));
        });
        requireThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(toArray(nilString, string));
        });
        requireThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(toArray(emptyString, string));
        });
        requireThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(toArray(whitespaceString, string));
        });

        requireNotThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(array);
        });
        requireNotThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(nilArray);
        });
        requireNotThrowsError("requireAllBlank_Array", () -> {
            requireAllBlank(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNotBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        requireThrowsError("requireNotBlank", () -> {
            requireNotBlank(nilString);
        });
        requireThrowsError("requireNotBlank", () -> {
            requireNotBlank(emptyString);
        });
        requireThrowsError("requireNotBlank", () -> {
            requireNotBlank(whitespaceString);
        });

        requireNotThrowsError("requireNotBlank", () -> {
            requireNotBlank(string);
        });
    }

    @Test
    public void testRequireNotBlank_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(toArray(nilString));
        });
        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(toArray(emptyString));
        });
        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(toArray(whitespaceString));
        });
        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(toArray(string, whitespaceString));
        });
        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(nilArray);
        });
        requireThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(emptyArray);
        });

        requireNotThrowsError("requireAllNotBlank_Array", () -> {
            requireAllNotBlank(array);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        requireThrowsError("requireValid", () -> {
            requireValid(notValidObject);
        });

        requireNotThrowsError("requireValid", () -> {
            requireValid(validObject);
        });
    }

    @Test
    public void testRequireValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllValid_Array", () -> {
            requireAllValid(toArray(notValidObject));
        });
        requireThrowsError("requireAllValid_Array", () -> {
            requireAllValid(toArray(validObject, nilObject));
        });
        requireThrowsError("requireAllValid_Array", () -> {
            requireAllValid(toArray(validObject, notValidObject));
        });

        requireNotThrowsError("requireAllValid_Array", () -> {
            requireAllValid(array);
        });
        requireNotThrowsError("requireAllValid_Array", () -> {
            requireAllValid(nilArray);
        });
        requireNotThrowsError("requireAllValid_Array", () -> {
            requireAllValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        requireThrowsError("requireNotValid", () -> {
            requireNotValid(validObject);
        });

        requireNotThrowsError("requireNotValid", () -> {
            requireNotValid(notValidObject);
        });
    }

    @Test
    public void testRequireNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNotValid_Array", () -> {
            requireAllNotValid(toArray(validObject));
        });
        requireThrowsError("requireAllNotValid_Array", () -> {
            requireAllNotValid(toArray(nilObject));
        });
        requireThrowsError("requireAllNotValid_Array", () -> {
            requireAllNotValid(nilArray);
        });
        requireThrowsError("requireAllNotValid_Array", () -> {
            requireAllNotValid(emptyArray);
        });

        requireNotThrowsError("requireAllNotValid_Array", () -> {
            requireAllNotValid(array);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        requireThrowsError("requireNullOrValid", () -> {
            requireNullOrValid(notValidObject);
        });

        requireNotThrowsError("requireNullOrValid", () -> {
            requireNullOrValid(validObject);
        });
        requireNotThrowsError("requireNullOrValid", () -> {
            requireNullOrValid(nilObject);
        });
    }

    @Test
    public void testRequireNullOrValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject, nilObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(toArray(notValidObject));
        });
        requireThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(toArray(validObject, notValidObject));
        });

        requireNotThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(toArray(validObject, nilObject));
        });
        requireNotThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(array);
        });
        requireNotThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(nilArray);
        });
        requireNotThrowsError("requireAllNullOrValid_Array", () -> {
            requireAllNullOrValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        requireThrowsError("requireNullOrNotValid", () -> {
            requireNullOrNotValid(validObject);
        });

        requireNotThrowsError("requireNullOrNotValid", () -> {
            requireNullOrNotValid(nilObject);
        });
        requireNotThrowsError("requireNullOrNotValid", () -> {
            requireNullOrNotValid(notValidObject);
        });
    }

    @Test
    public void testRequireNullOrNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(nilObject, notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNullOrNotValid_Array", () -> {
            requireAllNullOrNotValid(toArray(validObject));
        });
        requireThrowsError("requireAllNullOrNotValid_Array", () -> {
            requireAllNullOrNotValid(nilArray);
        });
        requireThrowsError("requireAllNullOrNotValid_Array", () -> {
            requireAllNullOrNotValid(emptyArray);
        });

        requireNotThrowsError("requireAllNullOrNotValid_Array", () -> {
            requireAllNullOrNotValid(array);
        });
    }

// MARK: - Private Methods

    private void requireThrowsError(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
            Assert.fail(method + ": Method not thrown an exception");
        }
        catch (RequirementError e) {
            // Do nothing
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }
    }

    private void requireNotThrowsError(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (RequirementError e) {
            Assert.fail(method + ": Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }
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
