package com.wanhe.util;

import java.util.Random;

/**
 * 验证码
 * @author 肖兆升
 */
public class CodeUtil {
	
	/**
	 * 获取验证码
	 */
	public static String code() throws Exception {
		char[] code = new char[] {'0', '1', '2', '3', '4', '5', '6','7', '8', '9'};
		Random random = new Random();
		
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(code[random.nextInt(code.length)]);
			sRand += rand;
		}
		return sRand;
	}
	public static String code(int len) {
		char[] code = new char[] {'0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		Random random = new Random();

		String sRand = "";
		for (int i = 0; i < len; i++) {
			String rand = String.valueOf(code[random.nextInt(code.length)]);
			sRand += rand;
		}
		return sRand;
	}


	public static void main(String[] args) {
//		System.out.println(DigestUtils.md5Hex("111111"));
		
//		try {
//			System.out.println(DateTimeHelper.getDaysOfTwoDate(DateTimeHelper.parseToDate("2016-1-5 12:00:00", "yyyy-MM-dd"), DateTimeHelper.parseToDate("2016-1-1 05:00:00", "yyyy-MM-dd")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		String str = convertUnicode("\u5357\u65b9\u533a\u57df\u516c\u53f8");
		System.out.println(str);
		System.out.println(chinaToUnicode(str));
	}
	
	/** 
     * 把中文转成Unicode码 
     * @param str 
     * @return 
     */  
    public static String chinaToUnicode(String str){  
        String result="";  
        for (int i = 0; i < str.length(); i++){  
            int chr1 = (char) str.charAt(i);  
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
                result+="\\u" + Integer.toHexString(chr1);  
            }else{  
                result+=str.charAt(i);  
            }  
        }  
        return result;  
    }  
	
	/**
	 * 把Unicode转换成中文
	 * @param ori
	 * @return
	 */
	public static String convertUnicode(String ori){
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
 
        }
        return outBuffer.toString();
	}
}
