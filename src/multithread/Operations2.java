package multithread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Shoma on 01.08.2018.
 */
public class Operations2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        final Account a = new Account(3000);
        final Account b = new Account(2000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(new Transfer(a, b, new Random().nextInt(400), latch));
        }
        latch.countDown();
        service.shutdown();
    }
}
