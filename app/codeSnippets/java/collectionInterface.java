1）Set接口（不重复）
    HashSet：无序
    TreeSet：有序
    常用方法：size/clear/add/remove

2）List接口（能重复，能根据位置来访问）
    ArrayList：线程不安全
    Vector：线程安全
    常用方法：size/clear/add/remove/removeIf/get

3）Map接口（键值对）
    HashMap：无序
    TreeMap：有序
    Hashtable：线程安全
    常用方法：size/clear/put/remove/get

4）Deque接口（双端队列）
    ArrayDeque：线程不安全
    常用方法：size/clear/getFirst/getLast
      用作栈： addFirst(push)/removeFirst(pop)
    用作队列： addLast(add)/pollFirst(poll)