package org.fai.localUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ReadTaxanomyDetails {

	static List<Map<String, String>> listOfAllTests = new ArrayList<>();
	static Map<String,String> map = new HashMap<>();
		
	public static Map<String,String> getExtractiondetails(String catName,String docTypeName) {
		
		if(listOfAllTests.isEmpty()) {
			listOfAllTests= GetTaxanomyDetails.getTaxDetails();
			}
			
			List<Map<String, String>> listOfExecutableTests= new ArrayList<>();
			
			for(int i=0; i<listOfAllTests.size();i++) {
				
				
				
				if(catName.contains(listOfAllTests.get(i).get("Customer Category"))&& listOfAllTests.get(i).get("Customer SubCategory").equalsIgnoreCase(docTypeName)) {

					listOfExecutableTests.add(listOfAllTests.get(i));
					map = listOfAllTests.get(i);
				}

			}
			System.out.println(map.get("External Tags"));
			System.out.println("Name "+map.get("Applicant Last Name"));
			return map;
		
	}
	
	
}
