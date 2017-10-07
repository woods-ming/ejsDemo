// 命令类
interface Command  {  
    void Execute();  
}

// 接收命令（并实际执行）的类
public class Receiver {
    public void Read(){
        system.out.println("读...");
    }

    public void Write(){
        system.out.println("写...");
    }
}

// 具体命令1：读
public class ReadCommand implements Command {
    private Receiver _receiver;
    public ReadCommand(Receiver receiver){
        _receiver = receiver;
    }

    public void Execute(){
        _receiver.Read();
    }
}
// 具体命令2：写
public class WriteCommand implements Command {
    private Receiver _receiver;
    public WriteCommand(Receiver receiver){
        _receiver = receiver;
    }
    
    public void Execute(){
        _receiver.Write();
    }
}

public class Client {
    public void Main() {
        Receiver receiver = new Receiver();
        Command command1 = new ReadCommand(receiver);
        Command command2 = new WriteCommand(receiver);

        // 传输、存储...
        command1.Execute();
        command2.Execute();
    }
}