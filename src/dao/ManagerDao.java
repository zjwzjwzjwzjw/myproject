package dao;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2018/10/15.
 */
public interface ManagerDao extends EmploeeDao{
    void saveDept(Dept dept);//���Ӳ���
    void delDeptByDid(Integer dId);//ɾ������
    void updateDept(Dept dept);//���Ĳ���
    void saveJob(Job job);//����ְλ
    void delJobByJid(Integer jId);//ɾ��ְλ
    void updateJob(Job job);//����ְλ
    void saveComputerTrainTable(ComputerTrainTable computerTrainTable);//������ѵ��Ϣ
    List<ComputerTrainTable> findAllComputerTrainTable();
    ComputerTrainTable findComputerTrainTableByCtid(Integer ctid);
    void updateComputerTrainTable(ComputerTrainTable computerTrainTable);//������ѵ��Ϣ
    void delComputerTrainTable(Integer ctid);//ɾ����ѵ��Ϣ
    List<ComputerResumes> findAllComputerResumes();//�������еļ�����Ϣ
    void delComputerResumesByCrid(Integer crid);//ɾ��������Ϣ
    ComputerResumes findComputerResumesByCrid(Integer crid);//���Ҽ�����Ϣ
    ComputerResumes findComputerResumesByRid(Integer rid);//���Ҽ�����Ϣ
    void updateComputerResumesByCrid(ComputerResumes computerResumes);//���¼�����Ϣ
    void saveInterviewTable(ComputerResumes computerResumes);//����������Ϣ
    List<InterviewTable> findAllInterview();
    List<Publishment> findAllPublishment();
    Publishment findPubByPid(Integer pid);
    void delPubByPid(Integer pid);
    void editPubByPid(Publishment publishment);
    InterviewTable findInterviewByRid(Integer rid);
    Job findJobByJname(@Param("jName") String jName, @Param("dId") Integer dId);
}
