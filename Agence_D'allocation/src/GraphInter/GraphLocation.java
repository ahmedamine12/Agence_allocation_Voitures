package GraphInter;

import Traitement.*;
import javafx.geometry.Dimension2D;

import java.awt.BorderLayout;
// import java.awt.Button;
// import java.awt.Checkbox;
// import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
// import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.ItemEvent;
// import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;

// import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
// import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
// import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.border.Border;
// import javax.swing.border.CompoundBorder;
// import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
// import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class GraphLocation {
	 private JFrame frameorg;
	 private JPanel mainpanel;
	 private JTable table;
	 DefaultTableModel model;
	 private JPanel my_Panel;
	 private JPanel PanelOperation;
     private JButton btnLouer ;
     private JButton btnRendre ;    
     private Agence agence=new Agence();
     private LouerDialog mydialog ;

	
	 
	 public JPanel getpanel()
	 {
		 return mainpanel;
	 }
	 
	 public  GraphLocation(JFrame frameorg)
	 {
		 this.frameorg=frameorg;
		 
	 }
	 
	 
	 public void Initialisertable()
	 {
		 
		 Object[] columns = {"ID","Voiture","CIN","Client"};
	     model = new DefaultTableModel();
	     model.setColumnIdentifiers(columns);
	     table.setModel(model);
	     table.setBackground(Color.white);
         table.setForeground(Color.black);
         Font font = new Font("Time New Roman",0,13);
         table.setFont(font);
         table.setRowHeight(30);
	     /*Remplir par les voiture qui existe*/
	     for (Iterator<Object[]> i= agence.List_Of_Client_Voiture().iterator() ; i.hasNext();)
	     {
	        model.addRow(i.next());
	        
	     }
	     
	 }
	 
	 public void init()
	 {	
		try {
			agence=agence.LireSerialize();
		} catch (ClassNotFoundException | IOException e1) {
			
			System.out.print(e1.getMessage());
		}
    	mainpanel = new JPanel();
        my_Panel=new JPanel(new BorderLayout());
        mainpanel.add(my_Panel);
        /*JTable valeur liste */
        table= new JTable();  
        Initialisertable();  
        
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(800, 400));
		
        /* Panel Operation : basique ( supp, ajout, modifier) + Rechercher Critere) */
        PanelOperation=new JPanel();
        PanelOperation.setPreferredSize(new Dimension(800,200));
        my_Panel.add(BorderLayout.CENTER, PanelOperation);
        my_Panel.add(BorderLayout.SOUTH, pane);
        
        JPanel PanelOpBasic=new JPanel(new BorderLayout());
		PanelOpBasic.setPreferredSize(new Dimension(800,100));
        PanelOpBasic.setBorder(new TitledBorder("Rendre une voiture / Louer une voiture :"));
        PanelOperation.add(PanelOpBasic); 
        
        /*Panel Operation Basique supp , ajout,modifier avec les input*/
        
  
        PanelOpBasic.setLayout(new BoxLayout(PanelOpBasic, BoxLayout.X_AXIS));
        btnLouer = new JButton("    Louer    ");
        btnLouer.setPreferredSize(new Dimension(100, 100));
        btnRendre = new JButton("  Rendre ");   
        btnRendre.setPreferredSize(new Dimension(100, 100));
        btnRendre.setEnabled(false);
        
       // PanelOpBasic.add(Box.createVerticalStrut(23));
        PanelOpBasic.add(btnRendre,BorderLayout.NORTH);
        PanelOpBasic.add(Box.createHorizontalStrut(600));
        PanelOpBasic.add(btnLouer,BorderLayout.NORTH);
        PanelOpBasic.add(Box.createVerticalStrut(10));
    
        table.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mousePressed(MouseEvent e){
                btnRendre.setEnabled(true);
            }
            });
        
        
        //Object[] row = new Object[4];
        // button rendre
       
        btnRendre.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
            	int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment rendre cette voiture ?"
            	, "Attention", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                	  int i = table.getSelectedRow();
                      if(i >= 0){
                          System.out.println("ID voiture Liberer : "+model.getValueAt(i,0)
                          +" du client "+(String)model.getValueAt(i,2));
                          agence.rendVoitureClient( 
                        		  agence.find_voiture_byId(Integer.parseInt((String)model.getValueAt(i,0).toString()))
                        		  ,  agence.find_Client_byCIN((String)model.getValueAt(i,2).toString()));
                          model.removeRow(i);
                          try {
      						agence.EcrireSerialize();
      					} catch (IOException e1) {
      						e1.printStackTrace();
      					}
                          
                      }
                      else{
                          System.out.println("Rendre Error");
                      }
                  }
                }
                 
        });
        
        
        // button update row
        mydialog = new LouerDialog(this);
        btnLouer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            			mydialog.init();
            			
                    }   
        });
        /*
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  
        */ 
    }

	public JFrame getFrame() {
		return frameorg;
	}

	public Agence getAgence() {
		return agence;
	} 
	
	public void setAgence(Agence agence) {
		this.agence=agence;
	} 
	 
}


