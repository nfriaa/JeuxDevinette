/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeuxdevinette;

/**
 *
 * @author nafaa
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bureau extends JFrame implements ActionListener
{
    private JDesktopPane desktop;
    //private Jeux fenetreJeux;
    private Apropos fenetreApropos;
    private JLabel barreEtat; 
    
    private JMenuBar menuBar;
    private JMenu menuFichier;
    private JMenuItem itemNouveau;
    private JMenuItem itemQuitter;
    
    private JMenu menuParams;
    private JMenu menuNiveau;
    private JRadioButtonMenuItem itemFacile;
    private JRadioButtonMenuItem itemMoyen;
    private JRadioButtonMenuItem itemDifficile;    
    
    private JMenu menuAide;
    private JMenuItem itemApropos;
    
    
    /**
     * constructeur
     */
    public Bureau()
    {        
        setTitle("Jeux de dévinette");
        setBounds(100, 100, 850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // le conteneur de type bureau :
        desktop = new JDesktopPane();  
        this.getContentPane().add(desktop, BorderLayout.CENTER);
        
        // création des fenêtres : 
        fenetreApropos = new Apropos();
        
        // ajout des fenêtres au bureau : 
        desktop.add(fenetreApropos);
        
        // barre d'état : 
        barreEtat = new JLabel("Bienvenus dans notre jeux dévinette, bonne chance !");
        this.getContentPane().add(barreEtat, BorderLayout.SOUTH);
        
        // pour afficher le menu en haut sous Mac : 
        if (System.getProperty("os.name").contains("Mac")) 
        {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        
        creerMenu();
        setJMenuBar(menuBar);
        
        setVisible(true);
    }
    
    public void creerMenu()
    {
        menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuFichier.setMnemonic('F');
        itemNouveau = new JMenuItem("Nouveau Jeux");
        itemNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        itemQuitter = new JMenuItem("Quitter");
        itemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        
        menuParams = new JMenu("Paramètres");
        menuParams.setMnemonic('P');
        menuNiveau = new JMenu("Niveau");
        menuNiveau.setMnemonic('N');
        itemFacile = new JRadioButtonMenuItem("Facile", true);
        itemFacile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        itemMoyen = new JRadioButtonMenuItem("Moyen");
        itemMoyen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        itemDifficile = new JRadioButtonMenuItem("Difficile");
        itemDifficile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        
        menuAide = new JMenu("Aide");
        menuAide.setMnemonic('A');
        itemApropos = new JMenuItem("A Propos");
        itemApropos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        
        menuBar.add(menuFichier);
        menuBar.add(menuParams);
        menuBar.add(menuAide);
        
        menuFichier.add(itemNouveau);
        menuFichier.add(itemQuitter);
        
        menuParams.add(menuNiveau);
        menuNiveau.add(itemFacile);
        menuNiveau.add(itemMoyen);
        menuNiveau.add(itemDifficile);
        ButtonGroup g1 = new ButtonGroup();
        g1.add(itemFacile);
        g1.add(itemMoyen);
        g1.add(itemDifficile);
        
        menuAide.add(itemApropos);
        
        // écouteurs : 
        itemQuitter.addActionListener(this);
        itemNouveau.addActionListener(this);
        itemApropos.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource() == itemQuitter)
        {
            System.exit(0);
        }
        
        if(ev.getSource() == itemNouveau)
        {
            Jeux fenetreJeux;
            
            if(itemFacile.isSelected())
            {
                fenetreJeux = new Jeux(1);
            }
            else if(itemMoyen.isSelected())
            {
                fenetreJeux = new Jeux(2);
            }
            else
            {
                fenetreJeux = new Jeux(3);
            }
            
            desktop.add(fenetreJeux);
        }
        
        if(ev.getSource() == itemApropos)
        {
            fenetreApropos.setVisible(true);
        }
        
    }
}
