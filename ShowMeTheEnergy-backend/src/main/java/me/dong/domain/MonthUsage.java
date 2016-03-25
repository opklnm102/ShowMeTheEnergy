package me.dong.domain;

public class MonthUsage {

	Long timestamp; // start timestamp (ms)
	Long unitPeriodUsage; // electricity usages in this period (mWh)
	Long unitPeriodBill; // bill for elctricity usages (Korean Won)
	Long meteringPeriodUsage; //
	Long meteringPeriodBill; //

	public MonthUsage() {
	}

	public MonthUsage(Long timestamp, Long unitPeriodUsage, Long unitPeriodBill, Long meteringPeriodUsage,
			Long meteringPeriodBill) {
		this.timestamp = timestamp;
		this.unitPeriodUsage = unitPeriodUsage;
		this.unitPeriodBill = unitPeriodBill;
		this.meteringPeriodUsage = meteringPeriodUsage;
		this.meteringPeriodBill = meteringPeriodBill;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getUnitPeriodUsage() {
		return unitPeriodUsage;
	}

	public void setUnitPeriodUsage(Long unitPeriodUsage) {
		this.unitPeriodUsage = unitPeriodUsage;
	}

	public Long getUnitPeriodBill() {
		return unitPeriodBill;
	}

	public void setUnitPeriodBill(Long unitPeriodBill) {
		this.unitPeriodBill = unitPeriodBill;
	}

	public Long getMeteringPeriodUsage() {
		return meteringPeriodUsage;
	}

	public void setMeteringPeriodUsage(Long meteringPeriodUsage) {
		this.meteringPeriodUsage = meteringPeriodUsage;
	}

	public Long getMeteringPeriodBill() {
		return meteringPeriodBill;
	}

	public void setMeteringPeriodBill(Long meteringPeriodBill) {
		this.meteringPeriodBill = meteringPeriodBill;
	}
}
