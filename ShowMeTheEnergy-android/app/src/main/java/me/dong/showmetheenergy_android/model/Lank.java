package me.dong.showmetheenergy_android.model;

/**
 * Created by Dong on 2016-03-26.
 */
public class Lank {

    Integer lank;
    String id;
    Integer point;
    Integer change;  //1:up, 0:그대로, -1:down
    Integer changeRange;

    public Integer getLank() {
        return lank;
    }

    public void setLank(Integer lank) {
        this.lank = lank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
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
