package com.h.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * Created by riq on 2017/5/19.
 */

public class MyUtils {
    /*
     * TO 字符串工具
     * 1.匹配手机号码
     * 2.把字符串转换为md5加密形式
     * 3.去掉text中的所有空格、换行等／替换text中的所有空格、换行等为newStr
     * 4.判断文本是否为自然数
     * 5.判断文本是否为字母
     * 6.判断文本是否为汉字(只能判断一个字)     *
     */
    public static class StringUtils {
        /**
         * TO 匹配手机号码
         * 移动号段：139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188 新增:198
         * 联通号段：130 131 132 155 156 185 186 145 175 176 新增:166
         * 电信号段：133 153 177 173 180 181 189 新增:199
         * 虚拟运营商号段：170 171
         * 合计：
         * 130 131 132 133 134 135 136 137 138 139 145 147 150 151 152 153 155 156 157 158
         * 159 166 170 171 173 175 176 177 178 180 181 182 183 184 185 186 187 188 189 198 199
         *
         * @param phone 手机号
         */
        public static boolean isPhoneNumber(String phone) {
            return Pattern.compile("^1((3\\d)|(4[57])|(5[^4,\\D])|(6[6])|(7[0135678])|(9[89])|(8\\d))\\d{8}$").matcher(phone).matches();
        }


        /**
         * 判断座机电话号码
         *
         * @param telephone 0开头，02712345678/071312345678/075512345678/027-12345678/0713-1234567/0755-12345678
         */
        public static boolean isTelephoneNumber(String telephone) {
            return telephone.startsWith("0") && Pattern.compile("\\d{3}\\d{8}|\\d{4}\\d{8}|\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{8}").matcher(telephone).matches();
        }

        /**
         * 是否是手机号或座机
         *
         * @param phone
         * @return
         */
        public static boolean isRelativePhoneNumber(String phone) {
            if (phone.startsWith("0")) {
                return Pattern.compile("\\d{3}\\d{8}|\\d{4}\\d{8}|\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{8}").matcher(phone).matches();
            } else
                return phone.startsWith("1") && Pattern.compile("^1((3\\d)|(4[57])|(5[^4,\\D])|(6[6])|(7[013678])|(9[89])|(8\\d))\\d{8}$").matcher(phone).matches();
        }


        /**
         * TO 把字符串转换为md5加密形式
         *
         * @param content 需要加密的内容
         * @return 已加密的内容文本
         */
        public static String toMD5(String content) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(content.getBytes());
                StringBuilder builder = new StringBuilder();
                for (byte b : digest.digest()) {
                    builder.append(Integer.toHexString((b >> 4) & 0xf));
                    builder.append(Integer.toHexString(b & 0xf));
                }
                return builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * TO 去掉text中的所有空格、换行等
         * 包括空格、制表符、换页符等（每一个空白字符均会被替换）
         *
         * @param text 需要被替换的字符串
         * @return 如, replaceAllChar("a ","w")    ----> 返回"aw"
         */
        public static String replaceAllChar(String text) {
            return text.replaceAll("[\\s+]", "");
        }

        /**
         * TO 替换text中的所有空格、换行等为newStr
         * 包括空格、制表符、换页符等（每一个空白字符均会被替换）
         *
         * @param text   需要被替换的字符串
         * @param newStr 需要将空白字符替换为的字符
         * @return 如, replaceAllChar("a ","w")    ----> 返回"aw"
         */
        public static String replaceAllChar(String text, String newStr) {
            return text.replaceAll("[\\s+]", newStr);
        }

        /**
         * TO 判断文本是否为自然数
         *
         * @param text 文本
         * @return
         */
        public static boolean isNaturalNumber(String text) {
            if (text.equals("")) {
                return false;
            } else {
                return Pattern.compile("[0-9]*").matcher(text).matches();
            }
        }

        /**
         * TO 判断文本是否为字母
         *
         * @param text 文本
         * @return
         */
        public static boolean isLetter(String text) {
            return Pattern.compile("^[A-Za-z]+$").matcher(text).matches();
        }

