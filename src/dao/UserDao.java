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
    void saveUser(@Param("name") String name, @Param("password") String password);//ע���û�
    void updateUser(User user);//�����û�
    void saveResume(Resume resume);//�������
    void saveComputerResumes(Resume Resume);//��������
    void updateResume(Resume resume);//���¼���
    Resume findResumeById(Integer id);//���Ҽ���
    Resume findResumeByRid(Integer rid);//���Ҽ���
    List<Dept> findAllDept();//�������в���
    Dept findByName(String name);
    List<Job> findAllJob();//��������ְλ
    List<Job> findJobByDId(Integer dId);
    InterviewTable findInterviewByIid(Integer id);//�������Ա��id����������Ϣ
    List<InterviewTable> findInterviewByUid(Integer id);//�����û���id����������Ϣ
    void updateInterviewType(InterviewTable interviewTable);
}
