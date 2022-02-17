package GraphInter;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main 
{

    private GraphVoiture GV;
    private GraphClient GC;
    private GraphLocation GL;
  Main()
  {
    //Créer le frame
    JFrame frame = new JFrame();

    //Créer le panneau 1
    GV = new GraphVoiture();
    GV.init();
    JPanel p1 = new JPanel();
    p1.add(GV.getpanel());
    //Créer le panneau 2
    GC = new GraphClient();
    GC.init();
    JPanel p2 = new JPanel();
    p2.add(GC.getpanel());
    //Créer le panneau 3
    GL = new GraphLocation(frame);
    GL.init();
    JPanel p3 = new JPanel();
    p3.add(GL.getpanel());
    
    //Créer le conteneur des onglets
    JTabbedPane onglets = new JTabbedPane();
    //Définir la position de conteneur d'onglets
    onglets.setBounds(40,20,900,800);
    //Associer chaque panneau à l'onglet correspondant
    onglets.add("Voitures", p1);
    onglets.add("Clients", p2);
    onglets.add("Locations", p3);
    
    //Ajouter les onglets au frame
    frame.add(onglets);
    
    frame.setSize(1000,800);
    frame.setLayout(null);
    frame.setVisible(true);
  }
  public static void main(String[] args) throws UnsupportedLookAndFeelException 
  {
    UIManager.setLookAndFeel(new NimbusLookAndFeel());
    new Main();
  }
}