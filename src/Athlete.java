import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Athlete {
    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private String skiTimeResult;
    private String firstShootingRange;
    private String secondShootingRange;
    private String thirdShootingRange;
    private String finalTimeResult;

    public Athlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult, String firstShootingRange, String secondShootingRange, String thirdShootingRange) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShootingRange = firstShootingRange;
        this.secondShootingRange = secondShootingRange;
        this.thirdShootingRange = thirdShootingRange;
        this.finalTimeResult = addPenalties(skiTimeResult, calculatePenalty());
    }

    public void validate() throws IdValidationException, NameValidationException, TimeValidationException, ShootingRangeValidationException {
        validateId();
        validateName();
        validateSkiTimeResult();
        validateShootingRange(firstShootingRange);
        validateShootingRange(secondShootingRange);
        validateShootingRange(thirdShootingRange);
    }

    private String addPenalties(String time, int penalty) {
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        try {
            Date date = df.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.SECOND, penalty);
            return df.format(cal.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private int calculatePenalty() {
        List<String> shootingResults = new ArrayList<>();
        int missedShots = 0;

        shootingResults.add(firstShootingRange);
        shootingResults.add(secondShootingRange);
        shootingResults.add(thirdShootingRange);

        for (String result : shootingResults) {
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == 'o') {
                    missedShots++;
                }
            }
        }
        return missedShots*10;
    }

    @Override
    public String toString() {
        if (finalTimeResult != null)
            return athleteName + " " + finalTimeResult + " (" +
                    skiTimeResult + " + " + calculatePenalty() * 10 + ")";
        else
            return athleteNumber + " " + athleteName + " " + countryCode + " " + skiTimeResult + "\n" +
                    "Shooting Ranges: " + firstShootingRange + "," + secondShootingRange + "," + thirdShootingRange;
    }

    private void validateId() throws IdValidationException {
        if (!(this.athleteNumber > 0 && this.athleteNumber < 999))
            throw new IdValidationException("The athlete number " + '"' + athleteNumber +
                    '"' + " you have entered is not valid.");
    }

    private void validateName() throws NameValidationException {
        if (!athleteName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
            throw new NameValidationException("The name " + '"' + athleteName + '"' +
                    " read from the csv file is invalid. Please edit it and try again.");
        }
    }

    private void validateSkiTimeResult() throws TimeValidationException {
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

    private void validateShootingRange(String s) throws ShootingRangeValidationException {
        if(s.length()!=5){
            throw new ShootingRangeValidationException("The Shooting Range" + '"' + s + '"' + " read from the csv " +
                    "file does not have the proper length. Please make sure you have the proper format and try again.");
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'x' && s.charAt(i) != 'o') {
                throw new ShootingRangeValidationException("The Shooting Range" + '"' + s + '"' + " read from the "
                        + "csv file contains the following invalid character: " + s.charAt(i) +
                        ". Please make sure you have the proper format and try again.");
            }
        }
    }


    public String getFinalTimeResult() {
        return finalTimeResult;
    }

}


