package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class TrainTable {
    private Integer tid;
    private Integer uid;
    private String context;//��ѵ����
    private Date ttime;//��ʼʱ��
    private String dname;//����ѵ�Ĳ���name
    private Integer ttype;//�Ƿ�μ�
    private String tbtype;//��ѵ�Ƿ��ѿ�ʼ ��������ѵ ���ѽ���
    private Integer needTime;//��ѵʱ��
    private String trype;//Ա���Ƿ�鿴�� δ�鿴 �Ѳ鿴
    public TrainTable() {
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public String getTrype() {
        return trype;
    }

    public void setTrype(String trype) {
        this.trype = trype;
    }

    public Integer getTid() {
        return tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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

    public Integer getTtype() {
        return ttype;
    }

    public void setTtype(Integer ttype) {
        this.ttype = ttype;
    }

    public String getTbtype() {
        return tbtype;
    }

    public void setTbtype(String tbtype) {
        this.tbtype = tbtype;
    }
}
