package com.ai.sam.utils;


import com.ai.sam.common.EnvironmentValue;
import com.ai.sam.exception.SequenceException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 序列生成工具
 */
@Component("sequenceUtils")
public class SequenceUtils {

    private static final int BASE_LENGTH = 5;

    @Autowired
//    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    private static final Log logger = LogFactory.getLog(SequenceUtils.class);

    /**
     * 根据redis键值获取给定长度序列唯一标识
     * 警告:自定长度必须大于五位!
     * yyyyMMddHHmmssSSS（17位）+ 2位实例编码 + 给定长度自增序列
     * mysql varchar
     *
     * @param key    缓存键值
     * @param length 给定尾数长度
     * @return 序列唯一标识
     */
    public String getSequence(String key, int length) throws Exception {
        long timestamp = System.currentTimeMillis();
        String datePattern = "yyyyMMddHHmmssSSS";
        String envInstanceId = EnvironmentValue.getEnvInstanceId();
        String incrSequence = getSequenceOnlyIncr(key, length);
        String datePrefix = DateFormatUtils.format(timestamp, datePattern);

        if (StringUtils.isNotEmpty(envInstanceId)) {
            return datePrefix + envInstanceId + incrSequence;
        } else {
            return datePrefix + incrSequence;
        }
    }

    /**
     * 根据redis键值获取长整型序列唯一标识
     * 警告: yyMMddHHmmss（12位）+ 2位实例编码 + 5位长度自增序列
     * mysql bigint
     *
     * @param key 缓存键值
     * @return 序列唯一标识
     */
    public Long getSequence(String key) throws Exception {
        long timestamp = System.currentTimeMillis();
        String datePattern = "yyMMddHHmmss";
        String envInstanceId = EnvironmentValue.getEnvInstanceId();
        String incrSequence = getSequenceOnlyIncr(key, 5);
        String datePrefix = DateFormatUtils.format(timestamp, datePattern);
        String sequence;

        if (StringUtils.isNotEmpty(envInstanceId)) {
            sequence = datePrefix + envInstanceId + incrSequence;
        } else {
            sequence = datePrefix + incrSequence;
        }
        return Long.parseLong(sequence);
    }

    /**
     * 根据redis键值获取序列唯一标识
     * 警告:自定长度必须大于六位!
     *
     * @param key    缓存键值
     * @param length 给定尾数长度
     * @return 序列唯一标识
     */
    private String getSequenceOnlyIncr(String key, int length) {
        if (length < BASE_LENGTH) {
            throw new SequenceException("序列长度不满足唯一标识生成需求");
        }
        if (StringUtils.isEmpty(key)) {
            throw new SequenceException("redis键值不能为空");
        }

        String sequence;
        try {
            Long redisValue = redisTemplate.opsForValue().increment(key, 1);

            //如果缓存Incr的键值大于基准数值则进行截取
            if (redisValue > (Math.pow(10, length) - 1)) {
                sequence = StringUtils.substring(redisValue.toString(), redisValue.toString().length() - 5);
            } else {
                sequence = StringUtils.leftPad(redisValue.toString(), length, "0");
            }
        } catch (Exception e) {
            logger.info("使用redis获取主键失败，开始使用:随机字符串" + "key=" + key, e);
            sequence = RandomStringUtils.randomNumeric(length);
        }
        return sequence;
    }

}