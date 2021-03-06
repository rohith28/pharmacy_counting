import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyCounting {

    public static void main(String[] args) throws IOException {

        List<drugDetails> listOfDrugDet = new ArrayList<>();

        String path = args[0];
        // Checking whether file exist or not
        if(checkFileExist(path)){

            listOfDrugDet = readDataFromFile(path, listOfDrugDet);

            // Sorting the drugDetails objects based on Cost and Name (Descending order)
            // SortByCost is implemented in drugDetails class
            Collections.sort(listOfDrugDet, new SortBycost());

            // Checking whether the output path exist or not
            if(isValidPath(args[1])){
                writeToFile(args[1],listOfDrugDet);
            }else{
                System.out.print("path not exist");
            }


        }else{

            throw new FileNotFoundException("File not found in the given file path.");
        }
    }

    /**
     *  Name : checkFileExist
     *  parameter : Input file path
     *  returns : If file exist 'True' else 'False'
     *
     * */
    public static boolean checkFileExist(String path){
        boolean res = new File(path).exists();
        return res;
    }

    /**
     *  Name : isValidPath
     *  Parameter : Path to the output file
     *  Returns : boolean. If path is exist then 'True' else 'False'
     * */

    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);

        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    /**
     * Name : writeToFile
     * parameters : output file path and List of drugDetails object
     * This method will return all the drugs into given output file.
     * */
    public static void writeToFile(String path,List<drugDetails> listOfDrugDet){
        try {
            FileWriter fileWriter = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("drug_name,num_prescriber,total_cost");
            NumberFormat nf = new DecimalFormat("##.##");

            for (drugDetails tempList : listOfDrugDet) {
                String str = tempList.getdName() + "," + tempList.getNum() + "," +  nf.format(tempList.getCost());
                printWriter.println(str);

            }
            printWriter.close();
        }catch (IOException ie){
            System.out.println("Error in File writer" + ie.getMessage());
        }
    }

    /*
    * Name of the Method : readDataFromFile
    * Parameters : Path of the input file, List of drugDetails objects
    *  This method reads the data from the file , parse the data and create the list of drugDetails object
    * */

    public static List<drugDetails> readDataFromFile(String path, List<drugDetails> listOfDrugDet) throws IOException{

        FileInputStream inputStream = null;
        Scanner sc = null;
        Set<String> names = new HashSet<>();

        try {

            inputStream = new FileInputStream(path);

            sc = new Scanner(inputStream, "UTF-8");
            sc.nextLine();
            int errorLine =0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!line.trim().isEmpty()){
                    // In some cases drug name or name of prescriber has commas in it. 
                    // To split given line into 5 we need to use following regualr expression.
                    // Ref: https://stackoverflow.com/a/1757107
                    String[] arr = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    arr[3] = arr[3].replace("\"", "");
                    drugDetails reomvePrint = null;
                    if (names.contains(arr[3])) {
                        drugDetails updatePrint = new drugDetails(arr[3], 0, 0);

                        for (drugDetails temp:listOfDrugDet) {
                            if (temp.getdName().equals(arr[3])) {
                                reomvePrint = temp;
                            }
                        }

                        try{
                            for (drugDetails temp : listOfDrugDet) {
                                if (temp.getdName().equals(arr[3])) {
                                    updatePrint.setNum(temp.getNum() + 1);
                                    updatePrint.setCost(temp.getCost() + Double.parseDouble(arr[4]));
                                }
                            }
                        } catch (NumberFormatException ne) {
                                System.out.println();
                        }

                        listOfDrugDet.remove(reomvePrint);
                        listOfDrugDet.add(updatePrint);
                    } else {
                        try {
                            drugDetails tempPrint = new drugDetails(arr[3], 1, Double.parseDouble(arr[4]));
                            listOfDrugDet.add(tempPrint);
                            names.add(arr[3]);
                        } catch (NumberFormatException ne) {
                            System.out.println(ne.getMessage());
                        }
                    }
                }else{
                    errorLine++;
                    System.out.print("Line is not present");
                }
            }
            System.out.println("Number of line empty are :"+errorLine);

        } catch (FileNotFoundException fe) {

            System.out.println("File not found. Please enter correct path.");
            System.out.println(fe.getMessage());
            throw new FileNotFoundException();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            if (inputStream != null) {
                 inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        return listOfDrugDet;
    }


}
