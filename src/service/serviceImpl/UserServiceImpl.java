package service.serviceImpl;

import dao.UserDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

/**
 * Created by user on 2018/10/11.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByNameAndPassword(String name, String password) {
        return userDao.findUserByNameAndPassword(name,password);
    }
    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public void saveUser(String name, String password) {
        userDao.saveUser(name,password);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void saveResume(Resume resume) {
        userDao.saveResume(resume);
    }

    @Override
    public void updateResume(Resume resume) {
        userDao.updateResume(resume);
    }

    @Override
    public Resume findResumeById(Integer id) {
        return userDao.findResumeById(id);
    }

    @Override
    public Resume findResumeByRid(Integer rid) {
        return userDao.findResumeByRid(rid);
    }

    @Override
    public void saveComputerResumes(Resume Resume) {
        userDao.saveComputerResumes(Resume);
    }

    @Override
    public List<Dept> findAllDept() {
        return userDao.findAllDept();
    }

    @Override
    public Dept findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<Job> findAllJob() {
        return userDao.findAllJob();
    }

    @Override
    public List<Job> findJobByDId(Integer dId) {
        return userDao.findJobByDId(dId);
    }

    @Override
    public InterviewTable findInterviewByIid(Integer id) {
        return userDao.findInterviewByIid(id);
    }

    @Override
    public List<InterviewTable> findInterviewByUid(Integer id) {
        return userDao.findInterviewByUid(id);
    }

    @Override
    public void updateInterviewType(InterviewTable interviewTable) {
        userDao.updateInterviewType(interviewTable);
    }
}
