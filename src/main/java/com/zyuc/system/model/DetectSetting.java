package com.zyuc.system.model;

import java.io.Serializable;
import java.util.Date;

import com.zyuc.common.model.CommonContent;

public class DetectSetting extends CommonContent implements Serializable {

	private static final long serialVersionUID = -8177137821928603062L;

	String uuid;
	String custid;
	String setting;
	String detectmode;
	String enablebline;
	int severityrate;
	double severitymultiplier;
	int ignorerate;
	int severitybps;
	int severitypps;
	int basetime;
	String enabletsline;
	int trainperiod;
	int modelperiod;
	int tsdetectperiod;
	int tsdropnum;
	int tsbasetime;
	Date modifytime;
	String modifyip;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getDetectmode() {
		return detectmode;
	}

	public void setDetectmode(String detectmode) {
		this.detectmode = detectmode;
	}

	public String getEnablebline() {
		return enablebline;
	}

	public void setEnablebline(String enablebline) {
		this.enablebline = enablebline;
	}

	public int getSeverityrate() {
		return severityrate;
	}

	public void setSeverityrate(int severityrate) {
		this.severityrate = severityrate;
	}

	public double getSeveritymultiplier() {
		return severitymultiplier;
	}

	public void setSeveritymultiplier(double severitymultiplier) {
		this.severitymultiplier = severitymultiplier;
	}

	public int getIgnorerate() {
		return ignorerate;
	}

	public void setIgnorerate(int ignorerate) {
		this.ignorerate = ignorerate;
	}

	public int getSeveritybps() {
		return severitybps;
	}

	public void setSeveritybps(int severitybps) {
		this.severitybps = severitybps;
	}

	public int getSeveritypps() {
		return severitypps;
	}

	public void setSeveritypps(int severitypps) {
		this.severitypps = severitypps;
	}

	public int getBasetime() {
		return basetime;
	}

	public void setBasetime(int basetime) {
		this.basetime = basetime;
	}

	public String getEnabletsline() {
		return enabletsline;
	}

	public void setEnabletsline(String enabletsline) {
		this.enabletsline = enabletsline;
	}

	public int getTrainperiod() {
		return trainperiod;
	}

	public void setTrainperiod(int trainperiod) {
		this.trainperiod = trainperiod;
	}

	public int getModelperiod() {
		return modelperiod;
	}

	public void setModelperiod(int modelperiod) {
		this.modelperiod = modelperiod;
	}

	public int getTsdetectperiod() {
		return tsdetectperiod;
	}

	public void setTsdetectperiod(int tsdetectperiod) {
		this.tsdetectperiod = tsdetectperiod;
	}

	public int getTsdropnum() {
		return tsdropnum;
	}

	public void setTsdropnum(int tsdropnum) {
		this.tsdropnum = tsdropnum;
	}

	public int getTsbasetime() {
		return tsbasetime;
	}

	public void setTsbasetime(int tsbasetime) {
		this.tsbasetime = tsbasetime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifyip() {
		return modifyip;
	}

	public void setModifyip(String modifyip) {
		this.modifyip = modifyip;
	}

	@Override
	public String toString() {
		return "DetectSetting [uuid=" + uuid + ", custid=" + custid
				+ ", setting=" + setting + ", detectmode=" + detectmode
				+ ", enablebline=" + enablebline + ", severityrate="
				+ severityrate + ", severitymultiplier=" + severitymultiplier
				+ ", ignorerate=" + ignorerate + ", severitybps=" + severitybps
				+ ", severitypps=" + severitypps + ", basetime=" + basetime
				+ ", enabletsline=" + enabletsline + ", trainperiod="
				+ trainperiod + ", modelperiod=" + modelperiod
				+ ", tsdetectperiod=" + tsdetectperiod + ", tsdropnum="
				+ tsdropnum + ", tsbasetime=" + tsbasetime + ", modifytime="
				+ modifytime + ", modifyip=" + modifyip + "]";
	}

}
