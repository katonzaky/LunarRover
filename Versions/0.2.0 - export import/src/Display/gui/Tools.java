package Display.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Display.Display;
import Display.gfx.Assets;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

public class Tools {
	private JPanel Toolbar;
	private JButton Export, Import, Destination, addNGZ, removeNGZ, help;
	private JFileChooser importFile, exportFile;
	private int x, y, width, height;
	private Display display;
	private Image tools[];
	private FileNameExtensionFilter filter;
	
	public Tools(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
		tools = new Image[6];
		
		tools[0] = Assets.icn_export.getScaledInstance((int)(Assets.icn_export.getHeight()*display.getsHeight()), (int) (Assets.icn_export.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		tools[1] = Assets.icn_import.getScaledInstance((int)(Assets.icn_import.getHeight()*display.getsHeight()), (int) (Assets.icn_import.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		tools[2] = Assets.icn_destination.getScaledInstance((int)(Assets.icn_destination.getHeight()*display.getsHeight()), (int) (Assets.icn_destination.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		tools[3] = Assets.icn_add.getScaledInstance((int)(Assets.icn_add.getHeight()*display.getsHeight()), (int) (Assets.icn_add.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		tools[4] = Assets.icn_delete.getScaledInstance((int)(Assets.icn_delete.getHeight()*display.getsHeight()), (int) (Assets.icn_delete.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		tools[5] = Assets.icn_help.getScaledInstance((int)(Assets.icn_help.getHeight()*display.getsHeight()), (int) (Assets.icn_help.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		
		createTools();
	}
	
	private void createTools() {
		//Creates toolbar panel
		Toolbar = new JPanel();
		Toolbar.setBackground(Color.LIGHT_GRAY);
		Toolbar.setBounds(x, y, width, height);
		Toolbar.setLayout(null);
		
		//EXPORT BUTTON
		Export = new JButton("Export");
		Export.setFocusable(false);
		Export.setIcon(new ImageIcon(tools[0]));
		Export.setVerticalTextPosition(SwingConstants.BOTTOM);
		Export.setToolTipText("Export map");
		Export.setOpaque(false);
		Export.setHorizontalTextPosition(SwingConstants.CENTER);
		Export.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		Export.setFocusPainted(false);
		Export.setBorderPainted(true);
		Export.setBackground((Color) null);
		Export.setBounds((int) (0*display.getsWidth()), (int) (0*display.getsHeight()), (int) (140*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(Export);
		filter = new FileNameExtensionFilter("XML FILES", "xml");
		
		//IMPORT BUTTON
		Import = new JButton("Import");
		Import.setFocusable(false);
		Import.setIcon(new ImageIcon(tools[1]));
		Import.setVerticalTextPosition(SwingConstants.BOTTOM);
		Import.setToolTipText("Import map");
		Import.setOpaque(false);
		Import.setHorizontalTextPosition(SwingConstants.CENTER);
		Import.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		Import.setFocusPainted(false);
		Import.setBorderPainted(true);
		Import.setBackground((Color) null);
		Import.setBounds((int) (140*display.getsWidth()), (int) (0*display.getsHeight()), (int) (140*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(Import);
		
		//ADD DESTINATION BUTTON
		Destination = new JButton("Destination");
		Destination.setFocusable(false);
		Destination.setIcon(new ImageIcon(tools[2]));
		Destination.setVerticalTextPosition(SwingConstants.BOTTOM);
		Destination.setToolTipText("Add destination");
		Destination.setOpaque(false);
		Destination.setHorizontalTextPosition(SwingConstants.CENTER);
		Destination.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		Destination.setFocusPainted(false);
		Destination.setBorderPainted(true);
		Destination.setBackground((Color) null);
		Destination.setBounds((int) (280*display.getsWidth()), (int) (0*display.getsHeight()), (int) (140*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(Destination);
		
		//ADD NO-GO-ZONE BUTTON
		addNGZ = new JButton("Add NGZ");
		addNGZ.setFocusable(false);
		addNGZ.setIcon(new ImageIcon(tools[3]));
		addNGZ.setVerticalTextPosition(SwingConstants.BOTTOM);
		addNGZ.setToolTipText("Add No-Go-Zone");
		addNGZ.setOpaque(false);
		addNGZ.setHorizontalTextPosition(SwingConstants.CENTER);
		addNGZ.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		addNGZ.setFocusPainted(false);
		addNGZ.setBorderPainted(true);
		addNGZ.setBackground((Color) null);
		addNGZ.setBounds((int) (420*display.getsWidth()), (int) (0*display.getsHeight()), (int) (140*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(addNGZ);
		
		//REMOVE NO-GO-ZONE BUTTON
		removeNGZ = new JButton("Remove NGZ");
		removeNGZ.setFocusable(false);
		removeNGZ.setIcon(new ImageIcon(tools[4]));
		removeNGZ.setVerticalTextPosition(SwingConstants.BOTTOM);
		removeNGZ.setToolTipText("Remove No-Go-Zone");
		removeNGZ.setOpaque(false);
		removeNGZ.setHorizontalTextPosition(SwingConstants.CENTER);
		removeNGZ.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		removeNGZ.setFocusPainted(false);
		removeNGZ.setBorderPainted(true);
		removeNGZ.setBackground((Color) null);
		removeNGZ.setBounds((int) (560*display.getsWidth()), (int) (0*display.getsHeight()), (int) (140*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(removeNGZ);
		
		//OPEN HELP MANUAL BUTTON
		help = new JButton("Help");
		help.setFocusable(false);
		help.setIcon(new ImageIcon(tools[5]));
		help.setVerticalTextPosition(SwingConstants.BOTTOM);
		help.setToolTipText("Open help file");
		help.setOpaque(false);
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setFont(new Font("Tahoma", Font.BOLD, (int) (15*display.getsWidth())));
		help.setFocusPainted(false);
		help.setBorderPainted(true);
		help.setBackground((Color) null);
		help.setBounds((int) (700*display.getsWidth()), (int) (0*display.getsHeight()), (int) (100*display.getsWidth()), (int) (70*display.getsHeight()));
		Toolbar.add(help);
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desktop = Desktop.getDesktop();
				File file = new File("help.pdf");
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Import.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importFile = new JFileChooser();
				importFile.setFileFilter(filter);
				importFile.setDialogTitle("Import file");
				importFile.setCurrentDirectory(new File("."));
				int returnVal = importFile.showOpenDialog(null);
				if(returnVal  == JFileChooser.APPROVE_OPTION)
				{
					File f = importFile.getSelectedFile();
					String filename = f.getAbsolutePath();
					File file = new File(filename);
					readXML(file);
				}
			}
		});
		
		Export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportFile = new JFileChooser();
				exportFile.setFileFilter(filter);
				exportFile.setDialogTitle("Export file");
				exportFile.setCurrentDirectory(new File("."));
				int returnVal = exportFile.showSaveDialog(null);
				if(returnVal  == JFileChooser.APPROVE_OPTION)
				{
					File f = exportFile.getSelectedFile();
					String filename = f.getAbsolutePath();
					writeXML(filename);
				}
			}
		});
	}
	
	public void tick() {
		Export.setIcon(new ImageIcon(tools[0]));
		Import.setIcon(new ImageIcon(tools[1]));
		Destination.setIcon(new ImageIcon(tools[2]));
		addNGZ.setIcon(new ImageIcon(tools[3]));
		removeNGZ.setIcon(new ImageIcon(tools[4]));
		help.setIcon(new ImageIcon(tools[5]));
	}

	public JPanel getToolbar() {
		return Toolbar;
	}
	
	public void readXML(File file) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setIgnoringElementContentWhitespace(true);
			dbFactory.setIgnoringComments(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList vsList = doc.getElementsByTagName("vehicle-status");
			 
			for (int x = 0; x < vsList.getLength(); x++) {
				//Node nNode = vsList.item(x);
				NodeList pointList = doc.getElementsByTagName("point");
				 
				for (int i = 0; i < pointList.getLength(); i++) {
					Node xNode = pointList.item(i);
					
					if (xNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) xNode;
						int getx = (int) Float.parseFloat(eElement.getAttribute("x"));
						display.getRover().setX(getx);
						int gety = (int) Float.parseFloat(eElement.getAttribute("y"));
						display.getRover().setY(gety);
					}
				}
 
				NodeList attList = doc.getElementsByTagName("attribute");
 
				for (int i = 0; i < attList.getLength(); i++) {
					Node sNode = attList.item(i);
					
					if (sNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) sNode;
						int getHeading = (int) Float.parseFloat(eElement.getElementsByTagName("value").item(0).getTextContent());
						display.getRover().setHeading(getHeading);
					}
				}
         
         
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeXML(String filename) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("lunarrovermap");
			doc.appendChild(rootElement);

			//vehicle-status elements
			Element vehicleStat = doc.createElement("vehicle-status");
			rootElement.appendChild(vehicleStat);

			//point element
			Element point = doc.createElement("point");
			
			//point-x attribute
			Attr pointX = doc.createAttribute("x");
			pointX.setValue(String.valueOf(display.getRover().getX()));
			point.setAttributeNode(pointX);
			
			//point-y attribute
			Attr pointY = doc.createAttribute("y");
			pointY.setValue(String.valueOf(display.getRover().getY()));
			point.setAttributeNode(pointY);
			vehicleStat.appendChild(point);

			//attribute elements
			Element attribute = doc.createElement("attribute");
			
			//Key elements
			Element key = doc.createElement("key");
			key.appendChild(doc.createTextNode("heading"));
			attribute.appendChild(key);
			
			//Heading value elements
			Element value = doc.createElement("value");
			value.appendChild(doc.createTextNode(String.valueOf(display.getRover().getHeading())));
			attribute.appendChild(value);
			vehicleStat.appendChild(attribute);

			//Write to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result;
			if(filename.contains(".xml")){
				result = new StreamResult(new File(filename));
			}
			else{
				result = new StreamResult(new File(filename+".xml"));
			}

			transformer.transform(source, result);

		  } 
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			} 
		catch (TransformerException tfe) {
			tfe.printStackTrace();
			}
	}
}
