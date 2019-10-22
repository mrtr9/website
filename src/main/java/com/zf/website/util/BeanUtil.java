package com.zf.website.util;

import java.lang.reflect.Field;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/27 16:55
 */
public class BeanUtil {

    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        Class<?> c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        boolean[] flag = new boolean[fields.length];
        int i = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object obj = field.get(object);
                if (obj == null)
                    flag[i] = true;
                else
                    flag[i] = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        int n = 0;
        for (boolean b : flag) {
            if (!b)
                n++;
        }
        return !(n > 0);
    }
}
