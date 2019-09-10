package com.ai.sam.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("clusterConfigProperties")
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfigProperties {

    /*
     * spring.redis.cluster
     */
    private List<String> nodes;

    /**
     * Get initial collection of known cluster nodes in format {@code host:port}.
     *
     * @return cluster nodes
     */
    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

}
