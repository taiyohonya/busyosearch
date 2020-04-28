package syainsearch;

import java.io.Serializable;

public class Employee implements Serializable {
	private String empId;

	private String empName;

	private int empAge;

	private String empSex;

	private String empAdress;

	private String empDepId;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpAdress() {
		return empAdress;
	}

	public void setEmpAdress(String empAdress) {
		this.empAdress = empAdress;
	}

	public String getEmpDepId() {
		return empDepId;
	}

	public void setEmpDepId(String empDepId) {
		this.empDepId = empDepId;
	}

}
