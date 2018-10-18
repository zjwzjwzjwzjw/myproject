package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class TrainTable {
    private Integer tid;
    private Integer uid;
    private String context;//培训内容
    private Date ttime;//开始时间
    private String dname;//受培训的部门name
    private Integer ttype;//是否参加
    private String tbtype;//培训是否已开始 或正在培训 或已结束
    private Integer needTime;//培训时长
    private String trype;//员工是否查看过 未查看 已查看
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
