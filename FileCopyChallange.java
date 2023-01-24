import java.io.*;
import java.util.Scanner;

public class FileCopyChallange {

    //This is a file copy program that works exaxtly like a windows file copy machanism
    public static void main(String[] args) {
        /*As mentioned the functionalities are:

        1st. Asking the user for the file name which need to be copied */

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the file name to copy: ");
        String fileName = sc.nextLine();

        
        /*2nd. Checking if the input file exists then it will copy that file into a new file name
        and for this it will use the exact naming conventions */


        /*this is how it will check that the input file exist and if not then it will print the error message
        that Sorry the file you named doesn't exist
        */
        File inputFile = new File(fileName);
        if (!inputFile.exists()) {
            System.out.println("Error: Sorry the file you named doesn't exist");
            return;
        }
        
        /*Now as per the given assignment naming convention you have to stop at the extension
         * and add - Copy in the name
         * 
         * for that you have to stop when you encounters .txt
         * 
         * for that I have used indexOf method
         */

        if (fileName.indexOf(".") > 0)
        fileName = fileName.substring(0, fileName.lastIndexOf("."));

        //if file exist this is how it will copy that file into a new file with the Windows naming convention
        String outputFileName = fileName + " - Copy.txt";
        File outputFile = new File(outputFileName);
        int copyNumber = 2;
        while (outputFile.exists()) {
            outputFileName = fileName + " - Copy (" + copyNumber + ").txt";
            outputFile = new File(outputFileName);
            copyNumber++;
        }

        //this will perform the actual file copy
        //for this I am using try can catch block you can also use throw and throws keyword.
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length); 
            }
            inputStream.close();
            outputStream.close();
            //when file sucessfully gets copied it will give this message
            System.out.println("Yay!!!! Your file is sucessfully copied to " + outputFileName);
        } catch (IOException e) {
            //otherwise if some error occured then it will print this "Fail to copy file." message
            System.out.println("Error: Failed to copy file.");
            e.printStackTrace();
        }
    }
}