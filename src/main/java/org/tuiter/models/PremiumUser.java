package org.tuiter.models;

import org.tuiter.util.PaymentDay;

public class PremiumUser extends User {
	private PaymentDay paymentDay;

	public PaymentDay getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(PaymentDay paymentDay) {
		this.paymentDay = paymentDay;
	}
	
}
