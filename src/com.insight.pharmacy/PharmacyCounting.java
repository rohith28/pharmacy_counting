package com.insight.pharmacy;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyCounting {

    public static void main(String[] args) throws IOException {

        List<drugDetails> listOfPrint = new ArrayList<>();

        String path = args[0];

        listOfPrint = readDataFromFile(path, listOfPrint);

        Collections.sort(listOfPrint, new SortBycost());

        writeToFile(args[1],listOfPrint);


    }

    public static void writeToFile(String path,List<drugDetails> listOfPrint){
        try {
            FileWriter fileWriter = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("drug_name,num_prescriber,total_cost");


            for (drugDetails tempList : listOfPrint) {
                String str = tempList.getdName() + "," + tempList.getNum() + "," + (int) tempList.getCost() + "\n";
                printWriter.println(str);

            }
            printWriter.close();
        }catch (IOException ie){
            System.out.println("Error in File writer" + ie.getMessage());
        }
    }


    public static List<drugDetails> readDataFromFile(String path, List<drugDetails> listOfPrint) throws IOException{

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
                        Iterator<drugDetails> itr = listOfPrint.iterator();
                        while (itr.hasNext()) {
                            drugDetails temp = itr.next();
                            if (temp.getdName().equals(arr[3])) {
                                reomvePrint = temp;
                            }
                        }
                        try {
                            for (drugDetails temp : listOfPrint) {
                                if (temp.getdName().equals(arr[3])) {
                                    updatePrint.setNum(temp.getNum() + 1);
                                    updatePrint.setCost(temp.getCost() + Float.parseFloat(arr[4]));
                                }

                            }
                        } catch (NumberFormatException ne) {
                            System.out.println();
                        }

                        listOfPrint.remove(reomvePrint);
                        listOfPrint.add(updatePrint);
                    } else {
                        try {
                            drugDetails tempPrint = new drugDetails(arr[3], 1, Float.parseFloat(arr[4]));
                            listOfPrint.add(tempPrint);
                            names.add(arr[3]);
                        } catch (NumberFormatException ne) {
                            System.out.println(ne.getMessage());
                        }
                    }

            }
        } catch (FileNotFoundException fe) {
            System.out.println("File not found. Please enter correct path.");
            System.out.println(fe.getMessage());
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

        return listOfPrint;
    }
}