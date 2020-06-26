/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.teclo.base.vo.configuracion;

/**
 *
 * @author Gibran Garcia
 */
public class ConfiguracionVO {
    
    private String path="C:\\SistemaDigitalizacion\\";
    private String localFilePath="C:\\TECLO_PROCESSING\\";
    private String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWNsb3NhcGkiLCJmdW5jaW9uUGFzc3dvcmQiOiI1NVRiNkgqVyIsImNyZWF0ZWQiOjE1Mzg3NjU3NTgyNTh9.lzxG0WFHKhhP_q8j5HLU_WqA7Y2OC0ijQq4CcKb0SEG0ZRZZG7FryQ43h0SMXR4m_tHnOj9LlIiWV7f7cE5QNA"; 
	private String formatDateDecode="dd-MM-yy";//
	private String formatDateEncode="yyyy-MM-dd HH:mm:ss";//
	
	/*
	 * Nota: Considerar el ambiente 
	 */
	/*DESA*/
//	private String baseurl="http://localhost:9080/sscdprocesamiento_v220r1des_services";
//	private String baseAppurl="http://localhost:9080/digitalizacion_ssc";
//	/*QA*/
//	private String baseurl="http://172.40.27.25/sscdprocesamiento_v220r1qa_services";
//	private String baseAppurl="http://172.40.27.25/digitalizacion_ssc_qabp";
//	/*PROD*/
	private String baseurl="http://172.40.27.25/sscdprocesamiento_v220r1prod_services";
	private String baseAppurl="http://172.40.27.25/digitalizacion_ssc";
	
	public ConfiguracionVO() {
		super();
	}
	public ConfiguracionVO(String path, String localFilePath, String token, String formatDateDecode,
			String formatDateEncode, String baseurl, String baseAppurl) {
		super();
		this.path = path;
		this.localFilePath = localFilePath;
		this.token = token;
		this.formatDateDecode = formatDateDecode;
		this.formatDateEncode = formatDateEncode;
		this.baseurl = baseurl;
		this.baseAppurl = baseAppurl;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLocalFilePath() {
		return localFilePath;
	}
	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFormatDateDecode() {
		return formatDateDecode;
	}
	public void setFormatDateDecode(String formatDateDecode) {
		this.formatDateDecode = formatDateDecode;
	}
	public String getFormatDateEncode() {
		return formatDateEncode;
	}
	public void setFormatDateEncode(String formatDateEncode) {
		this.formatDateEncode = formatDateEncode;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getBaseAppurl() {
		return baseAppurl;
	}
	public void setBaseAppurl(String baseAppurl) {
		this.baseAppurl = baseAppurl;
	}
	
	
	
	
}
