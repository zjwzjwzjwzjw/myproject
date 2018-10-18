package handler;

import com.alibaba.fastjson.JSON;
import entity.*;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import service.serviceImpl.EmploeeServiceImpl;
import service.serviceImpl.ManagerServiceImpl;
import util.MyUtil;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/13.
 */
@RequestMapping("man")
@Controller
public class ManagerHandler {
    @Autowired
    @Qualifier("managerService")
    private ManagerServiceImpl managerService;
    @RequestMapping("showComputerResumes")
    private String showComputerResumes(Model model){
        List<ComputerResumes> list=managerService.findAllComputerResumes();
        List<InterviewTable> list1=managerService.findAllInterview();
        List<InterviewTable> todayInterview=new ArrayList<>();
        if(list1.size()!=0){
            String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            for(int i=0;i<list1.size();i++){
                String interviewTime = new SimpleDateFormat("yyyy-MM-dd").format(list1.get(i).getIinterviewtime());
                if (list1.get(i).getItype() .equals("预约面试") && nowTime.compareTo(interviewTime) == 0) {
                    todayInterview.add(list1.get(i));
                }
            }
        }
        model.addAttribute("todayInterview",todayInterview);
        model.addAttribute("computerResumes",list);
        return "manager/showComputerResumes";
    }
    @RequestMapping("findComputerResumes")
    @ResponseBody
    private String findComputerResumes() throws UnsupportedEncodingException {
        List<ComputerResumes> list=managerService.findAllComputerResumes();
        boolean flag=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCrtype().equals("未查看")){
                flag=true;
            }
        }
        if(flag){
            return "yes";//存在新的简历
        }else{
            return "no";
        }
    }
    @RequestMapping("delComputerResumes")
    @ResponseBody
    private String delComputerResumes(Integer crid){
       managerService.delComputerResumesByCrid(crid);
        return "";
    }
    @RequestMapping("showDetailResumes")
    private String showDetailResumes(Integer crid,Model model) {
        ComputerResumes computerResumes = managerService.findComputerResumesByCrid(crid);
        computerResumes.setCrtype("已查看");
        managerService.updateComputerResumesByCrid(computerResumes);
        model.addAttribute("computerResumes", computerResumes);
        return "manager/showDetailResume";
    }
    @RequestMapping("tellUserInterview")
    @ResponseBody
    private String tellUserInterview(Integer crid,Date date){
        ComputerResumes computerResumes=managerService.findComputerResumesByCrid(crid);
        computerResumes.setTime(date);
        computerResumes.setCstype("通知面试");
        managerService.updateComputerResumesByCrid(computerResumes);
        managerService.saveInterviewTable(computerResumes);
        return "";
    }
    @RequestMapping("checkInterMessage")
    @ResponseBody
    private String checkInterMessage(){
        List<InterviewTable> list=managerService.findAllInterview();
        Boolean flag = false;
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String interviewTime = new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getIinterviewtime());
                if (list.get(i).getItype() .equals("预约面试") && nowTime.compareTo(interviewTime) == 0) {
                    flag = true;
                }
            }
            if(flag){
                return "yes";
            }
        }
        return "";
    }
    @RequestMapping("enterinterview")
    private String enterinterview(Integer iid,Model model) {
       InterviewTable interviewTable=managerService.findInterviewByIid(iid);
        model.addAttribute("interviewTable", interviewTable);
        return "manager/enterInterview";
    }
    @RequestMapping("success")
    @ResponseBody
    private String success(Integer uid,Integer rid,Integer iid,String diff){
        User user=managerService.findUserById(uid);
        ComputerResumes computerResumes=managerService.findComputerResumesByRid(rid);
        InterviewTable interviewTable=managerService.findInterviewByIid(iid);
        if(diff==null) {
            user.setUtype(1);
            computerResumes.setCstype("录取");
            interviewTable.setItype("录取");
            managerService.saveEmploee(interviewTable);
            managerService.updateUser(user);
        }else{
            computerResumes.setCstype("不录取");
            interviewTable.setItype("不录取");
        }
        managerService.updateComputerResumesByCrid(computerResumes);
        managerService.updateInterviewType(interviewTable);
        return "";
    }
    @RequestMapping("showAllDeptAndJob")
    private String showAllDeptAndJob(Model model) {
       List<Dept> depts=managerService.findAllDept();
        List<Job>jobs=managerService.findAllJob();
        model.addAttribute("depts",depts);
        model.addAttribute("jobs",jobs);
        return "manager/showAlldeptManager";
    }
    @RequestMapping("delDept")
    @ResponseBody
    private String delDept(String deptName){
        Dept dept=managerService.findByName(deptName);
        List<Emploee> users=managerService.findEmpByDname(deptName);
        Boolean flag=false;
        if(users.size()!=0){
            for(int i=0;i<users.size();i++){
                if(users.get(i).getEreason()==null){
                    flag=true;//存在在职员工
                }
            }
        }
        if(flag){
            return "no";
        }else {
            managerService.delDeptByDid(dept.getdId());
            return "yes";
        }
    }
    @RequestMapping("addDept")
    @ResponseBody
    private String addDept(String deptName,Dept dept){
        Dept dept1=managerService.findByName(deptName);
       if(dept1!=null){
           return "no";//名字已存在
       }else{
           dept.setdName(deptName);
           dept.setDtime(new Date());
            managerService.saveDept(dept);
           return "yes";
       }
    }
    @RequestMapping("editDept")
    @ResponseBody
    private String editDept(String deptName,String lastName){
        Dept dept=managerService.findByName(deptName);
        Dept dept1=managerService.findByName(lastName);
        if(dept1!=null){
            return "no";//名字已存在
        }else{
            dept.setdName(lastName);
            List<Emploee> emploees=managerService.findEmpByDname(deptName);
            if(emploees.size()!=0){
                for(Emploee e:emploees){
                    e.setEdept(lastName);
                    managerService.updateEmploee(e);
                }
            }
            managerService.updateDept(dept);
            return "yes";
        }
    }
    @RequestMapping("delJob")
    @ResponseBody
    private String delJOb(Integer jId){
        Job job=managerService.findJobByJid(jId);
        Dept dept=managerService.findDeptByDId(job.getdId());
        List<Emploee> users=managerService.findEmpByDnameAndJName(dept.getdName(),job.getjName());
        Boolean flag=false;
        if(users.size()!=0){
            for(int i=0;i<users.size();i++){
                if(users.get(i).getEreason()==null){
                    flag=true;//该部门职位存在在职员工
                }
            }
        }
        if(flag){
            return "no";
        }else {
            managerService.delJobByJid(jId);
            return "yes";
        }
    }
    @RequestMapping("addJob")
    @ResponseBody
    private String addJOb(String jobname,String deptname,Job job){
        Dept dept=managerService.findByName(deptname);
        List<Job> jobs=managerService.findJobByDId(dept.getdId());
        Boolean flag=true;
        if(jobs!=null){
            for(int i=0;i<jobs.size();i++){
                if(jobs.get(i).getjName().equals(jobname)){
                    flag=false;//该部门此职位已存在
                }
            }
        }
        if(flag){
            job.setdId(dept.getdId());
            job.setjName(jobname);
            managerService.saveJob(job);
            return "yes";
        }else{
            return "no";
        }
    }
    @RequestMapping("editJob")
    @ResponseBody
    private String editJob(Integer jId,String newJobName){
        Job job=managerService.findJobByJid(jId);
        Dept dept=managerService.findDeptByDId(job.getdId());
        Job job1=managerService.findJobByJname(newJobName,dept.getdId());
        Boolean flag=true;
        if(job1==null){
            job.setjName(newJobName);
            managerService.updateJob(job);
            List<Emploee> emploees=managerService.findEmpByDnameAndJName(dept.getdName(),job.getjName());
            if(emploees.size()!=0){
                for(Emploee e:emploees){
                    e.setEjob(newJobName);
                    managerService.updateEmploee(e);
                }
            }
            return "yes";
        }else{
            return "no";
        }
    }
    @RequestMapping("showTrainTable")
    private String showTrainTable(Model model,Integer ctid) {
        List<ComputerTrainTable> computerTrainTables=managerService.findAllComputerTrainTable();
        List<Dept> depts=managerService.findAllDept();
        model.addAttribute("computerTrainTables",computerTrainTables);
        model.addAttribute("depts",depts);
        if(ctid!=null){
            ComputerTrainTable computerTrainTable=managerService.findComputerTrainTableByCtid(ctid);
            model.addAttribute("computerTrainTable",computerTrainTable);
        }
        return "manager/showTrainTable";
    }
    @RequestMapping("saveComputerTrainTable")
    private String saveComputerTrainTable(ComputerTrainTable computerTrainTable) {
        String time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time1=new SimpleDateFormat("yyyy-MM-dd").format(computerTrainTable.getTtime());
        if(time.compareTo(time1)==0){
            computerTrainTable.setTbtype("已开始");
        }else{
            computerTrainTable.setTbtype("未开始");
        }
        managerService.saveComputerTrainTable(computerTrainTable);
        return "forward:showTrainTable";
    }
    @RequestMapping("editComputerTrainTable")
    @ResponseBody
    private String editComputerTrainTable(ComputerTrainTable computerTrainTable) {
        System.out.println(computerTrainTable);
        String time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time1=new SimpleDateFormat("yyyy-MM-dd").format(computerTrainTable.getTtime());
        if(time.compareTo(time1)==0){
            computerTrainTable.setTbtype("已开始");
        }else{
            computerTrainTable.setTbtype("未开始");
        }
        managerService.updateComputerTrainTable(computerTrainTable);
        return "";
    }
    @RequestMapping("showEmploee")
    private String showEmploee(Model model) {
       List<Emploee> list=managerService.findAllEmp();
        model.addAttribute("emploees",list);
        return "manager/showEmploee";
    }
    @RequestMapping("showDetailEmploee")
    private String showDetailEmploee(Integer uid,Model model) {
        Emploee emploee=managerService.findEmpByUid(uid);
        model.addAttribute("emploee",emploee);
        return "manager/showDetailEmploee";
    }
    @RequestMapping("savePublishment")
    @ResponseBody
    private String savePublishment(Publishment publishment) {
        publishment.setPtime(new Date());
        managerService.savePublishment(publishment);
        return "";
    }
    @RequestMapping("delEmploee")
    @ResponseBody
    private String delEmploee(Integer uid,String reason){
        Emploee emploee=managerService.findEmpByUid(uid);
        emploee.setEaftertime(new Date());
        emploee.setEreason(reason);
        emploee.setEtype(0);
        managerService.updateEmploee(emploee);
        return "";
    }
    @RequestMapping("adjustEmploee")
    private String adjustEmploee(Integer uid,Model model){
        Emploee emploee=managerService.findEmpByUid(uid);
        List<Dept> depts=managerService.findAllDept();
        List<Job>jobs=managerService.findAllJob();
        model.addAttribute("depts",depts);
        model.addAttribute("jobs",jobs);
        model.addAttribute("emploee",emploee);
        return "/manager/adjustEmploee";
    }
    @RequestMapping("editEmploee")
    @ResponseBody
    private String editEmploee(Integer uid,String dept,String job){
        Emploee emploee=managerService.findEmpByUid(uid);
        emploee.setEjob(job);
        emploee.setEdept(dept);
        managerService.updateEmploee(emploee);
        return "";
    }
    @RequestMapping("showpublishment")
    private String showpublishment(Model model){;
       List<Publishment> publishments=managerService.findAllPublishment();
        model.addAttribute("publishment",publishments);
        return "/manager/showpublishment";
    }
    @RequestMapping("delpublishment")
    @ResponseBody
    private String delpublishment(Integer pid){;
        managerService.delPubByPid(pid);
        return "";
    }
    @RequestMapping("editpublishment")
    @ResponseBody
    private String editpublishment(Integer pid,String pcontext,Double publishmentSalary){;
        Publishment publishment=managerService.findPubByPid(pid);
        publishment.setPcontext(pcontext);
        publishment.setPublishmentSalary(publishmentSalary);
        managerService.editPubByPid(publishment);
        return "";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }
}
