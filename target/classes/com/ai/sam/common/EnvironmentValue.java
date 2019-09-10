package com.ai.sam.common;

import com.ai.sam.exception.SequenceException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 获取环境变量
 */
public class EnvironmentValue {

    // 环境变量实例编码的KEY
    private final static String ENV_KEY_AI_CENTER_ID = "ai.instance.code";
    private static final Log logger = LogFactory.getLog(EnvironmentValue.class);

    public static String getEnvInstanceId() {
        String envInstanceId = System.getProperty(ENV_KEY_AI_CENTER_ID);

        try {
            if (envInstanceId == null) {
                logger.info("未在环境变量中配置实例编码");
            } else {
                Long centerId = Long.parseLong(envInstanceId);
                // 实例编码2位（最大99）
                envInstanceId = String.format("%02d", centerId);
                if (centerId < 0L || centerId > 99L) {
                    throw new SequenceException("从环境变量获取实例编码数据超出取值范围：【实例编码：" + centerId + "】");
                }
            }
        } catch (NumberFormatException e) {
            throw new SequenceException("从环境变量获取实例编码格式错误，【实例编码：" + envInstanceId + "】");
        }

        return StringUtils.isNotBlank(envInstanceId) ? envInstanceId : "00";
    }

}
