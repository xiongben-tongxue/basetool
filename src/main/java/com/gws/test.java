package com.gws;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author:wangdong
 * @description:
 */
public class test {
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        int[] nums = new int[]{3,2,4};
        int target = 6;
        for (int i : nums) {
            for (int j : nums) {
                int p = i + j;
                if (p == target ){
                    result2.add(i);
                    result2.add(j);
                }
            }
        }
        for (Integer index : result2){
            int positon = Arrays.binarySearch(nums,index);
            if (positon>=0) {
                result.add(positon);
            }
        }
        int[] result3 = new int[2];
        result3[0] = result.get(0);
        result3[1] = result.get(1);

    }
}
