// 1-1）InputStream抽象类（读字节流）
FileInputStream：文件 -> 字节流
BufferedInputStream：（包装一下InputStream）增加缓冲区，提高读的效率

// 1-2）Reader抽象类（读字符流）
InputStreamReader：（包装一下InputStream）字节流 -> 字符流
BufferedReader：（包装一下Reader）增加缓冲区，提高读的效率

// 2-1）OutputStream抽象类（写字节流）
FileOutputStream：字节流 -> 文件
BufferedOutputStream：（包装一下OutputStream）增加缓冲区，提高写的效率

// 2-1）Writer抽象类（写字符流）
OutputStreamWriter：（包装一下OutputStream）字节流 -> 字符流
BufferedWriter：（包装一下Reader）增加缓冲区，提高写的效率