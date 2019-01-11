// String常用的实例方法
String s = "  Hello World 你好  ";

System.out.println("长度：" + s.length());
System.out.println("是否相等？" + s.equals("  hello World你好  "));
System.out.println("是否相等（忽略大小写）？" + s.equalsIgnoreCase("  hellO world你好  "));
System.out.println("去除前后空白字符：" + s.trim());
System.out.println("前缀是 Hel？" + s.trim().startsWith("Hel"));
System.out.println("后缀是 好？" + s.trim().endsWith("好"));
System.out.println("截取（从0开始，不包含结束位置）：" + s.substring(2, 7));
System.out.println("替换：" + s.replace("World", "银川"));

// StringBuilder类：用于字符串拼接，节省内存
StringBuilder stringBuilder = new StringBuilder("Hello");

stringBuilder.append(" World!");
System.out.println(stringBuilder.toString());

stringBuilder.insert(5, " My");
System.out.println(stringBuilder.toString());

stringBuilder.delete(5, 8);
System.out.println(stringBuilder.toString());

System.out.println(stringBuilder.reverse().toString());

// StringBuffer类：与StringBuilder用法相同，唯一区别：适用于多线程环境