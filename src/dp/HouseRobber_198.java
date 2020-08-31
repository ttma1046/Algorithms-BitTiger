package dp;

/*
You are a professional robber planning to rob houses along a street.

Each house has a certain amount of money stashed,

the only constraint stopping you from robbing each of them is that adjacent houses have security system connected

and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,

determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 400
*/
public class HouseRobber_198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;

        if (length <= 1) {
            return nums[0];
        }

        int[] dp = new int[length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[length - 1];
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int a = 0;
        int b = 0;
        // a b
        for (int i : nums) {
            int temp = b;
            b = Math.max(b, a + i]);
            a = temp;
        }

        return b;
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber_198().rob(new int[] {1, 2, 3, 1}));
        System.out.println(new HouseRobber_198().rob(new int[] {2, 7, 9, 3, 1}));

        System.out.println(new HouseRobber_198().rob(new int[] {1}));
        System.out.println(new HouseRobber_198().rob(new int[] {1, 14}));
    }

    public int robIII(int[] nums) {
        int length = nums.length;

        if (length <= 0) {
            return 0;
        }

        if (length == 1) {
            return nums[0];
        }

        int[] memo = new int[length + 1];

        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 2; i <= length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i - 1]);
        }

        return memo[length];
    }

    public int robII(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int previousMax1 = 0;
        int previousMax2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int tmp = previousMax1;
            previousMax1 = Math.max(previousMax2 + nums[i], tmp);
            previousMax2 = tmp;
        }
        return previousMax1;
    }
}