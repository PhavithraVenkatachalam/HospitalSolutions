import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

 enum Location{
	banglore,
	chennai,
	coimbatore;
 }

class Patient {	
	private String patient_Name;
	private String patient_ID;
	private Location patinet_Location;
	private List<Visit> patientVisitDate;
	
	
	public Patient(String ID,String name,Location location)
	{
		patient_ID=ID;
		patient_Name=name;
		patinet_Location=location;
		patientVisitDate=new ArrayList<>();
	}
	
	public String getPatientId()
	{
		return patient_ID;
	}
	
	public String getPatientName()
	{
		return patient_Name;
	}
	
	public Location getPatientLocation()
	{
		return patinet_Location;
	}
	
	public void addVisitDates(Visit...visits)
	{	
		Collections.addAll(patientVisitDate,visits);
	}
	
	public List<Visit> getVisitList()
	{
		return patientVisitDate;
	}
	
	
	public void patientTotalVisit()
	{
		System.out.println(patientVisitDate.size());
	}
	

}
