package recursion;

import java.util.ArrayList;
import java.util.List;

class Powersets {
    public List<List<Integer>> subsetsWithDupIte(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        for (int num : nums) {
            int length = result.size();
            for (int i = 0; i < length; i++) {
                List<Integer> subset = new ArrayList<Integer>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }

        return result;
    }

    public List<List<Integer>> subsetsWithDupRec(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }

        return this.subsetsWithDupRec(nums, nums.length - 1);
    }

    public ArrayList<List<Integer>> subsetsWithDupRec(int[] nums, int index) {
        if (index < 0) {
            ArrayList<List<Integer>> empty = new ArrayList<List<Integer>>();
            empty.add(new ArrayList<Integer>());
            return empty;
        }

        int ele = nums[index];

        ArrayList<List<Integer>> currentSubsets = subsetsWithDupRec(nums, index - 1);
        int length = currentSubsets.size();
        for (int i = 0; i < length; i++) {
            List<Integer> temp = new ArrayList<Integer>(currentSubsets.get(i));
            temp.add(ele);
            currentSubsets.add(temp);
        }

        return currentSubsets;
    }

    ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allSubsets;

        if (set.size() == index) {
            // Base case - add empty set;
            allSubsets = new ArrayList<ArrayList<Integer>>();

            allSubsets.add(new ArrayList<Integer>()); // Empty set;
        } else {
            allSubsets = getSubsets(set, index + 1);
            int item = set.get(index);

            ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> subset : allSubsets) {

                ArrayList<Integer> newSubset = new ArrayList<Integer>(subset); //
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }

            allSubsets.addAll(moreSubsets);
        }

        return allSubsets;
    }

    ArrayList<ArrayList<Integer>> getSubsets2(int x, ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();

        int max = 1 << set.size(); /* Compute 2^n */

        for (int k = 0; k < max; k++) {
            ArrayList<Integer> subset = convertIntToSet(k, set);

            allsubsets.add(subset);
        }

        return allsubsets;
    }

    ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
        ArrayList<Integer> subset = new ArrayList<Integer>();

        int index = 0;
        for (int k = x; k > 0; k >>= 1) {
            if ((k & 1) == 1) {
                subset.add(set.get(index));
            }
            index++;
        }

        return subset;
    }

    public static void main(String[] args) {
        System.out.println(new Powersets().subsetsWithDupIte(new int[] { 1, 2, 3 }));

        System.out.println(new Powersets().subsetsWithDupRec(new int[] { 1, 2, 3 }));
    }
}