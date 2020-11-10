package Thread;

import pojo.Account;

public class DrawThread extends Thread {
    //模拟账户
    private Account account;
    private double drawAmount;

    public DrawThread(String name,Account account,double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(drawAmount);
        }

    }
}
