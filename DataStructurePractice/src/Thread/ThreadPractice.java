package Thread;



public class ThreadPractice {

	
	public static void main(String[] args) throws InterruptedException {
		
		MyThread t =  new MyThread();
		t.start();
		System.out.println(System.currentTimeMillis());
		//t.sleep(20000l);
		Thread t1 = new Thread();
		System.out.println(t.currentThread());
		System.out.println(t1.currentThread());
		System.out.println(System.currentTimeMillis());
		
	}
}
