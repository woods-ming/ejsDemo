// 工人
public interface IWorkable{
	void work();
}
public class Coder implements IWorkable{
	public void work(){
		System.out.println("coding...");
	}
}
public class Tester implements IWorkable{
	public void work(){
		System.out.println("testing...");
	}
}
public class Financer implements IWorkable{
	public void work(){
		System.out.println("accounting...");
	}
}

// 企业
public interface IPayable{
	void pay();
}
public class 国企{
	public void pay(){
		System.out.println("公积金...过节费...各种补助");
	}
}
public class 私企{
	public void pay(){
		System.out.println("工资...");
	}
}
public class 外企{
	public void pay(){
		System.out.println("工资...绩效奖金...");
	}
}

// 人才市场
public class MarketMediator {
	private 
	public void registerWorker(IWorkable worker){

	}

	public void registerCompany(IPayable payer){

	}

	public void AddDesigner(){

	}

	public void addBoss(){

	}

	public void getAJob(){

	}

	public void getAWorker(){

	}
}
