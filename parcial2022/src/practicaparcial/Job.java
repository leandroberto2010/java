package practicaparcial;

public abstract class Job {
	int id;
	String name;
	String description;
	double estimatedHours;
	double insuranceCost;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public double getInsuranceCost() {
		return insuranceCost;
	}
	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	
	
}
