import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class MRRDataCollector
{
	private static int CELL_TYPE_STRING = 1;
	private static int CELL_TYPE_BLANK = 3;
	//ArrayList to hold all the RMAProblem details
	//Each ArrayList will hold RMAProblems with the same Product Number
	//Overall Arraylist rmaData will hold all of the ArrayLists with RMAProblem data
	ArrayList<ArrayList<RMAProblem>> rmaData; 
	public MRRDataCollector()
	{
		rmaData = new ArrayList<ArrayList<RMAProblem>>(); 
	}
	
	/*
	 * Reads an excel file with RMA Problem information and stores the data into the rmaData list
	 * Uses file's column titles to determine what part of an RMA Problem the contents of the file are a part of
	 * After using a row's data to create an RMAProblem, add that RMAProblem to the rmaData array
	 * @param String filePath: location of the File to be read
	 */
	public void readFile(String filePath)
	{
		try 
		{
			System.out.println();
		    System.out.println(new File("work location").getAbsolutePath());
		    File test = new File(filePath);
		    System.out.println(test.exists());
		    System.out.println();

		    //need to use filePath instead of fileName: program cannot access a file just by fileName after program has been exported
		    //reads in an exel file from before Excel 2007
		    //need to handle pre 2007 and post 2007 excel files differently: pre 2007 files are .xls and are handled as HSSFWorkbooks while post 2007 files are .xlsx and are handled as XSSFWorkbooks 
		    if(filePath.contains("xlsx") == false)
			{
		    	FileInputStream fs = new FileInputStream(filePath); 
			    HSSFWorkbook wb = new HSSFWorkbook(fs);
			    HSSFSheet sheet = wb.getSheetAt(0);
			    for(int s = 0; s < wb.getNumberOfSheets(); s++)
			    {
			    	if(wb.getSheetAt(s).getSheetName().contains("Details"))
			    	{
			    		if(!wb.getSheetAt(s).getSheetName().contains("DOA"))
			    		{
			    			sheet = wb.getSheetAt(s);
			    			//System.out.println(sheet.getSheetName()); used to check if correct sheet chosen
			    		}
			    	}
			    	else
			    	{
			    		System.out.println("File doesn't contain Details page");
			    	}
			    }
			    
			    HSSFRow row; 
			    HSSFRow firstRow; //first row of the excel file: used to determine categories
			    HSSFCell cell;
			    HSSFCell category; //cell from firstRow used to tell what type of information a cell in a specific column is
			    String cateContents; //contents of category cell
			    String contents;
			    
	
			    int rows; // No of rows
			    rows = sheet.getPhysicalNumberOfRows();
	
			    int cols = 0; // No of columns
			    int tmp = 0;
	
			    // This trick ensures that we get the data properly even if it doesn't start from first few rows
			    for(int i = 0; i < 10 || i < rows; i++) 
			    {
			        row = sheet.getRow(i);
			        if(row != null) 
			        {
			            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
			            if(tmp > cols) cols = tmp;
			        }
			    }
			    RMAProblem prob = new RMAProblem(); //RMAProblem object to be added to 2D array
			    firstRow = sheet.getRow(0);
			    
			    //go up till rows-2 because excel file has a buffer of 2 blank rows at the end of the file
			    for(int r = 1; r < rows-2; r++) 
			    {
			        row = sheet.getRow(r);
			        if(row != null) 
			        {
			        	//checks an row in the file and creates an RMAProblem object from the column contents
			            for(int c = 0; c < cols; c++) 
			            {
			            	
			            	category = firstRow.getCell(c);
			                cell = row.getCell(c);
			                if(cell != null) 
			                {
			                	cateContents = category.getStringCellValue();
			                	//System.out.println(cateContents);	
			                	if(cell.getCellType() == CELL_TYPE_STRING || cell.getCellType() == CELL_TYPE_BLANK)
			                	{
				                	contents = cell.getStringCellValue();
				                	//System.out.println(contents);
				                	if(cateContents.equals("Product No"))
				                	{
				                		prob.setProdNo(contents);
				                		//System.out.println(prob);
				                	}
				                	else if(cateContents.equals("Failed Obj 1"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setFailedObj("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setFailedObj(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("NTF Code"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setNTFCode("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setNTFCode(contents);
				                    	}
				                    	//System.out.println(contents);
				                    }
				                	else if(cateContents.equals("Component Jnpr Part"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setCompPart("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setCompPart(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("Serial No"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setSerialNo("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setSerialNo(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("RMA Detail Number"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setRMADetailNo("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setRMADetailNo(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("SR_CUSTOMER_NAME"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setSrCustomer("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setSrCustomer(contents);
				                    	}			                    	
				                    }
			                	}
			                }
			            }
			            //System.out.println(prob); 
			            
			            this.addProb(prob);
			        }
			        wb.close();
			        //System.out.println(rmaData);
			    }
			}
		    
		    //reads in an Excel file from Excel 2007 or later
		    else //if file is .xlsx file
		    {
			    FileInputStream fs = new FileInputStream(filePath); 
			    XSSFWorkbook wb = new XSSFWorkbook(fs);
			    XSSFSheet sheet = wb.getSheetAt(0);
			    for(int s = 0; s < wb.getNumberOfSheets(); s++)
			    {
			    	if(wb.getSheetAt(s).getSheetName().contains("Details"))
			    	{
			    		if(!wb.getSheetAt(s).getSheetName().contains("DOA"))
			    		{
			    			sheet = wb.getSheetAt(s);
			    			//System.out.println(sheet.getSheetName()); used to test if getting correct page
			    		} 
			    	}
			    }
			    
			    XSSFRow row; 
			    XSSFRow firstRow; //first row of the excel file: used to determine categories
			    XSSFCell cell;
			    XSSFCell category; //cell from firstRow used to tell what type of information a cell in a specific column is
			    String cateContents; //contents of category cell
			    String contents;
			    
	
			    int rows; // No of rows
			    rows = sheet.getPhysicalNumberOfRows();
	
			    int cols = 0; // No of columns
			    int tmp = 0;
	
			    // This trick ensures that we get the data properly even if it doesn't start from first few rows
			    for(int i = 0; i < 10 || i < rows; i++) 
			    {
			        row = sheet.getRow(i);
			        if(row != null) 
			        {
			            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
			            if(tmp > cols) cols = tmp;
			        }
			    }
			    RMAProblem prob = new RMAProblem(); //RMAProblem object to be added to 2D array
			    firstRow = sheet.getRow(0);
			    
			    //go up till rows-2 because excel file has a buffer of 2 blank rows at the end of the file
			    for(int r = 1; r < rows-2; r++) 
			    {
			        row = sheet.getRow(r);
			        if(row != null) 
			        {
			        	//checks an row in the file and creates an RMAProblem object from the column contents
			            for(int c = 0; c < cols; c++) 
			            {
			            	
			            	category = firstRow.getCell(c);
			                cell = row.getCell(c);
			                if(cell != null) 
			                {
			                	cateContents = category.getStringCellValue();
			                	//System.out.println(cateContents);	
			                	if(cell.getCellType() == CELL_TYPE_STRING || cell.getCellType() == CELL_TYPE_BLANK)
			                	{
				                	contents = cell.getStringCellValue();
				                	//System.out.println(contents);
				                	if(cateContents.equals("Product No"))
				                	{
				                		prob.setProdNo(contents);
				                		//System.out.println(prob);
				                	}
				                	else if(cateContents.equals("Failed Obj 1"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setFailedObj("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setFailedObj(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("NTF Code"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setNTFCode("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setNTFCode(contents);
				                    	}
				                    	//System.out.println(contents);
				                    }
				                	else if(cateContents.equals("Component Jnpr Part"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setCompPart("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setCompPart(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("Serial No"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setSerialNo("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setSerialNo(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("RMA Detail Number"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setRMADetailNo("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setRMADetailNo(contents);
				                    	}
				                    }
				                	else if(cateContents.equals("SR_CUSTOMER_NAME"))
				                    {
				                    	if(cell.getCellType() == CELL_TYPE_BLANK)
				                    	{
				                    		prob.setSrCustomer("not provided");
				                    	}
				                    	else
				                    	{
				                    		prob.setSrCustomer(contents);
				                    	}			                    	
				                    }
			                	}
			                }
			            }
			            //System.out.println(prob); 
			            
			            this.addProb(prob);
			        }
			        wb.close();
			        //System.out.println(rmaData);
			    }
		    }
		} 
		catch(Exception ioe) 
		{
		    ioe.printStackTrace();
		}
	}
	
	/*
	 * Adds an RMAProblem object to the rmaData ArrayList
	 * Searches first to see which ArrayList contains RMAProblems with the same Product number as 
	 * the RMAProblem being added
	 * Once that ArrayList is found, add the RMAProblem to the list
	 */
	public void addProb(RMAProblem p)
	{
		boolean added = false;
		int index = 0;
		String currProd = "";
		RMAProblem newProb = new RMAProblem();
		
		//Since adding the original RMAProblem to an ArrayList only adds a reference to that object,
		//have to make a new RMAProblem with the same information to put into the ArrayList
		newProb.setProdNo(p.getProdNo());
		newProb.setCompPart(p.getCompPart());
		newProb.setFailedObj(p.getFailedObj());
		newProb.setNTFCode(p.getNTFCode());
		newProb.setRMADetailNo(p.getRMADetailNo());
		newProb.setSerialNo(p.getSerialNo());
		newProb.setSrCustomer(p.getSrCustomer());
		
		//While the RMAProblem hasn't been added yet, keep looking for a place to put it
		while(added == false)
		{
			//Handles special case when the rmaData list is empty
			if(rmaData.size() == 0)
			{
				rmaData.add(new ArrayList<RMAProblem>());
				rmaData.get(index).add(newProb);
				added = true;
				//System.out.println(rmaData.get(0));
				//System.out.println("TEST");
			}
			else
			{
				//if you are still looking through the ArrayLists, check if the RMAProblems in the 
				//ArrayLists have the same Product Number as the RMAProblem being added
				if(index < rmaData.size())
				{
					currProd = rmaData.get(index).get(0).getProdNo();
					//System.out.print(currProd + " " + index + " ");
					//System.out.println(p);
					if(!currProd.equals(newProb.getProdNo()))
					{
						index++;
						//System.out.println("TEST");
					}
					else
					{
						rmaData.get(index).add(newProb);
						added = true;
						//System.out.println(rmaData.get(index));
						//System.out.println("TEST");
					}
				}
				//If the RMAProduct is not found within the currently existing ArrayLists,
				//Make a new ArrayList to hold the RMAProblem
				else
				{
					ArrayList<RMAProblem> newList = new ArrayList<RMAProblem>();
					newList.add(newProb);
					rmaData.add(newList);
					//System.out.println("Test");
					added = true;
				}
			}
			
		}
	}
	
	/*
	 * 	prints out all of the RMA problems in the file read by this program
	 */
	public void printRMADetails()
	{
		int numRMA = 0;
		for(int r = 0; r < rmaData.size(); r++)
		{
			for(int c = 0; c < rmaData.get(r).size(); c++)
			{
				System.out.println(rmaData.get(r).get(c));
				numRMA++;
			}
		}
		System.out.println(numRMA);
	}
	
	/*
	 * returns the list of lists containing RMAProblems within the file read
	 * @return ArrayList<ArrayList<RMAProblem>>: the list of lists containing RMAProblems
	 */
	public ArrayList<ArrayList<RMAProblem>> getData()
	{
		return rmaData;
	}
	
	public static void main(String[] args)
	{
		
	}	
}