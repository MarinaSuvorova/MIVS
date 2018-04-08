import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class DataStorageTest {


    @Test
    public void givenListOfCourses_should_read_3_Courses() {
        //Arrange
        List<String> expected = Arrays.asList("crs-1;course 1;","crs-2;course 2;","crs-3;course 3;");
        DataStorage dataStorage= new DataStorage();
        //Act
        List<String> actual = new ArrayList<>();
        dataStorage.storeList("FileTest.txt",actual);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    public void givenHashMapOfCourses_should_read_3_Courses() {
        //Arrange
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("crs-1","course 1;");
        expected.put("crs-2","course 2;");
        expected.put("crs-3","course 3;");
        DataStorage dataStorage= new DataStorage();
        //Act
        HashMap<String,String> actual = new HashMap<>();
        dataStorage.storeData("FileTest.txt",actual);
        //Assert
        assertEquals(expected, actual);
    }
}
