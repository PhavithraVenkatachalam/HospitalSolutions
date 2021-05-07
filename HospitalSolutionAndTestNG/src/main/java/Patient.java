
 class Patient {	
	private String patient_Name;
	private String patient_ID;
	private String patinet_Location;
	private String dateOfRegestration;
	
	public Patient(String ID,String name,String location,String registrationDate)
	{
		patient_ID=ID;
		patient_Name=name;
		patinet_Location=location;
		dateOfRegestration=registrationDate;
		
	}
	public String getPatientId()
	{
		return patient_ID;
	}
	
	public String getPatientName()
	{
		return patient_Name;
	}
	
	public String getPatientLocation()
	{
		return patinet_Location;
	}
	
	public String getPatientDateOfRegistration()
	{
		return dateOfRegestration;
	}

}
