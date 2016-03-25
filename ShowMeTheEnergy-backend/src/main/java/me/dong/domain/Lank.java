package me.dong.domain;

public class Lank {

	Integer lank;
	String uuid;
	Long point;
	Integer change; // 1:up, 0:±×´ë·Î, -1:down
	Integer changeRange;

	public Lank(Integer lank, String uuid, Long point, Integer change, Integer changeRange) {
		this.lank = lank;
		this.uuid = uuid;
		this.point = point;
		this.change = change;
		this.changeRange = changeRange;
	}

	public Lank() {
	}

	public Integer getLank() {
		return lank;
	}

	public void setLank(Integer lank) {
		this.lank = lank;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}

	public Integer getChangeRange() {
		return changeRange;
	}

	public void setChangeRange(Integer changeRange) {
		this.changeRange = changeRange;
	}

}
