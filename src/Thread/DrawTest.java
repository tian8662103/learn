package Thread;

import pojo.Account;

public class DrawTest {
    public static void main(String[] args) {
        Account account = new Account("12345",0);
        new DepositThread("存款者1：",account, 800).start();
        new DrawThread("A1",account, 800).start();
        new DepositThread("存款者2：",account, 800).start();
        new DepositThread("存款者3：",account, 800).start();

//        new DrawThread("A2",account, 800).start();
//        new DrawThread("A3",account, 800).start();
    }
}
