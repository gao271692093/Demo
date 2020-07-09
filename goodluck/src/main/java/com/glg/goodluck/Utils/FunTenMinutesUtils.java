package com.glg.goodluck.Utils;

import com.glg.goodluck.FunTenMinutesItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FunTenMinutesUtils {

    public static List<FunTenMinutesItem> getItemFive(String kinds, int sum) {
        List<FunTenMinutesItem> list = new ArrayList<FunTenMinutesItem>();
        List<String> list1;
        int j = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 5; i += 1) {
            list1 = getOne(sum);
            stringBuilder.delete(0, stringBuilder.length());
            for(j = 0; j < sum; j += 1) {
                stringBuilder.append(list1.get(j) + ",");
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            FunTenMinutesItem funTenMinutesItem = new FunTenMinutesItem();
            funTenMinutesItem.setKinds(kinds);
            funTenMinutesItem.setDigital(stringBuilder.toString());
            funTenMinutesItem.setNum("1");
            list.add(funTenMinutesItem);
        }
        return list;
    }

    public static List<FunTenMinutesItem> getItem(String kinds, int sum) {
        List<FunTenMinutesItem> list = new ArrayList<FunTenMinutesItem>();
        List<String> list1 = getOne(sum);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < list1.size(); i +=  1) {
            stringBuilder.append(list1.get(i));
        }
        FunTenMinutesItem funTenMinutesItem = new FunTenMinutesItem();
        funTenMinutesItem.setKinds(kinds);
        funTenMinutesItem.setDigital(stringBuilder.toString());
        funTenMinutesItem.setNum("1");
        list.add(funTenMinutesItem);
        return list;
    }

    public static List<String> getOne(int sum) {
        List<String> list = fix(getRandom(sum), sum);
        return list;
    }

    public static List<Integer> getRandom(int sum) {
        List<Integer> resArr = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        String strNumber = "";
        int number = 0;
        while(set.size()<sum) {
            number = new Random().nextInt(20) + 1;
            set.add(number);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            resArr.add(iterator.next());
            iterator.remove();
        }
        return resArr;
    }

    public static List<String> fix(List<Integer> list, int sum) {
        int t = 0;
        int i = 0;
        for(i = 0; i < sum; i += 1) {
            for(int j = 0; j < sum - i - 1; j += 1) {
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