        /**
         * TO 判断文本是否为汉字(只能判断一个字)
         *
         * @param text 文本
         * @return
         */
        public static boolean isHanzi(String text) {
            return Pattern.compile("^[\u4e00-\u9fa5]{0,}$").matcher(text).matches();
        }


        /**
         * 判断是否是身份证号码
         *
         * @param idCardNumber
         * @return
         */
        public static boolean isIdCard(String idCardNumber) {
            if (idCardNumber.length() != 18) {
                return false;
            }
            char[] numbers = idCardNumber.toCharArray();
            int l_jyw = 0;
            int[] wi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
            char[] checkCodes = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
            for (int i = 0; i < 17; i++) {
                if (numbers[i] < '0' || numbers[i] > '9') {
                    return false;
                }
                l_jyw += (numbers[i] - '0') * wi[i];
            }
            l_jyw = l_jyw % 11;
            return checkCodes[l_jyw] == numbers[17];
        }

        /**
         * 四舍五入取小数格式
         *
         * @param number
         * @param pattern "#.##"
         */
        public static String formatNumber(double number, String pattern) {
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(number);
        }

        public static String formatNumber(Object number, String pattern) {
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(number);
        }

        public static String formatNumber(long number, String pattern) {
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(number);
        }

        /**
         * 转换kb到对应单位
         *
         * @param kbSize
         * @return
         */
        public static String formatFileSizeKb(long kbSize) {    //将kb字节转为对应单位
            DecimalFormat df = new DecimalFormat("#.00");
            String sizeStr;
            if (kbSize < 1024) {
                sizeStr = kbSize + "B";
            } else if (kbSize < 1048576) {
                sizeStr = df.format(kbSize / (double) 1024) + "KB";
            } else if (kbSize < 1073741824) {
                sizeStr = df.format(kbSize / (double) 1048576) + "MB";
            } else {
                sizeStr = df.format(kbSize / (double) 1073741824) + "GB";
            }
            return sizeStr;
        }

    }

    /*
     * 时间工具
     * 1.时间戳或时间文本转日期字符串／时间戳转指定格式日期字符串
     * 2.比较指定pattern日期大小／比较时间戳 或 yyyy-MM-dd hh:mm:ss／
     *
     */
    public static class DateUtils {
        /**
         * TO 时间戳或时间文本转日期字符串
         *
         * @param timeOrTimestamps 时间戳
         * @return 1970-01-01 08:00:00
         * 区分是时间戳格式还是时间格式1970-01-01 08:00:00.0
         */
        public static String formatTimestamp(String timeOrTimestamps) {
            if (StringUtils.isNaturalNumber(timeOrTimestamps)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.format(new Date(Long.parseLong(timeOrTimestamps)));
            } else if (timeOrTimestamps.contains(".")) {
                return timeOrTimestamps.substring(0, timeOrTimestamps.lastIndexOf("."));
            }
            return null;
        }

