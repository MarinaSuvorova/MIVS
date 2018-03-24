import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class Login {
    public static void main(String[] args) {
        updateMIVSPropertiesID();
    }

    public static void updateMIVSPropertiesID() {
        String id = "1000";
        try (FileReader fileReader = new FileReader("LoginInfo.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(";");
                String[] fullID = info[2].split("-");
                id = fullID[1];
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (FileWriter fileWriter  = new FileWriter("mivs.properties")) {

            Properties p = new Properties();
            p.setProperty("id", id);
            p.store(fileWriter,null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
