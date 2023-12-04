package org.solver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class FileWriterSolution {
    String filename;
    public FileWriterSolution(String filename){
        this.filename = filename;
    }
    public void writeToFile(double number, double[] values) {
        Locale.setDefault(Locale.US);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Записываем число в файл
            writer.write(String.format("%.4f",number));
//            writer.write(" ");

            // Записываем массив double в файл
            for (double value : values) {
//                writer.write(" ");
                writer.write(String.format(",%.4f",value));

            }

            // Переходим на новую строку
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void filenameToFile(String name) {
        Locale.setDefault(Locale.US);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("key.txt"))) {
            // Записываем число в файл
            writer.write(name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void filenameToFile() {
        Locale.setDefault(Locale.US);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("key.txt"))) {
            // Записываем число в файл
            writer.write(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stringToFile(String str) {
        Locale.setDefault(Locale.US);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Записываем число в файл
            writer.write(str+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stringToFile(String str, String name) {
        Locale.setDefault(Locale.US);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            // Записываем число в файл
            writer.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
