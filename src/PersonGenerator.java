import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main (String args[]) {
        Boolean moreEntries = true;
        ArrayList <String>recs = new ArrayList<>();
        ArrayList <String>people = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String cont = "";
        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        Integer YOB = 0;

        //Gather Data
        while (moreEntries == true) {
            System.out.println("Please enter a person record.");
            ID = SafeInput.getNonZeroLenString(input, "please enter person ID");
            recs.add(ID);
            FirstName = SafeInput.getNonZeroLenString(input, "Please enter person first name");
            recs.add(FirstName);
            LastName = SafeInput.getNonZeroLenString(input, "Please enter person last name");
            recs.add(LastName);
            Title = SafeInput.getNonZeroLenString(input, "Please enter person title");
            recs.add(Title);
            YOB = SafeInput.getInt(input, "Please enter person age");
            recs.add(YOB.toString());
            people.add(String.valueOf(recs));
            cont = SafeInput.getNonZeroLenString(input, "More entries? Y/N");
            if (Objects.equals(cont, "Y")) {
                moreEntries = true;
            } else {
                moreEntries = false;
            }
            recs.clear();
        }

        //Write File
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : people)
            {
                writer.write(rec, 0, rec.length());

                writer.newLine();
            }
            writer.close();
            System.out.println("File written!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
