import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class DataWriter {
    DataWriter(){
}
    public void updateUserProperties () {
        DataStorage dataStorage = new DataStorage();
        HashMap<String,String> userProperties = dataStorage.getUserProperties();
        File updateFile = new File("UserProperties.txt");
        try (FileWriter fileWriter = new FileWriter(updateFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("ID;firstName;lastName;dateOfBirth;email;mobileNumber;gender;address;\n");
            for (String p : userProperties.keySet()) {
                bufferedWriter.write(p + ";" + userProperties.get(p));
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
