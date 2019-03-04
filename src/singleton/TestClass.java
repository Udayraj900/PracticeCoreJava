package singleton;

public class TestClass {

	public static void main(String[] args) {
		
		//Singleton singleton1 = Singleton.getSingletonInstance();
		//System.out.println(singleton1.hashCode());
				
		//Singleton singleton2 = Singleton.getSingletonInstance();
		//System.out.println(singleton2.hashCode());

		//Singleton singleton1 = Singleton.getSingletonInstanceLazy();
		//System.out.println(singleton1.hashCode());
		
		//Singleton singleton2 = Singleton.getSingletonInstanceLazy();
		//System.out.println(singleton2.hashCode());
		
		//Thread t1 = new Thread(()-> Singleton.getSingletonInstanceThreadSafe());
		//Thread t2 = new Thread(()-> Singleton.getSingletonInstanceThreadSafe());
		//t1.start();t2.start();
		
		Thread t1 = new Thread(()-> Singleton.getSingletonInstanceThreadSafeDoubleCheck());
		Thread t2 = new Thread(()-> Singleton.getSingletonInstanceThreadSafeDoubleCheck());
		t1.start();t2.start();
	}

}
