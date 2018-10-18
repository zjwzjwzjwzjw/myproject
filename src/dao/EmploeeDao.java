package dao;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/12.
 */
public interface EmploeeDao extends UserDao{
    void  saveEmploee(InterviewTable interviewTable);//����Ա��
    void updateEmploee(Emploee emploee);//����Ա��
    Emploee findEmpByUid(Integer id);//ͨ��Ա��id����Ա����Ϣ
    List<Emploee> findEmpByDname(String dname);//����ĳ���ŵ�Ա��
    List<Emploee> findAllEmp();//
    Dept findDeptByDId(Integer dId);
    Job findJobByJid(Integer jId);
    List<Emploee> findEmpByDnameAndJName(@Param("dname") String dname, @Param("jname") String jname);//����ְͬλԱ��
    List<CheckWork> findCheckWorkByUid(Integer id);//����Ա��id�鿴����
    CheckWork findCheckWorkByCdateAndUid(@Param("cyear") Integer cyear, @Param("cmonth") Integer cmonth, @Param("cdate") Integer cdate, @Param("uid") Integer uid);//����ĳԱ��ĳ��ĳ��ĳ�յĴ򿨼�¼
    List<CheckWork> findCheckWorkByCmonthAndUid(@Param("cyear") Integer cyear, @Param("cmonth") Integer cmonth, @Param("uid") Integer uid);//����ĳԱ��ĳ��ĳ�µĴ򿨼�¼
    void saveCheckBeginTimeByUid(CheckWork checkWork);//�ϰ��
    void updateCheckAfterTimeByCid(CheckWork checkWork);//�°��
    void savePublishment(Publishment publishment);//���ɽ��ͼ�¼
    List<Publishment> findPubByUid(Integer id);//�鿴�ͷ���¼
    List<Salary> findSalaryByUid(Integer id);//�鿴Ա�����е�нˮ��¼
    Salary findSalaryByUidAndYearAndMonth(@Param("uid") Integer uid, @Param("year") Integer year, @Param("month") Integer month);//����Ա��ĳ�µ�нˮ��¼
    void updateSalaryBySid(Salary salary);//����ĳԱ����нˮ��¼
    void saveSalaryByUidAndYearAndMonth(@Param("uid") Integer uid, @Param("year") Integer year, @Param("month") Integer month);//����Ա��ĳ�µ�нˮ��¼
    List<TrainTable> findTrainTableByUid(Integer id);//�鿴������ѵ��¼
    void updateTrainTableByTidAndUid(TrainTable trainTable);//�ı�ĳԱ������ѵ��¼״̬
    TrainTable findTrainTableByUidAndTid(@Param("uid") Integer uid, @Param("tid") Integer tid);//�鿴����ĳ����ѵ��¼
    List<ChangeSalary> findChangeSalaryByUid(Integer id);
}
