package com.roxiemobile.androidcommons.concurrent;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An executor service that executes its tasks on the main thread.
 * <p>
 * Shutting down this executor is not supported.
 */
public final class MainThreadExecutor extends AbstractExecutorService
{
// MARK: - Construction

    public static class SingletonHolder {
        public static final MainThreadExecutor SHARED_INSTANCE = new MainThreadExecutor();
    }

    public static MainThreadExecutor instance() {
        return SingletonHolder.SHARED_INSTANCE;
    }

    private MainThreadExecutor() {
        // Do nothing
    }

// MARK: - Methods

    @Override
    public void execute(@NonNull Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    /**
     * Not supported and throws an exception when used.
     */
    @Deprecated
    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported and throws an exception when used.
     */
    @Deprecated
    @Override
    public @NonNull List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * Not supported and throws an exception when used.
     */
    @Deprecated
    @Override
    public boolean awaitTermination(long l, @NonNull TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

}
