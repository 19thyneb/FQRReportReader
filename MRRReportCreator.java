import java.util.ArrayList;

public class MRRReportCreator 
{
	MRRDataProcessor processor;
	MRRDataCollector collector;
	ArrayList<ArrayList<RMAProblem>> rmaDetails;
	String mrrData;
	
	public MRRReportCreator(String m)
	{
		mrrData = m;
		collector = new MRRDataCollector();
		collector.readFile(mrrData);
		rmaDetails = collector.getData();
		processor = new MRRDataProcessor();
	}
	
	public void createReport()
	{
		System.out.println("******Failed Obj Report******");
		this.createFailedObjReport();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("******Juniper Component Part Report******");
		this.createComponentReport();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("******SR Customer Name Report******");
		this.createCustomerNameReport();
	}
	
	public void createFailedObjReport()
	{
		for(int i = 0; i < rmaDetails.size(); i++)
		{
			processor.processFailedComponentData(rmaDetails.get(i));
			processor.printData(10);
		}
	}
	
	public void createComponentReport()
	{
		for(int i = 0; i < rmaDetails.size(); i++)
		{
			processor.processJuniperComponent(rmaDetails.get(i));
			processor.printData(10);
		}
	}
	
	public void createCustomerNameReport()
	{
		for(int i = 0; i < rmaDetails.size(); i++)
		{
			processor.processSRCustomerName(rmaDetails.get(i));
			processor.printData(10);
		}
	}
	
	public void printRMADetails()
	{
		for(int r = 0; r < rmaDetails.size(); r++)
		{
			for(int c = 0; c < rmaDetails.get(r).size(); c++)
			{
				System.out.println(rmaDetails.get(r).get(c));
			}
		}
	}
	
	public void printRMASample()
	{
		for(int r= 0; r < 1; r++)
		{
			for(int c = 0; c < 10; c++)
			{
				System.out.println(rmaDetails.get(r).get(c));
			}
		}
	}
	
	public MRRDataCollector getCollector()
	{
		return collector;
	}
	
	public static void main(String[] args)
	{
		
	}
}
