package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class CheckWork {
    private Integer cid;//���id
    private Integer uid;//�û�id
    private Integer cyear;//���
    private Integer cmonth;//�·�
    private Integer cdate;//����
    private String cbegintime;//�ϰ��ʱ��
    private String caftertime;//�°��ʱ��
    private Integer cworkday;//�ѹ���������
    private Integer cneedworkday;//�蹤��������
    private String cwtype;//�ϰ�״̬ �ٵ� ���� ����  ȱ��
    public CheckWork() {
    }

    public String getCwtype() {
        return cwtype;
    }

    public void setCwtype(String cwtype) {
        this.cwtype = cwtype;
    }

    public Integer getCyear() {
        return cyear;
    }

    public void setCyear(Integer cyear) {
        this.cyear = cyear;
    }

    public Integer getCdate() {
        return cdate;
    }

    public void setCdate(Integer cdate) {
        this.cdate = cdate;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCmonth() {
        return cmonth;
    }

    public void setCmonth(Integer cmonth) {
        this.cmonth = cmonth;
    }

    public String getCbegintime() {
        return cbegintime;
    }

    public void setCbegintime(String cbegintime) {
        this.cbegintime = cbegintime;
    }

    public String getCaftertime() {
        return caftertime;
    }

    public void setCaftertime(String caftertime) {
        this.caftertime = caftertime;
    }

    public Integer getCworkday() {
        return cworkday;
    }

    public void setCworkday(Integer cworkday) {
        this.cworkday = cworkday;
    }

    public Integer getCneedworkday() {
        return cneedworkday;
    }

    public void setCneedworkday(Integer cneedworkday) {
        this.cneedworkday = cneedworkday;
    }

    @Override
    public String toString() {
        return "CheckWork{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", cyear=" + cyear +
                ", cmonth=" + cmonth +
                ", cdate=" + cdate +
                ", cbegintime='" + cbegintime + '\'' +
                ", caftertime='" + caftertime + '\'' +
                ", cworkday=" + cworkday +
                ", cneedworkday=" + cneedworkday +
                ", cwtype='" + cwtype + '\'' +
                '}';
    }
}
