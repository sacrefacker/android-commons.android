package com.roxiemobile.androidcommons.diagnostics;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.data.model.NotValidModel;
import com.roxiemobile.androidcommons.data.model.ValidModel;
import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.util.StringUtils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public final class GuardTests
{
// MARK: - Tests

    @Test
    public void testRequireTrue() {
        String method = "Guard.isTrue";

        guardThrowsError(method, () -> {
            Guard.isTrue(1 > 2);
        });

        guardNotThrowsError(method, () -> {
            Guard.isTrue(2 > 1);
        });
    }

    @Test
    public void testRequireFalse() {
        String method = "Guard.isFalse";

        guardThrowsError(method, () -> {
            Guard.isFalse(2 > 1);
        });

        guardNotThrowsError(method, () -> {
            Guard.isFalse(1 > 2);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireEqual()
    {
        final String string = "value";
        final String nilString = null;


        guardThrowsError("equal", () -> {
            Guard.equal(1, 2);
        });
        guardThrowsError("equal", () -> {
            Guard.equal(string, nilString);
        });

        guardNotThrowsError("equal", () -> {
            Guard.equal(2, 2);
        });
        guardNotThrowsError("equal", () -> {
            Guard.equal(string, string);
        });
    }

    @Test
    public void testRequireNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        guardThrowsError("notEqual", () -> {
            Guard.notEqual(2, 2);
        });
        guardThrowsError("notEqual", () -> {
            Guard.notEqual(string, string);
        });

        guardNotThrowsError("notEqual", () -> {
            Guard.notEqual(1, 2);
        });
        guardNotThrowsError("notEqual", () -> {
            Guard.notEqual(string, nilString);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireSame()
    {
        String string = "value";
        String otherString = "otherValue";

        guardThrowsError("same", () -> {
            Guard.same(string, otherString);
        });

        guardNotThrowsError("same", () -> {
            Guard.same(string, string);
        });
    }

    @Test
    public void testRequireNotSame()
    {
        String string = "value";
        String otherString = "otherValue";

        guardThrowsError("notSame", () -> {
            Guard.notSame(string, string);
        });

        guardNotThrowsError("notSame", () -> {
            Guard.notSame(string, otherString);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNull()
    {
        guardThrowsError("isNull", () -> {
            Guard.isNull(2);
        });

        guardNotThrowsError("isNull", () -> {
            Guard.isNull(null);
        });
    }

    @Test
    public void testRequireNotNull()
    {
        guardThrowsError("notNull", () -> {
            Guard.notNull(null);
        });

        guardNotThrowsError("notNull", () -> {
            Guard.notNull(2);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        guardThrowsError("empty", () -> {
            Guard.empty(string);
        });

        guardNotThrowsError("empty", () -> {
            Guard.empty(nilString);
        });
        guardNotThrowsError("empty", () -> {
            Guard.empty(emptyString);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        guardThrowsError("empty_Array", () -> {
            Guard.empty(array);
        });

        guardNotThrowsError("empty_Array", () -> {
            Guard.empty(nilArray);
        });
        guardNotThrowsError("empty_Array", () -> {
            Guard.empty(emptyArray);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        guardThrowsError("empty_Collection", () -> {
            Guard.empty(list);
        });

        guardNotThrowsError("empty_Collection", () -> {
            Guard.empty(nilList);
        });
        guardNotThrowsError("empty_Collection", () -> {
            Guard.empty(emptyList);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        guardThrowsError("empty_Map", () -> {
            Guard.empty(map);
        });

        guardNotThrowsError("empty_Map", () -> {
            Guard.empty(nilMap);
        });
        guardNotThrowsError("empty_Map", () -> {
            Guard.empty(emptyMap);
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


        guardThrowsError("allEmpty", () -> {
            Guard.allEmpty(toArray(string));
        });
        guardThrowsError("allEmpty", () -> {
            Guard.allEmpty(toArray(nilString, string));
        });
        guardThrowsError("allEmpty", () -> {
            Guard.allEmpty(toArray(emptyString, string));
        });

        guardNotThrowsError("allEmpty", () -> {
            Guard.allEmpty(array);
        });
        guardNotThrowsError("allEmpty", () -> {
            Guard.allEmpty(nilArray);
        });
        guardNotThrowsError("allEmpty", () -> {
            Guard.allEmpty(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNotEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        guardThrowsError("notEmpty", () -> {
            Guard.notEmpty(nilString);
        });
        guardThrowsError("notEmpty", () -> {
            Guard.notEmpty(emptyString);
        });

        guardNotThrowsError("notEmpty", () -> {
            Guard.notEmpty(string);
        });

        // --

        final String[] array = toArray("value", "otherValue");
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};

        guardThrowsError("notEmpty_Array", () -> {
            Guard.notEmpty(nilArray);
        });
        guardThrowsError("notEmpty_Array", () -> {
            Guard.notEmpty(emptyArray);
        });

        guardNotThrowsError("notEmpty_Array", () -> {
            Guard.notEmpty(array);
        });

        // --

        final List<String> list = Arrays.asList("value", "otherValue");
        final List<String> nilList = null;
        final List<String> emptyList = Collections.emptyList();

        guardThrowsError("notEmpty_Collection", () -> {
            Guard.notEmpty(nilList);
        });
        guardThrowsError("notEmpty_Collection", () -> {
            Guard.notEmpty(emptyList);
        });

        guardNotThrowsError("notEmpty_Collection", () -> {
            Guard.notEmpty(list);
        });

        // --

        final Map<String, String> map = Stream.of(list).collect(Collectors.toMap(item -> item, item -> item));
        final Map<String, String> nilMap = null;
        final Map<String, String> emptyMap = Collections.emptyMap();

        guardThrowsError("notEmpty_Map", () -> {
            Guard.notEmpty(nilMap);
        });
        guardThrowsError("notEmpty_Map", () -> {
            Guard.notEmpty(emptyMap);
        });

        guardNotThrowsError("notEmpty_Map", () -> {
            Guard.notEmpty(map);
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


        guardThrowsError("allNotEmpty", () -> {
            Guard.allNotEmpty(toArray(string, nilString));
        });
        guardThrowsError("allNotEmpty", () -> {
            Guard.allNotEmpty(toArray(string, emptyString));
        });

        guardNotThrowsError("allNotEmpty", () -> {
            Guard.allNotEmpty(array);
        });
        guardNotThrowsError("allNotEmpty", () -> {
            Guard.allNotEmpty(nilArray);
        });
        guardNotThrowsError("allNotEmpty", () -> {
            Guard.allNotEmpty(emptyArray);
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


        guardThrowsError("blank", () -> {
            Guard.blank(string);
        });

        guardNotThrowsError("blank", () -> {
            Guard.blank(nilString);
        });
        guardNotThrowsError("blank", () -> {
            Guard.blank(emptyString);
        });
        guardNotThrowsError("blank", () -> {
            Guard.blank(whitespaceString);
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


        guardThrowsError("allBlank", () -> {
            Guard.allBlank(toArray(string));
        });
        guardThrowsError("allBlank", () -> {
            Guard.allBlank(toArray(nilString, string));
        });
        guardThrowsError("allBlank", () -> {
            Guard.allBlank(toArray(emptyString, string));
        });
        guardThrowsError("allBlank", () -> {
            Guard.allBlank(toArray(whitespaceString, string));
        });

        guardNotThrowsError("allBlank", () -> {
            Guard.allBlank(array);
        });
        guardNotThrowsError("allBlank", () -> {
            Guard.allBlank(nilArray);
        });
        guardNotThrowsError("allBlank", () -> {
            Guard.allBlank(emptyArray);
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


        guardThrowsError("notBlank", () -> {
            Guard.notBlank(nilString);
        });
        guardThrowsError("notBlank", () -> {
            Guard.notBlank(emptyString);
        });
        guardThrowsError("notBlank", () -> {
            Guard.notBlank(whitespaceString);
        });

        guardNotThrowsError("notBlank", () -> {
            Guard.notBlank(string);
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


        guardThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(toArray(nilString));
        });
        guardThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(toArray(emptyString));
        });
        guardThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(toArray(whitespaceString));
        });
        guardThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(toArray(string, whitespaceString));
        });

        guardNotThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(array);
        });
        guardNotThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(nilArray);
        });
        guardNotThrowsError("allNotBlank", () -> {
            Guard.allNotBlank(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        guardThrowsError("valid", () -> {
            Guard.valid(notValidObject);
        });

        guardNotThrowsError("valid", () -> {
            Guard.valid(validObject);
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


        guardThrowsError("allValid", () -> {
            Guard.allValid(toArray(notValidObject));
        });
        guardThrowsError("allValid", () -> {
            Guard.allValid(toArray(validObject, nilObject));
        });
        guardThrowsError("allValid", () -> {
            Guard.allValid(toArray(validObject, notValidObject));
        });

        guardNotThrowsError("allValid", () -> {
            Guard.allValid(array);
        });
        guardNotThrowsError("allValid", () -> {
            Guard.allValid(nilArray);
        });
        guardNotThrowsError("allValid", () -> {
            Guard.allValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable notValidObject = new NotValidModel();


        guardThrowsError("notValid", () -> {
            Guard.notValid(validObject);
        });

        guardNotThrowsError("notValid", () -> {
            Guard.notValid(notValidObject);
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


        guardThrowsError("allNotValid", () -> {
            Guard.allNotValid(toArray(validObject));
        });
        guardThrowsError("allNotValid", () -> {
            Guard.allNotValid(toArray(nilObject));
        });
        guardThrowsError("allNotValid", () -> {
            Guard.allNotValid(toArray(notValidObject, validObject));
        });

        guardNotThrowsError("allNotValid", () -> {
            Guard.allNotValid(array);
        });
        guardNotThrowsError("allNotValid", () -> {
            Guard.allNotValid(nilArray);
        });
        guardNotThrowsError("allNotValid", () -> {
            Guard.allNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        guardThrowsError("nullOrValid", () -> {
            Guard.nullOrValid(notValidObject);
        });

        guardNotThrowsError("nullOrValid", () -> {
            Guard.nullOrValid(validObject);
        });
        guardNotThrowsError("nullOrValid", () -> {
            Guard.nullOrValid(nilObject);
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


        guardThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(toArray(notValidObject));
        });
        guardThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(toArray(validObject, notValidObject));
        });
        guardThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(toArray(nilObject, notValidObject));
        });

        guardNotThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(toArray(validObject, nilObject));
        });
        guardNotThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(array);
        });
        guardNotThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(nilArray);
        });
        guardNotThrowsError("allNullOrValid", () -> {
            Guard.allNullOrValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testRequireNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        guardThrowsError("nullOrNotValid", () -> {
            Guard.nullOrNotValid(validObject);
        });

        guardNotThrowsError("nullOrNotValid", () -> {
            Guard.nullOrNotValid(nilObject);
        });
        guardNotThrowsError("nullOrNotValid", () -> {
            Guard.nullOrNotValid(notValidObject);
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


        guardThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(toArray(validObject));
        });
        guardThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(toArray(nilObject, validObject));
        });
        guardThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(toArray(notValidObject, validObject));
        });

        guardNotThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(array);
        });
        guardNotThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(nilArray);
        });
        guardNotThrowsError("allNullOrNotValid", () -> {
            Guard.allNullOrNotValid(emptyArray);
        });
    }

// MARK: - Private Methods

    private <T> void guardThrowsError(String method, Class<T> classOfT, Runnable task) {
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
                Assert.fail(method + ": Unknown error is thrown");
            }
        }
        else {
            Assert.fail(method + ": Method not thrown an error");
        }
    }

    private void guardThrowsError(String method, Runnable task) {
        guardThrowsError(method, GuardError.class, task);
    }

// --

    private <T> void guardNotThrowsError(@NonNull String method, @NonNull Class<T> classOfT, @NonNull Runnable task) {
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
                Assert.fail(method + ": Method thrown an error");
            }
            else {
                Assert.fail(method + ": Unknown error is thrown");
            }
        }
        else {
            // Do nothing
        }
    }

    private void guardNotThrowsError(String method, Runnable task) {
        guardNotThrowsError(method, GuardError.class, task);
    }

// --

    private static void checkArgument(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
