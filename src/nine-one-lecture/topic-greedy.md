## 定义

贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当下看来是最好的选择，不用考虑整体最优解。对于某些问题而言，全局解只是局部解的简单累加，因此局部最优解累加的结果就是全局最优解。不过，贪心算法并不能得到所有问题的全局最优解，关键是贪心策略的选择，以及证明贪心算法可以得到全局最优解。

## 适用场景

1. 贪心选择的局部最优解能导致全局最优解 （看能不能举出反例，如果存在反例则说明贪心不能得到全局最优解）
2. 贪心策略无后效性，即当前贪心选择不会影响以后的状态，只与当前状态有关。

贪心算法在进行求解时，总是根据已知信息做出当前看起来最好的选择。

不追求整体最优而是局部最优解，并且作出选择之后不能回溯回来改变这次选择。

贪心算法不能保证每次都能找到最优解，有时候只能找到接近最优解的方案。

所以求解时，要确定问题具有贪心选择性质：每一次选择的局部最优可以导致问题的整体最优。

## 证明

贪心算法的难点在于**如何知道贪心的策略是正确的**。比如给你一堆硬币，面值分别为 [1,3,5]，你需要找零 9 元，如何才能使得找零的硬币数目最少？正确的贪心策略是优先取面值最大的，这样可以更快地缩小问题的规模，因此我们先选 5。为了凑够剩下的 4，我们可以选择 4 枚 面值为 1 的硬币，但这显然不是最优的，因为还有面值比 1 更大且比需要找零的数目更小的硬币，所以我们其实可以直接先选 1 枚 面值为 3 的硬币。

那如何证明贪心算法的正确性呢？实际上，大多数题目我们可根据经验判断。如果无法根据经验判断，可以尝试下面要讲的方法。

贪心算法有两种证明方法：反证法和数学归纳法。

- 反证法：如果交换方案中任意两个元素，答案不会变得更好，那么可以推定目前的解已经是最优解了。
- 数学归纳法：先算得出边界情况的最优解，比如 F(1) ，然后再证明**F(n) 都可以由 F(n-1)推导出来**

## 常见题型

- 先排序，再按照价值从高到低或者从低到高选取。
- 事后诸葛亮。具体见我写的堆专题。

## 解题步骤

1. 将问题分解为子问题
2. 求出当前子问题的局部最优解
3. 通过这个局部最优解推导出全局最优解

## 经典题型

#### [881. 救生艇](https://leetcode-cn.com/problems/boats-to-save-people/)

1. 题目要求船数最少，既每个船都尽可能多装一些重量（分解为子问题）

2. 每个船在容量固定情况下，尽可能多装一些重量（类似背包问题）

   所以每次装人的时候先将最大重量的装上，

   然后再遍历剩下的人，看剩余容量能否再装下一人（制定贪心策略）

3. 将已经被装上船的人踢出列表，继续按 2 的策略装直到所有人都上船（局部最优解推导出全局最优解）

**代码**

```js
var numRescueBoats = function (people, limit) {
  people.sort((a, b) => a - b);
  let ans = 0,
    start = 0,
    end = people.length - 1;
  while (start <= end) {
    if (people[end] + people[start] <= limit) {
      start++;
      end--;
    } else {
      end--;
    }
    ans++;
  }
  return ans;
};
```

**时空复杂度**

时间复杂度：$o(nlogn)$

空间复杂度：$o(1)$

#### [765. 情侣牵手](https://leetcode-cn.com/problems/couples-holding-hands/)

首先我们将所有的情侣按（0，1）（2，3） 安排座位

> 如果某一个人（n 号）是奇数，那么他对应的情侣应该在他的左边（n - 1 号）
>
> 如果某一个人（n 号）是偶数，那么他对应的情侣应该在他的右边（n + 1 号）

1. 我们先做个一个哈希表进行映射，通过哈希表可以找到所有人当前所在的座位号

2. 依次遍历所有人，如果他的情侣坐在他旁边就继续下一轮循环，

   如果不在他旁边就通过哈希表找到情侣当前在哪儿，

   然后把伴侣跟身边的基佬换个位置这样就又凑成一对（制定贪心策略）

3. 循环执行第 2 步直到所有人都凑到一起了（局部最优解推导出全局最优解）

**代码**

```js
var minSwapsCouples = function (row) {
  if (row.length <= 2) return 0;
  var len = row.length;
  var map = new Array(len);
  for (var index in row) map[row[index]] = index;
  var next = (count = 0);
  for (var i = 0; i <= len - 2; i += 2) {
    next = row[i] + (row[i] % 2 === 0 ? 1 : -1);
    if (row[i + 1] != next) {
      var temp = row[i + 1];
      row[i + 1] = row[map[next]];
      row[map[next]] = temp;
      temp = map[row[map[next]]];
      map[row[map[next]]] = map[row[i + 1]];
      map[row[i + 1]] = temp;
      count++;
    }
  }
  return count;
};
```

**时空复杂度**

时间复杂度：$o(n)$

空间复杂度：$o(n)$
