package com.roxiemobile.androidcommons.util;

import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.diagnostics.RequirementError;

import org.junit.Assert;
import org.junit.Test;

import static com.roxiemobile.androidcommons.diagnostics.Require.requireEqual;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireFalse;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotWhiteSpace;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNull;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireNullOrWhiteSpace;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireTrue;
import static com.roxiemobile.androidcommons.diagnostics.Require.requireValid;

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


        requireThrowsError("requireNullOrEmpty", () -> {
            requireNullOrEmpty(string);
        });

        requireNotThrowsError("requireNullOrEmpty", () -> {
            requireNullOrEmpty(nilString);
        });
        requireNotThrowsError("requireNullOrEmpty", () -> {
            requireNullOrEmpty(emptyString);
        });
    }

    @Test
    public void testRequireNullOrEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = new String[]{nilString, emptyString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(new String[]{string});
        });
        requireThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(new String[]{nilString, string});
        });
        requireThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(new String[]{emptyString, string});
        });
        requireThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(nilArray);
        });

        requireNotThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(array);
        });
        requireNotThrowsError("requireNullOrEmpty_Array", () -> {
            requireNullOrEmpty(emptyArray);
        });
    }

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
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(new String[]{nilString});
        });
        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(new String[]{string, emptyString});
        });
        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(nilArray);
        });
        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(emptyArray);
        });

        requireNotThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(array);
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


        requireThrowsError("requireNullOrWhiteSpace", () -> {
            requireNullOrWhiteSpace(string);
        });

        requireNotThrowsError("requireNullOrWhiteSpace", () -> {
            requireNullOrWhiteSpace(nilString);
        });
        requireNotThrowsError("requireNullOrWhiteSpace", () -> {
            requireNullOrWhiteSpace(emptyString);
        });
        requireNotThrowsError("requireNullOrWhiteSpace", () -> {
            requireNullOrWhiteSpace(whitespaceString);
        });
    }

    @Test
    public void testRequireNullOrWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = new String[]{nilString, emptyString, whitespaceString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(new String[]{string});
        });
        requireThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(new String[]{nilString, string});
        });
        requireThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(new String[]{emptyString, string});
        });
        requireThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(new String[]{whitespaceString, string});
        });
        requireThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(nilArray);
        });

        requireNotThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(array);
        });
        requireNotThrowsError("requireNullOrWhiteSpace_Array", () -> {
            requireNullOrWhiteSpace(emptyArray);
        });
    }

    @Test
    public void testRequireNotWhiteSpace()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        requireThrowsError("requireNotWhiteSpace", () -> {
            requireNotWhiteSpace(nilString);
        });
        requireThrowsError("requireNotWhiteSpace", () -> {
            requireNotWhiteSpace(emptyString);
        });
        requireThrowsError("requireNotWhiteSpace", () -> {
            requireNotWhiteSpace(whitespaceString);
        });

        requireNotThrowsError("requireNotWhiteSpace", () -> {
            requireNotWhiteSpace(string);
        });
    }

    @Test
    public void testRequireNotWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(new String[]{nilString});
        });
        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(new String[]{emptyString});
        });
        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(new String[]{whitespaceString});
        });
        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(new String[]{string, whitespaceString});
        });
        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(nilArray);
        });
        requireThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(emptyArray);
        });

        requireNotThrowsError("requireNotWhiteSpace_Array", () -> {
            requireNotWhiteSpace(array);
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

        final Validatable[] array = new Validatable[]{validObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireValid_Array", () -> {
            requireValid(new Validatable[]{notValidObject});
        });
        requireThrowsError("requireValid_Array", () -> {
            requireValid(new Validatable[]{validObject, nilObject});
        });
        requireThrowsError("requireValid_Array", () -> {
            requireValid(new Validatable[]{validObject, notValidObject});
        });
        requireThrowsError("requireValid_Array", () -> {
            requireValid(nilArray);
        });

        requireNotThrowsError("requireValid_Array", () -> {
            requireValid(array);
        });
        requireNotThrowsError("requireValid_Array", () -> {
            requireValid(emptyArray);
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

        final Validatable[] array = new Validatable[]{notValidObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireNotValid_Array", () -> {
            requireNotValid(new Validatable[]{validObject});
        });
        requireThrowsError("requireNotValid_Array", () -> {
            requireNotValid(new Validatable[]{nilObject});
        });
        requireThrowsError("requireNotValid_Array", () -> {
            requireNotValid(nilArray);
        });
        requireThrowsError("requireNotValid_Array", () -> {
            requireNotValid(emptyArray);
        });

        requireNotThrowsError("requireNotValid_Array", () -> {
            requireNotValid(array);
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

        final Validatable[] array = new Validatable[]{validObject, nilObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(new Validatable[]{notValidObject});
        });
        requireThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(new Validatable[]{validObject, notValidObject});
        });
        requireThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(nilArray);
        });

        requireNotThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(new Validatable[]{validObject, nilObject});
        });
        requireNotThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(array);
        });
        requireNotThrowsError("requireNullOrValid_Array", () -> {
            requireNullOrValid(emptyArray);
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

        final Validatable[] array = new Validatable[]{nilObject, notValidObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireNullOrNotValid_Array", () -> {
            requireNullOrNotValid(new Validatable[]{validObject});
        });
        requireThrowsError("requireNullOrNotValid_Array", () -> {
            requireNullOrNotValid(nilArray);
        });
        requireThrowsError("requireNullOrNotValid_Array", () -> {
            requireNullOrNotValid(emptyArray);
        });

        requireNotThrowsError("requireNullOrNotValid_Array", () -> {
            requireNullOrNotValid(array);
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
