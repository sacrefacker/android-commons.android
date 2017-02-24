package com.roxiemobile.androidcommons.data.mapper.adapter;

import java.util.Collection;
import java.util.List;

public interface EnumStringConverter<T>
{
// MARK: - Methods

    /**
     * TODO
     */
    T getValueForKey(String key);

    /**
     * TODO
     */
    List<T> getValuesForKeys(Collection<String> keys);

    /**
     * TODO
     */
    String getKeyForValue(T value);

    /**
     * TODO
     */
    List<String> getKeysForValues(Collection<T> values);
}
