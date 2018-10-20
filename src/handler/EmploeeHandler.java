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
     *��ʾ������Ϣ
     **/
    @RequestMapping("showEmp")
    private String showEmp(Integer id,Model model){
        Emploee  emploee=emploeeService.findEmpByUid(id);
        model.addAttribute("emploee",emploee);
        return "/emploee/showEmploee";
    }
    /**
     *���������Ϣ
     **/
    @RequestMapping("saveEmploee")
    private String saveEmploee(Emploee emploee){
        emploeeService.updateEmploee(emploee);
        Integer id=emploee.getUid();
        return "forward:showEmp?id="+id;
    }
    /**
     *�鿴���˳ͽ��¼
     **/
    @RequestMapping("findPublishment")
    private String findPublishment(Integer id,Model model){
        List<Publishment> list=emploeeService.findPubByUid(id);
        model.addAttribute("publishment",list);
        return "/emploee/showPublishment";
    }
    /**
     *�鿴�����Ƿ���ڳͽ��¼
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
     *�鿴����н�ʷ���ʵ��
     **/
    @RequestMapping("findSalaryByUid")
    private String findSalaryByUid(Integer id,Model model){
        List<Salary> list=emploeeService.findSalaryByUid(id);
        model.addAttribute("salary",list);
        return "/emploee/showPublishment";
    }
    /**
     *�鿴���в���
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
    @RequestMapping(value="findEmpByDeptAndJob",produces={"text/html;charset=UTF-8"})
    @ResponseBody
    private String findEmpByDeptAndJob(String dept,String job){
        List<Emploee> list=emploeeService.findEmpByDnameAndJName(dept,job);
        return JSON.toJSONString(list);
    }
    @RequestMapping("toworking")
    @ResponseBody
    private String toworking(Integer uid,CheckWork checkWork,Publishment publishment,String diff){
        Calendar c = Calendar.getInstance();
        int day=c.get(c.DAY_OF_WEEK);
        if(diff!=null) {
            if (day == 1 || day == 7) {
                return "0";//��Ϣ�������
            }
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        CheckWork checkWork1=emploeeService.findCheckWorkByCdateAndUid(year,month,date,uid);
        if (checkWork1 == null) {//����δ��
            if(diff!=null) {
                List<CheckWork> list = emploeeService.findCheckWorkByCmonthAndUid(year, month, uid);//��ô�Ա�����µĴ򿨼�¼
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                checkWork.setUid(uid);
                checkWork.setCyear(year);
                checkWork.setCmonth(month);
                checkWork.setCdate(date);
                checkWork.setCneedworkday(new MyUtil().getWorkDay());//��ȡ���µĹ�������
                checkWork.setCbegintime(hour + "ʱ" + minute + "��" + second + "��");
                int count=0;
                if(list.size()==0){
                    checkWork.setCworkday(1);//�򿨴�����1
                }else{
                    for(CheckWork cc:list){
                        if(cc.getCbegintime()!=null||cc.getCaftertime()!=null){
                            count++;
                        }
                    }
                    checkWork.setCworkday(count+1);//�򿨴�����1
                }
                if (hour >= 11) {
                    checkWork.setCwtype("����");
                    publishment.setUid(uid);
                    publishment.setPcontext("����");
                    publishment.setPtime(new Date());
                    publishment.setPublishmentSalary(-50.0);
                    emploeeService.savePublishment(publishment);
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "1";
                }else if (hour >= 9) {
                    checkWork.setCwtype("�ٵ�");
                    publishment.setUid(uid);
                    publishment.setPcontext("�ٵ�");
                    publishment.setPtime(new Date());
                    publishment.setPublishmentSalary(-20.0);
                    emploeeService.savePublishment(publishment);
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "2";
                }else {
                    checkWork.setCwtype("����");
                }
                return "3";
            }else{
                return "";//
            }
        } else {
            return "4";//�Ѿ��������,�����ٴ�
        }
    }
    @RequestMapping("endwork")
    @ResponseBody
    private String endwork(Integer uid,CheckWork checkWork,Publishment publishment,String diff) throws UnsupportedEncodingException {
        Calendar c = Calendar.getInstance();
        int day=c.get(c.DAY_OF_WEEK);
        if(diff!=null) {//�������¼�
            if (day == 1 || day == 7) {
                return "0";//��Ϣ�������
            }
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        CheckWork checkWork1=emploeeService.findCheckWorkByCdateAndUid(year,month,date,uid);
        Salary salary=emploeeService.findSalaryByUidAndYearAndMonth(uid,year,month);//�����Ƿ����нˮ��¼
        if(salary==null){//������
            emploeeService.saveSalaryByUidAndYearAndMonth(uid,year,month);
        }
        Salary salary1=emploeeService.findSalaryByUidAndYearAndMonth(uid,year,month);//���ҵ���Ա�����µ�нˮ��¼
        if(diff!=null) {//����δ����¼�
            publishment.setUid(uid);
            publishment.setPtime(new Date());
            if(hour<15){
                publishment.setPcontext("����");
                publishment.setPublishmentSalary(-50.0);
            }else if (hour < 18) {//�Ƿ�����
                publishment.setPcontext("����");
                publishment.setPublishmentSalary(-20.0);
            }
            if(hour>19){
               salary1.setOverTimeSalary(salary1.getOverTimeSalary()+(hour-18)*20.0);//�Ӱ�ÿСʱ20Ԫ
            }
            emploeeService.savePublishment(publishment);
        }
        if(checkWork1==null){//�����ϰ�δ��
            if(diff!=null) {//����δ����¼�
                List<CheckWork> list = emploeeService.findCheckWorkByCmonthAndUid(year, month, uid);//��ô�Ա�����µĴ򿨼�¼
                checkWork.setUid(uid);
                checkWork.setCyear(year);
                checkWork.setCmonth(month);
                checkWork.setCdate(date);
                checkWork.setCneedworkday(new MyUtil().getWorkDay());//��ȡ���µĹ�������
                checkWork.setCaftertime(hour + "ʱ" + minute + "��" + second + "��");
                int count=0;
                if(list.size()==0){
                    checkWork.setCworkday(1);//�򿨴�����1
                }else{
                    for(CheckWork cc:list){
                        if(cc.getCbegintime()!=null||cc.getCaftertime()!=null){
                            count++;
                        }
                    }
                    checkWork.setCworkday(count+1);//�򿨴�����1
                }
                if(hour<15){
                    checkWork.setCwtype("����,ȱ���ϰ࿨");
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "6";//����
                }else if (hour < 18) {//�Ƿ�����
                    salary1.setBaseSalary(salary1.getBaseSalary()+50);//�������ʼ�50
                    checkWork.setCwtype("����,ȱ���ϰ࿨");
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "1";//���� �ϰ�δ��
                } else {
                    salary1.setBaseSalary(salary1.getBaseSalary()+80);//�������ʼ�80
                    checkWork.setCwtype("�����°�,ȱ���ϰ࿨");
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.saveCheckBeginTimeByUid(checkWork);
                    return "2";//�����°� �ϰ�δ��
                }
            }else{
                return "";
            }
        }else{
            if(diff!=null) {//�������¼�
                checkWork1.setCaftertime(hour + "ʱ" + minute + "��" + second + "��");//�°�ʱ��
                if (hour <18) {//�Ƿ�����
                    if(hour>16){//5-6���°�
                        if(checkWork1.getCwtype().equals("�ٵ�")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+80);
                        }
                        if(checkWork1.getCwtype().equals("�����ϰ�")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+100);
                        }
                    }else if(hour>15){//4-5���°�
                        if(checkWork1.getCwtype().equals("�ٵ�")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+70);
                        }
                        if(checkWork1.getCwtype().equals("�����ϰ�")) {
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary() + 60);
                        }
                    }else if(hour>14){//3-4���°�
                        if(checkWork1.getCwtype().equals("�ٵ�")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+60);
                        }
                        if(checkWork1.getCwtype().equals("�����ϰ�")){
                            salary1.setPerformanceSalary(salary1.getPerformanceSalary()+50);
                        }
                    }else{//3����ǰ�°�
                        checkWork1.setCwtype("����");
                    }
                    if(!(checkWork1.getCwtype().equals("����"))){
                        salary1.setBaseSalary(salary1.getBaseSalary()+100);//��������
                    }
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.updateCheckAfterTimeByCid(checkWork1);
                    return "3";//����
                } else {
                    salary1.setBaseSalary(salary1.getBaseSalary()+100);//��������
                    if(checkWork1.getCwtype().equals("�ٵ�")){
                        salary1.setPerformanceSalary(salary1.getPerformanceSalary()+100);
                    }
                    if(checkWork1.getCwtype().equals("�����°�")){
                        salary1.setPerformanceSalary(salary1.getPerformanceSalary()+120);
                    }
                    emploeeService.updateSalaryBySid(salary1);
                    emploeeService.updateCheckAfterTimeByCid(checkWork1);
                    return "4";//�����°�
                }
            }else{
                if(checkWork1.getCaftertime()!=null){
                    return "5";//�Ѵ��°࿨
                }else{
                    return "";//��δ�°��
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
                    flag=true;//���ڱ��·�֮ǰ��нˮ��¼
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
            int lastCheckWorkDay=list.get(list.size()-1).getCdate();//��һ�δ򿨵�����
            int count=date-lastCheckWorkDay;//�����δ�ʱ���೬��һ��
            if(count>1){
                int times=0;
                for(int i=lastCheckWorkDay;i<date;i++){
                    c.set(year,month,i);
                    if(c.get(c.DAY_OF_WEEK)!=1||c.get(c.DAY_OF_WEEK)!=7){//������Ϣ�յ�����ȱ�ڼ�¼
                        times++;
                        checkWork.setUid(uid);
                        checkWork.setCyear(year);
                        checkWork.setCmonth(month);
                        checkWork.setCdate(i);
                        checkWork.setCwtype("ȱ��");
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
                if(list.get(i).getTrype().equals("δ�鿴")) {//�ж��Ƿ�鿴��
                    ttime = new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getTtime());
                    if(nowTime.compareTo(ttime)<0){//��ѵʱ��δ����
                        count++;//�м���Ϊ�鿴����ѵ��¼
                    }
                }
                Calendar c = Calendar.getInstance();
                int nowyear = c.get(Calendar.YEAR);
                int nowmonth = c.get(Calendar.MONTH)+1;
                int nowdate = c.get(Calendar.DATE);
                String time=new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getTtime());
                int day=list.get(i).getNeedTime();//��ѵʱ��
                String time1=nowyear+""+nowmonth+(nowdate-day);
                if(time1.compareTo(time)>0){//��ѵ�ѽ���
                    list.get(i).setTbtype("�ѽ���");
                    emploeeService.updateTrainTableByTidAndUid(list.get(i));
                }
                String time2=nowyear+""+nowmonth+nowdate;
                if(time2.compareTo(time)>0){//��ѵ�ѿ�ʼ
                    list.get(i).setTbtype("�ѿ�ʼ");
                    emploeeService.updateTrainTableByTidAndUid(list.get(i));//������ѵ��״̬
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
        trainTable.setTrype("�Ѳ鿴");
        trainTable.setTtype(1);//�����μ�
        emploeeService.updateTrainTableByTidAndUid(trainTable);
        return "";
    }
    @RequestMapping("showcheckwork")
    private String showcheckwork(Integer  uid,Model model){
       List<CheckWork> list=emploeeService.findCheckWorkByUid(uid);
        model.addAttribute("checkworks",list);
        return "emploee/showCheckWork";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }
}
