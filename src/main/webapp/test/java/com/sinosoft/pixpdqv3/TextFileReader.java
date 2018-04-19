package com.sinosoft.pixpdqv3;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 13-4-27
 * Time: 上午11:50
 * To change this template use File | Settings | File Templates.
 */
public class TextFileReader {
    public static String read(String file){
        File f = new File(file);
        Long fileLengthLong = f.length();
        byte[] fileContent = new byte[fileLengthLong.intValue()];
        try {
            FileInputStream inputStream = new FileInputStream(f);
            inputStream.read(fileContent);
            inputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        String string = new String(fileContent);
        return string;
    }
}
