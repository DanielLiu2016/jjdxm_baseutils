package com.dou361.utils;

import com.jingwang.eluxue_online.bean.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网电子科技有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015-9-29 下午8:30:16
 * <p/>
 * 描 述：相同对象间的赋值，将一个对象的所有成员属性赋值给另一个对象，只赋值不改变对象的引用地址
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class ObjectCopyUtils {

    /**
     * 赋值一个新的对象，地址不一样
     */
    public static Object copyObject(Object object) {
        try {
            Class<?> classType = object.getClass();
            Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

            //获得对象的所有成员变量
            Field[] fields = classType.getDeclaredFields();
            for (Field field : fields) {
                //获取成员变量的名字
                String name = field.getName();    //获取成员变量的名字，此处为id，name,age
                if ("serialVersionUID".equals(name)) {
                    continue;
                }
                //获取get和set方法的名字
                String firstLetter = name.substring(0, 1).toUpperCase();    //将属性的首字母转换为大写
                String getMethodName = "get" + firstLetter + name.substring(1);
                String setMethodName = "set" + firstLetter + name.substring(1);


                //获取方法对象
                Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型

                //调用get方法获取旧的对象的值
                Object value = getMethod.invoke(object, new Object[]{});
                //调用set方法将这个值复制到新的对象中去
                setMethod.invoke(objectCopy, new Object[]{value});

            }
            return objectCopy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 赋值给另一个对象
     */
    public static void copyObject(Object src, Object des) {
        if (src == null) {
            return;
        }
        if (des == null) {
            return;
        }
        Class<?> classType = des.getClass();
        //获得对象的所有成员变量
        Field[] fields = classType.getDeclaredFields();
        try {
            for (Field field : fields) {
                //获取成员变量的名字
                String name = field.getName();
                if ("serialVersionUID".equals(name)) {
                    continue;
                }
                //获取get和set方法的名字
                String firstLetter = name.substring(0, 1).toUpperCase();    //将属性的首字母转换为大写
                String getMethodName = "get" + firstLetter + name.substring(1);
                String setMethodName = "set" + firstLetter + name.substring(1);
                //获取方法对象
                Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型

                //调用get方法获取旧的对象的值
                Object value = getMethod.invoke(des, new Object[]{});
                //调用set方法将这个值复制到新的对象中去
                setMethod.invoke(src, new Object[]{value});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
