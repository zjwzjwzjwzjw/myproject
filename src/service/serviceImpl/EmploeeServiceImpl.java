package service.serviceImpl;

import dao.EmploeeDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EmploeeService;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/13.
 */
@Service("emploeeService")
public class EmploeeServiceImpl extends UserServiceImpl implements EmploeeService{
    @Autowired
    private EmploeeDao emploeeDao;
    @Override
    public void saveEmploee(InterviewTable interviewTable) {
        emploeeDao.saveEmploee(interviewTable);
    }

    @Override
    public void updateEmploee(Emploee emploee) {
        emploeeDao.updateEmploee(emploee);
    }

    @Override
    public Emploee findEmpByUid(Integer id) {
        return emploeeDao.findEmpByUid(id);
    }

    @Override
    public List<Emploee> findEmpByDname(String dname) {
        return emploeeDao.findEmpByDname(dname);
    }

    @Override
    public List<Emploee> findEmpByDnameAndJName(String dname, String jname) {
        return emploeeDao.findEmpByDnameAndJName(dname,jname);
    }

    @Override
    public Dept findDeptByDId(Integer dId) {
        return emploeeDao.findDeptByDId(dId);
    }

    @Override
    public List<Emploee> findAllEmp() {
        return emploeeDao.findAllEmp();
    }

    @Override
    public Job findJobByJid(Integer jId) {
        return emploeeDao.findJobByJid(jId);
    }

    @Override
    public List<CheckWork> findCheckWorkByUid(Integer id) {
        return emploeeDao.findCheckWorkByUid(id);
    }

    @Override
    public void saveCheckBeginTimeByUid(CheckWork checkWork) {
        emploeeDao.saveCheckBeginTimeByUid(checkWork);
    }

    @Override
    public void updateCheckAfterTimeByCid(CheckWork checkWork) {
        emploeeDao.updateCheckAfterTimeByCid(checkWork);
    }

    @Override
    public void savePublishment(Publishment publishment) {
        emploeeDao.savePublishment(publishment);
    }

    @Override
    public List<Publishment> findPubByUid(Integer id) {
        return emploeeDao.findPubByUid(id);
    }

    @Override
    public List<Salary> findSalaryByUid(Integer id) {
        return emploeeDao.findSalaryByUid(id);
    }

    @Override
    public Salary findSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month) {
        return emploeeDao.findSalaryByUidAndYearAndMonth(uid,year,month);
    }

    @Override
    public void updateSalaryBySid(Salary salary) {
        emploeeDao.updateSalaryBySid(salary);
    }

    @Override
    public void saveSalaryByUidAndYearAndMonth(Integer uid, Integer year, Integer month) {
        emploeeDao.saveSalaryByUidAndYearAndMonth(uid,year,month);
    }

    @Override
    public List<TrainTable> findTrainTableByUid(Integer id) {
        return emploeeDao.findTrainTableByUid(id);
    }

    @Override
    public void updateTrainTableByTidAndUid(TrainTable trainTable) {
        emploeeDao.updateTrainTableByTidAndUid(trainTable);
    }

    @Override
    public TrainTable findTrainTableByUidAndTid(Integer uid, Integer tid) {
        return emploeeDao.findTrainTableByUidAndTid(uid,tid);
    }

    @Override
    public List<ChangeSalary> findChangeSalaryByUid(Integer id) {
        return emploeeDao.findChangeSalaryByUid(id);
    }

    @Override
    public CheckWork findCheckWorkByCdateAndUid(Integer cyear, Integer cmonth, Integer cdate, Integer uid) {
        return emploeeDao.findCheckWorkByCdateAndUid(cyear,cmonth,cdate,uid);
    }

    @Override
    public List<CheckWork> findCheckWorkByCmonthAndUid(Integer cyear, Integer cmonth, Integer uid) {
        return emploeeDao.findCheckWorkByCmonthAndUid(cyear,cmonth,uid);
    }



}
