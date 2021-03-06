# 数据结构与算法概述

## 简介

本篇是数据结构与算法的先导篇，目的是帮大家认识数据结构和算法的世界，具体的数据结构和算法会在之后的讲义详细进行讲述。

狭义的算法指的是经典的具体算法，广义的算法指的是解决问题的方法。比如 math.sqrt 就是一种广义的算法， 其具体的算法可以是牛顿迭代法，二分法，也可以是暴力法等。在这里，我们往往关注的是狭义的算法， 并且是那些被反复验证的经典的算法思想。

研究这些经典算法很重要，它不仅可以帮我们解决实际的问题，锻炼思维，而且还可以帮助我们交流， 在这个层面上来说，其作用类似设计模式。比如我跟你说这道题用二分法就行了，你如果也恰好明白什么是二分，就可能知道我的意思。而如果你不知道二分， 我只能这样解释你才可能明白”用两个指针，分别指向数组的头部和尾部，然后计算中间位置，通过比较目标元素和中间位置的关系移动两个指针。。。“。

而数据结构是计算机存储、组织**数据**的方式，指相互之间存在一种或多种**特定关系**的数据元素的集合。要想深刻理解其数据结构，一定要结合算法。 因为任何数据结构都是为了实现某一个或者多个算法的，数据结构是为算法所服务的。 就好像你要做红烧鱼需要鱼，你做可乐鸡翅需要鸡翅。 当你用到特定算法的话，自然会用到特定的数据结构。

我们知道，内存的物理表现实际上就是一系列连续的内存单元，每一个内存单元的大小是固定的，这也是内存支持随机访问的本质原因。那么应该怎么存储和检索以及修改内存呢（增删查）？这就是数据结构研究的话题。

上面提到的是内存的物理结构，我们也可以基于这个物理结构拓展逻辑结构。比如，上面提到物理内存是固定大小连续存储的，那我可不可以将一段大的数据存储在不连续的空间呢？当然可以，这需要你自己处理，当你尝试去处理的时候，实际上就涉及到了逻辑结构。你所知道的，以及我们即将研究的全部都是逻辑结构。

不同的逻辑结构存储实际上有不同的特点，我们需要结合实际的场景分析。要想拥有具体问题具体分析的能力，我们首先要对基本的数据结构要特别清楚。包括他们的特点， 适用场景，不适合场景等。我的建议是先过一遍数据结构， 有个大致印象，然后研究具体算法。之后结合算法回头继续复习数据结构.

## 例子

我们公司前台可以代收快递。但是当我们取快递的时候，需要我们在一个事先写了我们报告信息的表格上签字。表格大概是这样的：

| 时间       | 名字 | 快递公司 | 单号     | 签字 |
| ---------- | ---- | -------- | -------- | ---- |
| 2020-09-09 | 西法 | SF       | sf298001 | 西法 |
| 2020-09-10 | 张三 | SF       | sf133990 |      |

由于是来一个快递，快递小哥就会在表格增加一个记录，因此时间那一列是升序排列的，如果你想要快速找到你的包裹所在的行，一个好的方式则是先根据日期检索，确定大概范围之后再根据快递公司找，由于一个快递小哥通常会一次登记很多快递，因此会出现连续的同一个快递公司的常见现象，最后再根据你的单号来最终确认即可。

我们来分析一下上面的问题。 实际上，上面的例子查询的次数要远远大于插入。而查询的过程除了时间（只有时间是有序的）可以二分，其他条件则不可以。 如果当天的快递很多，那么效率下降会很明显。如果相同名字的包裹放在一起，这样我们不是就可以像查字典一样快速定位到自己的包裹了么？如果这么做的话，又来了一个西法的快递，我们就需要擦掉张三那一行，写上西法的信息。然后将擦掉的张三的信息重写到下一行。

我们也可以给公司的所有人准备一个表格，每一个人的快递都写到对应的表格。这就对快递员（写入）有了新的要求。并且会更加浪费表格（空间），但是相应地查询速度会更快。

那如果公司有很多人都不寄快递到公司呢？为他们也准备表格就显得多余。 另外我们需要给每个人都准备同样大小的表格么？这都是我们需要考虑的。 实际上，我们可以取舍一下，比如给所有名字 L 开头的用三张表，给所有名字 B 开头的一张表。

> 我们假设公司 L 开头的人比较多， 是 B 开头的大约三倍。

计算机也是类似，不同的存储有不同的特点。有好处也有坏处，我们要做的就是根据实际情况选择合适的数据结构。

## 物理结构和逻辑结构

我前面说了。 内存的物理表现实际上就是一系列连续的内存单元，这是物理结构。数据结构只有两个对应基础的数据结构，它们是数组和链表。其他数据结构都是基于它们产生的。

数组用来表示连续的内存空间，而链表通常用来表示不连续的内存空间。

连续我们比较好理解，毕竟内存本来就是连续的。那么如何理解不连续的内存空间呢？其实，我们只需要区分数据域和指针域即可。数组的话，我们可以根据内存的物理地址来索引，由于数组中的元素大小固定，因此我们可以实现随机访问。链表则不行，因为其下个元素并不一定是紧邻的。因此需要多一个指针域，来表示其下一个元素的内存单元位置。 不难看出， 链表相比数组，会增加额外的空间负担。好处则是，增加或删除变得容易。

![](https://cdn.jsdelivr.net/gh/azl397985856/cdn/2020-10-22/1603338453129-image.png)

如上图是一段物理内存。 如果我在内存中存值，并通过内存编号访问那就是数组。如果我在内存中存内存编号，那就是链表。

内存还是那个内存， 通过不同的逻辑抽象就是两种数据结构了。当然这个解释比较粗糙，但是如果却可以帮助你认识本质。

> 想想上面快递的例子。

## 总结

本节我们学习了数据结构和算法的关系，以及狭义和广义的算法。

对于算法而言本讲义的全部内容都是围绕狭义的算法展开，帮我大家理解就经典的算法思想，并将这些思想串联起来，进行综合运用。

对于数据结构而言本讲义的全部内容都是围绕逻辑结构而已，逻辑结构只不过是我们使用物理结构的一种抽象表示而已。基于数组和链表这两种基本的逻辑结构，拓展了无数的丰富的数据结构，这些数据结构都是为了解决特定问题产生的，因此理解数据结构一定要结合算法。
