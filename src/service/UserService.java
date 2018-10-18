package service;

import entity.*;

import java.util.List;

/**
 * Created by user on 2018/10/11.
 */
public interface UserService {
    User findUserByNameAndPassword(String name, String password);
    User findUserByName(String name);
    User findUserById(Integer id);
    void saveUser(String name, String password);//�����û�
    void updateUser(User user);//�����û�
    void saveResume(Resume resume);//�������
    void updateResume(Resume resume);//���¼���
    Resume findResumeById(Integer id);//���Ҽ���
    Resume findResumeByRid(Integer rid);
    void saveComputerResumes(Resume Resume);//��������
    List<Dept> findAllDept();//�������в���
    Dept findByName(String name);
    List<Job> findAllJob();//��������ְλ
    List<Job> findJobByDId(Integer dId);
    InterviewTable findInterviewByIid(Integer id);//����������Ϣ
    List<InterviewTable> findInterviewByUid(Integer id);//�����û���id����������Ϣ
    void updateInterviewType(InterviewTable interviewTable);
}
