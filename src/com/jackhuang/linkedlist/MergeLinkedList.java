package com.jackhuang.linkedlist;

import com.jackhuang.linkedlist.HeroNode;
import com.jackhuang.linkedlist.SingleLinkedList;

/**
 * 使用并归排序实现单链表排序
 */
public class MergeLinkedList {
    public static void main(String[] args) {
        //merge1 链表
        SingleLinkedList mergeLinkedList1 = new SingleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(5, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(7, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        mergeLinkedList1.addByOrder(hero1);
        mergeLinkedList1.addByOrder(hero2);
        mergeLinkedList1.addByOrder(hero3);
        mergeLinkedList1.addByOrder(hero4);

        //merge2 链表
        SingleLinkedList mergeLinkedList2 = new SingleLinkedList();
        HeroNode hero8 = new HeroNode(2, "宋江", "及时雨");
        HeroNode hero5 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero6 = new HeroNode(6, "吴用", "智多星");
        HeroNode hero7 = new HeroNode(8, "林冲", "豹子头");

        mergeLinkedList2.addByOrder(hero5);
        mergeLinkedList2.addByOrder(hero6);
        mergeLinkedList2.addByOrder(hero7);
        mergeLinkedList2.addByOrder(hero8);

        System.out.println("链表一原链表输出");
        mergeLinkedList1.list();
        System.out.println("链表二原链表输出");
        mergeLinkedList2.list();

        //使用并归排序进行排序后后的链表
        SingleLinkedList newList = MergeLinkedListMethod(mergeLinkedList1,mergeLinkedList2);
        System.out.println("使用并归排序进行排序后后的链表");
        newList.list();


    }

    public static SingleLinkedList MergeLinkedListMethod(SingleLinkedList mLL1,SingleLinkedList mLL2){
        SingleLinkedList newList = new SingleLinkedList();
//        HeroNode head = newList.getHead();
        HeroNode newListNode = newList.getHead();
        HeroNode cur1 = mLL1.getHead().next;
        HeroNode cur2 = mLL2.getHead().next;
        while (cur1 != null && cur2 != null){
            if(cur1.no <= cur2.no){
                newListNode.next = cur1;
                newListNode = newListNode.next;
                cur1 = cur1.next;
            }else{
                newListNode.next = cur2;
                newListNode = newListNode.next;
                cur2 = cur2.next;
            }
        }
        if(cur1 == null && cur2!=null){
            newListNode.next = cur2;
        }else if(cur1 != null && cur2 ==null){
            newListNode.next = cur1;
        }
        return newList;
    }
}
