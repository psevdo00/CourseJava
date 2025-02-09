package pozdravlator;

import java.time.LocalDate;

public class Birthday {

	private int day, month, year;
	private String fullName;
	
	public Birthday(int day, int month, int year, String fullName) {
		
		this.day = day;
		this.month = month;
		this.year = year;
		this.fullName = fullName;
		
	}
	
	public String fullInfoBirthday() {
		
		String dayStr;
        String monthStr;
        
        if (this.day < 10) {
            dayStr = "0" + this.day;
        } else {
            dayStr = String.valueOf(this.day);
        }
        
        if (this.month < 10) {
            monthStr = "0" + this.month;
        } else {
            monthStr = String.valueOf(this.month);
        }
		
        return fullName + ". День рождения: " + dayStr + "." + monthStr;
		
	}
	
	public String fullInfoBirthdayInFile() {
		
		return day + " " + month + " " + year + " " + fullName;
		
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
