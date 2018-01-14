package com.cjw.demo1_reference;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用测试
 * Created by chenjiawei on 2018/1/13.
 */

public class ReferenceTest {

    @Test
    public void testSoftRef() throws Exception {

        Object obj = new Object();

        // 引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        // 软引用
        // 内存不足时,会清除软引用中的对象,将其放入到引用队列中
        SoftReference<Object> softReference = new SoftReference<>(obj, referenceQueue);
    }

    @Test
    public void testWeakRef() throws Exception {

        Object obj = new Object();
        // 引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        // 弱引用
        WeakReference<Object> weakReference = new WeakReference<>(obj, referenceQueue);

        printMessage(weakReference, referenceQueue);

        obj = null;

        // 请求gc,但不会马上被回收,需要等待gc扫描到该对象
        System.gc();
        Thread.sleep(1_000);

        printMessage(weakReference, referenceQueue);
    }

    public void printMessage(Reference reference, ReferenceQueue referenceQueue) {
        Object obj = reference.get();
        System.out.println(obj);

        Reference pollObj = referenceQueue.poll();
        System.out.println(pollObj);
    }
}
