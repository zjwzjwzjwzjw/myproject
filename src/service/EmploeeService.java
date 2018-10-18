package service;

import entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/13.
 */
public interface EmploeeService {
    void  saveEmploee(InterviewTable interviewTable);//增加员工
    void updateEmploee(Emploee emploee);//更新员工
    Emploee findEmpByUid(Integer id);//通过员工id查找员工信息
    List<Emploee> findEmpByDname(String dname);//查找某部门的员工
    List<Emploee> findEmpByDnameAndJName(String dname, String jname);//查找同职位员工
    Dept findDeptByDId(Integer dId);
    List<Emploee> findAllEmp();
    Job findJobByJid(Integer jId);
    List<CheckWork> findCheckWorkByUid(Integer id);//查看考勤
    void saveCheckBeginTimeByUid(CheckWork checkWork);//上班打卡
    void updateCheckAfterTimeByCid(CheckWork checkWork);//下班打卡
    void savePublishment(Publishment publishment);//生成奖惩记录
    List<Publishment> findPubByUid(Integer id);//查看惩罚记录
    List<Salary> findSalaryByUid(Integer id);//查看薪水
    Salary findSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month);//查找员工某月的薪水记录
    void updateSalaryBySid(Salary salary);//更新某员工的薪水记录
    void saveSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month);//创建员工某月的薪水记录
    List<TrainTable> findTrainTableByUid(Integer id);//查看本人培训记录
    void updateTrainTableByTidAndUid(TrainTable trainTable);//改变某员工的培训记录状态
    TrainTable findTrainTableByUidAndTid(Integer uid, Integer tid);//查看本人某次培训记录
    List<ChangeSalary> findChangeSalaryByUid(Integer id);
    CheckWork findCheckWorkByCdateAndUid(Integer cyear, Integer cmonth, Integer cdate, Integer uid);//查找某员工某年某月某日的打卡记录
    List<CheckWork> findCheckWorkByCmonthAndUid(Integer cyear, Integer cmonth, Integer uid);//查找某员工某年某月的打卡记录
}
