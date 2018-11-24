package com.jvm.classtest;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ReferenceDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<?,?> cache = new ConcurrentHashMap<String,SoftReference<?>>();
    }
}
