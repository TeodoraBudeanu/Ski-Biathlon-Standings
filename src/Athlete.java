import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Athlete {
    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private String skiTimeResult;
    private String firstShootingRange;
    private String secondShootingRange;
    private String thirdShootingRange;
    private Date finalTimeResult;
    private int penaltySeconds;

    SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

    public Athlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult, String firstShootingRange, String secondShootingRange, String thirdShootingRange) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShootingRange = firstShootingRange;
        this.secondShootingRange = secondShootingRange;
        this.thirdShootingRange = thirdShootingRange;

    }

    public Date formatTimeResultAsDate(String time) throws ParseException {
        Date date = formatter.parse(time);
        return date;
    }

    public Date getFinalTimeResult() {
        return finalTimeResult;
    }

    public void setFinalTimeResult(Date finalTimeResult) {
        this.finalTimeResult = finalTimeResult;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public String getFirstShootingRange() {
        return firstShootingRange;
    }

    public String getSecondShootingRange() {
        return secondShootingRange;
    }

    public String getThirdShootingRange() {
        return thirdShootingRange;
    }

    public int getPenaltySeconds() {
        return penaltySeconds;
    }

    public void setPenaltySeconds(int penaltySeconds) {
        this.penaltySeconds = penaltySeconds;
    }

    public void validate() throws IdValidationException, NameValidationException, TimeValidationException, ShootingRangeValidationException {
        validateId();
        validateName();
        validateSkiTimeResult();
        validateShootingRange(firstShootingRange);
        validateShootingRange(secondShootingRange);
        validateShootingRange(thirdShootingRange);
    }

    @Override
    public String toString() {
        if (finalTimeResult != null)
            return athleteName + " " + formatter.format(finalTimeResult) + " (" +
                    skiTimeResult + " + " + penaltySeconds + ")";
        else
            return athleteNumber + " " + athleteName + " " + countryCode + " " + skiTimeResult + "\n" +
                    "Shooting Ranges: " + firstShootingRange + "," + secondShootingRange + "," + thirdShootingRange;
    }

    public void validateId() throws IdValidationException {
        if (!(this.athleteNumber > 0 && this.athleteNumber < 999))
            throw new IdValidationException("The athlete number " + '"' + athleteNumber +
                    '"' + " you have entered is not valid.");
    }

    public void validateName() throws NameValidationException {
        if (!athleteName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
            throw new NameValidationException("The name " + '"' + athleteName + '"' +
                    " read from the csv file is invalid. Please edit it and try again.");
        }
    }

    public void validateSkiTimeResult() throws TimeValidationException {
        if (skiTimeResult.length() != 5) {
            throw new TimeValidationException("The ski time result " + '"' + skiTimeResult + '"' +
                    " read from the csv file is invalid. Please edit it and try again.");
        }
        if (!Character.toString(skiTimeResult.charAt(0)).matches("[0-5]") ||
                !Character.toString(skiTimeResult.charAt(1)).matches("[0-9]") ||
                !Character.toString(skiTimeResult.charAt(3)).matches("[0-5]") ||
                !Character.toString(skiTimeResult.charAt(4)).matches("[0-9]") ||
                skiTimeResult.charAt(2) != ':') {
            throw new TimeValidationException("The ski time result " + '"' + skiTimeResult + '"' + " read from the " +
                    "csv file is invalid. Please make sure you have the proper format: mm:ss and try again.");
        }

    }

    public void validateShootingRange(String s) throws ShootingRangeValidationException {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'x' && s.charAt(i) != 'o') {
                throw new ShootingRangeValidationException("The Shooting Range" + '"' + s + '"' + " read from the "
                        + "csv file contains the following invalid character: " + s.charAt(i) +
                        ". Please make sure you have the proper format and try again.");
            }
        }
    }
}


