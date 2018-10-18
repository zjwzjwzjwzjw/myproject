package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/11.
 */
public class Job {
    private Integer jId;
    private Integer dId;//部门id
    private String jName;//职位名
    private Date jtime;//创建时间
    private String juname;//创建者的名字
    private Integer juid;//创建者的id

    public Job() {
    }

    public Date getJtime() {
        return jtime;
    }

    public void setJtime(Date jtime) {
        this.jtime = jtime;
    }

    public String getJuname() {
        return juname;
    }

    public void setJuname(String juname) {
        this.juname = juname;
    }

    public Integer getJuid() {
        return juid;
    }

    public void setJuid(Integer juid) {
        this.juid = juid;
    }

    public Integer getjId() {
        return jId;
    }

    public void setjId(Integer jId) {
        this.jId = jId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }
}
