/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listduplicatingfiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class ListDuplicatingFiles {

    public static List<File> getListOfFiles(String dirName) {
        File dir = new File(dirName);
        List<File> result = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                result.add(file);
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                result.addAll(getListOfFiles(file.getAbsolutePath()));
            }
        }
        return result;
    }


    public static List<File> removeDuplicatingFiles(List<File> fileData) {
        List<File> temp = new ArrayList<>(fileData);
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                try {
                    if (FileUtils.contentEquals(temp.get(i), temp.get(j))) {
                        temp.remove(temp.get(i));
                    }
                } catch (IOException ex) {
                    System.out.println("A problem was encountered while the files were compared.");
                    System.exit(1);
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicatingFiles(getListOfFiles("C:\\Users\\Admin\\Desktop\\Test")).size());
    }
}
