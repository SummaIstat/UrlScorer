package it.istat.urlscorer;

/**
* @author  Donato Summa
*/
public class Firm {

	private String firmId = "";
	private String vatNumber = "";
	private String firmName = "";
	private String certifiedEmail = "";
	private String municipality = "";
	private String provinceAbbreviation = "";
	private String telephonePrefix = "";
	private String telephoneNumber = "";
	private String website = "";
	private String zipCode = "";
	
	
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getCertifiedEmail() {
		return certifiedEmail;
	}
	public void setCertifiedEmail(String certifiedEmail) {
		this.certifiedEmail = certifiedEmail;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getProvinceAbbreviation() {
		return provinceAbbreviation;
	}
	public void setProvinceAbbreviation(String provinceAbbreviation) {
		this.provinceAbbreviation = provinceAbbreviation;
	}
	public String getTelephonePrefix() {
		return telephonePrefix;
	}
	public void setTelephonePrefix(String telephonePrefix) {
		this.telephonePrefix = telephonePrefix;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
