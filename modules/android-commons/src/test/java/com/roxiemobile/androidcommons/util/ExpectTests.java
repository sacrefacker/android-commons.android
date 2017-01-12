package com.roxiemobile.androidcommons.util;

import com.roxiemobile.androidcommons.data.validator.Validatable;

import org.junit.Assert;
import org.junit.Test;

import static com.roxiemobile.androidcommons.util.Expect.expectEqual;
import static com.roxiemobile.androidcommons.util.Expect.expectFalse;
import static com.roxiemobile.androidcommons.util.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.util.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.util.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.util.Expect.expectNotSame;
import static com.roxiemobile.androidcommons.util.Expect.expectNotValid;
import static com.roxiemobile.androidcommons.util.Expect.expectNotWhiteSpace;
import static com.roxiemobile.androidcommons.util.Expect.expectNull;
import static com.roxiemobile.androidcommons.util.Expect.expectNullOrEmpty;
import static com.roxiemobile.androidcommons.util.Expect.expectNullOrNotValid;
import static com.roxiemobile.androidcommons.util.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.util.Expect.expectNullOrWhiteSpace;
import static com.roxiemobile.androidcommons.util.Expect.expectSame;
import static com.roxiemobile.androidcommons.util.Expect.expectTrue;
import static com.roxiemobile.androidcommons.util.Expect.expectValid;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public class ExpectTests
{
// MARK: - Tests

    @Test
    public void testExpectTrue()
    {
        expectThrowsError("expectTrue", () -> {
            expectTrue(1 > 2);
        });

        expectNotThrowsError("expectTrue", () -> {
            expectTrue(2 > 1);
        });
    }

    @Test
    public void testExpectFalse()
    {
        expectThrowsError("expectFalse", () -> {
            expectFalse(2 > 1);
        });

        expectNotThrowsError("expectFalse", () -> {
            expectFalse(1 > 2);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsError("expectEqual", () -> {
            expectEqual(1, 2);
        });
        expectThrowsError("expectEqual", () -> {
            expectEqual(string, nilString);
        });

        expectNotThrowsError("expectEqual", () -> {
            expectEqual(2, 2);
        });
        expectNotThrowsError("expectEqual", () -> {
            expectEqual(string, string);
        });
    }

    @Test
    public void testExpectNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsError("expectNotEqual", () -> {
            expectNotEqual(2, 2);
        });
        expectThrowsError("expectNotEqual", () -> {
            expectNotEqual(string, string);
        });

        expectNotThrowsError("expectNotEqual", () -> {
            expectNotEqual(1, 2);
        });
        expectNotThrowsError("expectNotEqual", () -> {
            expectNotEqual(string, nilString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectSame()
    {
        String string = "value";
        String otherString = "otherValue";

        expectThrowsError("expectSame", () -> {
            expectSame(string, otherString);
        });

        expectNotThrowsError("expectSame", () -> {
            expectSame(string, string);
        });
    }

    @Test
    public void testExpectNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        expectThrowsError("expectNotSame", () -> {
            expectNotSame(string, string);
        });

        expectNotThrowsError("expectNotSame", () -> {
            expectNotSame(string, otherString);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNull()
    {
        expectThrowsError("expectNull", () -> {
            expectNull(2);
        });

        expectNotThrowsError("expectNull", () -> {
            expectNull(null);
        });
    }

    @Test
    public void testExpectNotNull()
    {
        expectThrowsError("expectNotNull", () -> {
            expectNotNull(null);
        });

        expectNotThrowsError("expectNotNull", () -> {
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


        expectThrowsError("expectNullOrEmpty", () -> {
            expectNullOrEmpty(string);
        });

        expectNotThrowsError("expectNullOrEmpty", () -> {
            expectNullOrEmpty(nilString);
        });
        expectNotThrowsError("expectNullOrEmpty", () -> {
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


        expectThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{string});
        });
        expectThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{nilString, string});
        });
        expectThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(new String[]{emptyString, string});
        });
        expectThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(nilArray);
        });

        expectNotThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(array);
        });
        expectNotThrowsError("expectNullOrEmpty_Array", () -> {
            expectNullOrEmpty(emptyArray);
        });
    }

    @Test
    public void testExpectNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        expectThrowsError("expectNotEmpty", () -> {
            expectNotEmpty(nilString);
        });
        expectThrowsError("expectNotEmpty", () -> {
            expectNotEmpty(emptyString);
        });

        expectNotThrowsError("expectNotEmpty", () -> {
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


        expectThrowsError("expectNotEmpty_Array", () -> {
            expectNotEmpty(new String[]{nilString});
        });
        expectThrowsError("expectNotEmpty_Array", () -> {
            expectNotEmpty(new String[]{string, emptyString});
        });
        expectThrowsError("expectNotEmpty_Array", () -> {
            expectNotEmpty(nilArray);
        });
        expectThrowsError("expectNotEmpty_Array", () -> {
            expectNotEmpty(emptyArray);
        });

        expectNotThrowsError("expectNotEmpty_Array", () -> {
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


        expectThrowsError("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(string);
        });

        expectNotThrowsError("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(nilString);
        });
        expectNotThrowsError("expectNullOrWhiteSpace", () -> {
            expectNullOrWhiteSpace(emptyString);
        });
        expectNotThrowsError("expectNullOrWhiteSpace", () -> {
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


        expectThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{string});
        });
        expectThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{nilString, string});
        });
        expectThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{emptyString, string});
        });
        expectThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(new String[]{whitespaceString, string});
        });
        expectThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(nilArray);
        });

        expectNotThrowsError("expectNullOrWhiteSpace_Array", () -> {
            expectNullOrWhiteSpace(array);
        });
        expectNotThrowsError("expectNullOrWhiteSpace_Array", () -> {
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


        expectThrowsError("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(nilString);
        });
        expectThrowsError("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(emptyString);
        });
        expectThrowsError("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(whitespaceString);
        });

        expectNotThrowsError("expectNotWhiteSpace", () -> {
            expectNotWhiteSpace(string);
        });
    }

    @Test
    public void testExpectNotWhiteSpace_ArrayOfOptionals()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{nilString});
        });
        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{emptyString});
        });
        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{whitespaceString});
        });
        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(new String[]{string, whitespaceString});
        });
        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(nilArray);
        });
        expectThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(emptyArray);
        });

        expectNotThrowsError("expectNotWhiteSpace_Array", () -> {
            expectNotWhiteSpace(array);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        expectThrowsError("expectValid", () -> {
            expectValid(notValidObject);
        });

        expectNotThrowsError("expectValid", () -> {
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


        expectThrowsError("expectValid_Array", () -> {
            expectValid(new Validatable[]{notValidObject});
        });
        expectThrowsError("expectValid_Array", () -> {
            expectValid(new Validatable[]{validObject, nilObject});
        });
        expectThrowsError("expectValid_Array", () -> {
            expectValid(new Validatable[]{validObject, notValidObject});
        });
        expectThrowsError("expectValid_Array", () -> {
            expectValid(nilArray);
        });

        expectNotThrowsError("expectValid_Array", () -> {
            expectValid(array);
        });
        expectNotThrowsError("expectValid_Array", () -> {
            expectValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testExpectNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        expectThrowsError("expectNotValid", () -> {
            expectNotValid(validObject);
        });

        expectNotThrowsError("expectNotValid", () -> {
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


        expectThrowsError("expectNotValid_Array", () -> {
            expectNotValid(new Validatable[]{validObject});
        });
        expectThrowsError("expectNotValid_Array", () -> {
            expectNotValid(new Validatable[]{nilObject});
        });
        expectThrowsError("expectNotValid_Array", () -> {
            expectNotValid(nilArray);
        });
        expectThrowsError("expectNotValid_Array", () -> {
            expectNotValid(emptyArray);
        });

        expectNotThrowsError("expectNotValid_Array", () -> {
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


        expectThrowsError("expectNullOrValid", () -> {
            expectNullOrValid(notValidObject);
        });

        expectNotThrowsError("expectNullOrValid", () -> {
            expectNullOrValid(validObject);
        });
        expectNotThrowsError("expectNullOrValid", () -> {
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


        expectThrowsError("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{notValidObject});
        });
        expectThrowsError("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{validObject, notValidObject});
        });
        expectThrowsError("expectNullOrValid_Array", () -> {
            expectNullOrValid(nilArray);
        });

        expectNotThrowsError("expectNullOrValid_Array", () -> {
            expectNullOrValid(new Validatable[]{validObject, nilObject});
        });
        expectNotThrowsError("expectNullOrValid_Array", () -> {
            expectNullOrValid(array);
        });
        expectNotThrowsError("expectNullOrValid_Array", () -> {
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


        expectThrowsError("expectNullOrNotValid", () -> {
            expectNullOrNotValid(validObject);
        });

        expectNotThrowsError("expectNullOrNotValid", () -> {
            expectNullOrNotValid(nilObject);
        });
        expectNotThrowsError("expectNullOrNotValid", () -> {
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


        expectThrowsError("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(new Validatable[]{validObject});
        });
        expectThrowsError("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(nilArray);
        });
        expectThrowsError("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(emptyArray);
        });

        expectNotThrowsError("expectNullOrNotValid_Array", () -> {
            expectNullOrNotValid(array);
        });
    }

// MARK: - Private Methods

    private void expectThrowsError(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        Error error = null;
        try {
            task.run();
        }
        catch (ExpectationError e) {
            error = e;
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }

        if (error == null) {
            Assert.fail(method + ": Method not thrown an error");
        }
    }

    private void expectNotThrowsError(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (ExpectationError e) {
            Assert.fail(method + ": Method thrown an error");
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
