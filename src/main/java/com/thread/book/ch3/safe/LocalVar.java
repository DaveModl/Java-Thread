package com.thread.book.ch3.safe;




import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class LocalVar {
    Ark ark;
    public int localTheArk(Collection<A> collections){
        SortedSet<A> ss;
        int num = 0;
        A collection = null;
        //栈封闭
        ss = new TreeSet<A>((Collection<? extends A>) new SpecialComparator());
        ss.addAll(collections);
        for (A a : ss) {
            if (collection == null || !collection.isSomeStr(a)){
                collection = a;
            }else{
                ark.load(new Aa(collection,a));
                ++num;
                collection = null;
            }
        }
        return num;
    }
}
