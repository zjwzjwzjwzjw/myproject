package entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by user on 2018/10/11.
 */
@Component
public class ComputerResumes {
    private Integer crid;//
    private Integer rid;
    private Integer uid;
    private String tname;//真实名字
    private Integer age;
    private Integer tel;//联系方式
    private String dept;//部门
    private String job;//职位
    private String beforeJob;//上一次职位
    private String salary;//期望薪水
    private String gender;//性别
    private String education;//学历
    private String email;
    private String policitalStatus;//政治面貌
    private String workExperience;//工作经历
    private String favorite;//爱好
    private String crtype;//未查看已查看
    private String cstype;//面试状态
    private Date time;//面试时间
    private Date sendTime;//投递简历时间
    public ComputerResumes() {
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getCstype() {
        return cstype;
    }

    public void setCstype(String cstype) {
        this.cstype = cstype;
    }

    public Integer getCrid() {
        return crid;
    }

    public void setCrid(Integer crid) {
        this.crid = crid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBeforeJob() {
        return beforeJob;
    }

    public void setBeforeJob(String beforeJob) {
        this.beforeJob = beforeJob;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPolicitalStatus() {
        return policitalStatus;
    }

    public void setPolicitalStatus(String policitalStatus) {
        this.policitalStatus = policitalStatus;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getCrtype() {
        return crtype;
    }

    public void setCrtype(String crtype) {
        this.crtype = crtype;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ComputerResumes{" +
                "crid=" + crid +
                ", rid=" + rid +
                ", uid=" + uid +
                ", tname='" + tname + '\'' +
                ", age=" + age +
                ", tel=" + tel +
                ", dept='" + dept + '\'' +
                ", job='" + job + '\'' +
                ", beforeJob='" + beforeJob + '\'' +
                ", salary='" + salary + '\'' +
                ", gender='" + gender + '\'' +
                ", education='" + education + '\'' +
                ", email='" + email + '\'' +
                ", policitalStatus='" + policitalStatus + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", favorite='" + favorite + '\'' +
                ", crtype='" + crtype + '\'' +
                ", cstype='" + cstype + '\'' +
                ", time=" + time +
                ", sendTime=" + sendTime +
                '}';
    }
}
