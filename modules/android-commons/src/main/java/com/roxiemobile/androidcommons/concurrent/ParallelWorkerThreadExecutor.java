package com.roxiemobile.androidcommons.concurrent;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An executor service that executes its tasks on the background thread.
 * <p>
 * Shutting down this executor is not supported.
 */
public final class ParallelWorkerThreadExecutor extends AbstractExecutorService
{
// MARK: - Construction

    public static class SingletonHolder {
        public static final ParallelWorkerThreadExecutor SHARED_INSTANCE = new ParallelWorkerThreadExecutor();
    }

    public static ParallelWorkerThreadExecutor instance() {
        return SingletonHolder.SHARED_INSTANCE;
    }

    private ParallelWorkerThreadExecutor() {
        // Do nothing
    }

// MARK: - Methods

    @Override
    public void execute(@NonNull Runnable runnable) {
        sThreadPoolExecutor.execute(runnable);
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

// MARK: - Constants

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;

// MARK: - Variables

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(final @NonNull Runnable runnable) {
            String threadName = ParallelWorkerThreadExecutor.class.getSimpleName() + " #" + mCount.getAndIncrement();

            return new Thread(() -> {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                runnable.run();
            }, threadName);
        }
    };

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(128);

    // An {@link Executor} that can be used to execute tasks in parallel.
    private final Executor sThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

}
