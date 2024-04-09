package com.jhc;

import org.junit.jupiter.api.Test;

/**
 * @description: 测试各线程的独立性
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-02-28 17:03
 **/
public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet(){
        //先创建一个ThreadLocal对象
        ThreadLocal tl = new ThreadLocal();

        //再创建两个线程
        new Thread(() -> {
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"蓝色").start();

        new Thread(() -> {
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"绿色").start();
    }
}