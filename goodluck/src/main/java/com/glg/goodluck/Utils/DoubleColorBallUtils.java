package com.glg.goodluck.Utils;

import com.glg.goodluck.DoubleColorBallItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DoubleColorBallUtils {

    public static List<DoubleColorBallItem> getItem() {
        List<String> list1 = getOne();
        List<DoubleColorBallItem> list = new ArrayList<DoubleColorBallItem>();
        DoubleColorBallItem doubleColorBallItem = new DoubleColorBallItem();
        doubleColorBallItem.setOne(list1.get(0));
        doubleColorBallItem.setTwo(list1.get(1));
        doubleColorBallItem.setThree(list1.get(2));
        doubleColorBallItem.setFour(list1.get(3));
        doubleColorBallItem.setFive(list1.get(4));
        doubleColorBallItem.setSix(list1.get(5));
        doubleColorBallItem.setSeven(list1.get(6));
        list.add(doubleColorBallItem);
        return list;
    }

    public static List<DoubleColorBallItem> getItemFive() {
        List<String> list1;
        List<DoubleColorBallItem> list = new ArrayList<DoubleColorBallItem>();
        for(int i = 0; i < 5; i += 1) {
            list1 = getOne();
            DoubleColorBallItem doubleColorBallItem = new DoubleColorBallItem();
            doubleColorBallItem.setOne(list1.get(0));
            doubleColorBallItem.setTwo(list1.get(1));
            doubleColorBallItem.setThree(list1.get(2));
            doubleColorBallItem.setFour(list1.get(3));
            doubleColorBallItem.setFive(list1.get(4));
            doubleColorBallItem.setSix(list1.get(5));
            doubleColorBallItem.setSeven(list1.get(6));
            list.add(doubleColorBallItem);
        }
        return list;
    }

    public static List<String> getOne() {
        List<String> list = fix(getRandom());
        return list;
    }

    public static List<Integer> getRandom() {
        List<Integer> resArr = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        String strNumber = "";
        int number = 0;
        while(set.size()<6) {
            number = new Random().nextInt(33) + 1;
            set.add(number);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            resArr.add(iterator.next());
            iterator.remove();
        }
        number = new Random().nextInt(16) + 1;
        resArr.add(number);
        return resArr;
    }

    public static List<String> fix(List<Integer> list) {
        int t = 0;
        int i = 0;
        for(i = 0; i < 6; i += 1) {
            for(int j = 0; j < 6 - i - 1; j += 1) {
                if(list.get(j) >  list.get(j + 1)) {
                    t = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, t);
                }
            }
        }
        List<String> list1 = new ArrayList<String>();
        String string;
        for(i = 0; i < list.size(); i += 1) {
            if(list.get(i) < 10) {
                string = "0" + String.valueOf(list.get(i));
            } else {
                string = String.valueOf(list.get(i));
            }
            list1.add(string);
        }
        return list1;
    }
}
