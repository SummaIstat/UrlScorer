package it.istat.urlscorer;

/**
* @author  Donato Summa
*/
public class Conf {

	public static final String linksFileName = "linksScores";

	private static int telephoneScore = 156;
	private static int simpleUrlScore = 158;
	private static int linkPositionScore = 174;
	private static int vatNumberScore = 188;
	private static int municipalityScore = 163;
	private static int provinceScore = 161;
	private static int zipCodeScore = 0;
	private static int firmsInfoNumCols = 9;
	
	
	public static int getTelephoneScore() {
		return telephoneScore;
	}
	public static void setTelephoneScore(int telephoneScore) {
		Conf.telephoneScore = telephoneScore;
	}
	public static int getSimpleUrlScore() {
		return simpleUrlScore;
	}
	public static void setSimpleUrlScore(int simpleUrlScore) {
		Conf.simpleUrlScore = simpleUrlScore;
	}
	public static int getLinkPositionScore() {
		return linkPositionScore;
	}
	public static void setLinkPositionScore(int linkPositionScore) {
		Conf.linkPositionScore = linkPositionScore;
	}
	public static int getVatNumberScore() {
		return vatNumberScore;
	}
	public static void setVatNumberScore(int vatNumberScore) {
		Conf.vatNumberScore = vatNumberScore;
	}
	public static int getMunicipalityScore() {
		return municipalityScore;
	}
	public static void setMunicipalityScore(int municipalityScore) {
		Conf.municipalityScore = municipalityScore;
	}
	public static int getProvinceScore() {
		return provinceScore;
	}
	public static void setProvinceScore(int provinceScore) {
		Conf.provinceScore = provinceScore;
	}
	public static int getZipCodeScore() {
		return zipCodeScore;
	}
	public static void setZipCodeScore(int zipCodeScore) {
		Conf.zipCodeScore = zipCodeScore;
	}
	public static String getLinksfilename() {
		return linksFileName;
	}
	public static int getFirmsInfoNumCols() {
		return firmsInfoNumCols;
	}
	public static void setFirmsInfoNumCols(int firmsInfoNumCols) {
		Conf.firmsInfoNumCols = firmsInfoNumCols;
	}
	
}
