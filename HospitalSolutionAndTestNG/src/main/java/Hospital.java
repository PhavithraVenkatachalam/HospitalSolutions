
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Hospital {

	private String hospital_Name;
	private Location hospital_Location;
	private List<Patient> patientList;

	//constructor which initializes all the data members
	public Hospital(String name,Location location)
	{
		hospital_Name=name;
		hospital_Location=location;
		patientList=new ArrayList<>();

	}

	//adds patient objects to the ArrayList
	public boolean addPatientsToList(Patient... args)
	{
		if(args.length!=0)
		{	
			Arrays.stream(args).forEach((patient)-> {patientList.add(patient);} );
			return true;
		}
		return false;
	}


	//calculates the percentage of patients from banglore in the given tenure
	public double getLocalPatientPercent(int tenure)
	{	
		double localPercent=calculateLocalPatientsPercent(patientList,tenure);
		localPercent =Double.parseDouble(new DecimalFormat("##.##").format(localPercent));		
		return localPercent;
	}

	//returns the percentage of local patients
	public double getOtherPatientPercentage(double localPercent)
	{
		double otherPercent=Double.parseDouble(new DecimalFormat("##.##").format(100-localPercent));
		return otherPercent;
	}

	//calculates the local patient percentage
	public double calculateLocalPatientsPercent(List<Patient> patientsInfo,int tenure)
	{

		List<Patient> patientsInTenure=patientsInfo.stream()
				.filter(patient -> hasPatientVisitedInGivenTenure(patient,tenure)==true)
				.collect(Collectors.toList());

		int localPatientsInTenure=(int) patientsInTenure.stream()
				.filter(patient -> isPatientLocal(patient)==true)
				.count();

		return (double)localPatientsInTenure/(double)patientsInTenure.size()*100;
	}

	//checks if the patient has visited the hospital in the given tenure
	public boolean hasPatientVisitedInGivenTenure(Patient patient,int tenure)
	{
		List<Visit> visits=patient.getVisitList();
		Optional<Visit> hasVisited=visits.stream().filter(v -> (calculateDateDifference(v.getVisitDate(),tenure)==true)).findAny();
		return hasVisited.isPresent();
	}


	//checks if the patient's location is same as hospital
	public boolean isPatientLocal(Patient patient)
	{
		if((patient.getPatientLocation()).equals(hospital_Location))
		{	
			return true;
		}
		else 
			return false;
	}



	//Check if the Patients visit date falls with the tenure
	private boolean calculateDateDifference(LocalDate pDate,int tenure)
	{
		Date date = new Date();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();	
		LocalDate todayDate = instant.atZone(defaultZoneId).toLocalDate();

		long daysBetween = ChronoUnit.DAYS.between(pDate, todayDate);
		if(daysBetween<0)
		{
			daysBetween=-daysBetween;
		}
		if(daysBetween<=tenure)
		{
			return true;
		}
		return false;
	}
}
