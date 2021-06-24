package entity;

public class Disease {
	
	private int id;
	private String name;
	private String symptom1;
	private String symptom2;
	private String symptom3;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymptom1() {
		return symptom1;
	}
	public void setSymptom1(String symptom1) {
		this.symptom1 = symptom1;
	}
	public String getSymptom2() {
		return symptom2;
	}
	public void setSymptom2(String symptom2) {
		this.symptom2 = symptom2;
	}
	public String getSymptom3() {
		return symptom3;
	}
	public void setSymptom3(String symptom3) {
		this.symptom3 = symptom3;
	}
	
	
	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", symptom1=" + symptom1 + ", symptom2=" + symptom2
				+ ", symptom3=" + symptom3 + "]";
	}
	
	public Disease(int id, String name, String symptom1, String symptom2, String symptom3) 
	{
		super();
		this.id = id;
		this.name = name;
		this.symptom1 = symptom1;
		this.symptom2 = symptom2;
		this.symptom3 = symptom3;
	}
	
	
	

}
