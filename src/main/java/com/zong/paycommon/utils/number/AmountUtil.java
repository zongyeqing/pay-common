package com.zong.paycommon.utils.number;

import java.math.BigDecimal;

/**
 * @author 宗叶青 on 2017/8/15/20:11
 */
public abstract class AmountUtil {

    private AmountUtil(){

    }

    public static double add(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2){
        return div(v1, v2, 2);
    }

/**
 * 除法运算，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
 */
 public static double div(double v1, double v2, int scale){
        if(scale < 0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 四舍五入
     * @param v
     * @param scale
     * @return
     */
    public static double round (double v, int scale){
     if(scale < 0){
         throw new IllegalArgumentException("The scale must be a positive integer or zero");
     }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断是否相等
     * @param v1
     * @param v2
     * @return
     */
    public static boolean equal(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        if(b1.compareTo(b2) == 0){
            return true;
        }
        return false;
    }

    public static boolean greaterThanOrEqualTo(double v1, double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        if(b1.compareTo(b2) >= 0){
            return true;
        }
        return false;
    }

    /**
     * 判断 a 是否大于 b
     *
     * @param a
     * @param b
     * @return a&gt;b 返回true, a&lt;=b 返回 false
     */
    public static boolean bigger(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断 a 是否小于 b
     *
     * @param a
     * @param b
     * @return a&lt;b 返回true, a&gt;=b 返回 false
     */
    public static boolean lessThan(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) == -1) {
            return true;
        }
        return false;
    }

    /**
     * 四舍五入保留小数点后两位
     *
     * @param num
     * @return
     */
    public static double roundDown(double num) {
        return Double.valueOf(String.format("%.2f", num));
        //return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
