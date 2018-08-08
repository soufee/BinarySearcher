package multithread;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private Lock l;
    private AtomicInteger failCounter = new AtomicInteger(0);
    private volatile int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        l = new ReentrantLock();
    }

    public void incFailedTransferCount(){
        System.out.println(failCounter.incrementAndGet());
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return l;
    }

    @Override
    public String toString() {
        return "balance=" + balance;
    }
}
