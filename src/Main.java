import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, NameValidationException, IdValidationException, TimeValidationException, ShootingRangeValidationException {

        FileProcessor f = new FileProcessor();
        f.parseAthletes();

    }
}
