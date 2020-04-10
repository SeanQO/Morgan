package model;
public class Date{
	private final static int CURRENT_DAY = 01;
	private final static int CURRENT_MONTH = 04;
	private final static int CURRENT_YEAR = 2020;
	private int day;
	private int month;
	private int year;

	/**
		*Name:Date
		*Date class constructor.
	*/
	public  Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
		*Name:getDay
		*returns the day.
		*@return day.
	*/
	public int getDay() {
		return day;
	}

	/**
		*Name:setDay
		*recives and sets the day an integer value.
		*@param day must me in between one and 31, day > 0 && day <= 31.
	*/
	public void setDay(int day) {
		this.day = day;
	}


	/**
		*Name:getMonth
		*returns the month.
		*@return month.
	*/
	public int getMonth() {
		return month;
	}


	/**
		*Name:setMonth
		*recives and sets the month an integer value.
		*@param month must me in between one and twelve, month > 0 && day <= 12.
	*/
	public void setMonth(int month) {
		this.month = month;
	}


	/**
		*Name:getyear
		*returns the year.
		*@return year.
	*/
	public int getYear() {
		return year;
	}

	/**
		*Name:setYear
		*recives and sets the year an integer value.
		*@param year must me in between one and MAX_VALUE, year > 0 && day <= MAX_VALUE.
	*/
	public void setYear(int year) {
		this.year = year;
	}

	/**
		*Name: validate.
		*checks if the date given meets the requirements to be added as a expedition date, 
		*and then returns a trut value. valid date = true, invalid date = false.
		*@param date if it has a length of ten, it mut have numbers in the position (0,1,3,4,6,7,8,9)
		*<b> pre CURRENT_YEAR !=Null
		*<b> pre CURRENT_MONTH !=Null
		*<b> pre CURRENT_DAY !=Null
		*@return valid.
	*/	
	public static boolean validate(String date) {
		boolean valid = true;

		if (date.length() != 10) {
			valid = false;

		}else{
			int d = Integer.parseInt(date.substring(0,2));
			int m = Integer.parseInt(date.substring(3,5));
			int y = Integer.parseInt(date.substring(6,10));

			if (y < CURRENT_YEAR) {
				valid = false;

			}else if ((y == CURRENT_YEAR && m < CURRENT_MONTH) || m<1 || m>12 ) {
				valid = false;

			}else if ( (y == CURRENT_YEAR && m == CURRENT_MONTH && d <= CURRENT_DAY) ||  d<1 || d>31 ) {
				valid = false;
			}
		}	

		return valid;
	}	

}