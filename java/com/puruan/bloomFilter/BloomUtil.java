package com.puruan.bloomFilter;

import org.springframework.stereotype.Component;

import java.util.BitSet;

@Component
public class BloomUtil {

    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};
    private static HashHelper[] hashHelpers = new HashHelper[seeds.length];
    public static final int DEFAULT_SIZE = 2 << 24;

    public BloomUtil() {
        for (int i = 0; i < seeds.length; i++) {
            hashHelpers[i] = new HashHelper(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(BitSet bitSet, String value) {
        for (HashHelper hashHelper : hashHelpers) {
            bitSet.set(hashHelper.calHash(value), true);
        }
    }

    /**
     * 判断字符串是否包含在布隆过滤器中
     */
    public boolean contains(BitSet bitSet, String value) {
        if (value == null) {
            return false;
        }
        boolean result = true;

        //将要比较的字符串重新以上述方法计算hash值，再与布隆过滤器比对
        for (HashHelper hashHelper : hashHelpers) {
            result = result && bitSet.get(hashHelper.calHash(value));
        }
        return result;
    }

    /**
     * 随机哈希值对象
     */

    public static class HashHelper {
        private int size;//二进制向量数组大小
        private int seed;//随机数种子

        public HashHelper(int cap, int seed) {
            this.size = size;
            this.seed = seed;
        }

        /**
         * 计算哈希值(也可以选用别的恰当的哈希函数)
         */
        public int calHash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }

            return (size - 1) & result;
        }
    }
}
