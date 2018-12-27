import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;
import org.junit.Test;

public class AthleteTest {

    @Test(expected = IdValidationException.class)
    public void testWhenInvaliIDIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        Athlete a = new Athlete(-5, "Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = NameValidationException.class)
    public void testWhenInvalidNameIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        Athlete a = new Athlete(5, "5Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = ShootingRangeValidationException.class)
    public void testWhenInvalidShootingRangeIsInsertedThrowsException() throws IdValidationException,
            NameValidationException, ShootingRangeValidationException, TimeValidationException {
        Athlete a = new Athlete(5, "Martin Fourcade", "DE", "20:45",
                "xxxxx", "xxvxxx", "xxxxx");
        a.validate();
    }

    @Test(expected = TimeValidationException.class)
    public void testWhenInvaliTimeIsInsertedThrowsException() throws IdValidationException, NameValidationException,
            ShootingRangeValidationException, TimeValidationException {
        Athlete a = new Athlete(5, "Martin Fourcade", "DE", "20.35",
                "xxxxx", "xxxxx", "xxxxx");
        a.validate();
    }
}
