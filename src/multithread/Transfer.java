package multithread;

import javax.naming.InsufficientResourcesException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shoma on 01.08.2018.
 */
public class Transfer implements Callable<Boolean> {
    private Account accountFrom;
    private Account accountTo;
    private int amount;
    CountDownLatch latch;
    private static final int WAIT_SEC = 3;

    public Transfer(Account accountFrom, Account accountTo, int amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfer(Account accountFrom, Account accountTo, int amount, CountDownLatch latch) {
        this(accountFrom, accountTo, amount);
        this.latch = latch;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("Waiting");
        latch.await();
        if (accountFrom.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
            try {
                if (accountTo.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                    try {
                        if (accountFrom.getBalance() < amount) throw new InsufficientResourcesException();
                        accountFrom.withdraw(amount);
                        accountTo.deposit(amount);
                        System.out.println("Перевод денег закончился. Аккаунт а = " + accountFrom.getBalance() + ". Аккаунт б = " + accountTo.getBalance());
                        return true;
                    } finally {
                        accountTo.getLock().unlock();
                    }
                } else {
                    System.out.println("Не получилось захватить монитор b");
                    accountTo.incFailedTransferCount();
                    return false;
                }
            } finally {
                accountFrom.getLock().unlock();
            }
        } else {
            System.out.println("Не получилось захватить монитор a");
            accountFrom.incFailedTransferCount();
            return false;

        }


    }
}
