# jjdxmbaseutils
自定义的jjdxmbaseutils常用的工具类使用说明

SPUtils

clearAll(Context): void
clearAll(Context, String): void
getData(Context, String, Object): Object
getData(Context, String, String, Object): Object
init(Context, String): void
putData(Context, String, Object): void
putData(Context, String, String, Object): void
remove(Context, String): void
remove(Context, String, String): void
spTagName(String): SPTagName
currentSP: SharedPreferences
mCustomSP: SharedPreferences
mSharedPreferences: SharedPreferences
SP_NAME: String = "config"
SPTagName
clearAll(Context): void
getData(Context, String, Object): Object
putData(Context, String, Object): void
remove(Context, String): void
setSpName(String): void
serialVersionUID: long = -765691622218959750L
spName: String

LogUtils
log(String): void
log(String, Throwable): void
log(Throwable): void
logElapsed(String): void
logStart(String): void
logTagName(String): LogTagName
logToFile(String, String): void
logToFile(String, String, boolean): void
printArray(T[]): void
printList(List<T>): void
setFristTag(String): void
setLogLevel(int): void
setPrintLog(boolean): void
mDebuggable: int = LogType.LEVEL_ERROR
mIsPrintLog: boolean = false
mLogLock: Object = new Object()
mTag: String = "dou361"
mTimestamp: long = 0
LogTagName
log(String): void
log(String, Throwable): void
log(Throwable): void
setSecondTag(String): void
secondTag: String
serialVersionUID: long = -765691622218959750L
LogType
LEVEL_DEBUG: int = 2
LEVEL_ERROR: int = 5
LEVEL_INFO: int = 3
LEVEL_NONE: int = 0
LEVEL_VERBOSE: int = 1
LEVEL_WARN: int = 4
