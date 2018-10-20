package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class CheckWork {
    private Integer cid;//表的id
    private Integer uid;//用户id
    private Integer cyear;//年份
    private Integer cmonth;//月份
    private Integer cdate;//日期
    private String cbegintime;//上班打卡时间
    private String caftertime;//下班打卡时间
    private Integer cworkday;//已工作多少天
    private Integer cneedworkday;//需工作多少天
    private String cwtype;//上班状态 迟到 旷工 早退  缺勤
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
