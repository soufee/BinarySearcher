import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

public class Utils {
    private static final int CNT = 3;
    private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    private static int random_work_time = 100;

    public static void main(String[] args) throws Exception {
Thread[] threads = new Thread[2];
threads[0] = new Thread(new Producer());
threads[1] = new Thread(new Consumer());
        for (Thread t:threads) {
            t.start();
        }
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < CNT; i++) {
                try {
                    System.out.println("иду продюссировать");
                    Thread.sleep(random_work_time);
                    queue.put(i);
                    System.out.println("закончил");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("иду забирать");
                int i = queue.take();
                Thread.sleep(random_work_time);
                System.out.println("забрал");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
