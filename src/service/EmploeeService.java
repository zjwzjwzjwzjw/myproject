package service;

import entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/13.
 */
public interface EmploeeService {
    void  saveEmploee(InterviewTable interviewTable);//����Ա��
    void updateEmploee(Emploee emploee);//����Ա��
    Emploee findEmpByUid(Integer id);//ͨ��Ա��id����Ա����Ϣ
    List<Emploee> findEmpByDname(String dname);//����ĳ���ŵ�Ա��
    List<Emploee> findEmpByDnameAndJName(String dname, String jname);//����ְͬλԱ��
    Dept findDeptByDId(Integer dId);
    List<Emploee> findAllEmp();
    Job findJobByJid(Integer jId);
    List<CheckWork> findCheckWorkByUid(Integer id);//�鿴����
    void saveCheckBeginTimeByUid(CheckWork checkWork);//�ϰ��
    void updateCheckAfterTimeByCid(CheckWork checkWork);//�°��
    void savePublishment(Publishment publishment);//���ɽ��ͼ�¼
    List<Publishment> findPubByUid(Integer id);//�鿴�ͷ���¼
    List<Salary> findSalaryByUid(Integer id);//�鿴нˮ
    Salary findSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month);//����Ա��ĳ�µ�нˮ��¼
    void updateSalaryBySid(Salary salary);//����ĳԱ����нˮ��¼
    void saveSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month);//����Ա��ĳ�µ�нˮ��¼
    List<TrainTable> findTrainTableByUid(Integer id);//�鿴������ѵ��¼
    void updateTrainTableByTidAndUid(TrainTable trainTable);//�ı�ĳԱ������ѵ��¼״̬
    TrainTable findTrainTableByUidAndTid(Integer uid, Integer tid);//�鿴����ĳ����ѵ��¼
    List<ChangeSalary> findChangeSalaryByUid(Integer id);
    CheckWork findCheckWorkByCdateAndUid(Integer cyear, Integer cmonth, Integer cdate, Integer uid);//����ĳԱ��ĳ��ĳ��ĳ�յĴ򿨼�¼
    List<CheckWork> findCheckWorkByCmonthAndUid(Integer cyear, Integer cmonth, Integer uid);//����ĳԱ��ĳ��ĳ�µĴ򿨼�¼
}
