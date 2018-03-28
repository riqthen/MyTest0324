package com.h.mylibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by riq on 2017/4/26.
 */

public class Lcat {
    private static int PRIORITY = Log.ERROR;    //默认优先级
    private static int PRIORITYD = Log.DEBUG;    //默认优先级
    public static boolean IS_DEBUG = true;     //默认显示log

    //-----------> String
    public static void e(String s) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + s);
        }
    }

    public static void e(String tag, String s) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + s);
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + s);
            }
        }
    }

    //-----------> Object
    public static void e(Object o) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "--", "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
        }
    }

    public static void e(String tag, Object o) {
        try {
            if (IS_DEBUG) {
                String threadName = Thread.currentThread().getName();
                String lineIndicator = getLineIndicator();
                if (TextUtils.isEmpty(tag)) {
                    Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
                } else {
                    Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + o.toString());
                }
            }
        } catch (Exception e) {
            Lcat.e("打印错误", e);
        }
    }

    //-----------> Exception
    public static void e(Exception e) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "--", "Thread: " + threadName + "／" + lineIndicator + " " + e.getMessage());
        }
    }

    public static void e(String tag, Exception e) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + e.getMessage());
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + e.getMessage());
            }
        }
    }


    //-----------> Integer
    public static void e(int i) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + i);
        }
    }

    public static void e(String tag, int i) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + i);
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + i);
            }
        }
    }

    //-----------> Double
    public static void e(double d) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + d);
        }
    }

    public static void e(String tag, double d) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + d);
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + d);
            }
        }
    }


    //-----------> Long
    public static void e(long l) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + l);
        }
    }

    public static void e(String tag, long l) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + l);
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + l);
            }
        }
    }

    //-----------> Boolean
    public static void e(boolean b) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + b);
        }
    }

    public static void e(String tag, boolean b) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + b);
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + b);
            }
        }
    }

    //----------->  String[]
    public static void e(String[] strings) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
        }
    }

    public static void e(String tag, String[] strings) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(strings));
            }
        }
    }

    //----------->  int[]
    public static void e(int[] ints) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
        }
    }

    public static void e(String tag, int[] ints) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(ints));
            }
        }
    }

    //----------->  double[]
    public static void e(double[] doubles) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
        }
    }

    public static void e(String tag, double[] doubles) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(doubles));
            }
        }
    }

    //----------->  byte[]
    public static void e(byte[] bytes) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
        }
    }

    public static void e(String tag, byte[] bytes) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(bytes));
            }
        }
    }


    //----------->  Object[]
    public static void e(Object[] objects) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
        }
    }

    public static void e(String tag, Object[] objects) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(objects));
            }
        }
    }

    //----------->  Long[]
    public static void e(Long[] longs) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
        }
    }

    public static void e(String tag, Long[] longs) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(longs));
            }
        }
    }


    //-----------> List
    public static <T> void e(List<T> list) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
        }
    }


    public static <T> void e(String tag, List<T> list) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(list));
            }
        }
    }


    //-----------> Set
    public static <T> void e(Set<T> set) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
        }
    }

    public static <T> void e(String tag, Set<T> set) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(set));
            }
        }
    }

    //-----------> Map
    public static <K, V> void e(Map<K, V> map) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
        }
    }

    public static <K, V> void e(String tag, Map<K, V> map) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITY, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
            } else {
                Log.println(PRIORITY, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(map));
            }
        }
    }

    //-------------------------------d-------------------------------


    //-----------> String
    public static void d(String s) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + s);
        }
    }

    public static void d(String tag, String s) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + s);
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + s);
            }
        }
    }

    //-----------> Object
    public static void d(Object o) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "--", "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
        }
    }

    public static void d(String tag, Object o) {
        try {
            if (IS_DEBUG) {
                String threadName = Thread.currentThread().getName();
                String lineIndicator = getLineIndicator();
                if (TextUtils.isEmpty(tag)) {
                    Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
                } else {
                    Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + o.toString());
                }
            }
        } catch (Exception e) {
            Lcat.e("打印错误", e);
        }
    }

    //-----------> Exception
    public static void d(Exception e) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "--", "Thread: " + threadName + "／" + lineIndicator + " " + e.getMessage());
        }
    }

    public static void d(String tag, Exception e) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + e.getMessage());
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + e.getMessage());
            }
        }
    }


    //-----------> Integer
    public static void d(int i) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + i);
        }
    }

    public static void d(String tag, int i) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + i);
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + i);
            }
        }
    }

    //-----------> Double
    public static void d(double d) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + d);
        }
    }

    public static void d(String tag, double d) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + d);
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + d);
            }
        }
    }


    //-----------> Long
    public static void d(long l) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + l);
        }
    }

    public static void d(String tag, long l) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + l);
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + l);
            }
        }
    }

    //-----------> Boolean
    public static void d(boolean b) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + b);
        }
    }

    public static void d(String tag, boolean b) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + b);
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + b);
            }
        }
    }

    //----------->  String[]
    public static void d(String[] strings) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
        }
    }

    public static void d(String tag, String[] strings) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(strings));
            }
        }
    }

    //----------->  int[]
    public static void d(int[] ints) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
        }
    }

    public static void d(String tag, int[] ints) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(ints));
            }
        }
    }

    //----------->  double[]
    public static void d(double[] doubles) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
        }
    }

    public static void d(String tag, double[] doubles) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(doubles));
            }
        }
    }

    //----------->  byte[]
    public static void d(byte[] bytes) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
        }
    }

    public static void d(String tag, byte[] bytes) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(bytes));
            }
        }
    }


    //----------->  Object[]
    public static void d(Object[] objects) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
        }
    }

    public static void d(String tag, Object[] objects) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(objects));
            }
        }
    }

    //----------->  Long[]
    public static void d(Long[] longs) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
        }
    }

    public static void d(String tag, Long[] longs) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + Arrays.toString(longs));
            }
        }
    }


    //-----------> List
    public static <T> void d(List<T> list) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
        }
    }


    public static <T> void d(String tag, List<T> list) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(list));
            }
        }
    }


    //-----------> Set
    public static <T> void d(Set<T> set) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
        }
    }

    public static <T> void d(String tag, Set<T> set) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(set));
            }
        }
    }

    //-----------> Map
    public static <K, V> void d(Map<K, V> map) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
        }
    }

    public static <K, V> void d(String tag, Map<K, V> map) {
        if (IS_DEBUG) {
            String threadName = Thread.currentThread().getName();
            String lineIndicator = getLineIndicator();
            if (TextUtils.isEmpty(tag)) {
                Log.println(PRIORITYD, "------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
            } else {
                Log.println(PRIORITYD, tag, "Thread: " + threadName + "／" + lineIndicator + tag + " ---->" + String.valueOf(map));
            }
        }
    }


    //获取行所在的方法指示
    private static String getLineIndicator() {
        //2代表方法的调用深度：0-getLineIndicator，1-performPrint，2-print
        StackTraceElement element = ((new Exception()).getStackTrace())[2];
        String packageName = element.getClassName().substring(0, element.getClassName().lastIndexOf("."));
        return packageName + "／" +
//                element.getMethodName() + "()／" +
                "(" +
                element.getFileName() + ":" +
                element.getLineNumber() + ")：";
    }
}
