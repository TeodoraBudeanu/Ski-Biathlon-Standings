import Exceptions.IdValidationException;
import Exceptions.NameValidationException;
import Exceptions.ShootingRangeValidationException;
import Exceptions.TimeValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    private Standings athletesStandings = new Standings();

    public void parseAthletes() throws NameValidationException, IdValidationException, TimeValidationException, ShootingRangeValidationException {
        readAthletesFromFile();
        athletesStandings.sort();
        athletesStandings.printStandings();
    }

    private void readAthletesFromFile() throws NameValidationException, IdValidationException, TimeValidationException, ShootingRangeValidationException {

        String csvFile = "./resources/data.csv";
        String line;
        int numberOfColumns = 7;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] tokens = line.split(",");
                if (tokens.length == numberOfColumns) {
                    Athlete a = new Athlete(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    a.validate();
                    athletesStandings.add(a);
                } else {
                    System.out.println("Line '" + line + "' is not formatted correctly");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}