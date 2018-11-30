Collection 接口：
-------------------------------
1）Set接口（不重复：没有index、没有key，如果重复了，怎么区分元素？！）
    HashSet：无序
    TreeSet：有序
    常用方法：size/clear/add/remove

2）List接口（[index, 值]）
    ArrayList：在集合尾部添加元素
    LinkedList：在集合中间经常插入、删除元素
    Vector：线程安全
    常用方法：size/clear/add/remove/removeIf/get

3）Map接口（<键, 值>对）
    HashMap：无序
    TreeMap：有序
    Hashtable：线程安全
    常用方法：size/clear/put/remove/get

4）Deque接口（双端队列）
    ArrayDeque：线程不安全
    LinkedBlockingDeque：线程安全
    常用方法：size/clear/getFirst/getLast
      用作栈： push(addFirst)/pop(removeFirst)
    用作队列： add(addLast)/poll(pollFirst)


如何选择不同 接口 和 集合类：
-------------------------------
A. 根据元素的类型，选择不同接口
     Set：元素不需要单独处理（只有批量处理的需求）
    List：元素需要单独处理（根据index找到它）
     Map：元素是 <键, 值>对
   Deque：元素的组织方式是 队列/堆栈 

B. 根据环境限制，选择不同接口
     Hash 没什么要求
     Tree 自带排序，性能自然下降
线程安全类 多线程环境


集合专用的遍历工具： Iterator迭代器
-------------------------------
      用法：跟指针很像（默认指向集合的第一个元素）
 hasNext()：判断下一个节点是否有元素
    next()：取出下一个元素
