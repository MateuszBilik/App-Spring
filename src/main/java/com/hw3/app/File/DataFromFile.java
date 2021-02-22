package com.hw3.app.File;

import com.hw3.app.one_line_of_data.DataForOneMinute;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataFromFile {

    FileLoader fileLoader;
    Converter converter;

    public DataFromFile(FileLoader fileLoader, Converter converter) {
        this.fileLoader = fileLoader;
        this.converter = converter;
    }

    public List<DataForOneMinute> getData() {
        return converter.createListWithDataForOneMinutesLines(fileLoader.loadFile());
    }
}
