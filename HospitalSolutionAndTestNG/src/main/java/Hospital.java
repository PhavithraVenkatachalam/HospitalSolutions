
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


public class Hospital {

	private String hospital_Name;
	private String hospital_Location;
	private HashMap<Patient,String> patientHashMap;

	//constructor which initializes all the data members
	public Hospital(String name,String location)
	{
		hospital_Name=name;
		hospital_Location=location;
		patientHashMap=new HashMap<>();

	}

	//adds patient objects to the HashMap with Patient Id as Value
	public boolean addPatientsToHashMap(Patient...args)
	{
		if(args.length!=0)
		{	
			Arrays.stream(args).forEach((patient)->
			{
				if(!patientHashMap.containsValue(patient.getPatientId()))
				{
					patientHashMap.put(patient,patient.getPatientId());
				}
			});

			System.out.println(patientHashMap.size());

			return true;
		}
		
     	return false;
	}


    //calculates the percentage of patients from banglore in the given tenure
	public String calculateBanglorePatientPercentHashMap(int tenure)
	{
		int localPatientsCount=0,totalRegistrationsInTenure=0;

		for(Patient p:patientHashMap.keySet())
		{
			if(calculateDateDifference(p.getPatientDateOfRegistration(),tenure))

			{
				if((p.getPatientLocation().toLowerCase()).equalsIgnoreCase(hospital_Location))
				{	
					localPatientsCount+=1;
				}
				totalRegistrationsInTenure+=1;
			}
		}
		
		double banglorePercent=(double)localPatientsCount/(double)totalRegistrationsInTenure*100;
		double otherPercent=100-banglorePercent;
		banglorePercent =Double.parseDouble(new DecimalFormat("##.##").format(banglorePercent));
		otherPercent =Double.parseDouble(new DecimalFormat("##.##").format(otherPercent));
		String returnString= String.valueOf(totalRegistrationsInTenure)+"&"+String.valueOf(banglorePercent)+"&"+String.valueOf(otherPercent);		
		return returnString;
	}

	//Check if the Patients registration date falls with the tenure
	private Boolean calculateDateDifference(String pDate,int tenure)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate patientRegDate = LocalDate.parse(pDate, dtf);

		Date date = new Date();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();	
		LocalDate todayDate = instant.atZone(defaultZoneId).toLocalDate();

		long daysBetween = ChronoUnit.DAYS.between(patientRegDate, todayDate);
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
