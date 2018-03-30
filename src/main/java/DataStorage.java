import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class DataStorage {

    DataStorage(){
    }
    private static HashMap<String, String> loginInfo = new HashMap<String, String>();
    private static HashMap<String, String> userProperties = new HashMap<String, String>();

    public void storeData(String fileName, HashMap mapName) {

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String loginLine = bufferedReader.readLine();
            while ((loginLine = bufferedReader.readLine()) != null) {
                String[] loginData = loginLine.split("[;]");
                String key = loginData[0];
                String data = "";
                for (int i = 1; i < loginData.length; i++) {
                    data = data + loginData[i] + ";";
                }
                mapName.put(key, data);

            }
        } catch (Exception e) {
            System.out.println("storeData failed: "+fileName);
        }
    }

    public HashMap<String, String> getLoginInfo() {
//        for (String p : loginInfo.keySet()) {
//            System.out.println(p + ";" + loginInfo.get(p));
//        }
        return loginInfo;
    }

    public void storeUserProperties() {

        try (FileReader fileReader = new FileReader("UserProperties.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String userLine = bufferedReader.readLine();
            while ((userLine = bufferedReader.readLine()) != null) {
                String[] userProperty = userLine.split("[;]");
                String key = userProperty[0];
                String data = "";
                for (int i = 1; i < userProperty.length; i++) {
                    data = data + userProperty[i] + ";";
                }
                userProperties.put(key, data);

            }
        } catch (Exception e) {
            System.out.println("storeUserProperties failed");
        }
    }

    public HashMap<String, String> getUserProperties() {
//        for (String p : loginInfo.keySet()) {
//            System.out.println(p + ";" + loginInfo.get(p));
//        }
        return userProperties;

    }
}
