package com.roxiemobile.androidcommons.logging;

import com.roxiemobile.androidcommons.util.ArrayUtils;

public class ChainedLogger implements Logger.Contract
{
// MARK: - Construction

    public ChainedLogger(Logger.Contract... loggers) {
        // Init instance variables
        mLoggers = ArrayUtils.isNullOrEmpty(loggers) ? new Logger.Contract[]{} : loggers;
    }

// MARK: - Methods

    @Override
    public void v(String tag, String msg) {
        for (Logger.Contract logger : mLoggers) {
            logger.v(tag, msg);
        }
    }

    @Override
    public void d(String tag, String msg) {
        for (Logger.Contract logger : mLoggers) {
            logger.d(tag, msg);
        }
    }

    @Override
    public void i(String tag, String msg) {
        for (Logger.Contract logger : mLoggers) {
            logger.i(tag, msg);
        }
    }

    @Override
    public void w(String tag, String msg) {
        for (Logger.Contract logger : mLoggers) {
            logger.w(tag, msg);
        }
    }

    @Override
    public void w(String tag, String msg, Throwable err) {
        for (Logger.Contract logger : mLoggers) {
            logger.w(tag, msg, err);
        }
    }

    @Override
    public void w(String tag, Throwable err) {
        for (Logger.Contract logger : mLoggers) {
            logger.w(tag, err);
        }
    }

    @Override
    public void e(String tag, String msg) {
        for (Logger.Contract logger : mLoggers) {
            logger.e(tag, msg);
        }
    }

    @Override
    public void e(String tag, String msg, Throwable err) {
        for (Logger.Contract logger : mLoggers) {
            logger.e(tag, msg, err);
        }
    }

    @Override
    public void e(String tag, Throwable err) {
        for (Logger.Contract logger : mLoggers) {
            logger.e(tag, err);
        }
    }

// MARK: - Variables

    private final Logger.Contract[] mLoggers;

}
