package com.roxiemobile.androidcommons.data.model;

public final class NotValidModel implements Validatable
{
    @Override
    public boolean isValid() {
        return false;
    }
}
