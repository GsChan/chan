package com.gitsome.chan;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : CGS
 * Date : 2018-04-01
 * Time : 19:03
 */
public class Test implements Runnable
{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run()
    {
        for (int j = 0; j < 100; j++)
        {
            lock.lock();
            try
            {
                i++;
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            finally
            {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        Test test = new Test();
        Thread t1 = new Thread(test);
        t1.setName("线程1");

        Thread t2 = new Thread(test);
        t2.setName("线程2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
