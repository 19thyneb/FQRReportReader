import java.util.ArrayList;

public class MRRDataProcessor 
{
	/*
	 * ArrayList categoryList will hold all RMAProblems with the same category content
	 * ex. list will hold all problems of a Product with the same Failed Component, SR customer name,
	 * 		or Juniper Component Part
	 * 
	 * ArrayList numFailed will hold the number of RMAProblems with a certain Failed Component, SR customer name,
	 * 		or Juniper Component Part
	 * 
	 * categoryList and numFailed's indexes will correspond with each other, the number in numFailed at any
	 * given index will be the number of RMAProblems with the Failed Component, SR customer name, or Juniper Component Part
	 * in categoryList at that same index
	 */
	ArrayList<RMAProblem> prob;
	ArrayList<String> categoryList;
	ArrayList<Integer> numFailed;
	
	/*
	 * Constructor for MRRDataProcessor
	 */
	public MRRDataProcessor()
	{
		
	}
	
	/*
	 * Sorts a list of RMAProblems by their Failed Components using insertion sort
	 * @param ArrayList<RMAProblem> p: the RMAProblem list to be processed
	 */
	public void processFailedComponentData(ArrayList<RMAProblem> p)
	{
		
		prob = p;
		categoryList = new ArrayList<String>();
		numFailed = new ArrayList<Integer>();
		
		String probComp = "";
		
		for(int i = 0; i < prob.size(); i++)
		{
			probComp = prob.get(i).getFailedObj();
			if(categoryList.contains(probComp) == false)
			{
				categoryList.add(probComp);
				numFailed.add(1);
			}
			else
			{
				int probLoc = categoryList.indexOf(probComp);
				int currNum = numFailed.get(probLoc);
				numFailed.set(probLoc,currNum+1);
			}
		}
		this.sortData();
	}  	   
	
	/*
	 * Sorts a list of RMAProblems by their Juniper Component Part
	 * @param ArrayList<RMAProblem> p: the RMAProblem list to be processed
	 */
	public void processJuniperComponent(ArrayList<RMAProblem> p)
	{
		
		prob = p;
		categoryList = new ArrayList<String>();
		numFailed = new ArrayList<Integer>();
		
		String probComp = "";
		
		for(int i = 0; i < prob.size(); i++)
		{
			probComp = prob.get(i).getCompPart();
			if(categoryList.contains(probComp) == false)
			{
				categoryList.add(probComp);
				numFailed.add(1);
			}
			else
			{
				int probLoc = categoryList.indexOf(probComp);
				int currNum = numFailed.get(probLoc);
				numFailed.set(probLoc,currNum+1);
			}
		}
		this.sortData();
	}  	   
	
	/*
	 * Sorts a list of RMAProblems by their SR Customer Name
	 * @param ArrayList<RMAProblem> p: the RMAProblem list to be processed
	 */
	public void processSRCustomerName(ArrayList<RMAProblem> p)
	{
		
		prob = p;
		categoryList = new ArrayList<String>();
		numFailed = new ArrayList<Integer>();
		
		String probComp = "";
		
		for(int i = 0; i < prob.size(); i++)
		{
			probComp = prob.get(i).getSrCustomer();
			if(categoryList.contains(probComp) == false)
			{
				categoryList.add(probComp);
				numFailed.add(1);
			}
			else
			{
				int probLoc = categoryList.indexOf(probComp);
				int currNum = numFailed.get(probLoc);
				numFailed.set(probLoc,currNum+1);
			}
		}
		this.sortData();
	}  	   
	
	/*
	 * Sorts the contents of categoryList and numFailed
	 * Uses insertion sort to sort numFailed; with each iteration in the sort, does the same swap in categoryList
	 * to keep categoryList and numFailed corresponding to each other in the same way
	 */
	public void sortData()
	{
		String tempObj = "";
		int tempCount = -1;
		for(int i = 1; i < numFailed.size(); i++)
		{
			tempObj = categoryList.get(i);
			tempCount = numFailed.get(i);
			for(int j = i-1; j >= 0; j--)
			{
				if(tempCount > numFailed.get(j))
				{
					numFailed.set(j+1, numFailed.get(j));
					categoryList.set(j+1, categoryList.get(j));
					numFailed.set(j, tempCount);
					categoryList.set(j, tempObj);
				}
			}
		}
	}
	
	/*
	 * prints out the contents of categoryList and numFailed together
	 * @param int size: the number of elements you want to print
	 * 
	 * if there are fewer elements than "size", prints out all elements
	 * else print out "size" number of elements
	 */
	public void printData(int size)
	{
		int length = 0;
		if(size >= categoryList.size())
		{
			length = categoryList.size();
		}
		else
		{
			length = size;
		}
		System.out.println("******" + prob.get(0).getProdNo() + "******");
		int count = 0;
		while(count < length)
		{
			System.out.println(categoryList.get(count) + "   " + numFailed.get(count));
			if(count == length-1)
			{
				System.out.println();
				System.out.println();
			}
			count++;
		}
	}
	
	public static void main(String[] args)
	{
		
		
	}
	
}
