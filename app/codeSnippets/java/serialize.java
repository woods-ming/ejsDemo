String 文件路径 = "E:\\1";

// 序列化
try (OutputStream outputStream = new FileOutputStream(文件路径);
		ObjectOutputStream objOutput = new ObjectOutputStream(outputStream);) {
	objOutput.writeObject(对象);
}

// 反序列化
try (FileInputStream inputStream = new FileInputStream(文件路径);
		ObjectInputStream objInput = new ObjectInputStream(inputStream);) {
	类 对象 = (类) objInput.readObject();
}

// 注：不想被序列化的字段，用 transient 标记