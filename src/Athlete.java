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



    @Override
    public String toString() {
        if(finalTimeResult!=null)
        return athleteName + " " + formatter.format(finalTimeResult) + " (" +
                skiTimeResult + " + " + penaltySeconds + ")";
        else return athleteNumber + " " + athleteName + " " + countryCode + " " + skiTimeResult + "\n" + "Shooting Ranges: " + firstShootingRange + "," + secondShootingRange + "," + thirdShootingRange;
    }
}
