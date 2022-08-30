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

        /*
        * A、B、C 三个线程争夺临界资源（锁），锁只有一把，A先到，此时锁处于空闲状态，它可以获取锁，执行业务。
        * 假设线程A执行业务耗时非常长，需要长期占有所资源。此时如果B线程再进来时就会阻塞，阻塞就会被加入AQS的等待队列。
        * */
        new Thread(()->{
            lock.lock();
            System.out.println("A come in");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "A").start();


        /*
        * B 是第2个线程，B一看到锁被A占用，只能去候客区等待（进入AQS队列），等待A办理完成后，
        * 尝试去抢占锁
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
         * C是第3个线程，C一看到锁被A占用，只能进入AQS队列，等待A办理完成后，
         * 尝试去抢占锁资源，但是在它前面还有一个顾客B
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
