package pojo;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可以显示的来保证线程同步
 *  显示的同步监视器
 */
public class Account {
    //ReentrantLock(可重入锁)
    private final ReentrantLock lock = new ReentrantLock();
    //获得指定lock对象对应的Condition
    private final Condition lockCondition = lock.newCondition();
    private String AccountNo;
    private double balance;
    private boolean flag = false;

    public Account() {
    }

    public Account(String accountNo, double balance) {
        AccountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }


//    ReentrantLock
    public  void draw(double drawAmount) {
        lock.lock();
        try {
            //false 标识账户没有钱
            if (!flag) {
                lockCondition.await();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName()+" 取钱"+drawAmount);
                balance -= drawAmount;
                System.out.println("余额为：" + balance);
                flag = false;
                lockCondition.signalAll();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void deposit(double depositAmount) {
        lock.lock();
        try {

            if (flag) {
                lockCondition.await();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为：" + balance);
                flag = true;
                lockCondition.signalAll();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


/*//Thread类
//提供一个线程安全的draw()方法来完成取钱操作
    public synchronized void draw(double drawAmount) {
        try {
            //false 标识账户没有钱
            if (!flag) {
                wait();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName()+" 取钱"+drawAmount);
                balance -= drawAmount;
                System.out.println("余额为：" + balance);
                flag = false;
                notifyAll();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void deposit(double depositAmount) {
        try {
            if (flag) {
                wait();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为：" + balance);
                flag = true;
                notifyAll();
            }

        } catch (Exception e) {

        }
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                Objects.equals(AccountNo, account.AccountNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(AccountNo, balance);
    }
}
