// 获取日期时间
LocalDateTime currentDateTime = LocalDateTime.now();
LocalDateTime customDateTime = LocalDateTime.of(2018, 7, 19, 17, 20, 21);

// 日期时间计算
LocalDateTime threeDaysLater = customDateTime.plusDays(3);
LocalDateTime threeDaysLater = customDateTime.minusDays(3);

// 日期时间格式化
DateTimeFormatter formmater = DateTimeFormatter.ofPattern("yyyy/MM/dd | E | HH:mm:ss | a hh");