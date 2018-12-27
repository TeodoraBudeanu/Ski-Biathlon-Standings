import java.text.ParseException;
import java.util.*;

public class Standings {

    private List<Athlete> standings = new ArrayList<>();

    public void add(Athlete a) {
        standings.add(a);
    }

    public void printAll() {
        for (Athlete a : standings) {
            System.out.println(a.toString());
        }
    }

    public void addPenalties() throws ParseException {
        for (Athlete a : standings) {
            a.setPenaltySeconds(10 * countMissedShots(a));
            a.setFinalTimeResult(addSeconds(a.formatTimeResultAsDate(a.getSkiTimeResult()), a.getPenaltySeconds()));
        }
    }

    public static Date addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public int countMissedShots(Athlete a) {
        int missedShots = 0;
        for (int i = 0; i < 5; i++) {
            if (a.getFirstShootingRange().charAt(i) == 'o') {
                missedShots++;
            }
            if (a.getSecondShootingRange().charAt(i) == 'o') {
                missedShots++;
            }
            if (a.getThirdShootingRange().charAt(i) == 'o') {
                missedShots++;
            }
        }
        return missedShots;
    }

    public void sort(){
        Collections.sort(standings, new TimeComparator());
    }

    public void printStandings(){
        System.out.println("Standings:");
        System.out.println("Winner - " + standings.get(0));
        System.out.println("Runner-up - " + standings.get(1));
        System.out.println("Third place - " + standings.get(2));
    }
}
