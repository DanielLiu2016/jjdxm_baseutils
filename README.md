# [jjdxm_baseutils][project] #

### Copyright notice ###

我在网上写的文章、项目都可以转载，但请注明出处，这是我唯一的要求。当然纯我个人原创的成果被转载了，不注明出处也是没有关系的，但是由我转载或者借鉴了别人的成果的请注明他人的出处，算是对前辈们的一种尊重吧！

虽然我支持写禁止转载的作者，这是他们的成果，他们有这个权利，但我不觉得强行扭转用户习惯会有一个很好的结果。纯属个人的观点，没有特别的意思。可能我是一个版权意识很差的人吧，所以以前用了前辈们的文章、项目有很多都没有注明出处，实在是抱歉！有想起或看到的我都会逐一补回去。

从一开始，就没指望从我写的文章、项目上获得什么回报，一方面是为了自己以后能够快速的回忆起曾经做过的事情，避免重复造轮子做无意义的事，另一方面是为了锻炼下写文档、文字组织的能力和经验。如果在方便自己的同时，对你们也有很大帮助，自然是求之不得的事了。要是有人转载或使用了我的东西觉得有帮助想要打赏给我，多少都行哈，心里却很开心，被人认可总归是件令人愉悦的事情。

站在了前辈们的肩膀上，才能走得更远视野更广。前辈们写的文章、项目给我带来了很多知识和帮助，我没有理由不去努力，没有理由不让自己成长的更好。写出好的东西于人于己都是好的，但是由于本人自身视野和能力水平有限，错误或者不好的望多多指点交流。

项目中如有不同程度的参考借鉴前辈们的文章、项目会在下面注明出处的，纯属为了个人以后开发工作或者文档能力的方便。如有侵犯到您的合法权益，对您造成了困惑，请联系协商解决，望多多谅解哈！若您也有共同的兴趣交流技术上的问题加入交流群QQ： 548545202

## Introduction ##

### 这是一个自定义的常用的工具类库，主要包括了UtilsManager、FileUtils、LogUitls、UIUtils、ListUtils、MD5Utils、RopUtils、RegexUtils、MediaUtils、SPUtils等常用的工具类 ###

## Features ##

### 用于监听当前接收到的短信信息 ###
### 过滤接收到的短信，得到自己想要的内容 ###

## Screenshots ##

## Download ##

[demo apk下载][downapk]

Download or grab via Maven:

	<dependency>
	  <groupId>com.dou361.baseutils</groupId>
	  <artifactId>jjdxm-baseutils</artifactId>
	  <version>x.x.x</version>
	</dependency>

or Gradle:

	compile 'com.dou361.baseutils:jjdxm-baseutils:x.x.x'

历史版本：

    compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.6'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.5'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.4'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.3'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.2'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.1'
	compile 'com.dou361.baseutils:jjdxm-baseutils:1.0.0'

jjdxm-baseutils requires at minimum Java 9 or Android 2.3.


[架包的打包引用以及冲突解决][jaraar]

## Proguard ##

类库中使用consumerProguardFiles属性，它指定了编译时，库自动引入的混淆规则。也就是说应用打包时候会自动的寻找库里的混淆文件，不需要手工配置了。


[AndroidStudio代码混淆注意的问题][minify]

## Get Started ##

###  一、快速集成 ###
1.在系统Application类中的oncreate（）方法中初始化

	/** 初始化sdk */
	UtilsManager.init(this, "");
	/** 设置debug模式，默认为false为正式环境不输出日志 */
    UtilsManager.getInstance().setDebugEnv(true);
	/** 设置日志输出等级 */
    UtilsManager.getInstance().setLogLevel(LogType.LEVEL_ERROR);



### 二、类主要方法说明 ###

### UtilsManager.java 工具类的管理类 ###
UtilsManager(Context, String) 构造函数

getAppContext(): Context 获取应用上下文

getInstance(): UtilsManager 单例模式获取对象

init(Context, String): void 工具使用前的初始化工具

setDebugEnv(boolean): void 设置调试模式或者正式模式,默认是false正式模式

setFristTag(String): void 设置输出日志的第一个tag名称，默认是dou361

setLogLevel(int): void 设置输出日志的等级，默认是debug等级

### SPUtils.java 偏好设置工具类 ###

clearAll(Context): void 清理默认文件所有的偏好参数

clearAll(Context, String): void 清理指定文件所有的偏好参数

getData(Context, String, Object): Object 获取默认文件的指定参数数据

getData(Context, String, String, Object): Object 获取指定文件的指定参数数据

init(Context, String): void 初始化偏好对象

putData(Context, String, Object): void 保存默认文件的指定参数

putData(Context, String, String, Object): void 保存指定文件的指定参数

remove(Context, String): void 移除默认文件的指定参数

remove(Context, String, String): void 移除指定文件的指定参数

spTagName(String): SPTagName 操作指定文件的数据

### SPTagName SPUtils的内部类 ###
clearAll(Context): void 清理指定文件所有的偏好参数

getData(Context, String, Object): Object 获取指定文件的指定参数数据

putData(Context, String, Object): void 保存指定文件的指定参数

remove(Context, String): void 移除指定文件的指定参数

### LogUtils.java 日志输出工具类 ###
log(String): void 输出字符串日志

log(String, Throwable): void 输出字符串和异常日志

log(Throwable): void 输出异常日志

logElapsed(String): void 输出带结束时间的日志

logStart(String): void 输出带开始时间的日志

logTagName(String): LogTagName 设置二级tag名称

logToFile(String, String): void 将日志保存为文件

logToFile(String, String, boolean): void 将日志保存为文件并追加

printArray(T[]): void 打印数组

