package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class ComputerTrainTable {
    private Integer ctid;
    private String context;//��ѵ����
    private Date ttime;//��ʼʱ��
    private String dname;//����ѵ�Ĳ���name
    private String tbtype;//��ѵ�Ƿ��ѿ�ʼ ��������ѵ ���ѽ���
    private Integer needTime;//��ѵʱ��
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
