package com.insight.pharmacy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PharmacyCounting{

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        Map<String,int[]> hMap = new HashMap<String,int[]>();

        try {
            String path = "input/itcont.txt";
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            int header = 0;


            while (sc.hasNextLine()) {
                if (header == 0) {
                    sc.nextLine();
                    header = 1;
                }
                else{
                String line = sc.nextLine();
                String[] arr = line.split(",");

                     if (hMap.containsKey(arr[3])) {

                        int[] temp = hMap.get(arr[3]);
                        temp[1] = Integer.parseInt(arr[4]) + temp[1];
                        temp[0] = temp[0] + 1;
                         hMap.put(arr[3],temp);

                    } else {
                        int[] temp = new int[2];
                        temp[1] = Integer.parseInt(arr[4]);
                        temp[0] = 1;
                        hMap.put(arr[3],temp);


                    }



             }


            }

            for(Map.Entry entry : hMap.entrySet()){
                int[] integers = hMap.get(entry.getKey());
                System.out.print(entry.getKey()+" ");
                for(int i=0;i<integers.length;i++){
                    System.out.print(integers[i]+" ");
                }

                System.out.println  ();

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