package Projekt.Wizualizacja;

import Projekt.Pole;
import Projekt.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.floor;

public class Plansza extends JPanel implements MouseListener {

    private Color color;
    private Pole[][] plansza;
    private int wysokosc, szerokosc;
    private Swiat swiat;
    private JFrame okno;

    public Plansza(JFrame _okno, Swiat _swiat, Pole[][] _plansza, int _wysokosc, int _szerokosc) {
        plansza=_plansza;
        wysokosc=_wysokosc;
        szerokosc=_szerokosc;
        swiat=_swiat;
        okno=_okno;

        addMouseListener(this);
    }


    public void paint(Graphics g){
        super.paint(g);
    for(int i=1;i<=wysokosc;i++) {
        for (int j = 1; j <= szerokosc; j++) {
            g.setColor(plansza[i-1][j-1].maluj());
            g.fillRect(j*25,i*25,25,25);
            g.setColor(new Color(0,0,0));
            g.drawLine(j*25,i*25,j*25,i*25+25);
            g.drawLine(j*25,i*25,j*25+25,i*25);
        }
}
        g.drawLine(szerokosc*25+25,25,szerokosc*25+25,wysokosc*25+25);
        g.drawLine(25,wysokosc*25+25,szerokosc*25+25,wysokosc*25+25);
    g.drawString("Julian Krąbel",30,15);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX()-25;
        int y=e.getY()-25;
        int indexX= (int) floor(x/25);
        int indexY=(int) floor(y/25);

        JFrame dodawanie=new JFrame();
        dodawanie.setLayout(new GridLayout(1,1,1,1));
        dodawanie.setSize(300,350);
        dodawanie.setResizable(false);

        String[] dane={"Owca","Wilk","Lis","Zolw","Antylopa","Trawa","Mlecz","Guarana","WilczeJagody","BarszczSosnowskiego","CyberOwca"};

        JList list = new JList(dane);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JButton button = new JButton("Dodaj");
        dodawanie.add(list);
        dodawanie.add(button);

        list.setVisible(true);
        dodawanie.setVisible(true);

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String organizm = (String) list.getSelectedValue();
                dodawanie.setVisible(false); //you can't see me!
                dodawanie.dispose(); //Destroy the JFrame object
                swiat.ZmienOrganizm(organizm,indexX,indexY);
            }
        });

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


