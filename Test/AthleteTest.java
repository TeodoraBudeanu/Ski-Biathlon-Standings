import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AthleteTest {

    private Athlete a;

    @Test(expected = IdValidationException.class)
    public void testWhenInvalidIDIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        a = new Athlete(-5, "Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = NameValidationException.class)
    public void testWhenInvalidNameIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        a = new Athlete(5, "5Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = ShootingRangeValidationException.class)
    public void testWhenInvalidShootingRangeWithImproperCharacterIsInsertedThrowsException() throws IdValidationException,
            NameValidationException, ShootingRangeValidationException, TimeValidationException {
        a = new Athlete(5, "Martin Fourcade", "DE", "20:45",
                "xxlxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = ShootingRangeValidationException.class)
    public void testWhenInvalidShootingRangeWithImproperLengthIsInsertedThrowsException() throws IdValidationException,
            NameValidationException, ShootingRangeValidationException, TimeValidationException {
        a = new Athlete(5, "Martin Fourcade", "DE", "20:45",
                "xxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = TimeValidationException.class)
    public void testWhenInvaliTimeIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        a = new Athlete(5, "Martin Fourcade", "DE", "20.35",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test
    public void testWhenInitialTimeAndPenaltySecondsAreGivenCalculatesFinalTimeResult(){
        a = new Athlete(5, "Martin Fourcade", "DE", "20:45",
                "oxxxx", "xxxxx", "xxxxx");
        assertEquals("20:55", a.getFinalTimeResult());
    }

    @Test
    public void testWhenAthleteHasNoPenaltyReturnsInitialTimeResult(){
        a = new Athlete(5, "Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxxxx", "xxxxx");
        assertEquals("20:45", a.getFinalTimeResult());
    }
}