class LouerDialog 
{
	private GraphLocation my_graphique;
	private JDialog jd;
	private JPanel DiPanel;
	private JTable table1,table2;
	private DefaultTableModel model1,model2;
	JScrollPane pane ;
	private JButton BtnDQuitter;
	private JButton btnDlouer ;
	public LouerDialog(GraphLocation my_graphique)
	{
		this.my_graphique=my_graphique;
	}
	
	public void init()
	{
		jd = new JDialog(my_graphique.getFrame());
    	jd.setModal(true);
		jd.setTitle("Louer une voiture");
		jd.setSize(700,300);
		jd.setLocationRelativeTo(null);
		jd.setLayout(new GridLayout(2, 1));
		
		DiPanel=new JPanel(new BorderLayout());
		jd.getContentPane().add(DiPanel);
		table1= new JTable();   
		InitialiserTableVoiture();
         pane = new JScrollPane(table1);
         pane.setBorder(new TitledBorder("VoitureDispo"));
         pane.setPreferredSize(new Dimension(370, 370));
         DiPanel.add(pane,BorderLayout.EAST);
       
         table2= new JTable();  
         InitialiserTableClient();
         pane = new JScrollPane(table2);			 
         pane.setBorder(new TitledBorder("Client"));
         pane.setPreferredSize(new Dimension(300, 300));
         DiPanel.add(pane,BorderLayout.WEST);
	    
         JPanel PanelDoperation = new JPanel();
         btnDlouer = new JButton("    Louer    ");
         btnDlouer.setPreferredSize(new Dimension(100, 50));
         btnDlouer.setEnabled(false);
         BtnDQuitter = new JButton("  Quitter ");   
         BtnDQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				my_graphique.Initialisertable();
				jd.setVisible(false);
			}
		});
         
         BtnDQuitter.setPreferredSize(new Dimension(100, 50));
        
         
         PanelDoperation.add(Box.createVerticalStrut(172));
         PanelDoperation.add(btnDlouer);
         PanelDoperation.add(Box.createVerticalStrut(10));
         PanelDoperation.add(BtnDQuitter);
         PanelDoperation.add(Box.createVerticalStrut(10));
         
         jd.getContentPane().add(PanelDoperation);
     
         table1.addMouseListener(new MouseAdapter(){
             
             @Override
             public void mousePressed(MouseEvent e){
            	 if (table2.getSelectedRow()!=-1)
                 btnDlouer.setEnabled(true);
             }
             });
         table2.addMouseListener(new MouseAdapter(){
             
             @Override
             public void mousePressed(MouseEvent e){
            	 if (table1.getSelectedRow()!=-1)
                 btnDlouer.setEnabled(true);
             }
             });
         
         btnDlouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment Louer ?"
	                	, "Attention", JOptionPane.YES_NO_OPTION);
	                    if (reply == JOptionPane.YES_OPTION) {
	                    	int i1 = table1.getSelectedRow();
	                    	int i2 = table2.getSelectedRow();
	                          if(i1 >= 0 && i2>=0){
	                        	  String idsup=(String)model1.getValueAt(i1,0).toString();
	                              // remove a row from jtable
	                        	 my_graphique.getAgence().loueVoiture( my_graphique.getAgence().find_Client_byCIN((String)model2.getValueAt(i2,0))
	                        			 ,  my_graphique.getAgence().find_voiture_byId(Integer.parseInt(idsup)));
	                        	   try {
	             						my_graphique.getAgence().EcrireSerialize();
	             						
	             					} catch (IOException e1) {
	             						e1.printStackTrace();
	             					}
	                        	   btnDlouer.setEnabled(false);
	                        	   
	                        	   jd.setVisible(false);
	                        	   InitialiserTableVoiture();
	                        	   init();
	                        	   my_graphique.Initialisertable();
	                        	  
	                          }
	                    	
	                    	
	                    }
				
			}
		});
		jd.setVisible(true);
	}
	
	public void InitialiserTableVoiture()
	{
		Object[] columns = {"Id","Marque","Nom","AnneeProd","Prix/Jour"};
	     model1 = new DefaultTableModel();
	     model1.setColumnIdentifiers(columns);
	     table1.setModel(model1);   
	     table1.setBackground(Color.LIGHT_GRAY);
        table1.setForeground(Color.black);
        Font font = new Font("Time New Roman",0,12);
        table1.setFont(font);
        JScrollPane pane = new JScrollPane(table1);
        pane.setBorder(new TitledBorder("VoitureDispo"));
        pane.setPreferredSize(new Dimension(370, 370));
        
        for (Iterator<Object[]> i= my_graphique.getAgence().Imprimer_Voiture_Dispo().iterator() ; i.hasNext();)
	        model1.addRow(i.next());
	}
	
	public void InitialiserTableClient()
	{
		 Object[] columns2 = {"CIN","Nom","Prenom","Civilite"};
	     model2 = new DefaultTableModel();     
	     model2.setColumnIdentifiers(columns2);
	     Font font = new Font("Time New Roman",0,12);
	     table2.setModel(model2);
	     table2.setBackground(Color.LIGHT_GRAY);
         table2.setForeground(Color.black);
         table2.setFont(font);
         table2.setRowHeight(30);
         for (Iterator<Object[]> i= my_graphique.getAgence().List_Of_Client().iterator() ; i.hasNext();)
	 	        model2.addRow(i.next());
	}
		
}



