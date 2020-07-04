package aziz.util;

import org.jetbrains.annotations.NotNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ConcurrencyUtil {

    private ConcurrencyUtil() {
    }


    @NotNull
    public static ExecutorService newExecutorService(int numberOfThreads) {
        return Executors.newFixedThreadPool(Math.min(numberOfThreads, 100),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                });
    }

}
