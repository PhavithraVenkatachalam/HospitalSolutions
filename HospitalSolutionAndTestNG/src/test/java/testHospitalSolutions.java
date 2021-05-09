import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testHospitalSolutions {

	int tenure;
	Visit visit1,visit2,visit3,visit4;

	@BeforeClass
	public void Visits()
	{
		visit1=new Visit(LocalDate.parse("2021-05-01"),"Dr.Selvi");
		visit2=new Visit(LocalDate.parse("2021-05-03"),"Dr.Rani");
		visit3=new Visit(LocalDate.parse("2021-05-08"),"Dr.Sudhakar");
		visit4=new Visit(LocalDate.parse("2021-05-07"),"Dr.Prem");
		tenure=3;
	}

	@Test
	public void testPatientsWithOneVisit()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.coimbatore);
		Patient Ram =new Patient("P01","Ram",Location.banglore);
		Patient Promoth=new Patient("P02","Promoth",Location.coimbatore);
		Patient Rohith  =new Patient("P03","Rohith",Location.banglore);
		Ram.addVisitDates(visit3);
		Promoth.addVisitDates(visit4);
		Rohith.addVisitDates(visit4);
		apollo.addPatientsToList(Rohith,Ram,Promoth);

		double localPatientsActual=apollo.getLocalPatientPercent(3);
		double outsidePatientsActual=apollo.getOtherPatientPercentage(localPatientsActual);
		double localPatientsExpected=33.33;
		double outsidePatientsExpected=66.67;
		
		Assert.assertEquals(localPatientsActual,localPatientsExpected);
		Assert.assertEquals(outsidePatientsActual,outsidePatientsExpected);
	}


	@Test
	public void testAllOutsidePatients()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.coimbatore);
		Patient Ram =new Patient("P01","Ram",Location.banglore);
		Patient Promoth=new Patient("P02","Promoth",Location.banglore);
		Patient Rohith  =new Patient("P03","Rohith",Location.banglore);
		Ram.addVisitDates(visit1,visit2,visit3);
		Promoth.addVisitDates(visit1,visit2,visit3);
		Rohith.addVisitDates(visit1,visit3,visit4);
		apollo.addPatientsToList(Rohith,Ram,Promoth);
		
		double localPatientsActual=apollo.getLocalPatientPercent(3);
		double outsidePatientsActual=apollo.getOtherPatientPercentage(localPatientsActual);
		double localPatientsExpected=0.0;
		double outsidePatientsExpected=100.0;
		
		Assert.assertEquals(localPatientsActual,localPatientsExpected);
		Assert.assertEquals(outsidePatientsActual,outsidePatientsExpected);
	}
	

	@Test
	public void testAllPatientsWithMultipleVisit()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.coimbatore);
		Patient Ram =new Patient("P01","Ram",Location.banglore);
		Patient Promoth=new Patient("P02","Promoth",Location.coimbatore);
		Patient Rohith  =new Patient("P03","Rohith",Location.coimbatore);
		Ram.addVisitDates(visit1,visit2,visit3);
		Promoth.addVisitDates(visit1,visit2,visit3);
		Rohith.addVisitDates(visit1,visit3,visit4);
		apollo.addPatientsToList(Rohith,Ram,Promoth);
		
		double localPatientsActual=apollo.getLocalPatientPercent(3);
		double outsidePatientsActual=apollo.getOtherPatientPercentage(localPatientsActual);
		double localPatientsExpected=66.67;
		double outsidePatientsExpected=33.33;
		
		Assert.assertEquals(localPatientsActual,localPatientsExpected);
		Assert.assertEquals(outsidePatientsActual,outsidePatientsExpected);
	}

	@Test
	public void testLocalPatientsWithMultipleVisit()
	{
		Hospital apollo=new Hospital("chinniya missions one",Location.coimbatore);
		Patient Ram =new Patient("P01","Ram",Location.coimbatore);
		Patient Promoth=new Patient("P02","Promoth",Location.coimbatore);
		Patient Rohith  =new Patient("P03","Rohith",Location.coimbatore);
		Ram.addVisitDates(visit1,visit2,visit3);
		Promoth.addVisitDates(visit1,visit2,visit3);
		Rohith.addVisitDates(visit1,visit3,visit4);
		apollo.addPatientsToList(Rohith,Ram,Promoth);
		
		double localPatientsActual=apollo.getLocalPatientPercent(3);
		double outsidePatientsActual=apollo.getOtherPatientPercentage(localPatientsActual);
		double localPatientsExpected=100.0;
		double outsidePatientsExpected=0.0;
		
		Assert.assertEquals(localPatientsActual,localPatientsExpected);
		Assert.assertEquals(outsidePatientsActual,outsidePatientsExpected);
	}

	@Test
	public void testWithZeroPatientRecord()
	{
		Hospital CMC_hospital=new Hospital("CMC Hospital",Location.banglore);
		Assert.assertFalse(CMC_hospital.addPatientsToList());
	}

}
