import java.time.LocalDate;

public class Visit {

	private LocalDate date;
	private String doctorName;
	
	public Visit(LocalDate date,String doctorName)
	{
		this.date=date;
		this.doctorName=doctorName;
	}
	
	public LocalDate getVisitDate()
	{
		return date;
	}
	
	public String getDoctorName()
	{
		return doctorName;
	}
}
