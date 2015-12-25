package com.dou361.utils;

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
            LogUtils.tag(TAG).log("ClassNotFoundException: class=" + className + " fieldname=" + name);
        } catch (IllegalArgumentException var7) {
            LogUtils.tag(TAG).log("IllegalArgumentException: class=" + className + " fieldname=" + name);
        } catch (SecurityException var8) {
            LogUtils.tag(TAG).log("SecurityException: class=" + className + " fieldname=" + name);
        } catch (IllegalAccessException var9) {
            LogUtils.tag(TAG).log("IllegalAccessException: class=" + className + " fieldname=" + name);
        } catch (NoSuchFieldException var10) {
            LogUtils.tag(TAG).log("NoSuchFieldException: class=" + className + " fieldname=" + name);
        }

        return id;
    }

}
