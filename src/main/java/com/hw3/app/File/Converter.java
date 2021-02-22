package com.hw3.app.File;

import com.hw3.app.one_line_of_data.DataForOneMinute;
import com.hw3.app.one_line_of_data.MyDate;
import com.hw3.app.one_line_of_data.MyTime;
import com.hw3.app.one_line_of_data.MyValue;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class Converter {

    public ArrayList<DataForOneMinute> createListWithDataForOneMinutesLines(BufferedReader myFile) {
        ArrayList<DataForOneMinute> listOfData = new ArrayList();
        String line;
        try {
            boolean firstLineToSkip = true;
            while ((line = myFile.readLine()) != null) {
                if (firstLineToSkip) {
                    firstLineToSkip = false;
                } else {
                    String[] listSeparatedData = line.split(",");
                    String[] date = listSeparatedData[0].split("\\.");
                    String[] time = listSeparatedData[1].split(":");

                    listOfData.add(new DataForOneMinute(     //YYYY.MM.DD,HH:MM,OPEN,HIGH,LOW,CLOSE,VOLUME
                            new MyDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
                            new MyTime(Integer.parseInt(time[0]), Integer.parseInt(time[1])),
                            new MyValue(
                                    BigDecimal.valueOf(Double.parseDouble(listSeparatedData[2])),
                                    BigDecimal.valueOf(Double.parseDouble(listSeparatedData[3])),
                                    BigDecimal.valueOf(Double.parseDouble(listSeparatedData[4])),
                                    BigDecimal.valueOf(Double.parseDouble(listSeparatedData[5])))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfData;
    }
}
