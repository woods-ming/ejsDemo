import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * try { 
 *      正常代码; 
 * } catch(异常类型 e) { 
 *      异常处理; 
 * } finally {
 *      无论是否有异常，都会执行的代码;
 * }
 */
public class CatchException{
    // 不考虑任何潜在问题，顺着正常流程编写代码
    public void normalCode(){
        int totalStudents = 100;
        System.out.println("程序开始，请输入 迟到学生数：");

        int lateStudents = (new Scanner(System.in)).nextInt();
        float times = (totalStudents - lateStudents) / (float)lateStudents;
        System.out.println("出勤学生数 是 迟到学生数 的" + times + "倍");

        System.out.println("程序结束");
    }

    // 按正常思路写完代码后，预感到可能会出问题：
    //  1.万一用户输入不匹配（小数、字符），怎么取到数据？
    //  2.万一迟到的学生数是0，除法怎么算？
    // 用try catch进行改造
    public void considerExceptionCode(){
        int totalStudents = 100;
        System.out.println("程序开始，请输入 迟到学生数：");

        try{
            int lateStudents = (new Scanner(System.in)).nextInt();
            float times = (totalStudents - lateStudents) / (float)lateStudents;

            System.out.println("出勤学生数 是 迟到学生数 的" + times + "倍");
        } catch(InputMismatchException e){
            // 预案1：如果用户输入的不是整数，给出提示
            System.out.println("迟到学生数 必须是整数！");
        } catch(ArithmeticException e){
            // 预案2：如果除法计算出了问题，使用最大倍数
            System.out.println("出勤学生数 是 迟到学生数 的" + totalStudents + "倍");
        } finally{
            // 无论是否出问题，都要执行的代码
            System.out.println("程序结束");
        }

    }

    public static void main(String[] args){
        CatchException demo = new CatchException();

        // 如果输入非整数，程序会中断
        // 如果输入0，程序会中断
        //demo.normalCode(); 

        // 如果输入非整数或0，程序会按照“预案”进行处理
        demo.considerExceptionCode(); 
    }
}


