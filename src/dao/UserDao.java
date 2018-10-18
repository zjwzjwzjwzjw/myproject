package dao;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2018/10/11.
 */
public interface UserDao {
    User findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);//
    User findUserByName(String name);
    User findUserById(Integer id);
    void saveUser(@Param("name") String name, @Param("password") String password);//注册用户
    void updateUser(User user);//更新用户
    void saveResume(Resume resume);//保存简历
    void saveComputerResumes(Resume Resume);//发布简历
    void updateResume(Resume resume);//更新简历
    Resume findResumeById(Integer id);//查找简历
    Resume findResumeByRid(Integer rid);//查找简历
    List<Dept> findAllDept();//查找所有部门
    Dept findByName(String name);
    List<Job> findAllJob();//查找所有职位
    List<Job> findJobByDId(Integer dId);
    InterviewTable findInterviewByIid(Integer id);//根据面试表的id查找面试信息
    List<InterviewTable> findInterviewByUid(Integer id);//根据用户的id查找面试信息
    void updateInterviewType(InterviewTable interviewTable);
}
