# 25. K 个一组翻转链表

## 前置知识
1. 链表的翻转   
    这个直接上模板，有不清楚的地方可以再复习下链表的讲义   
    
    **伪代码:**
    ```js
    当前指针 =  头指针
    前一个节点 = null;
    while 当前指针不为空 {
      下一个节点 = 当前指针.next;
        当前指针.next = 前一个节点
        前一个节点 = 当前指针
        当前指针 = 下一个节点
    }
    return 前一个节点;
    ```
  
    **JS 代码参考:**
    ```
    let cur = head;
    let pre = null;
    while (cur) {
      const next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
    ```

    **复杂度分析**

    - 时间复杂度：O(N)
    - 空间复杂度：O(1)

2. 递归

## 迭代解法

### 思路

1. 定义一个翻转k个节点的辅助函数
2. 遍历链表按k个一组进行翻转
3. 每次翻转后注意指针的指向避免形成环


### 代码

JavaScript Code

```js
var reverseKGroup = function(head, k) {
    if(!head || !head.next) return head
    let reverseList = (head, k) => {
        let pre = null
        while(head && k--){
            let nextNode = head.next
            head.next = pre
            pre= head
            head = nextNode
        }
        return pre
    };
    let index = 0, preLinkEnd = null, start = res  = head
    while(head && ++index){
        let nextNode = head.next
        if(index % k == 0){
            reverseList(start, k)
            if(!preLinkEnd){
                res = head
            } else {
                preLinkEnd.next = head
            }
            preLinkEnd = start
            start = nextNode
        }
        head = nextNode
    }
    preLinkEnd.next = start
    return res
};
```

### 复杂度分析

-   时间复杂度：$O(n)$,最多将所有节点遍历2遍。
-   空间复杂度：$O(1)$

## 递归

### 思路

1. 如果当前链表的长度小于k直接返回（递归的边界条件）
2. 将当前链表的前k个进行翻转
3. 递归执行第一步，并将第k个节点的next指针指向递归返回的结果

### 代码

JavaScript Code

```js
var reverseKGroup = function(head, k) {
    if(!head || !head.next) return head
    let currentNode = head, num = k
    while(num--){
        if(!currentNode) return head
        currentNode = currentNode.next
    }
    num = k
    currentNode = head
    let preNode = null
    while(num--){
        let nextNode = currentNode.next
        currentNode.next = preNode
        preNode = currentNode
        currentNode = nextNode
    }
    head.next = reverseKGroup(currentNode, k)
    return preNode
};
```

### 复杂度分析

-   时间复杂度：$O(n), 最多将所有节点遍历2遍。
-   空间复杂度：$O(h)$，h 为链表的分组数量（递归时占用的函数栈帧）。


# 扩展
剩下的两道题
>206. 反转链表
>92. 反转链表 II  

也是直接套链表翻转的模板，注意处理一下特殊的边界条件即可，这里就不再赘述
