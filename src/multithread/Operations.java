package multithread;

import javax.naming.InsufficientResourcesException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Operations {

    private static final int WAIT_SEC = 3;
    public static void main(String[] args) throws InsufficientResourcesException, InterruptedException {
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(() -> {
            try {
                transfer(a, b, 500);
            } catch (InsufficientResourcesException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        transfer(b, a, 300);
    }

    private static void transfer(Account a, Account b, int amount) throws InsufficientResourcesException, InterruptedException {
        if (a.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
            try {
                if (b.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                    try {
                        if (a.getBalance() < amount) throw new InsufficientResourcesException();
                        a.withdraw(amount);
                        b.deposit(amount);
                        System.out.println("Перевод денег закончился. Аккаунт а = " + a.getBalance() + ". Аккаунт б = " + b.getBalance());
                    } finally {
                        b.getLock().unlock();
                    }
                } else {
                    System.out.println("Не получилось захватить монитор b");
                    b.incFailedTransferCount();
                }
            } finally {
                a.getLock().unlock();
            }
        } else {
            System.out.println("Не получилось захватить монитор a");
            a.incFailedTransferCount();

        }
    }
}
