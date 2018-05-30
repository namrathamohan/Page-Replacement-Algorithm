package com.lsu.algorithm;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




public class PageReplacementAlgorithm {
		
	int[][] inputPageRef = new int[1000][2];
	int totalPageRef;
	private void readInput(String inputFile){

		//Reads the input file.
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
			String currentLine = null;
			int i = 0;
			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] currentRow = currentLine.split(" ");

				if(currentRow[0].toUpperCase().equals("R")){
					inputPageRef[i][0] = 0;
				} else if (currentRow[0].toUpperCase().equals("W"))
					inputPageRef[i][0] = 1;
				
				inputPageRef[i][1] = Integer.parseInt(currentRow[1].trim());
				
				i++;
			}
			totalPageRef = i;
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
			}
		}

	
	}

	private void LRU(int f){
		int noOfFrames;
		int replaceIndex = 0;
		//int hit = 0;
		int miss = 0;
		int timeUnitsForMiss = 0;
		int timeUnitsForWriteBack = 0;
		ArrayList<Integer> pages = new ArrayList<Integer>();
		Boolean isCacheFull = false;
		int cacheArray[][];  //Frames Array
        
        noOfFrames = f; //no. of Frames
        cacheArray = new int[noOfFrames][2]; //first column represents the read or write bit. 0 for read and 1 for write.
        
        //initialize the cacheArray contents to -1.
        for(int j = 0; j < noOfFrames; j++)
        {
        	cacheArray[j][0] = -1;
        	cacheArray[j][1] = -1;
        }
        
       
        for (int i = 0; i < totalPageRef; i++) { 

            if(pages.contains(inputPageRef[i][1]))
            {
             pages.remove(pages.indexOf(inputPageRef[i][1]));
            }
            pages.add(inputPageRef[i][1]);
            int found = 0;
            for(int j = 0; j < noOfFrames; j++)
            {
                if(cacheArray[j][1] == inputPageRef[i][1])
                {
                    found = 1;
          //          hit++;
                    break;
                }
            }
            if(found == 0)
            {
             if(isCacheFull)
             {
              int pageIndex = totalPageRef;
                    for(int k = 0; k < noOfFrames; k++)
                    {
                     if(pages.contains(cacheArray[k][1]))
                        {
                            int tempIndex = pages.indexOf(cacheArray[k][1]);
                            if(tempIndex < pageIndex)
                            {
                            	pageIndex = tempIndex;
                                replaceIndex = k;
                            }
                        }
                    }
             }
             
             // Check if its a dirty page.
             if(cacheArray[replaceIndex][0]==1){
            	 timeUnitsForWriteBack +=10;
            	 timeUnitsForMiss +=15;
             } else {
            	 timeUnitsForMiss +=5;
             }
             cacheArray[replaceIndex][0] = inputPageRef[i][0];
             cacheArray[replaceIndex][1] = inputPageRef[i][1];
             	
                miss++;
                
                replaceIndex++;
                if(replaceIndex == noOfFrames)
                {
                 replaceIndex = 0;
                 isCacheFull = true;
                }
            }

            
		}
        
        System.out.println();
        System.out.println("LRU Algorithm Statistics.");
        System.out.println("---------------------------------------------------");
        System.out.println("The total number of page references: "+totalPageRef);
        System.out.println("The total number of page misses: " + miss);
        System.out.println("The total number of time units for page misses: "+timeUnitsForMiss);
        System.out.println("The total number of time units for writing back the dirty (modified) page: "+timeUnitsForWriteBack);
        System.out.println();
       
	}
	
	private void CLOCK(int f){
		int noOfFrames;  // no. of Frames 
		int replaceIndex = 0;
		int miss = 0;
        int cacheArray[][]; 
        Boolean isCacheFull = false;
        
        int timeUnitsForMiss = 0;
		int timeUnitsForWriteBack = 0;
		
		noOfFrames = f;

        cacheArray = new int[noOfFrames][3];
        for(int j = 0; j < noOfFrames; j++)
        {
        	cacheArray[j][0] = -1;
        	cacheArray[j][1] = -1;
        	cacheArray[j][2] = 0;
        }
        
        for(int i = 0; i < totalPageRef; i++)
        {
         int found = 0;
         for(int j = 0; j < noOfFrames; j++)
         {
          if(cacheArray[j][1] == inputPageRef[i][1])
          {
           found = 1;
           cacheArray[j][2] = 1;
           break;
          } 
         }
         if(found == 0)
         {
        	 if(isCacheFull)
             {
          
        		 while(cacheArray[replaceIndex][2] == 1)
        		 {
        			 cacheArray[replaceIndex][2] = 0;
        			 replaceIndex++;
        			 if(replaceIndex == noOfFrames)
        			 replaceIndex = 0;
        		 }
             }
          //Check for the dirty page.
          if(cacheArray[replaceIndex][0]==1){
         	 timeUnitsForWriteBack +=10;
         	 timeUnitsForMiss +=15;
          } else {
         	 timeUnitsForMiss +=5;
          }
          
          cacheArray[replaceIndex][0] = inputPageRef[i][0];
          cacheArray[replaceIndex][1] = inputPageRef[i][1];
          cacheArray[replaceIndex][2] = 1;
          miss++;
          replaceIndex++;
          
          if(replaceIndex == noOfFrames)
          {
           replaceIndex = 0;
           isCacheFull = true;
          }
     
         }
            
        }
        
        System.out.println();
        System.out.println("CLOCK Algorithm Statistics.");
        System.out.println("---------------------------------------------------");
        System.out.println("The total number of page references: "+totalPageRef);
        System.out.println("The total number of page misses: " + miss);
        System.out.println("The total number of time units for page misses: "+timeUnitsForMiss);
        System.out.println("The total number of time units for writing back the dirty (modified) page: "+timeUnitsForWriteBack);
        System.out.println();
        
}
	
	public static void main(String[] args) {
		PageReplacementAlgorithm solutionValidator = new PageReplacementAlgorithm();
		solutionValidator.readInput(args[2]);
		if(args[0].toUpperCase().equals("LRU")){
			solutionValidator.LRU(Integer.parseInt(args[1]));	
		} else if (args[0].toUpperCase().equals("CLOCK")) {
			solutionValidator.CLOCK(Integer.parseInt(args[1]));
		} else 
			System.out.println("Please specify either LRU or CLOCK");
			
		
		
	}
	
}

