package com.jackhuang.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建间向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        singleLinkedList.list();

        //删除节点
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        System.out.println("删除节点后的链表");
        singleLinkedList.list();

        //获取链表节点数
        System.out.printf("节点数为:%d",getLength(singleLinkedList.getHead()));
    }

    //获取单链表的节点个数（如果是带头结点的链表，不需要统计头节点）
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        //辅助变量
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }



}




//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //初始头节点
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead(){
        return head;
    }
    //添加节点到单向链表
    //1。找到最后一个节点
    //2。将最后最后节点的next 指向新的节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            //
            if (temp.next == null) {
                break;
            } else {
                temp = temp.next;
            }
        }
        temp.next = heroNode;
    }

    //根据排名添加英雄
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//flag标识添加的编号是否存在，默认为false

        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag == true){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入",heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;//已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            //没有找到
            System.out.printf("没有找到编号为%d的节点",newHeroNode.no);
        }
    }

    //删除节点
    //利用temp节点 temp.next.no 与 需要删除的节点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//是否找到待删除的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

    //显示链表
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重写toString()

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
