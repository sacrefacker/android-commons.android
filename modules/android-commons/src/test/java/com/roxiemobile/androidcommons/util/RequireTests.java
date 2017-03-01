package com.roxiemobile.androidcommons.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.data.model.NotValidModel;
import com.roxiemobile.androidcommons.data.model.ValidModel;
import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.diagnostics.RequirementError;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public void testRequireSame()
    {
        String string = "value";
        String otherString = "otherValue";

        requireThrowsError("requireSame", () -> {
            requireSame(string, otherString);
        });

        requireNotThrowsError("requireSame", () -> {
            requireSame(string, string);
        });
    }

    @Test
    public void testRequireNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        requireThrowsError("requireNotSame", () -> {
            requireNotSame(string, string);
        });

        requireNotThrowsError("requireNotSame", () -> {
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
    public void testRequireEmpty()
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

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        requireThrowsError("requireEmpty_Array", () -> {
            requireEmpty(array);
        });

        requireNotThrowsError("requireEmpty_Array", () -> {
            requireEmpty(nilArray);
        });
        requireNotThrowsError("requireEmpty_Array", () -> {
            requireEmpty(emptyArray);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        requireThrowsError("requireEmpty_Collection", () -> {
            requireEmpty(list);
        });

        requireNotThrowsError("requireEmpty_Collection", () -> {
            requireEmpty(nilList);
        });
        requireNotThrowsError("requireEmpty_Collection", () -> {
            requireEmpty(emptyList);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        requireThrowsError("requireEmpty_Map", () -> {
            requireEmpty(map);
        });

        requireNotThrowsError("requireEmpty_Map", () -> {
            requireEmpty(nilMap);
        });
        requireNotThrowsError("requireEmpty_Map", () -> {
            requireEmpty(emptyMap);
        });
    }

    @Test
    public void testRequireAllEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String[] array = toArray(nilString, emptyString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllEmpty", () -> {
            requireAllEmpty(toArray(string));
        });
        requireThrowsError("requireAllEmpty", () -> {
            requireAllEmpty(toArray(nilString, string));
        });
        requireThrowsError("requireAllEmpty", () -> {
            requireAllEmpty(toArray(emptyString, string));
        });

        requireNotThrowsError("requireAllEmpty", () -> {
            requireAllEmpty(array);
        });
        requireNotThrowsError("requireAllEmpty", () -> {
            requireAllEmpty(nilArray);
        });
        requireNotThrowsError("requireAllEmpty", () -> {
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

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(nilArray);
        });
        requireThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(emptyArray);
        });

        requireNotThrowsError("requireNotEmpty_Array", () -> {
            requireNotEmpty(array);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        requireThrowsError("requireNotEmpty_Collection", () -> {
            requireNotEmpty(nilList);
        });
        requireThrowsError("requireNotEmpty_Collection", () -> {
            requireNotEmpty(emptyList);
        });

        requireNotThrowsError("requireNotEmpty_Collection", () -> {
            requireNotEmpty(list);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        requireThrowsError("requireNotEmpty_Map", () -> {
            requireNotEmpty(nilMap);
        });
        requireThrowsError("requireNotEmpty_Map", () -> {
            requireNotEmpty(emptyMap);
        });

        requireNotThrowsError("requireNotEmpty_Map", () -> {
            requireNotEmpty(map);
        });
    }

    @Test
    public void testRequireAllNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllNotEmpty", () -> {
            requireAllNotEmpty(toArray(string, nilString));
        });
        requireThrowsError("requireAllNotEmpty", () -> {
            requireAllNotEmpty(toArray(string, emptyString));
        });

        requireNotThrowsError("requireAllNotEmpty", () -> {
            requireAllNotEmpty(array);
        });
        requireNotThrowsError("requireAllNotEmpty", () -> {
            requireAllNotEmpty(nilArray);
        });
        requireNotThrowsError("requireAllNotEmpty", () -> {
            requireAllNotEmpty(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireBlank()
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
    public void testRequireAllBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String[] array = toArray(nilString, emptyString, whitespaceString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllBlank", () -> {
            requireAllBlank(toArray(string));
        });
        requireThrowsError("requireAllBlank", () -> {
            requireAllBlank(toArray(nilString, string));
        });
        requireThrowsError("requireAllBlank", () -> {
            requireAllBlank(toArray(emptyString, string));
        });
        requireThrowsError("requireAllBlank", () -> {
            requireAllBlank(toArray(whitespaceString, string));
        });

        requireNotThrowsError("requireAllBlank", () -> {
            requireAllBlank(array);
        });
        requireNotThrowsError("requireAllBlank", () -> {
            requireAllBlank(nilArray);
        });
        requireNotThrowsError("requireAllBlank", () -> {
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
    public void testRequireAllNotBlank()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = toArray(string, otherString);
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        requireThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(toArray(nilString));
        });
        requireThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(toArray(emptyString));
        });
        requireThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(toArray(whitespaceString));
        });
        requireThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(toArray(string, whitespaceString));
        });

        requireNotThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(array);
        });
        requireNotThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(nilArray);
        });
        requireNotThrowsError("requireAllNotBlank", () -> {
            requireAllNotBlank(emptyArray);
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
    public void testRequireAllValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllValid", () -> {
            requireAllValid(toArray(notValidObject));
        });
        requireThrowsError("requireAllValid", () -> {
            requireAllValid(toArray(validObject, nilObject));
        });
        requireThrowsError("requireAllValid", () -> {
            requireAllValid(toArray(validObject, notValidObject));
        });

        requireNotThrowsError("requireAllValid", () -> {
            requireAllValid(array);
        });
        requireNotThrowsError("requireAllValid", () -> {
            requireAllValid(nilArray);
        });
        requireNotThrowsError("requireAllValid", () -> {
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
    public void testRequireAllNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(toArray(validObject));
        });
        requireThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(toArray(nilObject));
        });
        requireThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(toArray(notValidObject, validObject));
        });

        requireNotThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(array);
        });
        requireNotThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(nilArray);
        });
        requireNotThrowsError("requireAllNotValid", () -> {
            requireAllNotValid(emptyArray);
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
    public void testRequireAllNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(validObject, nilObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(toArray(notValidObject));
        });
        requireThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(toArray(validObject, notValidObject));
        });
        requireThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(toArray(nilObject, notValidObject));
        });

        requireNotThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(toArray(validObject, nilObject));
        });
        requireNotThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(array);
        });
        requireNotThrowsError("requireAllNullOrValid", () -> {
            requireAllNullOrValid(nilArray);
        });
        requireNotThrowsError("requireAllNullOrValid", () -> {
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
    public void testRequireAllNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = toArray(nilObject, notValidObject);
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        requireThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(toArray(validObject));
        });
        requireThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(toArray(nilObject, validObject));
        });
        requireThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(toArray(notValidObject, validObject));
        });

        requireNotThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(array);
        });
        requireNotThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(nilArray);
        });
        requireNotThrowsError("requireAllNullOrNotValid", () -> {
            requireAllNullOrNotValid(emptyArray);
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
}
