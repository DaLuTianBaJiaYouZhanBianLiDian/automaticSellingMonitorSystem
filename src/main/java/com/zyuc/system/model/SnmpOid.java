package com.zyuc.system.model;

/**
 * SNMP OID
 * @author verdant
 */
public class SnmpOid {
    private String oid;
    private String value;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
