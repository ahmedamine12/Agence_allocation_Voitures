package GraphInter;

import Traitement.*;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class GraphVoiture {
    private JPanel mainpanel;
    private JTable table;
    DefaultTableModel model;
    private JPanel my_Panel;
    private JPanel PanelOperation;
    private JPanel PanelOpBasic;
    private JPanel PanelInput;
    private JPanel PanelOpButton;
    private JPanel PanelSearch;
    private JTextField textMarque;
    private JTextField textNom;
    private JTextField textAnnee;
    private JTextField textPrixLocation;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private Agence agence = new Agence();
    private JCheckBox checkAnnee;
    private JCheckBox checkMarque;
    private JCheckBox checkPrix;
    private JTextField JCritereAnnee;
    private JTextField JCritereMarque;
    private JTextField JCriterePrix;

    public JPanel getpanel() {
        return mainpanel;
    }

    public void Initialisertable() {
        Object[] columns = { "Id", "Marque", "Modele", "Annee Prod", "Prix Location Par Jour" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.black);
        Font font = new Font("Time New Roman", 0, 13);
        table.setFont(font);
        table.setRowHeight(30);
        /* Remplir par les voiture qui existe */
        for (Iterator<Object[]> i = agence.Imprimer_Voiture().iterator(); i.hasNext();)
            model.addRow(i.next());
    }

    public void init() {
        // deserialization du fichier vers agence
        try {
            agence = agence.LireSerialize();
        } catch (ClassNotFoundException | IOException e1) {
            System.out.print(e1.getMessage());
        }

        mainpanel = new JPanel();
        my_Panel = new JPanel(new BorderLayout());
        mainpanel.add(my_Panel);
        
        /* JTable valeur liste */
        table = new JTable();
        // remplissage de table pour l'afficher apres
        Initialisertable();
        JScrollPane pane = new JScrollPane(table);
        /*
         * Panel d'Operations : basique ( supp, ajout, modifier) + Rechercher Critere)
         */
        PanelOperation = new JPanel(new FlowLayout());

        PanelSearch = new JPanel();
        my_Panel.add(BorderLayout.NORTH, PanelSearch);
        my_Panel.add(BorderLayout.CENTER, pane);
        my_Panel.add(BorderLayout.SOUTH, PanelOperation);

        /* Panel Operation Basique supp , ajout,modifier avec les input */
        PanelOpBasic = new JPanel(new BorderLayout());
        PanelOpBasic.setBorder(new TitledBorder("Operations"));
        // pour l'espacement entre les JForms et les buttons des operations
       // PanelOpBasic.add(Box.createHorizontalStrut(30));

        PanelOperation.add(PanelOpBasic);
// PanelOperation.add(new JLabel("Voitures"));

        /* Panel input ( input+ label) */
        PanelInput = new JPanel(new FlowLayout());
        PanelInput.setLayout(new BoxLayout(PanelInput, BoxLayout.Y_AXIS));
        PanelOpBasic.add(BorderLayout.WEST, PanelInput);
        PanelOpBasic.add(BorderLayout.CENTER,new JPanel(new FlowLayout()).add(Box.createHorizontalStrut(130)));

        textMarque = new JTextField(15);
        textNom = new JTextField(15);
        textAnnee = new JTextField(15);
        textPrixLocation = new JTextField(15);

        PanelInput.add(Box.createVerticalStrut(10));
        JLabel myLabel = new JLabel("Marque :");
        JPanel paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textMarque, BorderLayout.EAST);
        PanelInput.add(paneltemp);

        PanelInput.add(Box.createVerticalStrut(10));
        myLabel = new JLabel("Modele :");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textNom, BorderLayout.EAST);
        PanelInput.add(paneltemp);
        PanelInput.add(Box.createVerticalStrut(10));

        myLabel = new JLabel("Annee :");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textAnnee, BorderLayout.EAST);
        PanelInput.add(paneltemp);

        PanelInput.add(Box.createVerticalStrut(10));
        myLabel = new JLabel("Prix Location Par jour :  ");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textPrixLocation, BorderLayout.EAST);
        PanelInput.add(paneltemp);

        /* Panel input (Button Operation ) ajouter supprimer update */
        PanelOpButton = new JPanel();
        PanelOpButton.setLayout(new FlowLayout());
        PanelOpButton.add(Box.createHorizontalStrut(10));
        PanelOpBasic.add(BorderLayout.EAST, PanelOpButton);

        btnAdd = new JButton("    Add    ");
        // btnAdd.setMinimumSize(new Dimension(30, 30));
        btnDelete = new JButton("  Delete  ");
        btnDelete.setEnabled(false);
        btnUpdate = new JButton("  Update  ");
        btnUpdate.setEnabled(false);

        // PanelOpButton.add(Box.createVerticalStrut(23));
        PanelOpButton.add(btnAdd);
        // PanelOpButton.add(Box.createVerticalStrut(50));
        PanelOpButton.add(btnDelete);
        // PanelOpButton.add(Box.createVerticalStrut(10));
        PanelOpButton.add(btnUpdate);

        /* Panel De Recherche */

        PanelSearch.setLayout(new BoxLayout(PanelSearch, BoxLayout.X_AXIS));

        PanelSearch.setBorder(new TitledBorder("Recherche Par critere"));

        PanelSearch.add(Box.createVerticalStrut(10));
        checkAnnee = new JCheckBox("Annee  ");
        JCritereAnnee = new JTextField(15);
        JCritereAnnee.setEditable(false);
        checkAnnee.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    JCritereAnnee.setEditable(true);
                else {
                    if (checkMarque.isSelected() == false && checkPrix.isSelected() == false)
                        Initialisertable();
                    JCritereAnnee.setEditable(false);
                    JCritereAnnee.setText("");
                }

            }
        });
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(checkAnnee, BorderLayout.WEST);
        paneltemp.add(JCritereAnnee, BorderLayout.CENTER);
        PanelSearch.add(paneltemp);

        PanelSearch.add(Box.createVerticalStrut(10));

        checkMarque = new JCheckBox("Marque");
        JCritereMarque = new JTextField(15);
        JCritereMarque.setEditable(false);
        checkMarque.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    JCritereMarque.setEditable(true);
                else {
                    if (checkAnnee.isSelected() == false && checkPrix.isSelected() == false)
                        Initialisertable();
                    JCritereMarque.setEditable(false);
                    JCritereMarque.setText("");
                }

            }
        });
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(checkMarque, BorderLayout.WEST);
        paneltemp.add(JCritereMarque, BorderLayout.CENTER);
        PanelSearch.add(paneltemp);
        PanelSearch.add(Box.createVerticalStrut(10));
        checkPrix = new JCheckBox("Prix   ");
        JCriterePrix = new JTextField(15);
        JCriterePrix.setEditable(false);
        checkPrix.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    JCriterePrix.setEditable(true);
                else {
                    if (checkAnnee.isSelected() == false && checkMarque.isSelected() == false)
                        Initialisertable();
                    JCriterePrix.setEditable(false);
                    JCriterePrix.setText("");
                }

            }
        });
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(checkPrix, BorderLayout.WEST);
        paneltemp.add(JCriterePrix, BorderLayout.CENTER);
        PanelSearch.add(paneltemp);

        Object[] row = new Object[5];
        // button add row
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // row[0] = String.valueOf(agence.max_id_voiture()+1);
                try {
                    if (textAnnee.getText().length() == 0 || textMarque.getText().length() == 0 ||
                            textNom.getText().length() == 0 || textPrixLocation.getText().length() == 0)
                        throw new Exception();
                    Voiture temp2 = new Voiture(Integer.parseInt(textAnnee.getText()), textMarque.getText(),
                            textNom.getText(), Integer.parseInt(textPrixLocation.getText()),
                            agence.max_id_voiture() + 1);

                    row[0] = String.valueOf(agence.max_id_voiture() + 1);
                    row[1] = textMarque.getText();
                    row[2] = textNom.getText();
                    row[3] = textAnnee.getText();
                    row[4] = textPrixLocation.getText();
                    model.addRow(row);
                    agence.AddVoiture(temp2);
                    agence.EcrireSerialize();
                } catch (Exception ez) {
                    JOptionPane.showMessageDialog(mainpanel, "Champs Invalide", "Attention",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment Supprimer ?", "Attention",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    int i = table.getSelectedRow();
                    if (i >= 0) {
                        // remove a row from jtable
                        String idsup = (String) model.getValueAt(i, 0).toString();
                        model.removeRow(i);
                        System.out.println("Voiture Supprimer d'ID : " + Integer.parseInt(idsup));
                        agence.SupprimerVoiture(agence.find_voiture_byId(Integer.parseInt(idsup)));

                        try {
                            agence.EcrireSerialize();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("Delete Error");
                    }
                }
            }

        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                // textId.setText(model.getValueAt(i, 0).toString());
                textMarque.setText(model.getValueAt(i, 1).toString());
                textNom.setText(model.getValueAt(i, 2).toString());
                textAnnee.setText(model.getValueAt(i, 3).toString());
                textPrixLocation.setText(model.getValueAt(i, 4).toString());
                btnDelete.setEnabled(true);
                btnUpdate.setEnabled(true);
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // i = the index of the selected row
                int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment Modifier ?", "Attention",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    int i = table.getSelectedRow();
                    if (i >= 0) {
                        // remove a row from jtable
                        String idsup = (String) model.getValueAt(i, 0).toString();
                        model.setValueAt(textMarque.getText(), i, 1);
                        model.setValueAt(textNom.getText(), i, 2);
                        model.setValueAt(textAnnee.getText(), i, 3);
                        model.setValueAt(textPrixLocation.getText(), i, 4);
                        System.out.println("Voiture Modifier d'ID : " + Integer.parseInt(idsup));
                        agence.find_voiture_byId(Integer.parseInt(idsup)).ModifierVoiture(
                                Integer.parseInt(textAnnee.getText()), textMarque.getText(), textNom.getText(),
                                Integer.parseInt(textPrixLocation.getText()));
                        try {
                            agence.EcrireSerialize();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("UPDATE Error");
                    }
                }
            }
        });

        CaretListener caretupdate = new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {

                InterCritere My_Critere = new InterCritere();

                if (checkAnnee.isSelected() && JCritereAnnee.getText().length() != 0)
                    My_Critere.addCritere(new CritereAnnee(Integer.parseInt(JCritereAnnee.getText())));
                if (checkPrix.isSelected() && JCriterePrix.getText().length() != 0)
                    My_Critere.addCritere(new CriterePrix(Integer.parseInt(JCriterePrix.getText())));
                if (checkMarque.isSelected() && JCritereMarque.getText().length() != 0)
                    My_Critere.addCritere(new CritereMarque(JCritereMarque.getText()));

                if (My_Critere.nmbCritere() != 0) {
                    Object[] columns = { "Id", "Marque", "Nom", "AnneeProd", "PrixLocationParJour" };
                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(columns);
                    table.setModel(model);
                    Iterator<Voiture> ite = agence.selectionner(My_Critere);
                    while (ite.hasNext()) {
                        model.addRow(ite.next().AfficherSurObjet());
                        JTextField text = (JTextField) e.getSource();
                        System.out.println(text.getText());
                    }

                }
            }

        };
        JCritereAnnee.addCaretListener(caretupdate);
        JCritereMarque.addCaretListener(caretupdate);
        JCriterePrix.addCaretListener(caretupdate);
        /*
         * frame.setSize(900,400);
         * frame.setLocationRelativeTo(null);
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * frame.setVisible(true);
         */
    }

}
