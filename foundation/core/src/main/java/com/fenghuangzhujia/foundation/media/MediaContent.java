package com.fenghuangzhujia.foundation.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fenghuangzhujia.foundation.core.entity.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="medias")
public class MediaContent extends BaseModel {
	@JsonIgnore
	private String id;
	private String url;
	private String remark;
	@JsonIgnore
	private String serverName;
	@JsonIgnore
	private String serverAddress;
	@JsonIgnore
	private String originFileName;
	@JsonIgnore
	private String contentType;
	@JsonIgnore
	private Long fileSize;
	@JsonIgnore
	private String path;
	@JsonIgnore
	private String fileName;
	
	/**
	 * @return the id
	 */
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the url
	 * 数据库中只保存相对地址，不保存url
	 * url在setServerAddress和setSavePath过程中自动被赋值。
	 */
	@Transient
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the serverName
	 * 服务器名称，用于检索
	 */
	public String getServerName() {
		return serverName;
	}
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	/**
	 * @return the serverAddress
	 * 文件服务器地址，结尾不带/，是服务器ip和port以及应用名称的结合。
	 */
	public String getServerAddress() {
		return serverAddress;
	}
	/**
	 * @param serverAddress the serverAddress to set
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
		this.url=serverAddress+path;
	}
	
	/**
	 * @return the fileName
	 */
	public String getOriginFileName() {
		return this.originFileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the savePath
	 * 相对于文件访问服务器跟路径的相对地址，开头带/，表示相对跟路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param savePath the savePath to set
	 */
	public void setPath(String path) {
		this.path = path;
		this.url=serverAddress+path;
	}
	/**
	 * @return the saveFileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param saveFileName the saveFileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
