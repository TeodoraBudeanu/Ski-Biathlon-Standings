import java.util.Comparator;

import static java.lang.Math.toIntExact;

public class TimeComparator implements Comparator<Athlete> {

    @Override
    public int compare(Athlete o1, Athlete o2){
            return toIntExact(o1.getFinalTimeResult().getTime() - o2.getFinalTimeResult().getTime());
    }
}


