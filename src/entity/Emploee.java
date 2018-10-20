package entity;

import java.util.Date;

/**
 * Created by user on 2018/10/12.
 */
public class Emploee {
    private Integer uid;
    private Integer etype;//员工状态 1在职,0 离职
    private String ename;//员工姓名
    private String egender;
    private Integer eage;//员工年龄
    private String eeducation;//学历
    private String efavorite;//爱好
    private String eemail;//邮箱
    private Integer etel;//联系电话
    private String edept;//所属 部门
    private String ejob;//职位
    private String epolicitalStatus;//政治面貌
    private Date ebegintime;//入职时间
    private Date eaftertime;//离职时间
    private String ereason;//离职原因

    public Emploee() {
    }

    public String getEgender() {
        return egender;
    }

    public void setEgender(String egender) {
        this.egender = egender;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEtype() {
        return etype;
    }

    public void setEtype(Integer etype) {
        this.etype = etype;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

    public String getEeducation() {
        return eeducation;
    }

    public void setEeducation(String eeducation) {
        this.eeducation = eeducation;
    }

    public String getEfavorite() {
        return efavorite;
    }

    public void setEfavorite(String efavorite) {
        this.efavorite = efavorite;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public Integer getEtel() {
        return etel;
    }

    public void setEtel(Integer etel) {
        this.etel = etel;
    }

    public String getEdept() {
        return edept;
    }

    public void setEdept(String edept) {
        this.edept = edept;
    }

    public String getEjob() {
        return ejob;
    }

    public void setEjob(String ejob) {
        this.ejob = ejob;
    }

    public String getEpolicitalStatus() {
        return epolicitalStatus;
    }

    public void setEpolicitalStatus(String epolicitalStatus) {
        this.epolicitalStatus = epolicitalStatus;
    }

    public Date getEbegintime() {
        return ebegintime;
    }

    public void setEbegintime(Date ebegintime) {
        this.ebegintime = ebegintime;
    }

    public Date getEaftertime() {
        return eaftertime;
    }

    public void setEaftertime(Date eaftertime) {
        this.eaftertime = eaftertime;
    }

    public String getEreason() {
        return ereason;
    }

    public void setEreason(String ereason) {
        this.ereason = ereason;
    }

    @Override
    public String toString() {
        return "Emploee{" +
                "uid=" + uid +
                ", etype=" + etype +
                ", ename='" + ename + '\'' +
                ", egender='" + egender + '\'' +
                ", eage=" + eage +
                ", eeducation='" + eeducation + '\'' +
                ", efavorite='" + efavorite + '\'' +
                ", eemail='" + eemail + '\'' +
                ", etel=" + etel +
                ", edept='" + edept + '\'' +
                ", ejob='" + ejob + '\'' +
                ", epolicitalStatus='" + epolicitalStatus + '\'' +
                ", ebegintime=" + ebegintime +
                ", eaftertime=" + eaftertime +
                ", ereason='" + ereason + '\'' +
                '}';
    }
}
