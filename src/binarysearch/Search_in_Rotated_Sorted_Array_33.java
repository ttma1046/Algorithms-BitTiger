package binarysearch;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

class Search_in_Rotated_Sorted_Array_33 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }


    private int binarysearch(int[] array, int target, int start, int end) {

    }

    public static void main(String[] args) {
        System.out.println(new Search_in_Rotated_Sorted_Array_33().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new Search_in_Rotated_Sorted_Array_33().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
        /*
        Time complexity: O(logN).
        Space complexity: O(1).
        */


    }
}