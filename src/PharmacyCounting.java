import java.io.*;
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

        if(checkFileExist(path)){
            listOfDrugDet = readDataFromFile(path, listOfDrugDet);
            Collections.sort(listOfDrugDet, new SortBycost());

            writeToFile(args[1],listOfDrugDet);
        }else{
            throw new FileNotFoundException("File not found in the given file path.");
        }
    }

    public static boolean checkFileExist(String path){
        boolean res = new File(path).exists();
        return res;
    }
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


    public static List<drugDetails> readDataFromFile(String path, List<drugDetails> listOfDrugDet) throws IOException{

        FileInputStream inputStream = null;
        Scanner sc = null;
        Set<String> names = new HashSet<>();

        try {

            inputStream = new FileInputStream(path);

            sc = new Scanner(inputStream, "UTF-8");
            sc.nextLine();
            while (sc.hasNextLine()) {

                    String line = sc.nextLine();
                    String[] arr = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    arr[3] = arr[3].replace("\"", "");
                    drugDetails reomvePrint = null;
                    if (names.contains(arr[3])) {

                        drugDetails updatePrint = new drugDetails(arr[3], 0, 0);
                        Iterator<drugDetails> itr = listOfDrugDet.iterator();

                        for (drugDetails temp:listOfDrugDet) {
                            if (temp.getdName().equals(arr[3])) {
                                reomvePrint = temp;
                            }
                        }

                        try {
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

            }
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