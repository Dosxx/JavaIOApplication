
package com.Ics340.ProjectA;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Kekeli D Akouete
 * ICS-340 FALL 2018-2019
 * Description: this program will open a file chooser in the present directory 
 * read the content of a text file and sum up the values of the input per line 
 * and write it to a file named out_file.text in the same directory.
 * 
 */

public class FileChooser {
    public static void main(String[] args)throws IOException{
    	JFileChooser chooser = new JFileChooser();
	    
		//specify the current directory for the file chooser()
        File currentDir = new File(System.getProperty("user.dir")+"/src");
        chooser = new JFileChooser(currentDir);
        //filter on files with .text extension
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".TXT files", "txt");
        chooser.setFileFilter(filter);
        //open the file chooser dialog box
        int status = chooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            //construct the output file name
            String fname =  chooser.getSelectedFile().getName();
            fname = fname.substring(0, fname.length()-4)+"_out.txt";
            try {
                //path and construct of the output file
                File outPutFile = new File(System.getProperty("user.dir")+"/src/"+fname);
                
                Scanner scan = new Scanner(new BufferedReader(new FileReader( chooser.getSelectedFile())));
                
                PrintWriter writer = new PrintWriter(new BufferedWriter(
                        new FileWriter(outPutFile)), false);
                //Read the content of the chosen file from the source directory
                while(scan.hasNextLine()){
                    String [] line = scan.nextLine().split(" ");
                    int sum = 0;
                    for(int i = 0; i <line.length; i++){
                        sum += Integer.parseInt(line[i]);
                    }
                    //Write output stream to a file
                    writer.println(Integer.toString(sum));
                } 
                String message = "File content read successfully! \n" 
                			+"Please refresh the project file to see the output file";
                JOptionPane.showMessageDialog(null, message);
                scan.close();
                writer.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }  
    } 
}