printList(List<T>): void 打印集合

setLogLevel(int): void 设置输出日志等级，默认为debug模式

setPrintLog(boolean): void 设置是否打印日志，默认不打印
### LogTagName LogUtils的内部类 ###
log(String): void 输出字符串日志

log(String, Throwable): void 输出字符串和异常日志

log(Throwable): void 输出异常日志
### LogType LogUtils的内部类 ###
日志输出等级

LEVEL_DEBUG: int = 2

LEVEL_ERROR: int = 5

LEVEL_INFO: int = 3

LEVEL_NONE: int = 0

LEVEL_VERBOSE: int = 1

LEVEL_WARN: int = 4

### RegexUtils.java 正则工具类 ###
matcherEmail(String): Boolean 匹配邮箱

matcherIllegal(String): boolean 匹配非非法字符

matcherMobileNo(String): Boolean 匹配手机号

matcherNumber(String): Boolean 匹配数字

matcherNumberLetter(String): Boolean 匹配数字和字母

matcherPassword(String): boolean 匹配密码


### DateUtils.java 日期工具类 ###
compareDate(Date, Date): int 比较两个日期大小

getData(String, Date): Object 获取指定日期样式通过date

getData(String, long): Object 获取指定日期样式通过毫秒

getData(String, String, Object): Object 获取指定日期样式通过object

getDateToWeek(Date): String 获取当天是星期几

getDayOfMonth(Date): int 获得指定天在当月的天索引

getMonthLastDay(int, int): int 获得指定年和月的天数

### DateType DateUtils的内部类 ###

DATE: String = "DATE" 输出的类型为日期

DATETIME: String = "DATETIME" 输出的类型为毫秒

sdf_dd: String = "dd"

sdf_HH_mm: String = "HH: mm"

sdf_HH_mm_ss: String = "HH: mm: ss"

sdf_MM: String = "MM"

sdf_yyyy: String = "yyyy"

sdf_yyyy_MM_dd: String = "yyyy-MM-dd"

sdf_yyyy_MM_dd_HH_mm: String = "yyyy-MM-dd HH: mm"

sdf_yyyy_MM_dd_HH_mm_ss: String = "yyyy-MM-dd HH: mm: ss"

### CodeUtils.java 返回码和消息工具类 ###
getInstance(): CodeUtils 获取对象，单例模式

getMessage(int): String 获取消息通过返回码

### RopUtils.java 签名工具类 ###
byte2String(byte[]): String byte转换为字符串

byteTohex(byte[]): String byte转换为十六进制

getMD5Custom(String): String 获取标准MD5加密

getMD5Standard(String): String 获取自定义加密

SHA1Encode(String): String sha1加密

sign(List<String>, String): String 签名参数,默认签名key

sign(Map<String, String>): String 签名map参数,默认签名key

sign(Map<String, String>, List<String>): String 签名map参数除忽略参数

sign(Map<String, String>, List<String>, String): String 签名map参数除忽略参数，指定签名key

sign(Map<String, String>, String): String 签名map参数，指定签名key

### ResourceUtils.java 资源工具类 ###
getResourceArraryIdByName(String, String): int[] 获取资源中array的id数组

getResourceIdByName(String, String): int 获取资源中id

### ToastUtils.java Toast工具类 ###
需要传上下文的显示

showToastLong2Bottom(Context, int): void

showToastLong2Bottom(Context, String): void

showToastLong2Center(Context, int): void

showToastLong2Center(Context, String): void

showToastShort2Bottom(Context, int): void

showToastShort2Bottom(Context, String): void

showToastShort2Center(Context, int): void

showToastShort2Center(Context, String): void

使用系统上下文的显示

showToastLong2Bottom(int): void

showToastLong2Bottom(String): void

showToastLong2Center(int): void

showToastLong2Center(String): void

showToastShort2Bottom(int): void

showToastShort2Bottom(String): void

showToastShort2Center(int): void

showToastShort2Center(String): void

### MediaUtils.java 多媒体工具类 ###
muteAudioFocus(Context, boolean): boolean 开启或者关闭后台多媒体


## More Actions ##

## ChangeLog ##

2016.12.26 1.0.6版本发布，StringUtils中添加了String数据转int/double，UIUtils中添加了双击退出，多击步骤时间和指定时间防止点击过快点击事件

2016.11.16 1.0.5版本发布，StringUtils中添加了double数据的格式化，随机数的获取，DateUtils、LogUtils、SPUtils把内部类移除，使用外部类

## About Author ##

#### 个人网站:[http://www.dou361.com][web] ####
#### GitHub:[jjdxmashl][github] ####
#### QQ:316988670 ####
#### 交流QQ群:548545202 ####


## License ##

    Copyright (C) dou361, The Framework Open Source Project
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
     	http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## (Frequently Asked Questions)FAQ ##
## Bugs Report and Help ##

If you find any bug when using project, please report [here][issues]. Thanks for helping us building a better one.




[web]:http://www.dou361.com
[github]:https://github.com/jjdxmashl/
[project]:https://github.com/jjdxmashl/jjdxm_baseutils/
[issues]:https://github.com/jjdxmashl/jjdxm_baseutils/issues/new
[downapk]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_baseutils/master/apk/app-debug.apk
[lastaar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_baseutils/master/release/jjdxm-baseutils-1.0.0.aar
[lastjar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_baseutils/master/release/jjdxm-baseutils-1.0.0.jar
[icon01]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_baseutils/master/screenshots/icon01.png
[icon02]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_baseutils/master/screenshots/icon02.png
[jaraar]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/架包的打包引用以及冲突解决.md
[minify]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/AndroidStudio代码混淆注意的问题.md