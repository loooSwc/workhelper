package com.workhelper.log.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_log")
public class BaseSysLog implements java.io.Serializable{

	private static final long serialVersionUID = -2596698541778925630L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "log_id", unique = true, nullable = false, length = 32)
	private String logId;		//主键id

	@Column(name = "user_id")
    private String userId;		//操作人ID
	@Column(name = "user_account")
    private String userAccount;	//操作人账号
	@Column(name = "operation")
    private String operation;	//操作项
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
    private Date timestamp;		//操作时间
	@Column(name = "status")
    private String status;		//操作状态；1:成功；0：失败
	@Column(name = "details")
    private String details;		//详情
	@Column(name = "log_from")
	private String logFrom;		//日志来源
	@Column(name = "wx_id")
	private String wxId;			//微信ID

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLogFrom() {
		return logFrom;
	}

	public void setLogFrom(String logFrom) {
		this.logFrom = logFrom;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
}
