package com.zyuc.system.model;

import java.io.Serializable;
import java.util.Date;

import com.zyuc.common.model.CommonContent;

public class Cust extends CommonContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String cuscode;
	private String address;
	private String customername;
	private Long bandwidth;
	private Long asnum;
	private String pass;
	private String ispcode;
	private Date updatetime;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCuscode() {
		return cuscode;
	}
	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public Long getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(Long bandwidth) {
		this.bandwidth = bandwidth;
	}
	public Long getAsnum() {
		return asnum;
	}
	public void setAsnum(Long asnum) {
		this.asnum = asnum;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getIspcode() {
		return ispcode;
	}
	public void setIspcode(String ispcode) {
		this.ispcode = ispcode;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
