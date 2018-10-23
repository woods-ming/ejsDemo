// 1）准备工作
Pattern myPattern = Pattern.compile("正则表达式"); // 编译正则表达式
Matcher myMatcher = myPattern.matcher("文本");     // 匹配要检查的文本

// 2）匹配
boolean myBoolean = myMatcher.matches();   

// 3）查找
while (matcher.find()) {                   
    String matchedString = matcher.group(); // 查找结果
    int startIndex = matcher.start();       // 查找结果的 起始位置
    int nextSearchIndex = matcher.end();    // 剔除查找结果后，下一次查找的 起始位置
}

// 4）替换
String replacedString = matcher.replaceAll("替换文本");    // 全部替换
String replacedString = matcher.replaceFirst("替换文本");  // 仅替换 第一个查找结果

// 5）截取 本次匹配扫描的那段文本（从上次匹配后的位置，到这次匹配成功后的位置），替换后放到StringBuffer里
StringBuffer alreadyReplacedString = new StringBuffer();
matcher.appendReplacement(alreadyReplacedString, "替换文本");     

// 6）截取 尚未进行匹配的剩余文本
StringBuffer remainingString = new StringBuffer();
matcher.appendTail(remainingString);  