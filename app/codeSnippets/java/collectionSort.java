1）Comparable接口（描述 “我和另一个人” 怎么比较？）
    方法：compareTo(另一个元素)
    
2）Comparator接口（描述 “任意两个人” 怎么比较？）
    方法：compare(元素1, 元素2)
    
3）Collections.sort(list)排序
    list的元素类，实现了Comparable接口（元素 已经知道该如何与同伴比较）
    因此默认使用【元素提供的比较方式】
         
4）Collections.sort(list, 比较器)排序
    list的元素类，可以不实现Comparable接口（元素 可以不知道如何与同伴比较） 
    因为已经有了【比较器提供的比较方式】