package entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by user on 2018/10/11.
 */
@Component
public class Resume {
    private Integer rid;
    private Integer uid;
    private String tname;//��ʵ����
    private Integer age;
    private Integer tel;//��ϵ��ʽ
    private String dept;//����
    private String job;//ְλ
    private String beforeJob;//��һ��ְλ
    private String salary;//����нˮ
    private String gender;//�Ա�
    private String education;//ѧ��
    private String email;
    private String policitalStatus;//������ò
    private String workExperience;//��������
    private String favorite;//����
    private Integer rtype=0;//0δ����1�ѷ���2�Ѳ鿴3֪ͨ����
    private Date time;
    public Resume() {
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

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "rid=" + rid +
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
                ", rtype=" + rtype +
                ", time=" + time +
                '}';
    }
}
