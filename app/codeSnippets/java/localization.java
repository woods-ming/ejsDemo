// 1）日期格式化类 DateFormat
DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, new Locale("de","DE"));
String formatedDate = dateFormat.format(new Date());

// 2）货币/数字格式化类 NumberFormat
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("语言", "国家"));
String formatedCurrency = currencyFormat.format(货币);     

NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("语言", "国家"));
String formatedNumber = numberFormat.format(数字);    

// 3）文本资源束类 ResourceBundle
ResourceBundle resourceBundle = ResourceBundle.getBundle("资源束名称", new Locale("语言", "国家"));
String translatedText = resourceBundle.getString("词汇");