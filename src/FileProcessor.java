import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

public class FileProcessor {

    private Standings athletesStandings = new Standings();

    public void parseAthletes() throws ParseException, NameValidationException, IdValidationException, TimeValidationException, ShootingRangeValidationException {
        readAthletesfromFile();
        athletesStandings.addPenalties();
        athletesStandings.sort();
        athletesStandings.printStandings();
    }

    public void readAthletesfromFile() throws NameValidationException, IdValidationException, TimeValidationException, ShootingRangeValidationException {

        File f = new File("./resources/data.csv");

        try {
            Reader reader = new FileReader(f.getAbsolutePath());

            char[] buffer = new char[8];
            int read = reader.read(buffer);

            StringBuilder text = new StringBuilder();
            while (read != -1) {
                text.append(String.copyValueOf(buffer));
                read = reader.read(buffer);
            }

            String fullText = text.toString();
            String[] lines = fullText.split("\n");

            for (String line : lines) {
                String[] tokens = line.split(",");
                Athlete a = new Athlete(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                a.validate();
                athletesStandings.add(a);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }


}