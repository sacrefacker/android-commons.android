package com.roxiemobile.androidcommons.logging;

import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.logging.Logger.Contract;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ChainedLogger implements Logger.Contract
{
// MARK: - Construction

    public ChainedLogger(Logger.Contract... loggers) {
        // Init instance variables
        mLoggers = (loggers == null) ? Collections.EMPTY_LIST : Arrays.asList(loggers);
    }

// MARK: - Methods

    @Override
    public void v(String tag, String msg) {
        Stream.of(mLoggers).forEach(logger -> logger.v(tag, msg));
    }

    @Override
    public void d(String tag, String msg) {
        Stream.of(mLoggers).forEach(logger -> logger.d(tag, msg));
    }

    @Override
    public void i(String tag, String msg) {
        Stream.of(mLoggers).forEach(logger -> logger.i(tag, msg));
    }

    @Override
    public void w(String tag, String msg) {
        Stream.of(mLoggers).forEach(logger -> logger.w(tag, msg));
    }

    @Override
    public void w(String tag, String msg, Throwable err) {
        Stream.of(mLoggers).forEach(logger -> logger.w(tag, msg, err));
    }

    @Override
    public void w(String tag, Throwable err) {
        Stream.of(mLoggers).forEach(logger -> logger.w(tag, err));
    }

    @Override
    public void e(String tag, String msg) {
        Stream.of(mLoggers).forEach(logger -> logger.e(tag, msg));
    }

    @Override
    public void e(String tag, String msg, Throwable err) {
        Stream.of(mLoggers).forEach(logger -> logger.e(tag, msg, err));
    }

    @Override
    public void e(String tag, Throwable err) {
        Stream.of(mLoggers).forEach(logger -> logger.e(tag, err));
    }

// MARK: - Variables

    private final List<Contract> mLoggers;
}
