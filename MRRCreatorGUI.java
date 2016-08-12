import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.*;


public class MRRCreatorGUI extends JFrame
{
	ArrayList<ArrayList<RMAProblem>> data;
	MRRDataCollector collector;
	MRRDataProcessor processor;
    JComboBox<String> cb;
    JTextArea dropArea;
    JTextArea dataDisplay;
    
    /*
     * creates a GUI that allows users to see the Failed Components, Juniper Component parts, and
     * SR Customer names or a product of their choosing
     * @param String fileName: name of the File with all of the RMAProblem information
     */
	public MRRCreatorGUI(String filePath)
	{
		collector = new MRRDataCollector();
		processor = new MRRDataProcessor();
		data = collector.getData();
	    dataDisplay = new JTextArea();
	    dataDisplay.setEditable(false);
	    
	    collector.readFile(filePath);
		String[] choices = new String[collector.getData().size()];
	    for(int c = 0; c < choices.length; c++)
		{
			choices[c] = data.get(c).get(0).getProdNo();
		}
		sortChoices(choices);
		cb = new JComboBox<String>(choices);
		
		//sets up frame where MRR information will be displayed
		 JFrame main = new JFrame(filePath.substring(filePath.lastIndexOf("/")+1,filePath.length())); //frame name is the fileName
		    main.setVisible(true);
		    main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    main.setSize(1000, 500);
		    main.setLocation(430, 100);

		    //sets layout of the frame to BorderLayout 
		    //panelGrid will be used to store buttons, and displays
		    BorderLayout mainLayout = new BorderLayout();
		    GridLayout panelGrid = new GridLayout(3,1);
		    main.setLayout(mainLayout);
		    
		    JPanel optionPanel = new JPanel();
		    optionPanel.setLayout(panelGrid);
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setLayout(new GridLayout(1,3));
	        
		    main.add(optionPanel,BorderLayout.NORTH); 
		    main.add(dataDisplay, BorderLayout.CENTER);

		    cb.setVisible(true);
		    optionPanel.add(cb);

		    JButton failedBtn = new JButton("Failed Obj 1");
		    failedBtn.addActionListener(new FailedObjListener());
		    buttonPanel.add(failedBtn);
		    
		    JButton compBtn = new JButton("Juniper Component Part");
		    compBtn.addActionListener(new JnprPartListener());
		    buttonPanel.add(compBtn);
		    
		    JButton custBtn = new JButton("SR Customer Name");
		    custBtn.addActionListener(new SRCustomerListener());
		    buttonPanel.add(custBtn);
		    
		    optionPanel.add(buttonPanel);
	}
	
	/*
	 * ActionListener for the "Failed Obj 1" button. When pressed, displays the top 10 Failed Objects
	 * for the product currently chosen in GUI's dropbox.
	 */
	private class FailedObjListener implements ActionListener
	{
		String prob;
		ArrayList<RMAProblem> probList; //list of Failed Objects to be sorted

		public FailedObjListener()
		{
			
		}
		public void actionPerformed(ActionEvent e)
		{
			//sets JTextAreaOutputStream to the dataDisplay so that System.out.print will print to the MRRCreatorGUI's dataDisplay
			//have to reset JTextAreaOutputStream whenever one of the display buttons are pressed
			//this allows the use of multiple MRRCreatorGUIs to operate at once and have their own displays
		    JTextAreaOutputStream out = new JTextAreaOutputStream (dataDisplay);
	        System.setOut (new PrintStream (out)); 

			dataDisplay.setText("");
			prob = (String)cb.getSelectedItem();
			for(int i = 0; i < data.size(); i++) 
			{
				if(data.get(i).get(0).getProdNo().equals(prob))
				{
					probList = data.get(i);
				}
			}
			processor.processFailedComponentData(probList);
			processor.printData(10);
		}
	}
	
	/*
	 *ActionListener for the "Juniper Component Part" button. When pressed, displays the top 10 Juniper Component Parts
	 *for the product currently chosen in GUI's dropbox. 
	 */
	private class JnprPartListener implements ActionListener
	{
		String prob;
		ArrayList<RMAProblem> probList;

		public JnprPartListener()
		{
			
		}
		public void actionPerformed(ActionEvent e)
		{
		    JTextAreaOutputStream out = new JTextAreaOutputStream (dataDisplay);
	        System.setOut (new PrintStream (out));

			dataDisplay.setText("");
			prob = (String)cb.getSelectedItem();
			for(int i = 0; i < data.size(); i++)
			{
				if(data.get(i).get(0).getProdNo().equals(prob))
				{
					probList = data.get(i);
				}
			}
			processor.processJuniperComponent(probList);
			processor.printData(10);
		}
	}
	
	/*
	 * ActionListener for the SRCustomer Button
	 * When the button is pressed, prints out a list of the top 10 customers who have the most of the
	 * product chosen in the drop down menu
	 */
	private class SRCustomerListener implements ActionListener
	{
		String prob;
		ArrayList<RMAProblem> probList;

		public SRCustomerListener()
		{
			
		}
		public void actionPerformed(ActionEvent e)
		{
		    JTextAreaOutputStream out = new JTextAreaOutputStream (dataDisplay);
	        System.setOut (new PrintStream (out));

			dataDisplay.setText("");
			prob = (String)cb.getSelectedItem();
			for(int i = 0; i < data.size(); i++)
			{
				if(data.get(i).get(0).getProdNo().equals(prob))
				{
					probList = data.get(i);
				}
			}
			processor.processSRCustomerName(probList);
			processor.printData(10);
		}
	}
	
	/*
	 * Given an array of Strings, sort the array in alphabetical order
	 * Uses insertion sort to organize the array
	 * @param String[] c: array of Strings to be sorted
	 */
	public void sortChoices(String[] c)
	{
		String temp = "";
		for(int i = 1; i < c.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				if(c[j].compareTo(c[j-1]) < 0)
				{
					temp = c[j-1];
					c[j-1] = c[j];
					c[j] = temp;
				}
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		
	}
	
}

