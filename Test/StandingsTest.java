import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandingsTest {

    private Standings standings;

    @Before
    public void setup(){
        standings = new Standings();
    }
    @Test
    public void testWhenAthleteIsAddedListLengthIncreases(){
        Athlete a = new Athlete(11,"Martin Fourcade", "FR","25:18",
                "xxxxx","xxoxx","xxoxo");
        standings.add(a);

    }
}
