package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/11.
 */
public class Job {
    private Integer jId;
    private Integer dId;//����id
    private String jName;//ְλ��
    private Date jtime;//����ʱ��
    private String juname;//�����ߵ�����
    private Integer juid;//�����ߵ�id

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
