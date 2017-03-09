/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxdevinette;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author nafaa
 */
public class Jeux extends JInternalFrame implements ActionListener
{
    private JLabel labelIntro, labelMessage, labelTentatives;
    private JTextField textField1;
    private JButton boutonValider;
    private int nombreEssais = 9;
    private int nombreSecret = 0;

    /**
     * constructeur
     */
    public Jeux(int niveau)
    {
        //initialiser le niveau : 
        if (niveau == 1)
        {
            setTitle("Niveau facile");
            nombreEssais = 9;
        }
        else if (niveau == 2)
        {
            setTitle("Niveau moyen");
            nombreEssais = 5;
        }
        else if (niveau == 3)
        {
            setTitle("Niveau difficile");
            nombreEssais = 3;
        }

        // paramètres de la fenêtre :
        setBounds(50, 50, 300, 250);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);

        // tuer l'objet lorsqu'on clique sur l'icone fermer :
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // le mode de disposition :
        this.getContentPane().setLayout(new FlowLayout());

        // initialiser le nombre secret alléatoire entre 0 et 99 :
        nombreSecret = (int) (Math.random() * 100);

        creerComposants();
        ajouterComposants();
        placerEcouteurs();

        setVisible(true);
    }

    /**
     * créer / instancier les composants et les objets de la fenêtre :
     */
    public void creerComposants()
    {
        labelIntro = new JLabel("Saisir un nombre entre 0 et 100 : ");
        textField1 = new JTextField(8);
        boutonValider = new JButton("  Déviner  ");
        labelMessage = new JLabel("");
        labelTentatives = new JLabel("Il vous reste " + nombreEssais + " tentatives !");
    }

    /**
     * ajouter les composants crées à la fenêtre :
     */
    public void ajouterComposants()
    {
        this.getContentPane().add(labelIntro);
        this.getContentPane().add(textField1);
        this.getContentPane().add(boutonValider);
        this.getContentPane().add(labelMessage);
        this.getContentPane().add(labelTentatives);
    }

    /**
     * placer les écouteurs sur les composants :
     */
    public void placerEcouteurs()
    {
        boutonValider.addActionListener(this);
    }

    /**
     * gestion des évènements
     *
     * @param ev
     */
    public void actionPerformed(ActionEvent ev)
    {
        if (ev.getSource() == boutonValider)
        {
            // essayer de convertir en entier le nombre saisie :
            int nombreSaisie;
            try
            {
                nombreSaisie = Integer.parseInt(textField1.getText());
            }
            catch (NumberFormatException ex)
            {
                nombreSaisie = -1;
                //System.out.println(nombreSaisie + " => " + ex.toString());
            }

            // cas perdu :
            if (nombreEssais <= 0)
            {
                boutonValider.setEnabled(false);
                textField1.setEnabled(false);
                labelMessage.setForeground(Color.RED);
                labelMessage.setText("Désolé : vous avez perdu !");
                labelTentatives.setText("");
            }
            // cas gagné
            else if (nombreSaisie == nombreSecret)
            {
                boutonValider.setEnabled(false);
                textField1.setEnabled(false);
                labelMessage.setForeground(Color.blue);
                labelMessage.setText("Bravo : vous avez gagné !");
                //labelTentatives.setText("");
            }
            // décrémenter le compteur :
            else
            {
                nombreEssais--;

                if (nombreSaisie == -1)
                {
                    labelMessage.setForeground(Color.RED);
                    labelMessage.setText("Nombre incorrect !");
                }

                else if (nombreSaisie < nombreSecret)
                {
                    labelMessage.setForeground(Color.BLACK);
                    labelMessage.setText("Votre Nombre est inférieur !");
                }

                else if (nombreSaisie > nombreSecret)
                {
                    labelMessage.setForeground(Color.BLACK);
                    labelMessage.setText("Votre Nombre est supérieur !");
                }

                labelTentatives.setText("Il vous reste " + nombreEssais + " tentatives !");

            }
        }
    }

}
