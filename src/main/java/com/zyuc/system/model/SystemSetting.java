package com.zyuc.system.model;

import java.io.Serializable;


public class SystemSetting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String syslogpath;
	private String serverip;
	
	public String getSyslogpath() {
		return syslogpath;
	}
	public void setSyslogpath(String syslogpath) {
		this.syslogpath = syslogpath;
	}
	public String getServerip() {
		return serverip;
	}
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	
  
}
