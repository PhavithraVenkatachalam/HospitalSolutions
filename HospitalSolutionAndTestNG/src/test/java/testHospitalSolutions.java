import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testHospitalSolutions {
	
    public enum Location{
    	banglore,
    	coimbatore,
    	delhi;
    }
	
	@Test
	public void testHospitalSolutionsFuctionality()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.coimbatore.toString());
		Patient Ram =new Patient("P01","Ram",Location.coimbatore.toString(),"05/05/2021");
		Patient Promoth=new Patient("P02","Promoth",Location.coimbatore.toString(),"06/05/2021");
		Patient Rohith  =new Patient("P03","Rohith",Location.banglore.toString(),"04/05/2021");
		apollo.addPatientsToHashMap(Rohith,Rohith,Rohith,Promoth,Ram,Ram);
		String result=apollo.calculateBanglorePatientPercentHashMap(3);
		String[] percent=result.split("&");
		Assert.assertEquals(percent[1],"66.67");
	}

	
	@Test
	public void testAllOutsidePatients()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.banglore.toString());
		Patient Ram =new Patient("P01","Ram",Location.coimbatore.toString(),"05/05/2021");
		Patient Promoth=new Patient("P02","Promoth",Location.coimbatore.toString(),"06/05/2021");
		Patient Rohith  =new Patient("P03","Rohith",Location.coimbatore.toString(),"05/05/2021");
		apollo.addPatientsToHashMap(Rohith,Rohith,Rohith,Promoth,Ram,Ram);
		String result=apollo.calculateBanglorePatientPercentHashMap(3);
		String[] percent=result.split("&");
		
		Assert.assertEquals(percent[1],"0.0");
	}
	
	@Test
	public void testWithLocationUpperCase()
	{
		Hospital apollo=new Hospital("chinniya missions one","banglore");
		Patient Ram =new Patient("P01","Ram","TN","05/05/2021");
		Patient Promoth=new Patient("P02","Promoth","BANGLORE","06/05/2021");
		Patient Rohith  =new Patient("P03","Rohith","BANGLORE","05/05/2021");

		apollo.addPatientsToHashMap(Rohith,Rohith,Rohith,Promoth,Ram,Ram);
		String result=apollo.calculateBanglorePatientPercentHashMap(3);
		String[] percent=result.split("&");
		
		Assert.assertEquals(percent[1],"66.67");
	}
	
	@Test
	public void testWithLocationMixedCase()
	{
		Hospital apollo=new Hospital("chinniya missions one","banglore");
		Patient Ram =new Patient("P01","Ram","TN","05/05/2021");
		Patient Promoth=new Patient("P02","Promoth","BANgloRE","06/05/2021");
		Patient Rohith  =new Patient("P03","Rohith","banGLORE","05/05/2021");
		apollo.addPatientsToHashMap(Rohith,Rohith,Rohith,Promoth,Ram,Ram);
		String result=apollo.calculateBanglorePatientPercentHashMap(3);
		String[] percent=result.split("&");
		
		Assert.assertEquals(percent[1],"66.67");
	}
	
	@Test
	public void testWithZeroPatientRecord()
	{
		Hospital CMC_hospital=new Hospital("CMC Hospital","banglore");
		Assert.assertFalse(CMC_hospital.addPatientsToHashMap());
	}
	
}
