package com.demo01;

public class LambdaQs {

    //调用Eatable对象
    public void eat(Eatable a) {
        System.out.println(a);
        a.taste();
    }
    //调用Flyable对象
    public void drive(Flyable flyable) {
        System.out.println(flyable);
        flyable.fly("嘻嘻哈哈");
    }

    //调用Addable对象
    public void test(Addable addable) {
        System.out.println(addable.add(5,3));
    }

    public static void main(String[] args) {
        LambdaQs lam = new LambdaQs();
        lam.eat(() -> System.out.println("苹果味道不错"));
        lam.drive((weather) -> {
            System.out.println(" lam表达式参数 " + weather);
            System.out.println("直升机降落");
        });
        lam.test((a, b) -> a + b);
    }
}

interface Eatable{
    void taste();
}

interface Flyable{
    void fly(String weather);
}

interface Addable{
    int add(int a, int b);
}