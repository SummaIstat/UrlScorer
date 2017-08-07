package it.istat.urlscorer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
* @author  Donato Summa
*/
public class Utils {

	static Logger logger = Logger.getLogger(Utils.class);
	private static Map<String,String> provinceMap = new HashMap<String, String>();

	public static String getDateTimeAsString(){
		Date dateTime;
		String now = "";
		DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		dateTime = Calendar.getInstance().getTime();        
        now = formatter.format(dateTime);
        return now;
	}
	
	public static Map<String,String> loadProvinceListFromFile(String provinceFilePath) {
		
		try {
			
			FileInputStream fis = new FileInputStream(provinceFilePath);
			InputStream is = fis;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine;
			String delimiter = "\t";
			
			while ((strLine = br.readLine()) != null) {
			
				String[] tokens = strLine.split(delimiter,2);
								
				if(tokens.length == 2){
					provinceMap.put(tokens[0].toLowerCase(), tokens[1]);
				}
				
			}
			
			br.close();
			is.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
			logger.error("Error: " + e.getMessage());
		}
		return provinceMap;	
	}
		
	public static List<Firm> getFirmsOrderedListFromFile(String firmsInfoFilePath) {
		List<Firm> firmsOrderedList = new ArrayList<Firm>();
		try {
			
			FileInputStream fis = new FileInputStream(firmsInfoFilePath);
			InputStream is = fis;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine;
			Firm firm;
			
			br.readLine(); // avoid the first line with headers
			while ((strLine = br.readLine()) != null) {
		
				String delimiter = "\t";
				String[] tokens = strLine.split(delimiter,-2);
								
				if(tokens.length <= Conf.getFirmsInfoNumCols()){
					firm = new Firm();
					firm.setFirmId(tokens[0]);
					firm.setVatNumber(tokens[1]);
					firm.setFirmName(tokens[2]);
					firm.setCertifiedEmail(tokens[3]);
					firm.setMunicipality(tokens[4]);
					firm.setProvinceAbbreviation(tokens[5]);
					firm.setTelephonePrefix(tokens[6]);
					firm.setTelephoneNumber(tokens[7]);
					firm.setZipCode(tokens[8]);
					firm.setWebsite("");
					firmsOrderedList.add(firm);
				}
				
			}
			
			br.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
			logger.error("Error: " + e.getMessage());
		}
		return firmsOrderedList;
	}
	
	public static String getProvinceName(String provinceAbbreviation) {
		
		return provinceMap.get(provinceAbbreviation);		
	}
		
	public static boolean isAValidFile(String filePathString) {
		File f = new File(filePathString);
		if(f.exists() && !f.isDirectory()) { 
			return true;
		}
		return false;
	}
    
    public static boolean isAValidDirectory(String dirPathString) {
		File f = new File(dirPathString);
		if(f.exists() && f.isDirectory()) { 
			return true;
		}
		return false;
	}
	
}

	