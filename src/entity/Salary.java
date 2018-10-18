package entity;

/**
 * Created by user on 2018/10/12.
 */
public class Salary {
    private Integer sid;
    private Integer uid;
    private Integer syear;
    private Integer smonth;
    private Double baseSalary;//基本工资
    private Double overTimeSalary;//加班工资
    private Double performanceSalary;//绩效工资
    private Double publishmentSalary;//奖惩金额
    private Double socical;//社保
    private Double actualSalary;//实发工资
    public Salary() {
    }

    public Integer getSyear() {
        return syear;
    }

    public void setSyear(Integer syear) {
        this.syear = syear;
    }

    public Double getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(Double actualSalary) {
        this.actualSalary = actualSalary;
    }

    public Integer getSmonth() {
        return smonth;
    }

    public void setSmonth(Integer smonth) {
        this.smonth = smonth;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getOverTimeSalary() {
        return overTimeSalary;
    }

    public void setOverTimeSalary(Double overTimeSalary) {
        this.overTimeSalary = overTimeSalary;
    }

    public Double getPerformanceSalary() {
        return performanceSalary;
    }

    public void setPerformanceSalary(Double performanceSalary) {
        this.performanceSalary = performanceSalary;
    }

    public Double getPublishmentSalary() {
        return publishmentSalary;
    }

    public void setPublishmentSalary(Double publishmentSalary) {
        this.publishmentSalary = publishmentSalary;
    }

    public Double getSocical() {
        return socical;
    }

    public void setSocical(Double socical) {
        this.socical = socical;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", syear=" + syear +
                ", smonth=" + smonth +
                ", baseSalary=" + baseSalary +
                ", overTimeSalary=" + overTimeSalary +
                ", performanceSalary=" + performanceSalary +
                ", publishmentSalary=" + publishmentSalary +
                ", socical=" + socical +
                ", actualSalary=" + actualSalary +
                '}';
    }
}
