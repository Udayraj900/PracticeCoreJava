public class Tester {
	public static void printMessage() {
		System.out.println("Hello");
	}

	public static void main(String args[]) {
	//	Thread t = new Thread(() -> printMessage());
		//t.start();
		
	//Thread  t= new Thread(Tester->printMessage);
		// Thread  t= new Thread(::printMessage);
//	Thread  t= new Thread(Tester::printMessage());
	Thread  t= new Thread(Tester::printMessage);
	}
	
	
}