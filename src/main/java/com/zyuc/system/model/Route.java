package com.zyuc.system.model;

import java.io.Serializable;
import java.util.Date;

import com.zyuc.common.model.CommonContent;

public class Route extends CommonContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String routeip;
	private int snmp_version;
	private String snmp_ip;
	private String snmp_communiry;
	private String flow_ip;
	private int flow_port;
	private String flow_simple;
	private String uuid;
	private Date UPDATE_TIME;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRouteip() {
		return routeip;
	}
	public void setRouteip(String routeip) {
		this.routeip = routeip;
	}
	public int getSnmp_version() {
		return snmp_version;
	}
	public void setSnmp_version(int snmp_version) {
		this.snmp_version = snmp_version;
	}
	public String getSnmp_ip() {
		return snmp_ip;
	}
	public void setSnmp_ip(String snmp_ip) {
		this.snmp_ip = snmp_ip;
	}
	public String getSnmp_communiry() {
		return snmp_communiry;
	}
	public void setSnmp_communiry(String snmp_communiry) {
		this.snmp_communiry = snmp_communiry;
	}
	public String getFlow_ip() {
		return flow_ip;
	}
	public void setFlow_ip(String flow_ip) {
		this.flow_ip = flow_ip;
	}
	public int getFlow_port() {
		return flow_port;
	}
	public void setFlow_port(int flow_port) {
		this.flow_port = flow_port;
	}
	public String getFlow_simple() {
		return flow_simple;
	}
	public void setFlow_simple(String flow_simple) {
		this.flow_simple = flow_simple;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	
	
}
