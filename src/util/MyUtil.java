package util;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by user on 2018/10/14.
 */
public class MyUtil {
    public Integer getWorkDay(){//获取本月的工作天数
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int count=0;
        int day=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=1;i<=day;i++){
            c.set(year,month,i);
            if(c.get(c.DAY_OF_WEEK)==1||c.get(c.DAY_OF_WEEK)==7){
                count++;
            }
        }
        return count;
    }
}
