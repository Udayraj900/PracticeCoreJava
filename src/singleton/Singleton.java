package singleton;

public class Singleton {

	private Singleton() {
		System.out.println("Instance Created...");
	}

	// Eager Initialization
	private static Singleton singletonObjectEager = new Singleton();
	public static Singleton getSingletonInstance() {
		return singletonObjectEager;
	}

	
	private static Singleton singletonObject;

	// Lazy
	public static Singleton getSingletonInstanceLazy() {
		if (singletonObject == null)
			singletonObject = new Singleton();
		return singletonObject;
	}

	// Thread Safe
	public static synchronized Singleton getSingletonInstanceThreadSafe() {
		if (singletonObject == null)
			singletonObject = new Singleton();
		return singletonObject;
	}

	// Double check locking
	public static Singleton getSingletonInstanceThreadSafeDoubleCheck() {
		if (singletonObject == null) {
			synchronized (Singleton.class) {
				if (singletonObject == null)
					singletonObject = new Singleton();
			}
		}
		return singletonObject;
	}
}
