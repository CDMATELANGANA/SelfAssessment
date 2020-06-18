package com.mars.cdma.gov.Controller;

public class Demandyear {

	public static void main(String[] args) {
		
		//String demandYear="2019-2020-1";
		String demandYear="2019-2020-2";
		String PaidfromDate="";
		String PaidtoDate="";
		int int1Year = new Integer(demandYear.substring(0,4)).intValue();
		int int2Year = new Integer(demandYear.substring(5,9)).intValue();
		System.out.println("intYear @@@ "+int1Year);
		int int1stPeriod = new Integer(demandYear.substring(10)).intValue();
		System.out.println("int1Period ####  "+int1stPeriod);
		 int intFirstPeriod=int1stPeriod;
		System.out.println("intFirstPeriod@@@@@ "+intFirstPeriod);
		
		String DmndFirstPeriod= demandYear.substring(0,10);
		DmndFirstPeriod=DmndFirstPeriod+intFirstPeriod;
		
		
		if(int1stPeriod == 1) 
		{
			PaidfromDate = int1Year+"-04-01";
			PaidtoDate= int1Year+"-09-30";
		System.out.println("PaidfromDate "+ PaidfromDate);
		System.out.println("PaidtoDate "+ PaidtoDate);
		}
		else
		{
			PaidfromDate = int1Year+"-10-01";
			PaidtoDate=int2Year+"-03-31";
		System.out.println("PaidfromDate "+ PaidfromDate);
		System.out.println("PaidtoDate "+ PaidtoDate);
		}
		
		
	}

}
