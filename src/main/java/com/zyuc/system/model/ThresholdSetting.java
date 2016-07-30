package com.zyuc.system.model;

import java.sql.Date;

import com.zyuc.common.model.CommonContent;


/**
 * 
 * @Description: ThresholdSetting
 * 
 * @author npc
 * 
 */
public class ThresholdSetting extends CommonContent{

	private static final Double LOW_PRCENT = 0.5;
	private static final String OPEN = "on";

	/**
	 * 客户ＩＤ
	 */
	private String custid;
	private String tcustid;
	/**
	 * dns包　严重告警阈值
	 */
	private Double dnsseverityrate;
	
	private String dnsseverityunit;

	/**
	 * dns 阈值开关　on:开 off:关
	 */
	private String dnsthrenable;
	/**
	 * dns 包　触发告警阈值
	 */
	private Double dnstriggerrate;
	private String dnstriggerunit;
	/**
	 * icmp 包　严重告警阈值
	 */
	private Double icmpseverityrate;
	private String icmpseverityunit;
	/**
	 * icmp 阈值开关　on:开 off:关
	 */
	private String icmpthrenable;
	/**
	 * icmp 包　触发告警阈值
	 */
	private Double icmptriggerrate;
	private String icmptriggerunit;
	/**
	 * 主键ＩＤ
	 */
	private Integer id;

	/**
	 * ipfrag 包　严重告警阈值
	 */
	private Double ipfragseverityrate;
	private String ipfragseverityunit;
	/**
	 * ipfrag 阈值开关　on:开 off:关
	 */
	private String ipfragthrenable;
	/**
	 * ipfrag 包　触发告警阈值
	 */
	private Double ipfragtriggerrate;
	private String ipfragtriggerunit;

	/**
	 * ipnull 包　严重告警阈值
	 */
	private Double ipnullseverityrate;
	private String ipnullseverityunit;
	/**
	 * ipnull 阈值开关　on:开 off:关
	 */
	private String ipnullthrenable;
	/**
	 * ipnull 包　触发告警阈值
	 */
	private Double ipnulltriggerrate;
	private String ipnulltriggerunit;

	/**
	 * ipprivate 包　严重告警阈值
	 */
	private Double ipprivateseverityrate;
	private String ipprivateseverityunit;
	/**
	 * ipprivate 阈值开关　on:开 off:关
	 */
	private String ipprivatethrenable;
	/**
	 * ipprivate 包　触发告警阈值
	 */
	private Double ipprivatetriggerrate;
	private String ipprivatetriggerunit;

	/**
	 * tcpnull 包　严重告警阈值
	 */
	private Double tcpnullseverityrate;
	private String tcpnullseverityunit;
	/**
	 * tcpnull 阈值开关　on:开 off:关
	 */
	private String tcpnullthrenable;
	/**
	 * tcpnull 包　触发告警阈值
	 */
	private Double tcpnulltriggerrate;
	private String tcpnulltriggerunit;

	/**
	 * tcprst 包　严重告警阈值
	 */
	private Double tcprstseverityrate;
	private String tcprstseverityunit;
	/**
	 * tcprst 阈值开关　on:开 off:关
	 */
	private String tcprstthrenable;
	/**
	 * tcprst 包　触发告警阈值
	 */
	private Double tcprsttriggerrate;
	private String tcprsttriggerunit;
	/**
	 * tcpsyn 包　严重告警阈值
	 */
	private Double tcpsynseverityrate;
	private String tcpsynseverityunit;
	/**
	 * tcpsyn 阈值开关　on:开 off:关
	 */
	private String tcpsynthrenable;
	/**
	 * tcpsyn 包　触发告警阈值
	 */
	private Double tcpsyntriggerrate;
	private String tcpsyntriggerunit;
	/**
	 * totalbps 阈值开关　on:开 off:关
	 */
	private String totalbpshrenable;
	/**
	 * totalbps 包　严重告警阈值
	 */
	private Double totalbpsseverityrate;
	private String totalbpsseverityunit;
	/**
	 * totalbps 包　触发告警阈值
	 */
	private Double totalbpstriggerrate;
	private String totalbpstriggerunit;

