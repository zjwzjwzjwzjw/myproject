package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class ComputerTrainTable {
    private Integer ctid;
    private String context;//培训内容
    private Date ttime;//开始时间
    private String dname;//受培训的部门name
    private String tbtype;//培训是否已开始 或正在培训 或已结束
    private Integer needTime;//培训时长
    public ComputerTrainTable() {
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public Integer getCtid() {
        return ctid;
    }

    public void setCtid(Integer ctid) {
        this.ctid = ctid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTtime() {
        return ttime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }


    public String getTbtype() {
        return tbtype;
    }

    public void setTbtype(String tbtype) {
        this.tbtype = tbtype;
    }

    @Override
    public String toString() {
        return "ComputerTrainTable{" +
                "ctid=" + ctid +
                ", context='" + context + '\'' +
                ", ttime=" + ttime +
                ", dname='" + dname + '\'' +
                ", tbtype='" + tbtype + '\'' +
                ", needTime=" + needTime +
                '}';
    }
}
