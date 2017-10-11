//import java.io.*;
import java.sql.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception 
	{
		
		Scanner s=new Scanner(System.in);
	Connection connection = null;
	//Statement statement = null;
	float  total=0,ab=0,pr=0,CountPA1=0,CountPA2=0,CountPA3=0,CountPA4=0,CountPA5=0,CountPA6=0,CountAA1=0,CountAA2=0,CountAA3=0,CountAA4=0,CountAA5=0,CountAA6=0;
	 float  CountP0=0,CountP1=0,CountA0=0,CountA1=0;
	   float CountCP1=0,CountCP2=0,CountCP3=0,CountCP4=0,CountCA1=0,CountCA2=0,CountCA3=0,CountCA4=0;
	   float CountPBPN=0,CountPBPP=0,CountPBPH1=0,CountPBPH2=0,CountPBPHC=0,CountABPN=0,CountABPP=0,CountABPH1=0,CountABPH2=0,CountABPHC=0;
	   float CountPSN=0,CountPSBH=0,CountPSH=0,CountASN=0,CountASBH=0,CountASH=0;
	   float CountPFS0=0,CountPFS1=0,CountAFS0=0,CountAFS1=0;
	   float CountPVL=0,CountPL=0,CountPI=0,CountPH=0,CountAVL=0,CountAL=0,CountAI=0,CountAH=0;
	ResultSet resultSet = null;
	//ResultSetMetaData metaData = null;
	 
	float age,sex,chestpain,fastingsugar,resting_bp,serum,thalach;
	System.out.println("Enter The Data :Age");
	age=s.nextFloat();
	System.out.println("Enter The Data : Sex 0:Female 1:Male");
	sex=s.nextFloat();
	System.out.println("Enter The Data :Chest pain\n 1:typical type 1 angina \n 2:typical type angina \n 3:non-angina pain \n 4:asymptomatic");
	chestpain=s.nextFloat();
	System.out.println("Enter The Data :Resting Blood Pressure:");
	resting_bp=s.nextFloat();
	System.out.println("Enter The Data :Serum Cholestoral in mg/dl");
	serum=s.nextFloat();
	System.out.println("Enter The Data :Fasting Blood Sugar \n 1:>120mg/dl.\n 0:<120 mg/dl");
	fastingsugar=s.nextFloat();
	System.out.println("Enter The Data :Maximum heart rate achieved");
	thalach=s.nextFloat();
	try
	 
	{
	 
	Class.forName("com.mysql.jdbc.Driver");
	 
	connection = DriverManager.getConnection("jdbc:mysql://localhost/medicaltracker" ,"root","");
	 

	System.out.println("Connection Established");
	Statement sest = connection.createStatement();
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart");
	while(resultSet.next())
    {
		total=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where result=1");
	while(resultSet.next())
    {
		ab=resultSet.getInt("COUNT(*)");
    }
	
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where result=2");
	while(resultSet.next())
    {
		pr=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 20 and 30 and result=2");
	while(resultSet.next())
    {
		CountPA1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 20 and 30 and result=1");
	while(resultSet.next())
    {
		CountAA1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 31 and 40 and result=2");
	while(resultSet.next())
    {
		CountPA2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 31 and 40 and result=1");
	while(resultSet.next())
    {
		CountAA2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 41 and 50 and result=2");
	while(resultSet.next())
    {
		CountPA3=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 41 and 50 and result=1");
	while(resultSet.next())
    {
		CountAA3=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 51 and 60 and result=2");
	while(resultSet.next())
    {
		CountPA4=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 51 and 60 and result=1");
	while(resultSet.next())
    {
		CountAA4=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 61 and 70 and result=2");
	while(resultSet.next())
    {
		CountPA5=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 61 and 70 and result=1");
	while(resultSet.next())
    {
		CountAA5=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 71 and 80 and result=2");
	while(resultSet.next())
    {
		CountPA6=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where age between 71 and 80 and result=1");
	while(resultSet.next())
    {
		CountAA6=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where sex=0 and  result=2");
	while(resultSet.next())
    {
		CountP0=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where sex=1 and  result=2");
	while(resultSet.next())
    {
		CountP1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where sex=0 and  result=1");
	while(resultSet.next())
    {
		CountA0=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where sex=1 and  result=1");
	while(resultSet.next())
    {
		CountA1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=1 and  result=2");
	while(resultSet.next())
    {
		CountCP1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=1 and  result=1");
	while(resultSet.next())
    {
		CountCA1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=2 and  result=2");
	while(resultSet.next())
    {
		CountCP2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=2 and  result=1");
	while(resultSet.next())
    {
		CountCA2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=3 and  result=2");
	while(resultSet.next())
    {
		CountCP3=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=3 and  result=1");
	while(resultSet.next())
    {
		CountCA3=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=4 and  result=2");
	while(resultSet.next())
    {
		CountCP4=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where chest_pain=4 and  result=1");
	while(resultSet.next())
    {
		CountCA4=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 0 and 80 and result=2");
	while(resultSet.next())
    {
		CountPBPN=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 0 and 80 and result=1");
	while(resultSet.next())
    {
		CountABPN=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 81 and 89 and result=2");
	while(resultSet.next())
    {
		CountPBPP=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 81 and 89 and result=1");
	while(resultSet.next())
    {
		CountABPP=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 90 and 99 and result=2");
	while(resultSet.next())
    {
		CountPBPH1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 90 and 99 and result=1");
	while(resultSet.next())
    {
		CountABPH1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 100 and 110 and result=2");
	while(resultSet.next())
    {
		CountPBPH2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP between 100 and 110 and result=1");
	while(resultSet.next())
    {
		CountABPH2=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP > 110 and result=1");
	while(resultSet.next())
    {
		CountPBPHC=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where resting_BP > 110 and result=1");
	while(resultSet.next())
    {
		CountABPHC=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c between 0 and 200 and result=2");
	while(resultSet.next())
    {
		CountPSN=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c between 0 and 200 and result=1");
	while(resultSet.next())
    {
		CountASN=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c between 201 and 239 and result=2");
	while(resultSet.next())
    {
		CountPSBH=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c between 201 and 239 and result=1");
	while(resultSet.next())
    {
		CountASBH=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c > 239 and result=2");
	while(resultSet.next())
    {
		CountPSH=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where serum_c > 239 and result=1");
	while(resultSet.next())
    {
		CountASH=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where fasting_sugar=0 and result=2");
	while(resultSet.next())
    {
		CountPFS0=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where fasting_sugar=0 and result=1");
	while(resultSet.next())
    {
		CountAFS0=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where fasting_sugar=1 and result=2");
	while(resultSet.next())
    {
		CountPFS1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where fasting_sugar=1 and result=1");
	while(resultSet.next())
    {
		CountAFS1=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach  between 0 and 100 and result=2");
	while(resultSet.next())
    {
		CountPVL=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach  between 0 and 100 and result=1");
	while(resultSet.next())
    {
		CountAVL=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach between 101 and 160 and result=2");
	while(resultSet.next())
    {
		CountPL=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach  between 101 and 160 and result=1");
	while(resultSet.next())
    {
		CountAL=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach  between 161 and 200 and result=2");
	while(resultSet.next())
    {
		CountPI=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach  between 161 and 200 and result=1");
	while(resultSet.next())
    {
		CountAI=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach >200 and result=2");
	while(resultSet.next())
    {
		CountPH=resultSet.getInt("COUNT(*)");
    }
	resultSet = sest.executeQuery("SELECT COUNT(*) FROM heart where thalach >200 and result=1");
	while(resultSet.next())
    {
		 CountAH=resultSet.getInt("COUNT(*)");
    }
	
	

	 }
	 catch(SQLException sqlException)
	 
	{
	 
	sqlException.printStackTrace();
	 
	System.exit(1);
	 
	}
	 
	 
	 
	 //calculations
	 float yes=1,no=1;
	 float Pabs=1,Ppres=1;
	 //RESULT
	 Pabs=ab/total;;
	 Ppres=pr/total;
	

	 //age
	 if(age<=30)
	 {
		yes=Ppres*(CountPA1/pr);
		no=Pabs*(CountAA1/ab);	
	 }
	 else if(age>30 &&  age<=40)
	 {
		 yes=Ppres*(CountPA2/pr);
		no=Pabs*(CountAA2/ab);	
	 }
	 else if(age>41 &&  age<=50)
	 {
		 yes=Ppres*(CountPA3/pr);
		no=Pabs*(CountAA3/ab);	
	 } 
	 else if(age>51 &&  age<=60)
	 {
		 yes=Ppres*(CountPA4/pr);
		no=Pabs*(CountAA4/ab);	
	 } 
	 else if(age>61 &&  age<=70)
	 {
		 yes=Ppres*(CountPA5/pr);
		no=Pabs*(CountAA5/ab);	
	 }
	 else if(age>71 &&  age<=80)
	 {
		 yes=Ppres*(CountPA6/pr);
		no=Pabs*(CountAA6/ab);	
	 }
	 //sex
	 if(sex==0){
		yes=yes*(CountP0/pr);
		no=no*(CountA0/ab);
	 }
	 else
	 {
		 yes=yes*(CountP1/pr);
		 no=no*(CountA1/ab);
	 }
	 //chest_pain
	 if(chestpain==1){
		 yes=yes*(CountCP1/pr);
		 no=no*(CountCA1/ab);
		 }
	 else  if(chestpain==2){
		 yes=yes*(CountCP2/pr);
		 no=no*(CountCA2/ab);
		 }
	 if(chestpain==3){
		 yes=yes*(CountCP3/pr);
		 no=no*(CountCA3/ab);
		 }
	 if(chestpain==4){
		 yes=yes*(CountCP4/pr);
		 no=no*(CountCA4/ab);
		 }
	 //blood pressure
	 if(resting_bp<80)
	 {
		 yes=yes*(CountPBPN/pr);
		 no=no*(CountABPN/ab);
		 
	 }
	 else if(resting_bp>=80 && resting_bp<=89)
	 {
		 yes=yes*(CountPBPP/pr);
		 no=no*(CountABPP/ab);
		 
	 }
	 else if(resting_bp>=90 && resting_bp<=99)
	 {
		 yes=yes*(CountPBPH1/pr);
		 no=no*(CountABPH1/ab);
		 
	 }
	 else if(resting_bp>=100 && resting_bp<=110)
	 {
		 yes=yes*(CountPBPH2/pr);
		 no=no*(CountABPH2/ab);
		 
	 }
	 else if(resting_bp>110)
	 {
		 yes=yes*(CountPBPHC/pr);
		 no=no*(CountABPHC/ab);
		 
	 }
	 //serum
	 if(serum<200)
	 {
		 yes=yes*(CountPSN/pr);
		 no=no*(CountASN/ab);
		 
	 }
	 else if(serum>=200 && serum<=239)
	 {
		 yes=yes*(CountPSBH/pr);
		 no=no*(CountASBH/ab);
		 
	 }
	 else if(serum>=240)
	 {
		 yes=yes*(CountPSH/pr);
		 no=no*(CountASH/ab);
		 
	 }
	 //fasting sugar
	 if(fastingsugar==0){
			yes=yes*(CountPFS0/pr);
			no=no*(CountAFS0/ab);
		 }
		 else
		 {
			 yes=yes*(CountPFS1/pr);
			 no=no*(CountAFS1/ab);
		 }
	 //thalach
	 if(thalach<=100)
	 {
		 yes=yes*(CountPVL/pr);
		 no=no*(CountAVL/ab);
		 
	 }
	 else if(thalach>=101 && thalach<=160)
	 {
		 yes=yes*(CountPL/pr);
		 no=no*(CountAL/ab);
		 
	 }
	 else if(thalach>=161 && thalach<=200)
	 {
		 yes=yes*(CountPI/pr);
		 no=no*(CountAI/ab);
		 
	 }
	 else if(thalach>200)
	 {
		 yes=yes*(CountPH/pr);
		 no=no*(CountAH/ab);
		 
	 }
	 
	 
	 //COMPARING
	 float probtotal=yes+no;
	 float yespercent=(yes/probtotal)*100;
	 float nopercent=(no/probtotal)*100;
	 System.out.println("Current Status:");
	 if(yes>no)
	 {
		 System.out.println("Result:2-Has HEART disease");
	 }
	 else if(yes<no)
	 {
		 System.out.println("Result:1-NO HEART disease");
	 }
	 else
	 {
		 System.out.println("on border");
	 }
	 System.out.println(yespercent+"% HEART RISK ");
	 
	 
	 
	} 

}

