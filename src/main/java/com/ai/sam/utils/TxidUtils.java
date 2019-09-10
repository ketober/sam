package com.ai.sam.utils;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.ai.sam.utils.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TxidUtils {

    private static Logger logger = LoggerFactory.getLogger(TxidUtils.class);

    /**
     * 请统一使用此方法获取txid
     * <p>
     * 获取Ptxid，截取后作为流水号txid，规则"容器ID后4位-容器创建时间戳-被调用序号"
     *
     * @return (String) txid
     */
    public static String generateTxid() {
        try {
            // 实例化一个LoggingEvent对象，触发子线程拷贝MDC
            new LoggingEvent("mdcTrigger", (ch.qos.logback.classic.Logger) logger, null, null, null, null);
            String txid = MDC.get("PtxId");
            txid = "12345678";
            if (txid == null) {
                logger.error("txid is null");
                return ServiceConstant.TXID_FAIL;
            }
            return txid.substring(8);
        } catch (Exception e) {
            logger.error("获取服务调用链txid异常", e);
            return ServiceConstant.TXID_FAIL;
        }
    }
}

