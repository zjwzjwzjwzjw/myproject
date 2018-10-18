package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/11.
 */
public class Dept {
    private Integer dId;
    private String dName;//部门名
    private Date dtime;//创建时间
    private String duname;//创建者的名字
    private Integer duid;//创建者的id
    public Dept() {
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public String getDuname() {
        return duname;
    }

    public void setDuname(String duname) {
        this.duname = duname;
    }

    public Integer getDuid() {
        return duid;
    }

    public void setDuid(Integer duid) {
        this.duid = duid;
    }

    public Integer getdId() {
        return dId;
    }
    public void setdId(Integer dId) {
        this.dId = dId;
    }
    public String getdName() {
        return dName;
    }
    public void setdName(String dName) {
        this.dName = dName;
    }
}
