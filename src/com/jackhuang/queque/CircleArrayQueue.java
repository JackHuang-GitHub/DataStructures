package com.jackhuang.queque;

import java.util.Scanner;


/**
 * 就是是个环形数组以3个数据队列为列子,fron永远指向队列中第一个数据，rear 指向尾部后一个数据
 * arr[] = [][][][]
 * 满 return (rear + 1) % maxSize == front;
 * 空 fron == rear
 * 添加 arr[rear] = n;
 *         //rear 后移
 *         rear = (rear + 1) % maxSize;
 * 获取front的变化  front = (front + 1) % maxSize;
 * 队列长度size (rear + maxSize - front) % maxSize;
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组模拟环形队列的案例～～～");
        //初始队列
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出队列");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出～～");

    }
}

class CircleArray{
    private int maxSize;//表示数组的最大容量

    //front 变量：front指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
    //front 的初始值为0
    private int front;
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数组～");
            return;
        }
        arr[rear] = n;
        //rear 后移
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能读数据~");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据～");
            return;
        }
        for (int i = 0; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d", i % maxSize, arr[i % maxSize]);
            System.out.println();
        }

    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~");
        }
        return arr[front];
    }

}
