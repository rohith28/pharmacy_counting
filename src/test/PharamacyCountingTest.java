import insight.PharmacyCounting;
import insight.drugDetails;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PharamacyCountingTest {

    // Test case
    @Test(expected = FileNotFoundException.class)
    public void readDataFromFileTest() throws Exception{
        String path = "input2.txt";
        PharmacyCounting pc = new PharmacyCounting();
        pc.readDataFromFile(path,null);
    }
    @Test
    public void checkFileExistTest() {
        String path = "some/Path/which/not/exist.txt";
        PharmacyCounting pc  = new PharmacyCounting();
        Assert.assertFalse(pc.checkFileExist(path));
    }

    @Test
    public void writeToFileTest(){
        String path= "output/test_file_to_be_deleted.txt";
        List<drugDetails> testList = new ArrayList<>();
        drugDetails drugDetObj = new drugDetails("Drug",1,123);
        drugDetails drugDetObj2 = new drugDetails("Drug2",2,156);

        testList.add(drugDetObj);
        testList.add(drugDetObj2);

        PharmacyCounting pc = new PharmacyCounting();
        pc.writeToFile(path,testList);
        File file = new File(path);
        Assert.assertTrue(file.exists());
        file.delete();
    }



}
