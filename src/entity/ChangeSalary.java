package entity;

/**
 * Created by user on 2018/10/12.
 */
public class ChangeSalary {//Ա��н�������
    private Integer csid;
    private Integer csuid;//Ա��id
    private Integer csmonth;//�·�
    private Double cssalary;//���
    private String cstype;//�Ƿ񷢷�

    public ChangeSalary() {
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public Integer getCsuid() {
        return csuid;
    }

    public void setCsuid(Integer csuid) {
        this.csuid = csuid;
    }

    public Integer getCsmonth() {
        return csmonth;
    }

    public void setCsmonth(Integer csmonth) {
        this.csmonth = csmonth;
    }

    public Double getCssalary() {
        return cssalary;
    }

    public void setCssalary(Double cssalary) {
        this.cssalary = cssalary;
    }

    public String getCstype() {
        return cstype;
    }

    public void setCstype(String cstype) {
        this.cstype = cstype;
    }
}
