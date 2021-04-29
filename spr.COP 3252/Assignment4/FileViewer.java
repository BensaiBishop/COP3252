/////////////////////////////////////////////////////////////////////
/*
//Author: Benjamin Bishop
//Date: 28 Feb 2021
//Class: COP3252

A FileViwer that should accept the following command line options:
-i (for (i)nformation) with an optional file or directory as a 2nd parameter.
If no 2nd parameter is passed, default to the current directory   .
-v (for (v)iew) with a file to view as the 2nd parameter
-c (for (c)opy) with a sourcefile as the 2nd parameter and a destination file as the 3rd parameter
If no parameters are passed, then the program should default
to displaying information on the current directory (as if the user had passed -i)

//I kept getting an error, error: unmappable character (0x90) for encoding windows; within theses commments

extra credit: "-d" for difference; will compare two files.
*/
/////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;

public class FileViewer
{
  public static void main(String[] args) throws FileNotFoundException, IOException
  {


    boolean noParamtersGiven = false; // a bool to tell if it should run accompanying function

    boolean information = false;      // a bool to tell if it should run accompanying function
		boolean view = false;             // a bool to tell if it should run accompanying function
    boolean copy = false;             // a bool to tell if it should run accompanying function
		boolean difference = false;       // a bool to tell if it should run accompanying function

    String infoParameter = " ";       // parameters for -i
		String viewParameter = " ";       // parameters for -v
    String copyParameter1 = " ";      // parameters for -c
    String copyParameter2 = " ";      // parameters for -c
		String differenceParameter1 = " ";   // parameters for -d
		String differenceParameter2 = " ";   // parameters for -d


    // will need a list of possible args passed.
    if (args.length == 0)
    {
      // our default case, with no parameters
      infoParameter= ".";
      noParamtersGiven = true;
      // give path and files within
      information = true;
    }
    else if (args.length == 1)
    {
      // a parameter was passed
      if (args[0].equals("-i")){// give information about default, as a second parameter wasn't passed
        information = true;
        infoParameter = ".";
      }
    }
    else if(args. length == 2)
    {
      //two parameters passed
      if(args[0].equals("-i")){
        information = true;
        infoParameter = args[1];
      }
      else if(args[0].equals("-v")){
        view = true;
        viewParameter = args[1];
      }
      // can't do anything with copy with args 2
      else if(args[0].equals("-c")){}
      else{
        // usage error
        System.out.print("Usage: java -jar hw4.jar [-i [<file>|<directory>]|-v <file>|-c <sourceFile> <destFile>|-d <file1> <file2>]");
				return;
      }
    }
    else if(args. length == 3)
    {
      //three parameters passed either copy or compare
      if(args[0].equals("-c")){
        copy = true;
        copyParameter1 = args[1];
        copyParameter2 = args[2];
      }
      else if (args[0].equals("-d")){
        difference = true;
        differenceParameter1 = args[1];
        differenceParameter2 = args[2];
      }
      else{
        // usage error
        System.out.print("Usage: java -jar hw4.jar [-i [<file>|<directory>]|-v <file>|-c <sourceFile> <destFile>|-d <file1> <file2>]");
				return;
      }
    }
    else
    {
      // usage error
      System.out.print("Usage: java -jar hw4.jar [-i [<file>|<directory>]|-v <file>|-c <sourceFile> <destFile>|-d <file1> <file2>]");
      return;
    }
//Defining the starting parameters
    if(information == true)// code if the user passed -i and parameters if any
    {
        //file object for directory
      File path = new File(infoParameter);

      long size = 0;

      if(args.length <= 1){
        infoParameter = ".";//default to directory
        System.out.println("List of files and directories in specified directory");
        //List of all files and directories
        File filesList[] = path.listFiles();
        for(File file : filesList){
			size = file.length();
          //get files name
          System.out.println("File name: "+file.getName());
          //get files path
          System.out.println("File path: "+file.getAbsolutePath());
          //get files size
          System.out.println("Size: "+file.length()+ " bytes");
          //date last modified
          System.out.print("Last Modified: ");
          long time = 0;
          time = file.lastModified();
          Date date = new Date(time);
          System.out.println(date);
          System.out.println("");
        }
      }
      else if(args.length == 2){
		if (path.isFile()){
			//get files name
			System.out.println("File name: "+path.getName());
			//get files path
			System.out.println("File path: "+path.getAbsolutePath());
			//executable?
			System.out.print("Is Executable: ");
			
			  if(path.canExecute()){
				System.out.println("True");
				}
				else{
				  System.out.println("False");
				}

			//get files size
			System.out.println("Size: "+path.length()+ " bytes");
			//date last modified
			System.out.print("Last Modified: ");
			long time = 0;
			time = path.lastModified();
			Date date = new Date(time);
			System.out.println(date);
			System.out.println("");
		}  
		else if(path.isDirectory()){
        //List of all files and directories
		infoParameter = ".";
        File filesList[] = path.listFiles();
          System.out.println("List of files and directories in specified directory");
			  for(File file : filesList){
				//get files name
				System.out.println("File name: "+file.getName());
				//get files path
				System.out.println("File path: "+file.getAbsolutePath());
				//get files size
				System.out.println("Size: "+file.getTotalSpace());
				//date last modified
				System.out.print("Last Modified: ");
				long time = 0;
				time = file.lastModified();
				Date date = new Date(time);
				System.out.println(date);
				System.out.println("");
			    }
			}
        }
        else //if none of the parameters are met then it could be an error
          System.out.print("Unexpected Error occurred");
      }//end of information
    if(view == true)//if -v is passed
    {
      try{
          //File path = new File(viewParameter);
                  //scanner for contents of the file.
                  Scanner scan = new Scanner(new File(viewParameter));
                  String input;
                  StringBuffer buffer = new StringBuffer();
                  //while scanner can get lines, append to the buffer then print contents of file
                  while(scan.hasNextLine()){
                    input = scan.nextLine();
                    buffer.append(input+" ");
                  }
                  System.out.println("Contents of file: \n"+buffer.toString());
                  System.out.println(" ");
        }
        catch(FileNotFoundException ex){System.out.println(ex);}
    }//end of view
    if(copy == true)//if -c is passed
    {
      File source = new File(copyParameter1);
      File destination = new File(copyParameter2);
      if(source.isFile())
      {
        if(destination.isFile())//if the file exist don't overwrite 
          System.out.println("Error: Destination file already exists");
          else{
            try(FileReader reader = new FileReader(copyParameter1); FileWriter writer = new FileWriter(copyParameter2);)
            {     
				int x = -1;
				while((x = reader.read()) != -1){writer.write(x);}
            }
            catch(IOException ex){System.err.println(ex);}
          }
      }
      else
        System.out.print("Error: Source is not valid file");
    }//end of copy
    if(difference == true){
      int x = 0;
      int y = 0;

        try{
              File file1 = new File(differenceParameter1);
              File file2 = new File(differenceParameter2);

              //test if files are same size first
              if(file1.length() == file2.length()){
                InputStream in1 = new BufferedInputStream(new FileInputStream(file1));
  					    InputStream in2 = new BufferedInputStream(new FileInputStream(file2));
  				          while (x != -1 || y != -1){
                      //just read to the ints and see if they are the same
                      x = in1.read();
                      y = in2.read();
                      if(x != y)
                        System.out.print("The files are different");
                    }
                    System.out.print("The files are the same");
               }
               else
                System.out.print("The files are different");
      }
      catch(FileNotFoundException ex){};
    }//end of difference
  }//end of main
}//end of FileViwer class
