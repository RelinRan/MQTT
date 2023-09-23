package androidx.mqtt;

/**
 * 日志打印类<br/>
 * 该类主要适应日志打印不全问题，主要是因为Log最大长度4*1024，考虑到汉字问题<br/>
 * 此处我们采用的是2*1024长度打印，主要是分行打印数据，同时对每行进行[index]标识<br/>
 * 目前支持Log.i() Log.w()  Log.e()  Log.d()<br/>
 * 自定义Log.format,采用格式化打印日志。
 */
public class Log {

    /**
     * 最大一次打印长度
     */
    public final static int MAX_LENGTH = 2048;

    /**
     * 日志类型
     */
    public enum Type {
        V, I, E, D, W
    }

    /**
     * 打印详细日志
     *
     * @param tag 标志
     * @param msg 内容
     */
    public static void v(String tag, String msg) {
        maxPrint(Type.V, tag, msg);
    }

    /**
     * 打印信息日志
     *
     * @param tag 标志
     * @param msg 内容
     */
    public static void i(String tag, String msg) {
        maxPrint(Type.I, tag, msg);
    }

    /**
     * 打印错误日志
     *
     * @param tag 标志
     * @param msg 内容
     */
    public static void e(String tag, String msg) {
        maxPrint(Type.E, tag, msg);
    }

    /**
     * 打印调试日志
     *
     * @param tag 标志
     * @param msg 内容
     */
    public static void d(String tag, String msg) {
        maxPrint(Type.D, tag, msg);
    }

    /**
     * 打印警告日志
     *
     * @param tag 标志
     * @param msg 打印内容
     */
    public static void w(String tag, String msg) {
        maxPrint(Type.W, tag, msg);
    }

    /**
     * 适应最大长度打印
     *
     * @param type 日志类型
     * @param tag  标志
     * @param msg  信息
     */
    private static void maxPrint(Type type, String tag, String msg) {
        if (msg.length() > MAX_LENGTH) {
            int length = MAX_LENGTH + 1;
            String remain = msg;
            int index = 0;
            while (length > MAX_LENGTH) {
                index++;
                typePrint(type, tag + "[" + index + "]", " \n" + remain.substring(0, MAX_LENGTH));
                remain = remain.substring(MAX_LENGTH);
                length = remain.length();
            }
            if (length <= MAX_LENGTH) {
                index++;
                typePrint(type, tag + "[" + index + "]", " \n" + remain);
            }
        } else {
            typePrint(type, tag, msg);
        }
    }

    /**
     * 打印各种类型
     *
     * @param type 日志类型
     * @param tag  标志
     * @param msg  信息
     */
    private static void typePrint(Type type, String tag, String msg) {
        switch (type) {
            case V:
                android.util.Log.v(tag, msg);
                break;
            case I:
                android.util.Log.i(tag, msg);
                break;
            case E:
                android.util.Log.e(tag, msg);
                break;
            case W:
                android.util.Log.w(tag, msg);
                break;
            case D:
                android.util.Log.d(tag, msg);
                break;
        }
    }

}
