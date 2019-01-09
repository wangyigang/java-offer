package LeetCode.test599;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。

你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

示例 1:

输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。
示例 2:

输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
提示:

两个列表的长度范围都在 [1, 1000]内。
两个列表中的字符串的长度将在[1，30]的范围内。
下标从0开始，到列表的长度减1。
两个列表都没有重复的元素。
 */
public class Test599 {
    @Test
    public void test() {
//        String[] arr1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
//        String[] arr2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        String[] arr1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] arr2 = new String[]{"KFC", "Shogun", "Burger King"};
        String[] restaurant = findRestaurant(arr1, arr2);
        for (String s : restaurant) {
            System.out.println(s);
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        //String[] result = new String[1];
        List<String> list = new ArrayList<>();
        int sum = list1.length + list2.length;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; ++j) {
                if (list1[i].compareTo(list2[j]) == 0) {
                    int tmpsum = i + j;
                    if (tmpsum <= sum) {
                        sum = tmpsum;
                        list.add(list1[i]);
                    }
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