        public static String formatTimestamp(long timeOrTimestamps) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date(timeOrTimestamps));
        }

        /**
         * TO 时间戳转指定格式日期字符串
         *
         * @param timestamp 时间戳（毫秒）
         * @param pattern   格式：yyyy-MM-dd/yyyymmdd/yyyy-MM-dd hh:mm:ss...
         * @return 时间字符串 格式： yyyy-MM-dd/yyyyMMdd...
         */
        public static String formatTimestamp(String timestamp, String pattern) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(new Date(Long.parseLong(timestamp)));
        }


        /**
         * TO 比较指定pattern日期大小
         * date1 - date2
         *
         * @param date1   日期1
         * @param date2   日期2
         * @param pattern 格式，格式yyyy-MM-dd hh:mm:ss ...
         * @return 1，大于；    0，等于；   -1，小于；  -2,比较失败.
         */
        public static int compareDate(String date1, String date2, String pattern) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                Date dt1 = df.parse(date1);
                Date dt2 = df.parse(date2);
                if (dt1.getTime() > dt2.getTime()) {
                    return 1;   //大于
                } else if (dt1.getTime() == dt2.getTime()) {
                    return 0;   //等于
                } else {
                    return -1;  //小于
                }
            } catch (Exception e) {
                e.printStackTrace();
                return -99999;  //例如日期为空，或者日期格式不对
            }
        }

        /**
         * TO 比较时间戳 或 yyyy-MM-dd hh:mm:ss
         *
         * @param date1 时间戳1
         * @param date2 时间戳2
         * @return 时间戳1 - 时间戳2
         */
        public static int compareDate(String date1, String date2) {
            //比较时间戳
            if (StringUtils.isNaturalNumber(date1) && StringUtils.isNaturalNumber(date2)) {
                long difference = (Long.parseLong(date1) - Long.parseLong(date2));
                if (difference > 0) {
                    return 1;   //大于
                } else if (difference == 0) {
                    return 0;   //等于
                } else {
                    return -1;  //小于
                }
            }
            //比较日期 2017-05-19 10:24:55
            if (date1.contains(":") && date2.contains(":")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date dt1 = df.parse(date1);
                    Date dt2 = df.parse(date2);
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;   //大于
                    } else if (dt1.getTime() == dt2.getTime()) {
                        return 0;   //等于
                    } else {
                        return -1;  //小于
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return -99999;
                }
            } else {
                return -99999;
            }
        }

        /**
         * 时间转为时间戳
         *
         * @param time
         * @return
         */
        public static long dateToTimestamp(String time) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format.parse(time);
                return date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * 集合工具
     */
    public static class ListUtils {
        /**
         * TO ArrayList去除重复
         *
         * @param list
         * @return
         */
        public static ArrayList removeSame(ArrayList list) {
            if (null == list) {
                return null;
            }
            HashSet h = new HashSet(list);
            list.clear();
            list.addAll(h);
            return list;
        }

        /**
         * 集合转为数组
         */
        public static String[] listToArray(List<String> list) {
            int size = list.size();
            String[] array = new String[size];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }
    }


    public static class DeviceUtils {
        /**
         * GPS是否打开
         */
        public static boolean isGPSAvailable(Context context) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        /**
         * AGPS(网络定位)是否打开
         */
        public static boolean isAGPSAvailable(Context context) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        /**
         * 自动打开GPS
         * 失败
         */
//        public static boolean openGPS(Context context) {
//            Intent GPSIntent = new Intent();
//            GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
//            GPSIntent.setData(Uri.parse("custom:3"));
//            try {
//                PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
//                return true;
//            } catch (PendingIntent.CanceledException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }

        /**
         * 设置导航栏是否显示
         *
         * @param activity
         * @param visible
         */
        public static void setNavigationBar(Activity activity, int visible) {
            View decorView = activity.getWindow().getDecorView();
            //显示NavigationBar
            if (View.GONE == visible) {
                decorView.setSystemUiVisibility(SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }

        /**
         * 导航栏，状态栏隐藏
         * (不完美，只是隐藏，还会有对应的位置空白条)
         *
         * @param activity
         */
        public static boolean hideNavigationBarAndStatusBar(Activity activity) {
            if (Build.VERSION.SDK_INT >= 19) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                return true;
            } else {
                return false;
            }
        }

    }

    /*
     * 文件工具
     * 读取Storage和网络图片用Glide
     * 1.保存图片到storage
     * 2.从storage读取图片
     * 3.保存文本到file中
     * 4.从file中读取文本
     * 5.保存图片到file中
     * 6.读取file中的图片
     */
    public static class FileUtils {
        /**
         * TO：保存图片到Storage
         *
         * @param path     保存在手机的路径 /storage/emulated/0/
         * @param filename 文件名（需要扩展名,png,jpg...）
         * @param bmp      bitmap
         */
        public static void saveImageToStorage(String path, String filename, Bitmap bmp) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(path, filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * TO：从手机读取图片
         * 使用Glide
         *
         * @param path     文件路径
         * @param filename 文件名，需要扩展名
         * @return 图片
         */
        public static Bitmap getBitmapFromStorage(String path, String filename) {
            File file = new File(path);
            if (file.exists()) {
                return BitmapFactory.decodeFile(path + filename);
            } else {
                return null;
            }
        }

        /**
         * TO：保存文本到data/data/包/file中
         *
         * @param context  this
         * @param filename 文件名
         * @param text     保存到txt中的文本
         */
        public static void saveTextToFile(Context context, String filename, String text) {
            FileOutputStream out;
            BufferedWriter writer = null;
            OutputStreamWriter osw;
            try {
                out = context.openFileOutput(filename, MODE_PRIVATE);
                osw = new OutputStreamWriter(out);
                writer = new BufferedWriter(osw);
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * TO：读取data/data/包/file中的文本
         *
         * @param context  this
         * @param filename 文件名，需要扩展名
         * @return 读取的文本
         */
        public static String getTextFromFile(Context context, String filename) {
            FileInputStream in = null;//只需传文件名
            try {
                in = context.openFileInput(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();//输出到内存
            int len = 0;
            byte[] buf = new byte[1024];
            try {
                assert in != null;
                while ((len = in.read(buf)) != -1) {
                    outStream.write(buf, 0, len);//
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content_byte = outStream.toByteArray();
            return new String(content_byte);
        }

        /**
         * TO：保存图片到data/data/包/file中
         *
         * @param context  this
         * @param filename 文件名，需要扩展名
         * @param bitmap   图片bitmap
         */
        public static void saveImageToFile(Context context, String filename, Bitmap bitmap) {
            try {
                FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);
                //bitmap转文件对象
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 读取data/data/包/file中的图片
         *
         * @param context  this
         * @param filename 图片文件名 需要扩展名
         * @return bitmap
         */
        public static Bitmap getImageFromFile(Context context, String filename) {
            Bitmap bitmap = null;
            try {
                FileInputStream fis = context.openFileInput(filename);
                bitmap = BitmapFactory.decodeStream(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        /**
         * 删除文件
         *
         * @param filePath 文件路径
         * @return {@code true}: 删除成功<br>{@code false}: 删除失败
         */
        public static boolean deleteFile(String filePath) {
            File file = new File(filePath);
            return !file.exists() || file.isFile() && file.delete();
        }

        /**
         * 判断文件是否存在
         *
         * @param filePath 文件
         * @return {@code true}: 存在<br>{@code false}: 不存在
         */
        public static boolean isFileExists(String filePath) {
            File file = new File(filePath);
            return file.exists();
        }
        //----------------------------

        /**
         * 读取指定路径的所有文件（包含文件夹）
         */
        public static List<String> getFilenames(String filePath) {
            List<String> filenames = new ArrayList<>();
            File mFile = new File(filePath);
            // 取得文件名
            String filename = mFile.getName();
            return filenames;
        }

        public static long getFileSize(File file) throws Exception {
            if (file == null) {
                return 0;
            }
            long size = 0;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            }
            return size;
        }


    }

    /*
       * TO 检查是否有网络
       */
    public static class NetUtils {
        /**
         * 判断网络状态
         * wifi mobile none
         */
        public static String getNetworkType(Context context) {
            // 得到连接管理器对象
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                    return "wifi";
                } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                    return "cellular";
                }
            } else {
                return "none";
            }
            return "none";
        }

        public static boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            if (anInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                                return true;
                            } else if (anInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public static boolean isWifiAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            if (anInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public static boolean isCellularAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            if (anInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        /**
         * 获取wifi名称
         *
         * @param context
         * @return
         */
        public static String getWifiSsid(Context context) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            return ssid.substring(1, ssid.lastIndexOf("\""));
        }
    }

    /*
     * TO 随机数工具类
     */
    public static class RandomUtils {

        private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String NUMBERS = "0123456789";
        private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

        private RandomUtils() {
            throw new AssertionError();
        }

        /**
         * TO: 随机获取length长度个字符
         * 数字,大写字母,小写字母
         *
         * @param length length
         * @return RandomUtils
         */
        public static String getRandomNumbersAndLetters(int length) {
            return getRandom(NUMBERS_AND_LETTERS, length);
        }

        /**
         * TO: 随机获取length长度个 数字
         *
         * @param length 字符长度
         * @return RandomUtils
         */
        public static String getRandomNumbers(int length) {
            return getRandom(NUMBERS, length);
        }

        /**
         * TO: 随机获取length长度个 字母（无论大小写）
         *
         * @param length length
         * @return RandomUtils
         */
        public static String getRandomLetters(int length) {
            return getRandom(LETTERS, length);
        }

        /**
         * TO: 随机获取length长度个 大写字母
         *
         * @param length length
         * @return ADSFY
         */
        public static String getRandomUpperCaseLetters(int length) {
            return getRandom(UPPER_CASE_LETTERS, length);
        }

        /**
         * TO: 随机获取length长度个 小写字母
         *
         * @param length length
         * @return fdsfs
         */
        public static String getRandomLowerCaseLetters(int length) {
            return getRandom(LOWER_CASE_LETTERS, length);
        }

        /**
         * TO: 获取随机自然数
         *
         * @param max 接收的数值
         * @return 返回一个随机的数字[0, max)
         */
        public static int getRandom(int max) {
            return getRandom(0, max);
        }

        /**
         * TO: 在[min, max)范围内获取随机整数
         *
         * @param min 最小
         * @param max 最大
         * @return 返回一个范围的数值[min, max)
         */
        public static int getRandom(int min, int max) {
            if (min > max) {
                return 0;
            }
            if (min == max) {
                return min;
            }
            return min + new Random().nextInt(max - min);
        }

        /**
         * get a fixed-length random string, its a mixture of chars in source
         *
         * @param source source
         * @param length length
         * @return get a fixed-length random string, its a mixture of chars in source
         */
        public static String getRandom(String source, int length) {
            return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
        }

        /**
         * sourceChar个字符,随机排列为一个长度为length的字符串
         *
         * @param sourceChar new char[]{'3','f','d'}
         * @param length     4
         * @return f3d3
         */
        public static String getRandom(char[] sourceChar, int length) {
            if (sourceChar == null || sourceChar.length == 0 || length < 0) {
                return null;
            }
            StringBuilder str = new StringBuilder(length);
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                str.append(sourceChar[random.nextInt(sourceChar.length)]);
            }
            return str.toString();
        }


        /**
         * Shuffling algorithm, Randomly permutes the specified array using a default source of randomness
         *
         * @param objArray 数组
         * @return 从新的数组
         */
        public static boolean shuffle(Object[] objArray) {
            if (objArray == null) {
                return false;
            }
            return shuffle(objArray, getRandom(objArray.length));
        }

        /**
         * Shuffling algorithm, Randomly permutes the specified array
         *
         * @param objArray     数组
         * @param shuffleCount 洗的个数
         * @return 是否成功
         */
        public static boolean shuffle(Object[] objArray, int shuffleCount) {
            int length;
            if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
                return false;
            }

            for (int i = 1; i <= shuffleCount; i++) {
                int random = getRandom(length - i);
                Object temp = objArray[length - i];
                objArray[length - i] = objArray[random];
                objArray[random] = temp;
            }
            return true;
        }

        /**
         * 将数组元素随机排列,个数为[o,intArray.length)
         *
         * @param intArray 数组
         * @return 洗牌之后
         */
        public static int[] shuffle(int[] intArray, boolean includeChildArray) {
            if (intArray == null) {
                return null;
            }
            if (includeChildArray) {
                return shuffle(intArray, getRandom(intArray.length));
            } else {
                return shuffle(intArray, intArray.length);
            }
        }


        /**
         * Shuffling algorithm, Randomly permutes the specified int array
         *
         * @param intArray     数组
         * @param shuffleCount 范围
         * @return 新的数组
         */
        public static int[] shuffle(int[] intArray, int shuffleCount) {
            int length;
            if (intArray == null || shuffleCount < 0) {
                return new int[]{};
            }
            if ((length = intArray.length) < shuffleCount) {
                shuffleCount = length;
            }
            int[] out = new int[shuffleCount];

            for (int i = 1; i <= shuffleCount; i++) {
                int random = getRandom(length - i);
                out[i - 1] = intArray[random];
                int temp = intArray[length - i];
                intArray[length - i] = intArray[random];
                intArray[random] = temp;
            }
            return out;
        }
    }

    /*
     * TO APP相关
     * 1.获取程序版本名
     * 2.获取程序版本号
     * 3.获取屏幕宽度
     * 4.获取屏幕高度
     */
    public static class AppUtils {
        /**
         * TO 获取程序版本名
         */
        public static String getVersionName(Context context) {
            String versionName = "null";
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                versionName = info.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return versionName;
        }

        /**
         * TO 获取程序版本号
         */
        public static int getVersionCode(Context context) {
            int versionCode = -1;
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                versionCode = info.versionCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return versionCode;
        }

        /**
         * TO 获取屏幕宽度
         * 竖屏 1806   横屏 1080
         **/
        public static int getScreenWidth(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getWidth();
        }

        /**
         * TO 获取屏幕高度
         * 竖屏 1080   横屏 1806（不包含虚拟按键的高度）
         **/
        public static int getScreenHeight(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getHeight();
        }

        /**
         * TO 显示软键盘
         * (功能未实现)
         *
         * @param activity 当前Activity
         * @param view     接受软键盘输入的视图,光标在该视图上才显示软键盘
         */
        public static void showKeyboard(Activity activity, View view) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
        }

        /**
         * TO 隐藏软键盘
         *
         * @param activity 当前Activity
         */
        public static void hideKeyboard(Activity activity) {
            if (activity.getCurrentFocus() != null) {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }

        /**
         * TO：dp转px(像素)
         */
        public static int dp2px(Context context, float dp) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }

        /**
         * TO：px(像素)转dp
         */
        public static int px2dp(Context context, float px) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (px / scale + 0.5f);
        }
    }

    public static boolean isWeixinAvailible(Context context) {
//        boolean b = !APIUtil.getWxApi().isWXAppInstalled();
//        return b; //也可以
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置EditText的变化范围
     *
     * @param et  view
     * @param min 最小值
     * @param max 最大值
     */
    public void setRegion(final EditText et, final double min, final double max) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1) {
                    if (min != -1 && max != -1) {
                        double num = Double.parseDouble(s.toString());
                        if (num > max) {
                            s = String.valueOf(max);
                            et.setText(s);
                        } else if (num < min) {
                            s = String.valueOf(min);
                            et.setText(s);
                        }
                        et.setSelection(s.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals("")) {
                    if (min != -1 && max != -1) {
                        double markVal = 0;
                        try {
                            markVal = Double.parseDouble(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > max) {
                            et.setText(String.valueOf(max));
                            et.setSelection(String.valueOf(max).length());
                        }
                    }
                }
            }
        });
    }


    //---------------------------不作为工具使用，请复制到类中
    /*
     * TO 获取设备位置(经纬度)
     */
    /*
    public static double[] getLatLng(Context context) {
        String provider;
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {  //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {   //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(context, "请检查网络或GPS是否打开", Toast.LENGTH_LONG).show();
            throw new RuntimeException("请检查网络或GPS是否打开");
        }
        //检查权限（6.0以上）
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            double latitude = location.getLatitude();   //纬度
            double longitude = location.getLongitude(); //经度
            return new double[]{latitude, longitude};
        } else {
            return null;
        }
    }
    */

    public static class TempUtils {
        /**
         * 去掉毫秒
         *
         * @param time 2017-12-29 14:44:53:583
         * @return 2017-12-29 14:44:53
         */
        public static String formatTime(String time) {
            return time.substring(0, time.lastIndexOf(":"));
        }

//        public static void hideSoftInput() {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        }

    }
}