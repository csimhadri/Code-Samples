package com.poc.rest.springboot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public final class Money implements Comparable<Money>, Serializable {
	private static final long serialVersionUID = 7526471155622776147L;

	/**
	 * The money amount. Never null.
	 * 
	 * @serial
	 */
	private BigDecimal amount;

	/**
	 * The currency of the money, such as US Dollars or Euros. Never null.
	 * 
	 * @serial
	 */
	private Currency currency;

	
	/** @serial */
	private int fHashCode;
	private static final int HASH_SEED = 23;
	private static final int HASH_FACTOR = 37;

	static Currency DEFAULT_CURRENCY = Currency.getInstance("USD");

	/**
	 * Full constructor.
	 * 
	 * @param aAmount
	 *            is required, can be positive or negative. The number of
	 *            decimals in the amount cannot <em>exceed</em> the maximum
	 *            number of decimals for the given {@link Currency}. It's
	 *            possible to create a <tt>Money</tt> object in terms of
	 *            'thousands of dollars', for instance. Such an amount would
	 *            have a scale of -3.
	 * @param aCurrency
	 *            is required.
	 * @param aRoundingStyle
	 *            is required, must match a rounding style used by
	 *            {@link BigDecimal}.
	 */
	public Money() {
		
		currency = DEFAULT_CURRENCY;
		
	}

	/**
	 * Constructor taking only the money amount.
	 * 
	 * <P>
	 * The currency and rounding style both take default values.
	 * 
	 * @param aAmount
	 *            is required, can be positive or negative.
	 */
	public Money(BigDecimal aAmount) {
		this(aAmount, DEFAULT_CURRENCY);
	}
	

	/**
	 * Constructor taking the money amount and currency.
	 * 
	 * <P>
	 * The rounding style takes a default value.
	 * 
	 * @param aAmount
	 *            is required, can be positive or negative.
	 * @param aCurrency
	 *            is required.
	 */
	public Money(BigDecimal aAmount, Currency aCurrency) {
		this.amount = aAmount;
		this.currency = aCurrency;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	/** Return the amount passed to the constructor. */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Return the currency passed to the constructor, or the default currency.
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Returns {@link #getAmount()}.getPlainString() + space +
	 * {@link #getCurrency()}.getSymbol().
	 * 
	 * <P>
	 * The return value uses the runtime's <em>default locale</em>, and will not
	 * always be suitable for display to an end user.
	 */
	public String toString() {
		return amount.toPlainString() + " " + currency.getSymbol();
	}

	/**
	 * Like {@link BigDecimal#equals(java.lang.Object)}, this <tt>equals</tt>
	 * method is also sensitive to scale.
	 * 
	 * For example, <tt>10</tt> is <em>not</em> equal to <tt>10.00</tt> The
	 * {@link #eq(Money)} method, on the other hand, is <em>not</em> sensitive
	 * to scale.
	 */
	public boolean equals(Object aThat) {
		if (this == aThat)
			return true;
		if (!(aThat instanceof Money))
			return false;
		Money that = (Money) aThat;
		// the object fields are never null :
		boolean result = (this.amount.equals(that.amount));
		result = result && (this.currency.equals(that.currency));

		return result;
	}

	public int hashCode() {
		if (fHashCode == 0) {
			fHashCode = HASH_SEED;
			fHashCode = HASH_FACTOR * fHashCode + amount.hashCode();
			fHashCode = HASH_FACTOR * fHashCode + currency.hashCode();

		}
		return fHashCode;
	}

	public int compareTo(Money aThat) {
		final int EQUAL = 0;

		if (this == aThat)
			return EQUAL;

		// the object fields are never null
		int comparison = this.amount.compareTo(aThat.amount);
		if (comparison != EQUAL)
			return comparison;

		comparison = this.currency.getCurrencyCode().compareTo(aThat.currency.getCurrencyCode());
		if (comparison != EQUAL)
			return comparison;

		if (comparison != EQUAL)
			return comparison;

		return EQUAL;
	}


}
