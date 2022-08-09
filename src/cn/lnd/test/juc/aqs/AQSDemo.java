package cn.lnd.test.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/9 22:11
 */
public class AQSDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            // TODO 被锁住的业务逻辑
        }finally {
            lock.unlock();
        }

        /*
        * A B C 三个顾客，去银行办理业务，但是只有一个窗口，A先到，此时窗口空无一人，他优先获取锁，办理业务
        * 但是 A 耗时严重，估计长期占有窗口
        * */
        new Thread(()->{
            lock.lock();
            System.out.println("A come in");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "A").start();


        /*
        * B 是第2个线程，B一看到受理窗口被A占用，只能去候客区等待（进入AQS队列），等待A办理完成后，
        * 尝试去抢占受理窗口
        * */
        new Thread(()->{
            lock.lock();
            System.out.println("B come in");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "B").start();


        /*
         * C是第3个线程，C一看到受理窗口被A占用，只能去候客区等待（进入AQS队列），等待A办理完成后，
         * 尝试去抢占受理窗口，但是在它前面还有一个顾客B
         * */
        new Thread(()->{
            lock.lock();
            System.out.println("C come in");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "B").start();
    }
}
