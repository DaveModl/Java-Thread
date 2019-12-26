package com.thread.book.ch3.immutable;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public class Stooges {
    //Set是一个可变对象，stooges是一个final的不可变对象，所有的对象状态都通过一个final域访问
    private final Set<String> stooges = new HashSet<>();

    public Stooges(){
        stooges.add("A");
        stooges.add("B");
        stooges.add("C");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }
}
