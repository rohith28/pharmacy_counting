package com.insight.pharmacy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.*;

public class PharmacyCounting{

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        List<drugDetails> listOfPrint = new ArrayList<>();
        Set<String> names = new HashSet<>();
        try {
            String path = "input/itcont.txt";
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            int header = 0;


            while (sc.hasNextLine()) {

                if(header == 0){
                    sc.nextLine();
                    header = 1;
                }

                else {
                    String line = sc.nextLine();
                    String[] arr = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    arr[3] = arr[3].replace("\"","");
                    drugDetails reomvePrint = null;
                    if (names.contains(arr[3])) {

                        drugDetails updatePrint = new drugDetails(arr[3],0,0);
                        Iterator<drugDetails> itr  = listOfPrint.iterator();
                        while(itr.hasNext()){
                            drugDetails temp = itr.next();
                            if(temp.getdName().equals(arr[3])){
                                reomvePrint = temp;
                            }
                        }

                        for (drugDetails temp : listOfPrint) {
                            if(temp.getdName().equals(arr[3])){
                                updatePrint.setNum(temp.getNum() + 1);
                                updatePrint.setCost(temp.getCost() + Float.parseFloat(arr[4]));
                            }

                       }


                       listOfPrint.remove(reomvePrint);
                       listOfPrint.add(updatePrint);


                    } else {
                        try {
                            drugDetails tempPrint = new drugDetails(arr[3], 1, Float.parseFloat(arr[4]));
                            listOfPrint.add(tempPrint);
                            names.add(arr[3]);
                        }catch (NumberFormatException ne){
                            System.out.println(ne.getMessage());

                            System.out.println("----"+arr[4]+" ----"+arr[0]);
                        }
                        }
                }
            }


            Collections.sort(listOfPrint,new SortBycost());
            Path file = Paths.get("output/top-cost-drug.txt");
            for(drugDetails tempList : listOfPrint){
                System.out.println(tempList.toString());
            }

            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }catch (FileNotFoundException fe){
            System.out.println(fe.getMessage());
        }

        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {   
                sc.close();
            }
        }
    }

}