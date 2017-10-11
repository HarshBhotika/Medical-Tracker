/**
	 * Medical Tracker Application
	 * By: Harsh Bhotika, Nilesh Nathani & Dhaval Pathak
	 * W.I.E.E.C.T. B.E. Computers Final Year Project
	 */
	
	
public class MedicalTracker {

	private static Login sLogin;
	private static Params sParams;
	private static DbConnector sDb;
	private static Questionnaire sQ;
	private static Calculator sCalc;
	private static Result sResult;
	
	public static String sName;
	private static int sAge;
	private static int sSex;
	private static int sCpain;
	private static float sRbp;
	private static float sChol;
	private static int sBsugar;
	private static float sMaxHR;
	public static float yesPercent;
	public static int calcResult;
	public static int resultType;
	
	public static void main(String[] args) {
		
		sLogin = new Login();
		try{
			sLogin.open();
		}
		catch(Exception eLogin){
			eLogin.printStackTrace();
		}
		
		if(sLogin.success==true){
			sParams = new Params();
			try{
				sParams.open();
			}
			catch(Exception eParams){
				eParams.printStackTrace();
			}
		}
		
		sName = sParams.pName;
		sAge = sParams.pAge;
		sSex = sParams.pSex;
		sCpain = sParams.pPain;
		sRbp = sParams.pRbp;
		sChol = sParams.pChol;
		sBsugar = sParams.pSugar;
		sMaxHR = sParams.pHrate;
		
		sDb = new DbConnector();
		try{
			sDb.dbconnect();
		}
		catch(Exception eDB){
			eDB.printStackTrace();
		}
		
		sCalc = new Calculator();
		calcResult = sCalc.begin(sAge, sSex, sCpain, sRbp, sChol, sBsugar, sMaxHR);
		yesPercent = Calculator.yPerc;
		
		if(sParams.track==true){
			resultType=1;
			sResult = new Result();
			sResult.open();
		}
		if(sParams.hform==true){
			resultType=2;
			sQ = new Questionnaire();
			try{
				sQ.open();
			}
			catch(Exception eQ){
				eQ.printStackTrace();
			}
		}
		
	}
	
}
