package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class Publishment {
    private Integer pid;//���ͱ��id
    private Integer uid;//Ա��id
    private Double publishmentSalary;//���ͽ��
    private String pcontext;//����ԭ��
    private Date ptime;//����ʱ��

    public Publishment() {

    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getPublishmentSalary() {
        return publishmentSalary;
    }

    public void setPublishmentSalary(Double publishmentSalary) {
        this.publishmentSalary = publishmentSalary;
    }

    public String getPcontext() {
        return pcontext;
    }

    public void setPcontext(String pcontext) {
        this.pcontext = pcontext;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }
}
