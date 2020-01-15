package com.thread.clas.aba;

public class Node {
        public final String value;
        public Node next;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
}
