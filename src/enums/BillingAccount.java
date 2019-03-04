package enums;

enum PaymentTypes{
	PREPAID,POSTPAID,NEW
}

public class BillingAccount {

	@SuppressWarnings("unused")
	private String billingNumber;
	private String paymentType;
	
	public BillingAccount(){
		billingNumber = "32423";
		paymentType = PaymentTypes.POSTPAID.toString();
	
	}
	@Override
	public String toString(){
		return "paymentType=" + paymentType;
	}
	
public static void main(String[] args) {
		
		System.out.println( new BillingAccount());//paymentType=POSTPAID
		System.out.println(PaymentTypes.NEW);//NEW

	}
}


