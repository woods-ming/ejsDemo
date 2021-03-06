using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
 
// 下面的代码展示了如何实现 IComparable<T>
// 比较逻辑：Person 对象的 Age 大小
 
public class Person : IComparable<Person>
{
    public string Name { get; set; }
    public int Age { get; set; }
 
    public int CompareTo(Person other)
    {
        if (other == null)
        {
            return 1;
        }
 
        return this.Age - other.Age;
    }
}
 
// 下面的代码展示了使用 IComparable<T> 的场景
 
public class TestIComparable
{
    public static void Main()
    {
        List<Person> personList = new List<Person>();
        personList.Add(new Person() { Name = "张三", Age = "5" });
        personList.Add(new Person() { Name = "李四", Age = "4" });
        personList.Add(new Person() { Name = "王五", Age = "3" });
 
        personList.Sort();
 
        personList.ForEach(p => Console.WriteLine(p.Name + ": " + p.Age));
    }
}