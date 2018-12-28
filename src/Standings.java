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

    public void sort(){
        standings.sort(Comparator.comparing(Athlete::getFinalTimeResult));
    }

    public void printStandings(){
        System.out.println("Standings:");
        System.out.println("Winner - " + standings.get(0));
        System.out.println("Runner-up - " + standings.get(1));
        System.out.println("Third place - " + standings.get(2));
    }
}
