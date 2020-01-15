package com.thread.clas.casaatomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class FieldUpdater {
        private static AtomicIntegerFieldUpdater<User> atom =
                AtomicIntegerFieldUpdater.newUpdater(User.class, "id");

        public static void main(String[] args) {

            User user = new User(100, 100,"Kody");

            atom.addAndGet(user, 50);
            System.out.println("addAndGet(user, 50)             调用后值变为：" + user);

        }
    }

    class User {
        volatile int id;
        volatile int age;

        private String name;

        public User(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "id：" + id + " " + "age：" + age;
        }
    }
