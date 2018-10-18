package entity;

import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/10/11.
 */
@Component
public class User {
    private Integer id;
    private String accName;//账户名
    private String password;
    private Integer utype;//用户类型0主管1员工2游客

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accName='" + accName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + utype +
                '}';
    }
}