	/**
	 * totalpps 阈值开关　on:开 off:关
	 */
	private String totalppshrenable;
	/**
	 * totalpps 包　严重告警阈值
	 */
	private Double totalppsseverityrate;
	private String totalppsseverityunit;
	/**
	 * totalpps 包　触发告警阈值
	 */
	private Double totalppstriggerrate;
	private String totalppstriggerunit;

	/**
	 * udp 包　严重告警阈值
	 */
	private Double udpseverityrate;
	private String udpseverityunit;
	/**
	 * udp 阈值开关　on:开 off:关
	 */
	private String udpthrenable;
	/**
	 * udp 包　触发告警阈值
	 */
	private Double udptriggerrate;
	private String udptriggerunit;


	private Date modifytime;
	private String modifyip;
	

	public String getModifyip() {
		return modifyip;
	}

	public void setModifyip(String modifyip) {
		this.modifyip = modifyip;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getCustid() {
		return custid;
	}

	public Double getDnsseverityrate() {
		return dnsseverityrate;
	}

	public String getDnsthrenable() {
		return dnsthrenable;
	}

	public Double getDnstriggerrate() {
		return dnstriggerrate;
	}

	public Double getIcmpseverityrate() {
		return icmpseverityrate;
	}

	public String getIcmpthrenable() {
		return icmpthrenable;
	}

	public Double getIcmptriggerrate() {
		return icmptriggerrate;
	}

	public Integer getId() {
		return id;
	}

	public Double getIpfragseverityrate() {
		return ipfragseverityrate;
	}

	public String getIpfragthrenable() {
		return ipfragthrenable;
	}

	public Double getIpfragtriggerrate() {
		return ipfragtriggerrate;
	}

	public Double getIpnullseverityrate() {
		return ipnullseverityrate;
	}

	public String getIpnullthrenable() {
		return ipnullthrenable;
	}

	public Double getIpnulltriggerrate() {
		return ipnulltriggerrate;
	}

	public Double getIpprivateseverityrate() {
		return ipprivateseverityrate;
	}

	public String getIpprivatethrenable() {
		return ipprivatethrenable;
	}

	public Double getIpprivatetriggerrate() {
		return ipprivatetriggerrate;
	}

	public Double getTcpnullseverityrate() {
		return tcpnullseverityrate;
	}

	public String getTcpnullthrenable() {
		return tcpnullthrenable;
	}

	public Double getTcpnulltriggerrate() {
		return tcpnulltriggerrate;
	}

	public Double getTcprstseverityrate() {
		return tcprstseverityrate;
	}

	public String getTcprstthrenable() {
		return tcprstthrenable;
	}

	public Double getTcprsttriggerrate() {
		return tcprsttriggerrate;
	}

	public Double getTcpsynseverityrate() {
		return tcpsynseverityrate;
	}

	public String getTcpsynthrenable() {
		return tcpsynthrenable;
	}

	public Double getTcpsyntriggerrate() {
		return tcpsyntriggerrate;
	}

	public String getTotalbpshrenable() {
		return totalbpshrenable;
	}

	public Double getTotalbpsseverityrate() {
		return totalbpsseverityrate;
	}

	public Double getTotalbpstriggerrate() {
		return totalbpstriggerrate;
	}

	public String getTotalppshrenable() {
		return totalppshrenable;
	}

	public Double getTotalppsseverityrate() {
		return totalppsseverityrate;
	}

	public Double getTotalppstriggerrate() {
		return totalppstriggerrate;
	}

	public Double getUdpseverityrate() {
		return udpseverityrate;
	}

	public String getUdpthrenable() {
		return udpthrenable;
	}

	public Double getUdptriggerrate() {
		return udptriggerrate;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public void setDnsseverityrate(Double dnsseverityrate) {
		this.dnsseverityrate = dnsseverityrate;
	}

	public void setDnsthrenable(String dnsthrenable) {
		this.dnsthrenable = dnsthrenable;
	}

	public void setDnstriggerrate(Double dnstriggerrate) {
		this.dnstriggerrate = dnstriggerrate;
	}

	public void setIcmpseverityrate(Double icmpseverityrate) {
		this.icmpseverityrate = icmpseverityrate;
	}

	public void setIcmpthrenable(String icmpthrenable) {
		this.icmpthrenable = icmpthrenable;
	}

	public void setIcmptriggerrate(Double icmptriggerrate) {
		this.icmptriggerrate = icmptriggerrate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIpfragseverityrate(Double ipfragseverityrate) {
		this.ipfragseverityrate = ipfragseverityrate;
	}

	public void setIpfragthrenable(String ipfragthrenable) {
		this.ipfragthrenable = ipfragthrenable;
	}

	public void setIpfragtriggerrate(Double ipfragtriggerrate) {
		this.ipfragtriggerrate = ipfragtriggerrate;
	}

	public void setIpnullseverityrate(Double ipnullseverityrate) {
		this.ipnullseverityrate = ipnullseverityrate;
	}

	public void setIpnullthrenable(String ipnullthrenable) {
		this.ipnullthrenable = ipnullthrenable;
	}

	public void setIpnulltriggerrate(Double ipnulltriggerrate) {
		this.ipnulltriggerrate = ipnulltriggerrate;
	}

	public void setIpprivateseverityrate(Double ipprivateseverityrate) {
		this.ipprivateseverityrate = ipprivateseverityrate;
	}

	public void setIpprivatethrenable(String ipprivatethrenable) {
		this.ipprivatethrenable = ipprivatethrenable;
	}

	public void setIpprivatetriggerrate(Double ipprivatetriggerrate) {
		this.ipprivatetriggerrate = ipprivatetriggerrate;
	}

	public void setTcpnullseverityrate(Double tcpnullseverityrate) {
		this.tcpnullseverityrate = tcpnullseverityrate;
	}

	public void setTcpnullthrenable(String tcpnullthrenable) {
		this.tcpnullthrenable = tcpnullthrenable;
	}

	public void setTcpnulltriggerrate(Double tcpnulltriggerrate) {
		this.tcpnulltriggerrate = tcpnulltriggerrate;
	}

	public void setTcprstseverityrate(Double tcprstseverityrate) {
		this.tcprstseverityrate = tcprstseverityrate;
	}

	public void setTcprstthrenable(String tcprstthrenable) {
		this.tcprstthrenable = tcprstthrenable;
	}

	public void setTcprsttriggerrate(Double tcprsttriggerrate) {
		this.tcprsttriggerrate = tcprsttriggerrate;
	}

	public void setTcpsynseverityrate(Double tcpsynseverityrate) {
		this.tcpsynseverityrate = tcpsynseverityrate;
	}

	public void setTcpsynthrenable(String tcpsynthrenable) {
		this.tcpsynthrenable = tcpsynthrenable;
	}

	public void setTcpsyntriggerrate(Double tcpsyntriggerrate) {
		this.tcpsyntriggerrate = tcpsyntriggerrate;
	}

	public void setTotalbpshrenable(String totalbpshrenable) {
		this.totalbpshrenable = totalbpshrenable;
	}

	public void setTotalbpsseverityrate(Double totalbpsseverityrate) {
		this.totalbpsseverityrate = totalbpsseverityrate;
	}

	public void setTotalbpstriggerrate(Double totalbpstriggerrate) {
		this.totalbpstriggerrate = totalbpstriggerrate;
	}

	public void setTotalppshrenable(String totalppshrenable) {
		this.totalppshrenable = totalppshrenable;
	}

	public void setTotalppsseverityrate(Double totalppsseverityrate) {
		this.totalppsseverityrate = totalppsseverityrate;
	}

	public void setTotalppstriggerrate(Double totalppstriggerrate) {
		this.totalppstriggerrate = totalppstriggerrate;
	}

	public void setUdpseverityrate(Double udpseverityrate) {
		this.udpseverityrate = udpseverityrate;
	}

	public void setUdpthrenable(String udpthrenable) {
		this.udpthrenable = udpthrenable;
	}

	public void setUdptriggerrate(Double udptriggerrate) {
		this.udptriggerrate = udptriggerrate;
	}
	
	

	public String getDnsseverityunit() {
		return dnsseverityunit;
	}

	public void setDnsseverityunit(String dnsseverityunit) {
		this.dnsseverityunit = dnsseverityunit;
	}

	public String getDnstriggerunit() {
		return dnstriggerunit;
	}

	public void setDnstriggerunit(String dnstriggerunit) {
		this.dnstriggerunit = dnstriggerunit;
	}

	public String getIcmpseverityunit() {
		return icmpseverityunit;
	}

	public void setIcmpseverityunit(String icmpseverityunit) {
		this.icmpseverityunit = icmpseverityunit;
	}

	public String getIcmptriggerunit() {
		return icmptriggerunit;
	}

	public void setIcmptriggerunit(String icmptriggerunit) {
		this.icmptriggerunit = icmptriggerunit;
	}

	public String getIpfragseverityunit() {
		return ipfragseverityunit;
	}

	public void setIpfragseverityunit(String ipfragseverityunit) {
		this.ipfragseverityunit = ipfragseverityunit;
	}

	public String getIpfragtriggerunit() {
		return ipfragtriggerunit;
	}

	public void setIpfragtriggerunit(String ipfragtriggerunit) {
		this.ipfragtriggerunit = ipfragtriggerunit;
	}

	public String getIpnullseverityunit() {
		return ipnullseverityunit;
	}

	public void setIpnullseverityunit(String ipnullseverityunit) {
		this.ipnullseverityunit = ipnullseverityunit;
	}

	public String getIpnulltriggerunit() {
		return ipnulltriggerunit;
	}

	public void setIpnulltriggerunit(String ipnulltriggerunit) {
		this.ipnulltriggerunit = ipnulltriggerunit;
	}

	public String getIpprivateseverityunit() {
		return ipprivateseverityunit;
	}

	public void setIpprivateseverityunit(String ipprivateseverityunit) {
		this.ipprivateseverityunit = ipprivateseverityunit;
	}

	public String getIpprivatetriggerunit() {
		return ipprivatetriggerunit;
	}

	public void setIpprivatetriggerunit(String ipprivatetriggerunit) {
		this.ipprivatetriggerunit = ipprivatetriggerunit;
	}

	public String getTcpnullseverityunit() {
		return tcpnullseverityunit;
	}

	public void setTcpnullseverityunit(String tcpnullseverityunit) {
		this.tcpnullseverityunit = tcpnullseverityunit;
	}

	public String getTcpnulltriggerunit() {
		return tcpnulltriggerunit;
	}

	public void setTcpnulltriggerunit(String tcpnulltriggerunit) {
		this.tcpnulltriggerunit = tcpnulltriggerunit;
	}

	public String getTcprstseverityunit() {
		return tcprstseverityunit;
	}

	public void setTcprstseverityunit(String tcprstseverityunit) {
		this.tcprstseverityunit = tcprstseverityunit;
	}

	public String getTcprsttriggerunit() {
		return tcprsttriggerunit;
	}

	public void setTcprsttriggerunit(String tcprsttriggerunit) {
		this.tcprsttriggerunit = tcprsttriggerunit;
	}

	public String getTcpsynseverityunit() {
		return tcpsynseverityunit;
	}

	public void setTcpsynseverityunit(String tcpsynseverityunit) {
		this.tcpsynseverityunit = tcpsynseverityunit;
	}

	public String getTcpsyntriggerunit() {
		return tcpsyntriggerunit;
	}

	public void setTcpsyntriggerunit(String tcpsyntriggerunit) {
		this.tcpsyntriggerunit = tcpsyntriggerunit;
	}

	public String getTotalbpsseverityunit() {
		return totalbpsseverityunit;
	}

	public void setTotalbpsseverityunit(String totalbpsseverityunit) {
		this.totalbpsseverityunit = totalbpsseverityunit;
	}

	public String getTotalbpstriggerunit() {
		return totalbpstriggerunit;
	}

	public void setTotalbpstriggerunit(String totalbpstriggerunit) {
		this.totalbpstriggerunit = totalbpstriggerunit;
	}

	public String getTotalppsseverityunit() {
		return totalppsseverityunit;
	}

	public void setTotalppsseverityunit(String totalppsseverityunit) {
		this.totalppsseverityunit = totalppsseverityunit;
	}

	public String getTotalppstriggerunit() {
		return totalppstriggerunit;
	}

	public void setTotalppstriggerunit(String totalppstriggerunit) {
		this.totalppstriggerunit = totalppstriggerunit;
	}

	public String getUdpseverityunit() {
		return udpseverityunit;
	}

	public void setUdpseverityunit(String udpseverityunit) {
		this.udpseverityunit = udpseverityunit;
	}

	public String getUdptriggerunit() {
		return udptriggerunit;
	}

	public void setUdptriggerunit(String udptriggerunit) {
		this.udptriggerunit = udptriggerunit;
	}
	
	public String getTcustid() {
		return tcustid;
	}

	public void setTcustid(String tcustid) {
		this.tcustid = tcustid;
	}


	@Override
	public String toString() {
		return "ThresholdSetting [custid=" + custid + ", dnsseverityrate="
				+ dnsseverityrate + ", dnsthrenable=" + dnsthrenable
				+ ", dnstriggerrate=" + dnstriggerrate + ", icmpseverityrate="
				+ icmpseverityrate + ", icmpthrenable=" + icmpthrenable
				+ ", icmptriggerrate=" + icmptriggerrate + ", id=" + id
				+ ", ipfragseverityrate=" + ipfragseverityrate
				+ ", ipfragthrenable=" + ipfragthrenable
				+ ", ipfragtriggerrate=" + ipfragtriggerrate
				+ ", ipnullseverityrate=" + ipnullseverityrate
				+ ", ipnullthrenable=" + ipnullthrenable
				+ ", ipnulltriggerrate=" + ipnulltriggerrate
				+ ", ipprivateseverityrate=" + ipprivateseverityrate
				+ ", ipprivatethrenable=" + ipprivatethrenable
				+ ", ipprivatetriggerrate=" + ipprivatetriggerrate
				+ ", tcpnullseverityrate=" + tcpnullseverityrate
				+ ", tcpnullthrenable=" + tcpnullthrenable
				+ ", tcpnulltriggerrate=" + tcpnulltriggerrate
				+ ", tcprstseverityrate=" + tcprstseverityrate
				+ ", tcprstthrenable=" + tcprstthrenable
				+ ", tcprsttriggerrate=" + tcprsttriggerrate
				+ ", tcpsynseverityrate=" + tcpsynseverityrate
				+ ", tcpsynthrenable=" + tcpsynthrenable
				+ ", tcpsyntriggerrate=" + tcpsyntriggerrate
				+ ", totalbpshrenable=" + totalbpshrenable
				+ ", totalbpsseverityrate=" + totalbpsseverityrate
				+ ", totalbpstriggerrate=" + totalbpstriggerrate
				+ ", totalppshrenable=" + totalppshrenable
				+ ", totalppsseverityrate=" + totalppsseverityrate
				+ ", totalppstriggerrate=" + totalppstriggerrate
				+ ", udpseverityrate=" + udpseverityrate + ", udpthrenable="
				+ udpthrenable + ", udptriggerrate=" + udptriggerrate
				+ ", modifytime=" + modifytime + "]";
	}

}
