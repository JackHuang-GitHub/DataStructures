package com.jackhuang.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {
    public static void main(String[] args) {
        //创建原始二维数据 11*11
        //0：表示没有棋子，1：表示黑子 2：表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //从文件中读取稀疏数组

        //将二维数据转稀疏数组
        //1.遍历数据，得到非0数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到 sparaseArr中
        int count = 0; //用于记录是第几个非0的数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数据的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        //稀疏写入到文件中
        saveArray(sparseArr);

        //稀疏数组恢复为二维数组
        /*
         * 1.根据稀疏数组第一行的数据，创建原始的二维数组
         * 2.读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         */
        int chessArr2[][] = new int[sparseArr[0][1]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //通过io读取稀疏数组
        System.out.println("通过io读取稀疏数组:");
        int arr[][] = (int[][])readArray().clone();
        for(int[] row : arr){
            for(int data :row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


    }

    public static void saveArray(int[][] sparesArr) {
        FileWriter fw = null;
        try {
            //1.写入到哪个文件
            File file = new File("data/SparseArray.txt");//这里用的相对路径(本工程下)
            //2.创建输出流对象,传入文件对象
            fw = new FileWriter(file);
            //3.如果该文件不存在就创建
            if (!file.exists()) {
                file.createNewFile();
            }
            //4.写入操作:遍历将数组写入txt文件中:
            for (int i = 0; i < sparesArr.length; i++) {
                for (int j = 0; j < sparesArr[0].length-1; j++) {
                    fw.write(sparesArr[i][j]+",");
                }
                //数组最后一列后面不加","
                fw.write(sparesArr[i][sparesArr[0].length-1]+"");
                //换行
                fw.write("\n");
            }
            //刷新写入文件
            fw.flush();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
    public static int[][] readArray(){
        //将二维数组返回
        FileReader fr = null;
        BufferedReader br = null;
        int[][] array = null;
        try {
            //1.指明要读取的txt文件的路径
            fr = new FileReader("data/SparseArray.txt");
            //2.把文件放入缓冲流中
            br = new BufferedReader(fr);
            //3.创建一个集合用来存放读取文件中的内容
            List<String> list = new ArrayList<>();
            //3.1 用来存放一行的数据
            String line;
            //4.逐行读取txt文件中的内容
            while ((line = br.readLine())!=null){
                //4.1读取的行添加到list集合中
                list.add(line);
            }
            //5.获取文件有多少行
            int row = list.size();
            //6.获取数组有多少列
            String s = list.get(0);//先获取链表索引为0的值
            int column = s.split("\\,").length;//然后根据正则表达式逗号获取有多少个逗号分开的数据,即行数
            //7.根据文件行数和列数来创建一个新数组,用来返回稀疏数组
            array = new int[row][column];
            //8.记录输出当前行
            int count = 0;
            //9.遍历集合并将集合中的数据放入新数组array中
            for (String str:list){
                //10.将读取的数组用","来分割,用字符串数组来接收
                String[] strs = str.split("\\,");
                for (int i = 0; i < column; i++) {
                    array[count][i] = Integer.valueOf(strs[i]);
                }
                count++;//行数+1
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (fr!=null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //返回稀疏数组
        return array;
    }
}
