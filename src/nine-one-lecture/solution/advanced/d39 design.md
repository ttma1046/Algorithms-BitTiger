# [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 思路

维护两个栈，一个主栈用来存放数据，另外一个作为辅助栈
1. 当push数据时，直接push到主栈就好了
2. 当pop时，看辅助栈内有没有数据，有就直接pop
3. 辅助栈没数据时就将主栈的数据依次push进辅助栈，然后再pop

## 代码
JavaScript Code

```js
var CQueue = function() {
    this.data = []
    this.data2 = []
};

CQueue.prototype.appendTail = function(value) {
    this.data.push(value)
};

CQueue.prototype.deleteHead = function() {
    if(this.data2.length){
        return this.data2.pop()
    }
    if(this.data.length){
        while(this.data.length){
            this.data2.push(this.data.pop())
        }
        return this.data2.pop()
    }
    return -1
};
```

## 复杂度分析

-   时间复杂度：$O(1) push很明显为o(1), pop时步骤3的复杂度为O(n),但是辅助栈里的这n个元素后面pop的复杂度为O（1），所以平均为O（1）。
-   空间复杂度：$O(n)$。



# [641. 设计循环双端队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 思路

需要频繁进行增删，并且还要元素有序，所以直接使用双向链表（方便进行增删操作）
1. 为了逻辑上的统一，设置一个空的头节点与尾节点
2. 内部再设置一个变量存当前节点个数（每次对节点增删时同步更新），方便判满与判空
3. 剩下的操作都是链表的插入删除操作，如果有不清楚的地方可以去复习下链表的讲义

## 代码
JavaScript Code

```js
let Node = function(val){
    this.val = val
    this.pre = this.next = null
}

var MyCircularDeque = function(k) {
    this.maxSize = k
    this.currentSize = 0
    this.head = new Node()
    this.tail = new Node()
    this.head.next = this.tail
    this.head.pre = this.tail
    this.tail.next = this.head
    this.tail.pre = this.head
};

MyCircularDeque.prototype.insertFront = function(value) {
    if(this.isFull()){  
        return false
    }
    let newNode = new Node(value)
    this.head.next.pre = newNode
    newNode.next = this.head.next
    newNode.pre = this.head
    this.head.next = newNode
    this.currentSize++
    return true
};


MyCircularDeque.prototype.insertLast = function(value) {
    if(this.isFull()){
        return false
    }
    let newNode = new Node(value)
    this.tail.pre.next = newNode
    newNode.pre = this.tail.pre
    newNode.next = this.tail
    this.tail.pre = newNode
    this.currentSize++
    return true
};

MyCircularDeque.prototype.deleteFront = function() {
    if(this.isEmpty()){
        return false
    }
    this.head = this.head.next
    this.currentSize--
    return true
};

MyCircularDeque.prototype.deleteLast = function() {
    if(this.isEmpty()){
        return false
    }
    this.tail = this.tail.pre
    this.currentSize--
    return true
};

MyCircularDeque.prototype.getFront = function() {
    return this.isEmpty() ? -1 : this.head.next.val
};

MyCircularDeque.prototype.getRear = function() {
    return this.isEmpty() ? -1 : this.tail.pre.val
};

MyCircularDeque.prototype.isEmpty = function() {
    return !this.currentSize
};


MyCircularDeque.prototype.isFull = function() {
    return this.currentSize === this.maxSize
};
```

## 复杂度分析

-   时间复杂度：$O(1)。
-   空间复杂度：$O(n)$。

# [146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

## 哈希法

### 思路

1. 确定需要使用的数据结构

   1. 根据题目要求,存储的数据需要保证顺序关系(逻辑层面) ===> 使用数组,链表等保证顺序关系

   2. 同时需要对数据进行频繁的增删, 时间复杂度 O(1) ==> 使用链表等

   3. 对数据进行读取时, 时间复杂度 O(1) ===> 使用哈希表

      最终采取双向链表 + 哈希表

      > 1. 双向链表按最后一次访问的时间的顺序进行排列, 链表头部为最近访问的节点
      > 2. 哈希表,以关键字为键,以链表节点的地址为值

2. put 操作

   通过哈希表, 查看传入的关键字对应的链表节点, 是否存在

   1. 如果存在,
      1. 将该链表节点的值更新
      2. 将该该链表节点调整至链表头部
   2. 如果不存在
      1. 如果链表容量未满,
         1. 新生成节点,
         2. 将该节点位置调整至链表头部
      2. 如果链表容量已满
         1. 删除尾部节点
         2. 新生成节点
         3. 将该节点位置调整至链表头部
      3. 将新生成的节点，按关键字为键，节点地址为值插入哈希表

3. get 操作

   通过哈希表, 查看传入的关键字对应的链表节点, 是否存在

   1. 节点存在
      1. 将该节点位置调整至链表头部
      2. 返回该节点的值
   2. 节点不存在, 返回 null

伪代码:

```js
var LRUCache = function(capacity) {
	保存一个该数据结构的最大容量
	生成一个双向链表,同时保存该链表的头结点与尾节点
	生成一个哈希表
};

function get (key) {
	if 哈希表中存在该关键字 {
		根据哈希表获取该链表节点
		将该节点放置于链表头部
		return 链表节点的值
	} else {
		  return -1
	}
};

function put (key, value) {
    if 哈希表中存在该关键字 {
		根据哈希表获取该链表节点
		将该链表节点的值更新
		将该节点放置于链表头部
	} else {
		if 容量已满 {
			删除链表尾部的节点
			新生成一个节点
			将该节点放置于链表头部
		} else {
			新生成一个节点
			将该节点放置于链表头部
		}
	}
};
```

JS 代码参考:

```js
function ListNode(key, val) {
  this.key = key;
  this.val = val;
  this.pre = this.next = null;
}

var LRUCache = function (capacity) {
  this.capacity = capacity;
  this.size = 0;
  this.data = {};
  this.head = new ListNode();
  this.tail = new ListNode();
  this.head.next = this.tail;
  this.tail.pre = this.head;
};

function get(key) {
  if (this.data[key] !== undefined) {
    let node = this.data[key];
    this.removeNode(node);
    this.appendHead(node);
    return node.val;
  } else {
    return -1;
  }
}

function put(key, value) {
  let node;
  if (this.data[key] !== undefined) {
    node = this.data[key];
    this.removeNode(node);
    node.val = value;
  } else {
    node = new ListNode(key, value);
    this.data[key] = node;
    if (this.size < this.capacity) {
      this.size++;
    } else {
      key = this.removeTail();
      delete this.data[key];
    }
  }
  this.appendHead(node);
}

function removeNode(node) {
  let preNode = node.pre,
    nextNode = node.next;
  preNode.next = nextNode;
  nextNode.pre = preNode;
}

function appendHead(node) {
  let firstNode = this.head.next;
  this.head.next = node;
  node.pre = this.head;
  node.next = firstNode;
  firstNode.pre = node;
}

function removeTail() {
  let key = this.tail.pre.key;
  this.removeNode(this.tail.pre);
  return key;
}
```

**复杂度分析**

- 时间复杂度：$O(1)$
- 空间复杂度：$O(n)$ n为容量的大小
