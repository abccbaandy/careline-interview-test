package com.careline.interview.test.mission2.vo;

import lombok.Data;

import java.util.List;

@Data
public class ComputeVo {
    private long sum; // 數字總和
    private int max; // 最大數字
    private int min; // 最小數字
    private double avg; // 數字平均
    private List<Integer> sorted_list; // 由小到大排序的數字陣列
}