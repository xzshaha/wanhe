package com.wanhe.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* 类名称：DateUtil   
* 类描述：时间工具类   
* 创建人：Greg Wang
* 创建时间：2015-7-27 上午9:42:44     
*   
 */
public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 转化为字符串
	 * 
	 * @return
	 */
	public static String fomatDateString(Date date) {
			return sdfDay.format(date);
	}
	
	

	public static String getTime(Date time) {
		return sdfTime.format(time);
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
   

    /**
     * 当前时间加1个月
    * @Title: fomatDateAddMonth 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param checkTime
    * @param @param i
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
	public static String fomatDateAddMonth(String checkTime, int i) {
			Date beforeDate = fomatDate(checkTime);
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(beforeDate);
	        rightNow.add(Calendar.MONTH,i);//日期加i个月
	        Date dt1=rightNow.getTime();
	       return  fomatDateString(dt1);
	}
	
	/**
	 * 
	 * @Title: getDataString
	 * @Description: 获取当前时间的 字符拼接数据
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getDataString() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH) + 1;// 获取月份
		String mString = "";
		if (month < 10) {
			mString = "0" + month;
		} else {
			mString = mString + month;
		}
		int day = cal.get(Calendar.DATE);// 获取日
		String dString = "";
		if (day < 10) {
			dString = "0" + day;
		} else {
			dString = dString + day;
		}
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		String hString = "";
		if (hour < 10) {
			hString = "0" + hour;
		} else {
			hString = hString + hour;
		}
		int minute = cal.get(Calendar.MINUTE);// 分
		String miString = "";
		if (minute < 10) {
			miString = "0" + minute;
		} else {
			miString = miString + minute;
		}
		int second = cal.get(Calendar.SECOND);// 秒
		String sString = "";
		if (second < 10) {
			sString = "0" + second;
		} else {
			sString = sString + second;
		}
		return "" + year + mString + dString + hString + miString + sString;
	}
	
	 public static void main(String[] args) {
	    	System.out.println(getDays());
	    	System.out.println(getAfterDayWeek("3"));
	    }

	public static String getTimeM() {
		return null;
	}
	

	/**
	 * 生成单号
	 * @return
	 */
	public static String generatSerialNumber(){
		Date date = new Date();
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdfTime.format(date) + date.getTime();
	}

}
