package dao;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/12.
 */
public interface EmploeeDao extends UserDao{
    void  saveEmploee(InterviewTable interviewTable);//增加员工
    void updateEmploee(Emploee emploee);//更新员工
    Emploee findEmpByUid(Integer id);//通过员工id查找员工信息
    List<Emploee> findEmpByDname(String dname);//查找某部门的员工
    List<Emploee> findAllEmp();//
    Dept findDeptByDId(Integer dId);
    Job findJobByJid(Integer jId);
    List<Emploee> findEmpByDnameAndJName(@Param("dname") String dname, @Param("jname") String jname);//查找同职位员工
    List<CheckWork> findCheckWorkByUid(Integer id);//根据员工id查看考勤
    CheckWork findCheckWorkByCdateAndUid(@Param("cyear") Integer cyear, @Param("cmonth") Integer cmonth, @Param("cdate") Integer cdate, @Param("uid") Integer uid);//查找某员工某年某月某日的打卡记录
    List<CheckWork> findCheckWorkByCmonthAndUid(@Param("cyear") Integer cyear, @Param("cmonth") Integer cmonth, @Param("uid") Integer uid);//查找某员工某年某月的打卡记录
    void saveCheckBeginTimeByUid(CheckWork checkWork);//上班打卡
    void updateCheckAfterTimeByCid(CheckWork checkWork);//下班打卡
    void savePublishment(Publishment publishment);//生成奖惩记录
    List<Publishment> findPubByUid(Integer id);//查看惩罚记录
    List<Salary> findSalaryByUid(Integer id);//查看员工所有的薪水记录
    Salary findSalaryByUidAndYearAndMonth(@Param("uid") Integer uid, @Param("year") Integer year, @Param("month") Integer month);//查找员工某月的薪水记录
    void updateSalaryBySid(Salary salary);//更新某员工的薪水记录
    void saveSalaryByUidAndYearAndMonth(@Param("uid") Integer uid, @Param("year") Integer year, @Param("month") Integer month);//创建员工某月的薪水记录
    List<TrainTable> findTrainTableByUid(Integer id);//查看本人培训记录
    void updateTrainTableByTidAndUid(TrainTable trainTable);//改变某员工的培训记录状态
    TrainTable findTrainTableByUidAndTid(@Param("uid") Integer uid, @Param("tid") Integer tid);//查看本人某次培训记录
    List<ChangeSalary> findChangeSalaryByUid(Integer id);
}
