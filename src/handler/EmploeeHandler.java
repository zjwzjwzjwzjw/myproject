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
import util.MyUtil;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/10/13.
 */
@RequestMapping("emp")
@Controller
public class EmploeeHandler {
    @Autowired
    @Qualifier("emploeeService")
    private EmploeeServiceImpl emploeeService;
    /**
     *显示个人信息
     **/
    @RequestMapping("showEmp")
    private String showEmp(Integer id,Model model){
        Emploee  emploee=emploeeService.findEmpByUid(id);
        model.addAttribute("emploee",emploee);
        return "/emploee/showEmploee";
    }
    /**
     *保存个人信息
     **/
    @RequestMapping("saveEmploee")
    private String saveEmploee(Emploee emploee){
        emploeeService.updateEmploee(emploee);
        Integer id=emploee.getUid();
        return "forward:showEmp?id="+id;
    }
    /**
     *查看个人惩戒记录
     **/
    @RequestMapping("findPublishment")
    private String findPublishment(Integer id,Model model){
        List<Publishment> list=emploeeService.findPubByUid(id);
        model.addAttribute("publishment",list);
        return "/emploee/showPublishment";
    }
    /**
     *查看个人是否存在惩戒记录
     **/
    @RequestMapping("searchPublishment")
    @ResponseBody
    private String searchPublishment(Integer id){
        List<Publishment> list=emploeeService.findPubByUid(id);
        if(list.size()==0){
            return "";
        }
        return "aaa";
    }
    /**
     *查看个人薪资发放实情
     **/
    @RequestMapping("findSalaryByUid")
    private String findSalaryByUid(Integer id,Model model){
        List<Salary> list=emploeeService.findSalaryByUid(id);
        model.addAttribute("salary",list);
        return "/emploee/showPublishment";
    }
    /**
     *查看所有部门
     **/
    @RequestMapping("showAllDept")
    private String showAllDept(Model model){
        List<Dept> list=emploeeService.findAllDept();
        List<Job> list1=emploeeService.findAllJob();
        model.addAttribute("dept",list);
        model.addAttribute("job",list1);
        return "/emploee/showAlldept";
    }
    @RequestMapping(value="findEmpByDept",produces={"text/html;charset=UTF-8"})
    @ResponseBody
    private String findEmpByDept(String dName){
        List<Emploee> list=emploeeService.findEmpByDname(dName);
        return JSON.toJSONString(list);
    }
    @RequestMapping("toworking")
    @ResponseBody
    private String toworking(Integer uid,CheckWork checkWork,Publishment publishment,String diff){
        Calendar c = Calendar.getInstance();
        int day=c.get(c.DAY_OF_WEEK);
        if(diff!=null) {
            if (day == 1 || day == 7) {
                return "0";//休息日无需打卡
            }
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        CheckWork checkWork1=emploeeService.findCheckWorkByCdateAndUid(year,month,date,uid);
        if (checkWork1 == null) {//今日未打卡
            if(diff!=null) {
                List<CheckWork> list = emploeeService.findCheckWorkByCmonthAndUid(year, month, uid);//获得此员工本月的打卡记录
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                Salary salary=emploeeService.findSalaryByUidAndYearAndMonth(uid,year,month);//查找到此员工本月的薪水记录
                checkWork.setUid(uid);
                checkWork.setCyear(year);
                checkWork.setCmonth(month);
                checkWork.setCdate(date);
                checkWork.setCneedworkday(new MyUtil().getWorkDay());//获取本月的工作天数
                checkWork.setCbegintime(hour + "时" + minute + "分" + second + "秒");
                int count=0;
                if(list.size()==0){
                    checkWork.setCworkday(1);//打卡次数加1
                }else{
                    for(CheckWork cc:list){
                        if(cc.getCbegintime()!=null||cc.getCaftertime()!=null){
                            count++;
                        }
                    }
                    checkWork.setCworkday(count+1);//打卡次数加1
                }
                salary.setBaseSalary(salary.getBaseSalary()+100);//基本工资加100
                emploeeService.updateSalaryBySid(salary);
                if (hour >= 11) {
                    checkWork.setCwtype("旷工");
                    publishment.setUid(uid);
                    publishment.setPcontext("旷工");
                    publishment.setPtime(new Date());
                    publishment.setPublishmentSalary(-50.0);
                    emploeeService.savePublishment(publishment);
                    return "1";
                }else if (hour >= 9) {
                    checkWork.setCwtype("迟到");
                    publishment.setUid(uid);
                    publishment.setPcontext("迟到");
                    publishment.setPtime(new Date());
                    publishment.setPublishmentSalary(-20.0);
                    emploeeService.savePublishment(publishment);
                    return "2";
                }else {
                    checkWork.setCwtype("正常");

                }
                emploeeService.saveCheckBeginTimeByUid(checkWork);
                return "3";
            }else{
                return "";//
            }
        } else {
            return "4";//已经打过卡了,无需再打
        }
    }
    @RequestMapping("endwork")
    @ResponseBody
    private String endwork(Integer uid,CheckWork checkWork,Publishment publishment,String diff) throws UnsupportedEncodingException {
        Calendar c = Calendar.getInstance();
        int day=c.get(c.DAY_OF_WEEK);
        if(diff!=null) {//区别点击事件
            if (day == 1 || day == 7) {
                return "0";//休息日无需打卡
            }
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        CheckWork checkWork1=emploeeService.findCheckWorkByCdateAndUid(year,month,date,uid);
        Salary salary=emploeeService.findSalaryByUidAndYearAndMonth(uid,year,month);//查找是否存在薪水记录
        if(salary==null){//不存在
            emploeeService.saveSalaryByUidAndYearAndMonth(uid,year,month);
        }
        Salary salary1=emploeeService.findSalaryByUidAndYearAndMonth(uid,year,month);//查找到此员工本月的薪水记录
        if(diff!=null) {//区别未点击事件
            publishment.setUid(uid);
            publishment.setPtime(new Date());
            if (hour < 18) {//是否早退
                publishment.setPcontext("早退");
                publishment.setPublishmentSalary(-20.0);
                emploeeService.savePublishment(publishment);
            }
            if(hour>19){
                publishment.setPcontext("加班");
                publishment.setPublishmentSalary((hour-18)*20.0);//加班每小时20元
                emploeeService.savePublishment(publishment);
            }
        }
        if(checkWork1==null){//今日上班未打卡
            if(diff!=null) {//区别未点击事件
                salary1.setBaseSalary(salary1.getBaseSalary()+100);//基本工资加100
                List<CheckWork> list = emploeeService.findCheckWorkByCmonthAndUid(year, month, uid);//获得此员工本月的打卡记录
                checkWork.setUid(uid);
                checkWork.setCyear(year);
                checkWork.setCmonth(month);
                checkWork.setCdate(date);
                checkWork.setCneedworkday(new MyUtil().getWorkDay());//获取本月的工作天数
                checkWork.setCaftertime(hour + "时" + minute + "分" + second + "秒");
                int count=0;
                if(list.size()==0){
                    checkWork.setCworkday(1);//打卡次数加1
                }else{
                    for(CheckWork cc:list){
                        if(cc.getCbegintime()!=null||cc.getCaftertime()!=null){
                            count++;
                        }
                    }
                    checkWork.setCworkday(count+1);//打卡次数加1
                }
                if (hour < 18) {//是否早退
                    checkWork.setCwtype("早退,缺少上班卡");
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "1";//早退 上班未打卡
                } else {
                    checkWork.setCwtype("正常下班,缺少上班卡");
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "2";//正常下班 上班未打卡
                }
            }else{
                return "";
            }
        }else{
            if(diff!=null) {//区别点击事件
                checkWork1.setCaftertime(hour + "时" + minute + "分" + second + "秒");//下班时间
                if (hour <18) {//是否早退
                    if(hour>16){//5-6点下班
                        if(checkWork1.getCwtype().equals("迟到")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+80);
                        }else if(checkWork1.getCwtype().equals("旷工")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+50);
                        }else{
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+100);
                        }
                    }else if(hour>15){//4-5点下班
                        if(checkWork1.getCwtype().equals("迟到")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+80);
                        }else if(checkWork1.getCwtype().equals("旷工")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+40);
                        }else{
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+90);
                        }

                    }else if(hour>14){//3-4点下班
                        if(checkWork1.getCwtype().equals("迟到")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+60);
                        }else if(checkWork1.getCwtype().equals("旷工")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+30);
                        }else{
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+70);
                        }

                    }else if(hour>13){//2-3点下班
                        if(checkWork1.getCwtype().equals("迟到")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+50);
                        }else{
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+60);
                        }

                    }else{//1-2点下班
                        if(checkWork1.getCwtype().equals("迟到")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+40);
                        }else{
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+50);
                        }

                    }
                    checkWork1.setCwtype("早退");
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.updateCheckAfterTimeByCid(checkWork1);
                    return "3";//早退
                } else {
                    checkWork1.setCwtype("正常下班");
                    if(checkWork1.getCwtype().equals("迟到")){
                        salary1.setPerformanceSalary(salary1.getPerformanceSalary()+100);
                    }else if(checkWork1.getCwtype().equals("旷工")){
                        salary1.setPerformanceSalary(salary1.getPerformanceSalary()+60);
                    }else{
                        salary1.setPerformanceSalary(salary1.getPerformanceSalary()+120);
                    }
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.updateCheckAfterTimeByCid(checkWork1);
                    return "4";//正常下班
                }
            }else{
                if(checkWork1.getCaftertime()!=null){
                    return "5";//已打下班卡
                }else{
                    return "";//还未下班打卡
                }
            }
        }

    }
    @RequestMapping("showSalary")
    private String showSalary(Integer  uid,Model model){
        List<Salary> list=emploeeService.findSalaryByUid(uid);
        model.addAttribute("salary",list);
        return "/emploee/showSalary";
    }
    @RequestMapping("findAllSalary")
    @ResponseBody
    private String findAllSalary(Integer  uid){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        boolean flag=false;
        List<Salary> list=emploeeService.findSalaryByUid(uid);
        if(list.size()==0){
            return "";
        }else{
            for(int i=0;i<list.size();i++){
                if(list.get(i).getSyear()<=year&&list.get(i).getSmonth()<month){
                    flag=true;//存在本月份之前的薪水记录
                }
            }
            if(!flag){
                return "";
            }else{
                return "exit";
            }
        }
    }
    /*
    *
    **/
    @RequestMapping("searchcheckwork")
    @ResponseBody
    private String searchcheckwork(Integer uid,CheckWork checkWork){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        List<CheckWork> list=emploeeService.findCheckWorkByCmonthAndUid(year,month,uid);
        if(list.size()!=0){
            int lastCheckWorkDay=list.get(list.size()-1).getCdate();//上一次打卡的日期
            int count=date-lastCheckWorkDay;//若两次打卡时间间距超过一天
            if(count>1){
                int times=0;
                for(int i=lastCheckWorkDay;i<date;i++){
                    c.set(year,month,i);
                    if(c.get(c.DAY_OF_WEEK)!=1||c.get(c.DAY_OF_WEEK)!=7){//不是休息日的生成缺勤记录
                        times++;
                        checkWork.setUid(uid);
                        checkWork.setCyear(year);
                        checkWork.setCmonth(month);
                        checkWork.setCdate(i);
                        checkWork.setCwtype("缺勤");
                        emploeeService.saveCheckBeginTimeByUid(checkWork);
                    }
                }
                return times+"";
            }
        }
        return "";
    }
    @RequestMapping("searchTrainTable")
    @ResponseBody
    private String searchTrainTable(Integer  uid){
        List<TrainTable> list=emploeeService.findTrainTableByUid(uid);
        if(list.size()!=0){
            String nowTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int count=0;
            String ttime="";
            for(int i=0;i<list.size();i++){
                if(list.get(i).getTrype().equals("未查看")) {//判断是否查看过
                    ttime = new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getTtime());
                    if(nowTime.compareTo(ttime)<0){//培训时间未过期
                        count++;//有几个为查看的培训记录
                    }
                }
                Calendar c = Calendar.getInstance();
                int nowyear = c.get(Calendar.YEAR);
                int nowmonth = c.get(Calendar.MONTH)+1;
                int nowdate = c.get(Calendar.DATE);
                String time=new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getTtime());
                String year=time.substring(0,4);
                String month=time.substring(5,7);
                String date=time.substring(8,10);
                int day=list.get(i).getNeedTime();//培训时长
                String time1=nowyear+""+nowmonth+(nowdate-day);
                if(time1.compareTo(time)>0){//培训已结束
                    list.get(i).setTbtype("已结束");
                    emploeeService.updateTrainTableByTidAndUid(list.get(i));
                }
                String time2=nowyear+""+nowmonth+nowdate;
                if(time2.compareTo(time)>0){//培训已开始
                    list.get(i).setTbtype("已开始");
                    emploeeService.updateTrainTableByTidAndUid(list.get(i));//更新培训的状态
                }

            }
            return count+"";
        }
        return "";
    }
    @RequestMapping("showTrainTable")
    private String showTrainTable(Integer  uid,Model model){
        List<TrainTable> list=emploeeService.findTrainTableByUid(uid);
        model.addAttribute("salary",list);
        return "/emploee/showTrainTable";
    }
    @RequestMapping("updateTrainTable")
    private String updateTrainTable(Integer  uid,Integer tid){
        TrainTable trainTable=emploeeService.findTrainTableByUidAndTid(uid,tid);
        trainTable.setTrype("已查看");
        trainTable.setTtype(1);//报名参加
        emploeeService.updateTrainTableByTidAndUid(trainTable);
        return "";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }
}
