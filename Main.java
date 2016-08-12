import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import jdk.nashorn.internal.codegen.FileDrop;

public class Main 
{
	//Creates a GUI with a filedrop
	//once you drag and drag an excel file of an FQR report, a new window will
	//pop up and will allow you to view specific information about each type of product 
	//in the field.
	public static void main(String[] args)
	{
		//File here = new File("."); used to check current directory
		
		JFrame fileArea = new JFrame();
		fileArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileArea.setSize(500, 200);
				
		JPanel dropPanel = new JPanel(new BorderLayout()); //place where to drag and drop files to be processed
				
		JLabel lbl = new JLabel("Drag and drop an FQR Report file into the area below.");
		JTextArea dropArea = new JTextArea("**drop file here**");
		dropArea.setEditable(false);
		//dropArea.append("\n" + here.getAbsolutePath() + "\n");
				
		dropPanel.add(lbl, BorderLayout.NORTH);
		dropPanel.add(dropArea, BorderLayout.CENTER);

		fileArea.add(dropPanel);
		fileArea.setVisible(true);
				
		//file drop object to handle files dragged and dropped into dropArea
		FileDrop dropper = new FileDrop( System.out, dropArea, /*dragBorder,*/new FileDrop.Listener()
		{   
			//takes in a dropped file 
			//when a file is dropped into the GUI, try to read the file and output information
			//about the MRR data
			public void filesDropped( java.io.File[] files )
			{   
				for( int i = 0; i < files.length; i++ )
				{   
					try
					{   
						//File here = new File("here"); used to check location of dropped file
						//dropArea.append("\n" + here.getAbsolutePath() + "\n"); displays location of dropped file
						File excelFile = files[i].getAbsoluteFile(); //makes copy of dropped files
						if(excelFile.exists() == true)
						{
							dropArea.append("\n" + "file drop successful" + "\n");
						}
						else
						{
							dropArea.append("\n" + "filed drop unsuccessful" + "\n");
						}
						
						//dropArea.append("\n" + excelFile.exists() + "\n"); used to check if file drop worked
						dropArea.append("dropped file location: " + files[i].getCanonicalPath() + "\n" );
						dropArea.append("file name: " + excelFile.getName());
						MRRCreatorGUI gui = new MRRCreatorGUI(excelFile.getAbsolutePath());
					}   // end try
					catch( java.io.IOException e ) {}
				}   // end for: through each dropped file
			}   // end filesDropped
		});
	}	
}
