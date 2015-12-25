package com.dou361.utils;
/**
 * ========================================
 * <p/>
 * 版 权：jjdxmashl 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/26 0:15
 * <p/>
 * 描 述：资源文件ID获取工具
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class ResourceUtils {
    private static final String TAG = ResourceUtils.class.getSimpleName();

    public ResourceUtils() {
    }

    /**
     * 获取资源id
     *
     * @param className 资源类名称如（layout,drawable,mipmap,id）
     * @param name      资源名称如（tab_layout.xml 名称为tab_layout ）
     * @return 资源id
     */
    public static int getResourceIdByName(String className, String name) {
        String packageName = UtilsManager.getInstance().getAppContext().getPackageName();
        int id = 0;
        try {
            String e = packageName + ".R$" + className;
            Class desireClass = Class.forName(e);
            if (desireClass != null) {
                id = desireClass.getField(name).getInt(desireClass);
            }
        } catch (ClassNotFoundException var6) {
            LogUtils.logTagName(TAG).log("ClassNotFoundException: class=" + className + " fieldname=" + name);
        } catch (IllegalArgumentException var7) {
            LogUtils.logTagName(TAG).log("IllegalArgumentException: class=" + className + " fieldname=" + name);
        } catch (SecurityException var8) {
            LogUtils.logTagName(TAG).log("SecurityException: class=" + className + " fieldname=" + name);
        } catch (IllegalAccessException var9) {
            LogUtils.logTagName(TAG).log("IllegalAccessException: class=" + className + " fieldname=" + name);
        } catch (NoSuchFieldException var10) {
            LogUtils.logTagName(TAG).log("NoSuchFieldException: class=" + className + " fieldname=" + name);
        }

        return id;
    }

}
