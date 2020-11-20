import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static String swapRows(String textInFile, int index1, int index2){

        System.out.println("Swapping rows...");
        String finalStringToWriteInFile ="";
        try {
            String[] allLines = textInFile.split("\n");
            String temp = allLines[index1];
            allLines[index1] = allLines[index2];
            allLines[index2] = temp;
            for (int i = 0; i < allLines.length; i++)
                finalStringToWriteInFile = finalStringToWriteInFile + allLines[i] + "\n";
            finalStringToWriteInFile = finalStringToWriteInFile.replaceAll("\n", System.lineSeparator());
            System.out.printf("This is the text after swapping:\n%s",finalStringToWriteInFile);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid index for swapping rows");
            finalStringToWriteInFile =textInFile;
            finalStringToWriteInFile = finalStringToWriteInFile.replaceAll("\n", System.lineSeparator());}

        return finalStringToWriteInFile;
    }

    public static String swapWords(String textInFile, int index1row, int index2row, int index1word, int index2word){

        System.out.println("Swapping words...");
        String[] allLines = textInFile.split("\n");
        String finalStringToWriteInFile ="";

        try {
            String word1 = allLines[index1row].split("\t| ")[index1word];
            String word2 = allLines[index2row].split("\t| ")[index2word];


            int j = allLines[index1row].indexOf(word1);
            String newLineM = allLines[index1row].substring(0, j) + word2 + allLines[index1row].substring(j + word1.length());
            allLines[index1row] = newLineM;

            j = allLines[index2row].indexOf(word2);
            String newLineN = allLines[index2row].substring(0, j) + word1 + allLines[index2row].substring(j + word2.length());
            allLines[index2row] = newLineN;


            for (int i = 0; i < allLines.length; i++)
                finalStringToWriteInFile = finalStringToWriteInFile + allLines[i] + "\n";
            //  System.out.println("this will be written");
            finalStringToWriteInFile = finalStringToWriteInFile.replaceAll("\n", System.lineSeparator());
            System.out.printf("This is the text after swapping:\n%s",finalStringToWriteInFile);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid index for swapping words");
            finalStringToWriteInFile=textInFile;
            finalStringToWriteInFile = finalStringToWriteInFile.replaceAll("\n", System.lineSeparator());
        }
        return finalStringToWriteInFile;
    }


    public static void main(String[] args) throws IOException {
        Scanner scann = new Scanner(System.in);
        System.out.println("Enter the path of the file");
        String path = scann.nextLine();

        try {
            String toBeReturned = "";

            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String textInFile = "";
            while (scanner.hasNextLine()) {
                textInFile = textInFile.concat(scanner.nextLine() + "\n");
            }

            System.out.println("This is the text from the file");
            System.out.println(textInFile);

            System.out.println("If you want to swap rows write rows and if you want to swipe words write words");
            String typeOfSwap = scann.nextLine();
            if (typeOfSwap.equals("rows")) {
                System.out.println("Input the index of the rows you want to swap");
                int index1 = Integer.parseInt(scann.nextLine());
                int index2 = Integer.parseInt(scann.nextLine());
                toBeReturned = swapRows(textInFile, index1 - 1, index2 - 1);
            } else if (typeOfSwap.equals("words")) {
                System.out.println("Input the index of the words you want to swap(which row and the index of the word in the row)");
                System.out.println("Input like this: <first_line_index> <first_line_word_index> <second_line_index> <second_line_word_index>");
                int index1row = Integer.parseInt(scann.nextLine());
                int index1word = Integer.parseInt(scann.nextLine());
                int index2row = Integer.parseInt(scann.nextLine());
                int index2word = Integer.parseInt(scann.nextLine());
                toBeReturned = swapWords(textInFile, index1row - 1, index2row - 1, index1word - 1, index2word - 1);

            }
            else
            { System.out.println("Invalid input");
                toBeReturned=textInFile;
                toBeReturned = toBeReturned.replaceAll("\n", System.lineSeparator());}

            FileWriter writer = new FileWriter(path);
            writer.write(toBeReturned);
            writer.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (NumberFormatException e){
            System.out.println("Input number not a string(or you maybe wrote more than one number on one line)");
        }
    }
}
