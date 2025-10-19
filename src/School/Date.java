package School;

/**
 * This class models a simple date with year, month, and day components.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class Date implements Orderable, Comparable<Date> {

    private final int year;
    private final int month;
    private final int day;
    private static final int CURRENT_YEAR = 2025;
    private static final int MIN_YEAR = 1;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;

    // Month Numbers
    private static final int FEBRUARY = 2;
    private static final int APRIL = 4;
    private static final int JUNE = 6;
    private static final int SEPTEMBER = 9;
    private static final int NOVEMBER = 11;

    // Day Counts
    private static final int DAYS_IN_LEAP_FEB = 29;
    private static final int DAYS_IN_COMMON_FEB = 28;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int DAYS_IN_LONG_MONTH = 31;

    // Leap Year Divisors
    private static final int LEAP_YEAR_DIVISOR_4 = 4;
    private static final int LEAP_YEAR_DIVISOR_100 = 100;
    private static final int LEAP_YEAR_DIVISOR_400 = 400;

    // Month Codes
    final static int JANUARY_CODE = 1;
    final static int FEBRUARY_CODE = 4;
    final static int MARCH_CODE = 4;
    final static int APRIL_CODE = 0;
    final static int MAY_CODE = 2;
    final static int JUNE_CODE = 5;
    final static int JULY_CODE = 0;
    final static int AUGUST_CODE = 3;
    final static int SEPTEMBER_CODE = 6;
    final static int OCTOBER_CODE = 1;
    final static int NOVEMBER_CODE = 4;
    final static int DECEMBER_CODE = 6;

    // Day of Week Algorithm Constants
    private static final int CENTURY_2000S_CODE = 6;
    private static final int CENTURY_1800S_CODE = 2;
    private static final int LEAP_YEAR_JAN_FEB_ADJUSTMENT = 6;
    private static final int TWELVES_DIVISOR = 12;
    private static final int FOURS_DIVISOR = 4;
    private static final int MODULO_DAYS_IN_WEEK = 7;

    // Century boundaries
    private static final int YEAR_1800 = 1800;
    private static final int YEAR_1899 = 1899;
    private static final int YEAR_2000 = 2000;



    /**
     * Constructs a Date object.
     *
     * @param year  the year (e.g., 2023)
     * @param month the month (1-12)
     * @param day   the day of the month (1-31)
     */
    public Date(int year, int month, int day) {
        // Validation
        if( year < MIN_YEAR ||  year > CURRENT_YEAR ){throw new IllegalArgumentException("bad year!");}
        this.year = year;
        if( month < MIN_MONTH || month > MAX_MONTH ){throw new IllegalArgumentException("bad month!");}
        this.month = month;
        // Allowing for different number of days contingent on if we have a leap year or not
        if( day < MIN_DAY || day > MAX_DAY ){throw new IllegalArgumentException("bad day!");}

        int maxDaysInMonth;
        if (month == FEBRUARY) {
            maxDaysInMonth = isLeapYear(year) ? DAYS_IN_LEAP_FEB : DAYS_IN_COMMON_FEB;
        } else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            maxDaysInMonth = DAYS_IN_SHORT_MONTH;
        } else {
            maxDaysInMonth = DAYS_IN_LONG_MONTH;
        }

        if (day > maxDaysInMonth) {
            throw new IllegalArgumentException("Invalid day: " + day + " for month " + month + " in year " + year);
        }
        this.day = day;
    }

    /**
     * Gets the year.
     * @return the year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month.
     * @return the month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day.
     * @return the day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Formats the date into a "YYYY-MM-DD" string format.
     *
     * @return A formatted string representing the date.
     */
    public String getYyyyMmDd() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Checks if a given year is a leap year.
     * @param year The year to check.
     * @return true if the year is a leap year, false otherwise.
     */

    public static boolean isLeapYear(int year) {
        if (year % LEAP_YEAR_DIVISOR_400 == 0) {
            return true; // Divisible by 400, so it's a leap year
        } else if (year % LEAP_YEAR_DIVISOR_100 == 0) {
            return false; // Divisible by 100 but not 400, so not a leap year
        } else if (year % LEAP_YEAR_DIVISOR_4 == 0) {
            return true; // Divisible by 4 and not by 100, so it's a leap year
        } else {
            return false; // Not divisible by 4, so not a leap year
        }
    }

    /**
     * Obtains the monthCode from an array
     * @param month the month of the date.
     * @return The name of the day of the week as a String (e.g., "Monday").
     */

    public int getMonthCode(int month) {
        int[] monthCodes = {JANUARY_CODE, FEBRUARY_CODE, MARCH_CODE, APRIL_CODE,
                MAY_CODE,JUNE_CODE,JULY_CODE,AUGUST_CODE,SEPTEMBER_CODE,OCTOBER_CODE,NOVEMBER_CODE,DECEMBER_CODE};
        return monthCodes[month - 1];

    }


    /**
     * Calculates the day of the week for a given date.
     * @return The name of the day of the week as a String (e.g., "Monday").
     */

    public String getDayOfTheWeek(int year, int month, int day){

        int lastTwoYearDigits = year % LEAP_YEAR_DIVISOR_100;
        int numberOfTwelves;
        int remainderOfnumberOfTwelves;
        int numberOfFours;
        int janFebLeapYearValue;
        int datesIn2000sValue;
        int datesIn1800sValue;
        int daySelectionValue;
        String dayOfTheWeek ="";

        if (isLeapYear(year) && (month <= FEBRUARY)) {
            janFebLeapYearValue = LEAP_YEAR_JAN_FEB_ADJUSTMENT;
        } else {
            janFebLeapYearValue = 0;
        }

        if (year >= YEAR_2000){datesIn2000sValue = CENTURY_2000S_CODE;}
        else {datesIn2000sValue = 0;}

        if (year>=YEAR_1800 && year <= YEAR_1899) {datesIn1800sValue = CENTURY_1800S_CODE;}
        else {datesIn1800sValue = 0;}

        //Number of twelves in lastTwoYearDigits
        numberOfTwelves = lastTwoYearDigits / TWELVES_DIVISOR;
        //Remainder of numberOfTwelves
        remainderOfnumberOfTwelves = lastTwoYearDigits % TWELVES_DIVISOR;
        // Calculate the number of fours contained within remainderOfnumberOfTwelves
         numberOfFours = remainderOfnumberOfTwelves / FOURS_DIVISOR;




        // Day selection value
        daySelectionValue = (janFebLeapYearValue + datesIn2000sValue +
                datesIn1800sValue + numberOfTwelves + remainderOfnumberOfTwelves + numberOfFours +
                getDay() + getMonthCode(month)) % MODULO_DAYS_IN_WEEK;
        // if statements to choose day
        if (daySelectionValue == 0) {dayOfTheWeek = "Saturday";}
        else if (daySelectionValue == 1) {dayOfTheWeek = "Sunday";}
        else if (daySelectionValue == 2) {dayOfTheWeek = "Monday";}
        else if (daySelectionValue == 3) {dayOfTheWeek = "Tuesday";}
        else if (daySelectionValue == 4) {dayOfTheWeek = "Wednesday";}
        else if (daySelectionValue == 5) {dayOfTheWeek = "Thursday";}
        else if (daySelectionValue == 6) {dayOfTheWeek = "Friday";}
        return dayOfTheWeek;
    }



    @Override
    public String toString() {
        // Formats the date as YYYY-MM-DD
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Main method to demonstrate the Date class functionality.
     * @param args Command line arguments (not used).
     */


    /**
     * Compares this date with another date for order.
     *
     * @param other The other Date to be compared.
     * @return a negative integer, zero, or a positive integer as this date
     *         is before, equal to, or after the specified date.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }

    /**
     * @return the next Orderable object in the sequence.
     */
    @Override
    public Orderable next() {
        int maxDaysInMonth;
        if (month == FEBRUARY) {
            maxDaysInMonth = isLeapYear(year) ? DAYS_IN_LEAP_FEB : DAYS_IN_COMMON_FEB;
        } else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            maxDaysInMonth = DAYS_IN_SHORT_MONTH;
        } else {
            maxDaysInMonth = DAYS_IN_LONG_MONTH;
        }

        if (day < maxDaysInMonth) {
            return new Date(year, month, day + 1);
        } else {
            if (month < MAX_MONTH) {
                return new Date(year, month + 1, 1);
            } else {
                return new Date(year + 1, 1, 1);
            }
        }
    }

    /**
     * @return the previous Orderable object in the sequence.
     */
    @Override
    public Orderable previous() {
        if (day > 1) {
            return new Date(year, month, day - 1);
        } else {
            if (month > 1) {
                int prevMonth = month - 1;
                int daysInPrevMonth = new Date(year, prevMonth, 1).getDaysInMonth(year, prevMonth);
                return new Date(year, prevMonth, daysInPrevMonth);
            } else {
                return new Date(year - 1, MAX_MONTH, DAYS_IN_LONG_MONTH);
            }
        }
    }

    private int getDaysInMonth(int year, int month) {
        return new Date(year, month, 1).day;
    }


}// end of class
