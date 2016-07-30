package com.zyuc.system.model;

/**
 * SNMP 账户信息
 * @author verdant
 */
public class SnmpHost {
    private String ip;
    private String community;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }
}
