package com.swiftAssess.utils;

import java.util.function.Supplier;

public class Retry {
    public static <T> T attempt(int times, long sleepMs, Supplier<T> block) {
        RuntimeException last = null;
        for (int i=0;i<times;i++) {
            try { return block.get(); }
            catch (RuntimeException e) {
                last = e;
                try { Thread.sleep(sleepMs);} catch (InterruptedException ignored) {}
            }
        }
        throw last;
    }
}
