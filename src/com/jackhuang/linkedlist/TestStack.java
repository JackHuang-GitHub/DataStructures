package com.jackhuang.linkedlist;

import java.util.Stack;

//演示栈的基本使用 Stack
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("jack");
        stack.add("rose");
        stack.add("smith");

        //出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
