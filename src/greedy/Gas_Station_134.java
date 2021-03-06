/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas

to travel from station i to its next station (i+1).

You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index
if you can travel around the circuit once in the clockwise direction,
otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

Example 1:

Input:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

Example 2:

Input:
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

*/

package greedy;

class Gas_Station_134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length <= 0 || cost == null || cost.length <= 0) {
            return -1;
        }

        int length = gas.length;

        if (length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }

        for (int i = 0; i < length; i++) {
            if (gas[i] > cost[i]) {
                int j = i;
                int start = gas[j];

                while (start >= cost[j]) {
                    int next = j + 1 >= length ? (j + 1) % length : j + 1;
                    start = start - cost[j] + gas[next];
                    j = next;

                    if (j == i) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public int canCompleteCircuitAnswer(int[] gas, int[] cost) {
        int n = gas.length;

        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;

        // gas  = [1,2,3,4,5]
        // cost = [3,4,5,1,2]

        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }

    public int canCompleteCircuitII(int[] gas, int[] cost) {
        int start = gas.length - 1;
        int end = 0;

        int sum = gas[start] - cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                ++end;
            } else {
                --start;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }

    /*
        If car starts at A and can not reach B.
        Any station between A and B can not reach B. (B is the first station that A can not reach.)
        If the total number of gas is bigger than the total number of cost. There must be a solution.
        (Should I prove them?)
    */

    public int canCompleteCircuitIII(int[] gas, int[] cost) {
        int start = 0,
            total = 0,
            tank = 0;
        //if car fails at 'start', record the next station
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return (total + tank < 0) ? -1 : start;
    }

    public static void main(String[] args) {
        System.out.println(new Gas_Station_134().canCompleteCircuitIII(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(new Gas_Station_134().canCompleteCircuitIII(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));
    }
}