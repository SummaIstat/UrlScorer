package it.istat.urlscorer;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;

/**
* @author  Donato Summa
*/
public class ScoreCalculationStrategy {

	static Logger logger = Logger.getLogger(ScoreCalculationStrategy.class);
	
	public int linkScoreVectorCalculation(Document doc, Firm firm) {
		String vatNumber = firm.getVatNumber().toLowerCase();
		String firmName = firm.getFirmName().toLowerCase();
		String municipality = firm.getMunicipality().toLowerCase();
		String provinceAbbreviation = firm.getProvinceAbbreviation().toLowerCase();
		String telephonePrefix = firm.getTelephonePrefix().toLowerCase();
		String telephoneNumber = firm.getTelephoneNumber().toLowerCase();
		String zipCode = firm.getZipCode().toLowerCase();
		
		int score = 1000000;
		String pageTextualContent = doc.get("corpoPagina");
		//String pageTextualContent = doc.get("content");
		String title = doc.get("title");
		String metatagDescription = doc.get("metatagDescription");
		String metatagKeywords = doc.get("metatagKeywords");
		int linkPosition = Integer.parseInt(doc.get("codiceLink").trim());
		
		if (pageTextualContent != null){
			pageTextualContent = pageTextualContent.toLowerCase();
		}
		if (title != null){
			pageTextualContent = pageTextualContent + " " + title.toLowerCase();
		}
		if (metatagDescription != null){
			pageTextualContent = pageTextualContent + " " + metatagDescription.toLowerCase();
		}
		if (metatagKeywords != null){
			pageTextualContent = pageTextualContent + " " + metatagKeywords.toLowerCase();
		}
		
		String url = doc.get("url");
		//logger.debug("pageTextualContent = " + pageTextualContent);
		logger.debug("url = " + url);
		
		//============================================
		// I'm counting the numbers from LEFT to RIGHT
		//============================================
		
		//============================
		// NUMBER 1 ==> TELEPHONE
		//============================
		
		//checking the presence of the telephone number in the textual content of the page
		if (pageTextualContent != null){
			if (telephoneNumber != null){
				
				boolean telephonePrefixAndNumber = false;
				List<String> telephoneNumbersList = new ArrayList<String>();
				
				if(telephonePrefix != null){
					telephonePrefixAndNumber = true;
				} 
				
				if (telephonePrefixAndNumber == true){
					telephoneNumbersList.add(telephonePrefix.trim() + telephoneNumber.trim());
					telephoneNumbersList.add(telephonePrefix.trim() + " " + telephoneNumber.trim());
					telephoneNumbersList.add(telephonePrefix.trim() + "-" + telephoneNumber.trim());
					telephoneNumbersList.add(telephonePrefix.trim() + " - " + telephoneNumber.trim());
					telephoneNumbersList.add(telephonePrefix.trim() + "/" + telephoneNumber.trim());
					telephoneNumbersList.add(telephonePrefix.trim() + " / " + telephoneNumber.trim());
				}else{
					telephoneNumbersList.add(" " + telephoneNumber.trim() + " ");				
				}
				
				for(String number : telephoneNumbersList){
					if(pageTextualContent.contains(number)){
						score = score + 1000000; 
						break;
					}
				} 		
			}
		}
				
		//================================
		// NUMBER 2 ==> URL IN SIMPLE FORM
		//================================
		
		//checking the "simplicity" of the url ("www.name.it" is simple,   "www.name.it/something/somethingelse/page.html" is not simple)
		url = url.replaceAll("http://", "");
		String[] partsOfUrl = url.split("/");
		if ( partsOfUrl.length <= 1 ){
			score = score + 100000;
		}
		
		//=================================
		// NUMBER 3 ==> LINK POSITION
		//=================================
		
		//additional score depending on the position of the link
		// FROM		 0 if link position is 10     		TO    		9000 if link position is 1
		if (linkPosition <= 10){
			score = score + (10000 * (10 - linkPosition));
		}
						
		if (pageTextualContent == null){
			return score;
		}
		
		//=================================
		// NUMBER 4 ==> VAT NUMBER
		//=================================
		
		//checking the presence of the vat number in the textual content of the page	
		if(pageTextualContent.contains(vatNumber)){
			score = score + 1000; 
		}
			
		//=================================
		// NUMBER 5 ==> MUNICIPALITY
		//=================================
		
		//checking the presence of the municipality in the textual content of the page
		if (pageTextualContent.contains(municipality)){
			score = score + 100;
		}
		
		//=================================
		// NUMBER 6 ==> PROVINCE 
		//=================================
		
		//checking the presence of the province in the textual content of the page
		if (pageTextualContent.contains(" " + provinceAbbreviation + " ")){
			score = score + 10;
		}else if (pageTextualContent.contains("(" + provinceAbbreviation + ")")){
			score = score + 10;
		}else if (pageTextualContent.contains(Utils.getProvinceName(provinceAbbreviation).toLowerCase())){
			score = score + 10;
		}
		
		//=================================
		// NUMBER 7 ==> ZIP CODE 
		//=================================
		
		//checking the presence of the province in the textual content of the page
		if (pageTextualContent.contains(" " + zipCode + " ")){
			score = score + 1;
		}else if (pageTextualContent.contains("-" + zipCode + " ")){
			score = score + 1;
		}				
		
		return score;
	}
	
	public int linkScoreCalculation(String scoreVectorString) {
		int score = 0;
				
		String telephoneDigit = scoreVectorString.substring(0, 1);
		String simpleUrlDigit = scoreVectorString.substring(1, 2);
		String linkPositionDigit = scoreVectorString.substring(2, 3);
		String vatNumberDigit = scoreVectorString.substring(3, 4);
		String municipalityDigit = scoreVectorString.substring(4, 5);
		String provinceDigit = scoreVectorString.substring(5, 6);
		String zipCodeDigit = scoreVectorString.substring(6, 7);
				
		if (telephoneDigit.equals("2")){
			score = score + Conf.getTelephoneScore();
		}
		if (simpleUrlDigit.equals("1")){
			score = score + Conf.getSimpleUrlScore();
		}
		if (linkPositionDigit.equals("8")){
			score = score + Conf.getLinkPositionScore();
		}
		if (linkPositionDigit.equals("9")){
			score = score + Conf.getLinkPositionScore();
		}
		if (vatNumberDigit.equals("1")){
			score = score + Conf.getVatNumberScore();
		}
		if (municipalityDigit.equals("1")){
			score = score + Conf.getMunicipalityScore();
		}
		if (provinceDigit.equals("1")){
			score = score + Conf.getProvinceScore();
		}
		if (zipCodeDigit.equals("1")){
			score = score + Conf.getZipCodeScore();
		}
		
		return score;
	}
	
}
