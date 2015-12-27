package com.dou361.utils;

import java.io.File;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/27
 * <p/>
 * 描 述：代码量统计
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class CodeStatisticsUtils {

    /** 统计本项目代码 */
    public static CodeStatisticsResult countingOwnCode(String fliter, boolean flag) {
        File file= new File("");
        return countingCode(file,fliter,flag);
    }

    /** 统计项目代码 */
    public static CodeStatisticsResult countingCode(File file, String fliter, boolean flag) {
        CodeStatisticsResult mCodeStatisticsResult = new CodeStatisticsResult();
        return mCodeStatisticsResult;
    }

    /**
     * ========================================
     * <p/>
     * 版 权：dou361.com 版权所有 （C） 2015
     * <p/>
     * 作 者：陈冠明
     * <p/>
     * 个人网站：http://www.dou361.com
     * <p/>
     * 版 本：1.0
     * <p/>
     * 创建日期：2015/12/27
     * <p/>
     * 描 述：代码量统计结果
     * <p/>
     * <p/>
     * 修订历史：
     * <p/>
     * ========================================
     */
    public static class CodeStatisticsResult {

        /**
         * 源码数
         */
        public int getFileCount() {
            return 0;
        }

        /**
         * 总行数
         */
        public int getTotalLines() {
            return 0;
        }

        /**
         * 代码行
         */
        public int getNormalLines() {
            return 0;
        }

        /**
         * 注释行
         */
        public int getCommentLines() {
            return 0;
        }

        /**
         * 导入行
         */
        public int getImportLines() {
            return 0;
        }

        /**
         * 空白行
         */
        public int getWhiteLines() {
            return 0;
        }

    }

}
