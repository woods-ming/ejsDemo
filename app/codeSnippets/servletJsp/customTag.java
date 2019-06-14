/*1.create标签类：*/
    class XX extends TagSupport {
        doStartTag() { //解析开始标签时，输出内容 }
        doEndTag() { //解析结束标签时，输出内容 }
        /* property(与tag的attribute对应) */
    }

/*2.define标签名（在标签库.tld中）：*/
    tag名、对应的标签类、attribute

/*3.reference标签库：*/
    <%@ taglib uri="标签库的位置.tld" prefix="标签库的别名" %>
    
/*4.use标签：*/
    <prefix:tag attribute=""></prefix:tag>