package GraphInter;

import Traitement.*;
import java.awt.BorderLayout;
// import java.awt.Button;
// import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
// import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
// import javax.swing.border.Border;
// import javax.swing.border.CompoundBorder;
// import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
// import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class GraphClient {
    private JPanel mainpanel;
    private JTable table;
    DefaultTableModel model;
    private JPanel my_Panel;
    private JPanel PanelOperation;
    private JPanel PanelInput;
    private JPanel PanelOpButton;
    private JTextField textCIN;
    private JTextField textNom;
    private JTextField textPrenom;
    private Choice Civilite;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private Agence agence = new Agence();
    // private JCheckBox checkAnnee ;
    // private JCheckBox checkMarque;
    // private JCheckBox checkPrix;
    // private JTextField JCritereAnnee;
    // private JTextField JCritereMarque;
    // private JTextField JCriterePrix;

    public JPanel getpanel() {
        return mainpanel;
    }

    public void Initialisertable() {
        table = new JTable();
        Object[] columns = { "CIN", "First Name", "Last Name", "Civility" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.black);
        Font font = new Font("Time New Roman", 0, 13);
        table.setFont(font);
        table.setRowHeight(30);
        /* Remplir par les voiture qui existe */
        for (Iterator<Object[]> i = agence.List_Of_Client().iterator(); i.hasNext();)
            model.addRow(i.next());
    }

    public void init() {
        try {
            agence = agence.LireSerialize();
        } catch (ClassNotFoundException | IOException e1) {
            // TODO Auto-generated catch block
            System.out.print(e1.getMessage());
        }
        mainpanel = new JPanel();
        my_Panel = new JPanel(new GridLayout(2, 1));
        mainpanel.add(my_Panel);
        /* JTable valeur liste */
        Initialisertable();
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(600, 400));

        /* Panel Operation : basique ( supp, ajout, modifier) + Rechercher Critere) */
        PanelOperation = new JPanel(new BorderLayout());
        my_Panel.add(pane);
        my_Panel.add(PanelOperation);

        /* Panel Operation Basique supp , ajout,modifier avec les input */
        JPanel PanelOpBasic = new JPanel(new FlowLayout());
       // PanelOpBasic.setBorder(new TitledBorder("BasicOperation"));
        PanelOpBasic.add(Box.createHorizontalStrut(30));
        PanelOperation.add(BorderLayout.CENTER, PanelOpBasic);

        /* Panel Operation Basique supp , ajout,modifier avec les input */

        /* Panel input ( input+ label) */
        PanelInput = new JPanel();
        PanelInput.setLayout(new BoxLayout(PanelInput, BoxLayout.Y_AXIS));
        PanelOpBasic.add(PanelInput);
        //PanelOpBasic.add(Box.createHorizontalStrut(30));

        textCIN = new JTextField(23);
        textNom = new JTextField();
        textPrenom = new JTextField();
        Civilite = new Choice();
        Civilite.add("M");
        Civilite.add("Mlle");
        Civilite.add("Mme");

        PanelInput.add(Box.createVerticalStrut(17));
        JLabel myLabel = new JLabel("CIN                : ");
        JPanel paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textCIN, BorderLayout.CENTER);
        PanelInput.add(paneltemp);

        PanelInput.add(Box.createVerticalStrut(10));
        myLabel = new JLabel("Nom              : ");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textNom, BorderLayout.CENTER);
        PanelInput.add(paneltemp);
        PanelInput.add(Box.createVerticalStrut(10));

        myLabel = new JLabel("Prenom        : ");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(textPrenom, BorderLayout.CENTER);
        PanelInput.add(paneltemp);

        PanelInput.add(Box.createVerticalStrut(10));
        myLabel = new JLabel("Civilité          : ");
        paneltemp = new JPanel(new BorderLayout());
        paneltemp.add(myLabel, BorderLayout.WEST);
        paneltemp.add(Civilite, BorderLayout.CENTER);
        PanelInput.add(paneltemp);

        /* Panel input (Button Operation ) */
        PanelOpButton = new JPanel();
        PanelOpButton.setLayout(new BoxLayout(PanelOpButton, BoxLayout.Y_AXIS));
        PanelOpBasic.add(PanelOpButton, BorderLayout.EAST);

        btnAdd = new JButton("    Ajouter     ");
        btnAdd.setMinimumSize(new Dimension(30, 30));
        btnDelete = new JButton("  Supprimer ");
        btnDelete.setEnabled(false);
        btnUpdate = new JButton("       MAJ        ");
        btnUpdate.setEnabled(false);

        PanelOpButton.add(Box.createVerticalStrut(23));
        PanelOpButton.add(btnAdd);
        PanelOpButton.add(Box.createVerticalStrut(10));
        PanelOpButton.add(btnDelete);
        PanelOpButton.add(Box.createVerticalStrut(10));
        PanelOpButton.add(btnUpdate);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                // textId.setText(model.getValueAt(i, 0).toString());
                textCIN.setText(model.getValueAt(i, 0).toString());
                textNom.setText(model.getValueAt(i, 1).toString());
                textPrenom.setText(model.getValueAt(i, 2).toString());
                Civilite.select(model.getValueAt(i, 3).toString());
                btnDelete.setEnabled(true);
                btnUpdate.setEnabled(true);
            }
        });

        Object[] row = new Object[4];
        // button add row
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // row[0] = String.valueOf(agence.max_id_voiture()+1);

                try {

                    if (textCIN.getText().length() == 0 || textNom.getText().length() == 0 ||
                            textPrenom.getText().length() == 0)
                        throw new Exception();
                    Client temp2 = new Client(textNom.getText(), textPrenom.getText(), textCIN.getText(),
                            Civilite.getSelectedIndex() + 1);

                    row[0] = textCIN.getText();
                    row[1] = textNom.getText();
                    row[2] = textPrenom.getText();
                    row[3] = Civilite.getSelectedItem();
                    agence.AddClient(temp2);
                    model.addRow(row);

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
                        model.removeRow(i);
                        System.out.println("Client Supprimer CIN : " + textCIN);
                        agence.SupprimerClient(agence.find_Client_byCIN(textCIN.getText()));
                        try {
                            agence.EcrireSerialize();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("Delete Error");
                    }
                }
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

                        agence.find_Client_byCIN((String) model.getValueAt(i, 0)).ModifierClient(textNom.getText(),
                                textPrenom.getText(), textCIN.getText(), Civilite.getSelectedIndex() + 1);

                        model.setValueAt(textCIN.getText(), i, 0);
                        model.setValueAt(textNom.getText(), i, 1);
                        model.setValueAt(textPrenom.getText(), i, 2);
                        model.setValueAt(Civilite.getSelectedItem(), i, 3);
                        System.out.println("Client Modifier d'CIN : " + textCIN.getText());

                        try {
                            agence.EcrireSerialize();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("UPDATE Error");
                    }
                }
            }
        });

        /*
         * frame.setSize(900,400);
         * frame.setLocationRelativeTo(null);
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * frame.setVisible(true);
         */
    }

}
