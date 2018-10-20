package service;

import entity.*;

import java.util.List;

/**
 * Created by user on 2018/10/15.
 */
public interface ManagerService {
    void saveDept(Dept dept);//���Ӳ���
    void delDeptByDid(Integer did);//ɾ������
    void updateDept(Dept dept);//���Ĳ���
    void saveJob(Job job);//����ְλ
    void delJobByJid(Integer jid);//ɾ��ְλ
    void updateJob(Job job);//����ְλ
    void saveComputerTrainTable(ComputerTrainTable computerTrainTable);//������ѵ��Ϣ
    void updateComputerTrainTable(ComputerTrainTable computerTrainTable);//������ѵ��Ϣ
    void delComputerTrainTable(Integer ctid);//ɾ����ѵ��Ϣ
    List<ComputerResumes> findAllComputerResumes();
    void delComputerResumesByCrid(Integer crid);//ɾ��������Ϣ
    ComputerResumes findComputerResumesByCrid(Integer crid);//���Ҽ�����Ϣ
    ComputerResumes findComputerResumesByRid(Integer rid);//���Ҽ�����Ϣ
    void updateComputerResumesByCrid(ComputerResumes computerResumes);//���¼�����Ϣ
    void saveInterviewTable(ComputerResumes computerResumes);
    List<InterviewTable> findAllInterview();
    List<Publishment> findAllPublishment();
    List<Publishment> findPublishmentByUid(Integer uid);
    InterviewTable findInterviewByRid(Integer rid);
    Job findJobByJname(String jName, Integer dId);
    List<ComputerTrainTable> findAllComputerTrainTable();
    ComputerTrainTable findComputerTrainTableByCtid(Integer ctid);
    Publishment findPubByPid(Integer pid);
    void editPubByPid(Publishment publishment);
    void delPubByPid(Integer pid);
}
