package com.zf.website.util;

import java.lang.reflect.Field;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/27 16:55
 */
public class BeanUtil {

    public static boolean isNull(Object object){
        boolean flag = false;
        Class<?> c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                Object obj = field.get(object);
                if(obj == null)
                    flag = true;
                else
                    flag = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
