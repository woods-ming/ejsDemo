// 1.描述路径
// 将字符串形式的路径 用Path描述出来（可以用完整路径，也可以由多个部分来拼接）
Path directoryPath = Paths.get("E:\\java_introduce");
Path filePath = Paths.get("E:", "java_introduce", "java简介.txt");
System.out.println("目录的路径：" + directoryPath.toString());
System.out.println("文件的路径：" + filePath.toString());

// 获取 目录名/文件名
Path directoryNamePath = directoryPath.getFileName();
Path fileNamePath = filePath.getFileName();
System.out.println("目录名：" + directoryNamePath.toString());
System.out.println("文件名：" + fileNamePath.toString());

// 获取 组成 路径的元素数量
int directoryNameCount = directoryPath.getNameCount();
int fileNameCount = filePath.getNameCount();
System.out.println(directoryPath + " 的元素数量：" + directoryNameCount);
System.out.println(filePath + " 的元素数量：" + fileNameCount);

// 获取 组成 路径的元素
Path directory_0 = directoryPath.getName(0);
Path fileName_1 = filePath.getName(1);
System.out.println(directoryPath + " 的元素[0]：" + directory_0.toString());
System.out.println(filePath + " 的元素[1]：" + fileName_1.toString());

// 获取 文件系统名
FileSystem fileSystemName = filePath.getFileSystem();
System.out.println("文件系统名：" + fileSystemName.toString());

// 获取 父路径
Path directoryParent = directoryPath.getParent();
Path fileNameParent = filePath.getParent();
System.out.println(directoryPath + " 所在父目录：" + directoryParent.toString());
System.out.println(filePath + " 上一级路径：" + fileNameParent.toString());

// 获取 路径的盘符
Path rootPath = directoryPath.getRoot();
System.out.println(directoryPath + " 的盘符：" + rootPath.toString());

// 比较 路径
int compareResult = directoryPath.compareTo(filePath);
System.out.println(
directoryPath + " 与 " + filePath + " 的字符比较结果：" + compareResult);

// 是否 绝对路径
System.out.println(
directoryPath + " 是绝对路径：" + directoryPath.isAbsolute());
System.out.println(filePath + " 是绝对路径：" + directoryPath.isAbsolute());


// 2.描述文件和目录File
File file = new File(目录);
// 是否目录
file.isDirectory();
// 遍历目录
File[] files = file.listFiles();


// 3.操作文件和目录
// 3.1）文件
Files.createFile(文件)创建文件
Files.deleteIfExists(文件)删除文件
Files.copy(源文件, 目标文件, StandardCopyOption.REPLACE_EXISTING)复制文件
Files.move(源文件, 目标文件, StandardCopyOption.REPLACE_EXISTING)移动文件
Files.exists(文件)是否存在

Files.newBufferedWriter(文件, Charset.forName("utf-8"), StandardOpenOption.APPEND)获取BufferedWriter
Files.newBufferedReader(文件, Charset.forName("utf-8"))获取BufferedReader

// 3.2）目录
Files.createDirectories(目录)创建目录
Files.deleteIfExists(空目录)删除目录
Files.move(源目录, Paths.get(新目录, 改名), StandardCopyOption.REPLACE_EXISTING)移动目录（将源目录及其内容，挂到新目录下、并改名）
Files.exists(目录)是否存在

// 3.3）深层遍历目录下的所有文件
// Files.walkFileTree()负责递归遍历
// FileVisitor接口 负责定义访问文件时的操作 
// SimpleFileVisitor类 实现了FileVisitor接口（易用：使用它，无需重写接口中的所有方法）
Files.walkFileTree(目录, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file,
                 BasicFileAttributes attrs) throws IOException {
                // TODO：操作文件
                return FileVisitResult.CONTINUE;
        }
});

