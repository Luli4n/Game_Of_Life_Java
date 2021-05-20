package Projekt.Wizualizacja;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Swiat;
import Projekt.Zwierzeta.*;
import Projekt.Rośliny.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Menu extends JPanel implements KeyListener {

    private Color color;
    private Pole[][] plansza;
    private Swiat swiat;
    private JFrame okno;
    private Człowiek czlowiek;
    private JButton umiejetnoscCzlowieka;

    public Menu(JFrame _okno, Swiat _swiat,Organizm _czlowiek, Pole[][] _plansza) {
        plansza=_plansza;
        swiat=_swiat;
        okno=_okno;
        DodajPrzyciski();
        czlowiek= (Człowiek) _czlowiek;
        okno.addKeyListener(this);

        okno.setFocusable(true);
    }


    public void paint(Graphics g){
        super.paint(g);
        Legenda(g);
    }

    public void DodajPrzyciski()
    {
        JButton nowaTura = new JButton("Nastepna tura");
        umiejetnoscCzlowieka=new JButton("Aktywuj Magiczny Eliksir");
        JButton zapisz=new JButton("Zapisz grę!");
        JButton wczytaj=new JButton("Wczytaj grę!");

        nowaTura.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            swiat.WykonajTure();
            if(czlowiek.GetTuraUm()!=0 || czlowiek.GetTuraCd()!=0)
            {
                umiejetnoscCzlowieka.setVisible(false);
            }
            else
            {
                umiejetnoscCzlowieka.setVisible(true);
            }
            okno.requestFocus();
        }
    });
        add(nowaTura);

        umiejetnoscCzlowieka.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (czlowiek.GetTuraCd() == 0 && czlowiek.GetTuraUm()==0) {
                    czlowiek.aktywujUmiejetnosc();
                    okno.requestFocus();
                }
            }
        });
        add(umiejetnoscCzlowieka);

        zapisz.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame oknoZapisu = new JFrame();

                JTextField nazwaZapisu=new JTextField();
                JPanel panelZapisu= new JPanel();
                panelZapisu.setLayout(new BorderLayout(1,1));
                JLabel nazwa = new JLabel("Nazwa zapisu: ");
                panelZapisu.add(nazwaZapisu);
                oknoZapisu.add(nazwa);
                oknoZapisu.add(panelZapisu,BorderLayout.CENTER);

                JButton przyciskZapisu = new JButton("Zapisz");
                oknoZapisu.add(przyciskZapisu);

                przyciskZapisu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        String tekst=nazwaZapisu.getText();
                        if(!tekst.equals("")) {
                            try {
                                swiat.ZapiszSwiat(tekst);
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                        oknoZapisu.setVisible(false);
                        oknoZapisu.dispose();
                        okno.requestFocus();
                    }
                });
                oknoZapisu.setLayout(new GridLayout(1,1,0,0));
                oknoZapisu.setSize(400,150);
                oknoZapisu.setVisible(true);
                okno.requestFocus();
            }
        });
        add(zapisz);

        wczytaj.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame oknoOdczytu = new JFrame();

                JTextField nazwaOdczytu=new JTextField();
                JPanel panelOdczytu= new JPanel();
                panelOdczytu.setLayout(new BorderLayout(1,1));
                JLabel nazwa = new JLabel("Nazwa pliku do wczytania: ");
                panelOdczytu.add(nazwaOdczytu);
                oknoOdczytu.add(nazwa);
                oknoOdczytu.add(panelOdczytu,BorderLayout.CENTER);

                JButton przyciskOdczytu = new JButton("Wczytaj");
                oknoOdczytu.add(przyciskOdczytu);

                przyciskOdczytu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        String tekst=nazwaOdczytu.getText();
                        if(!tekst.equals("")) {
                            try {
                                swiat.ZaladujSwiat(tekst+".txt");
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                        oknoOdczytu.setVisible(false);
                        oknoOdczytu.dispose();
                        okno.requestFocus();
                    }
                });
                oknoOdczytu.setLayout(new GridLayout(1,1,0,0));
                oknoOdczytu.setSize(500,150);
                oknoOdczytu.setVisible(true);
                okno.requestFocus();
            }
        });
        add(wczytaj);
    }

    private void Legenda(Graphics g){

        g.setColor(new Owca().maluj());
        g.fillRect(0, 100, 30,30);
        g.setColor(new Wilk().maluj());
        g.fillRect(0, 150, 30,30);
        g.setColor(new Człowiek().maluj());
        g.fillRect(0,200, 30,30);
        g.setColor(new Lis().maluj());
        g.fillRect(0,250, 30,30);
        g.setColor(new Żółw().maluj());
        g.fillRect(0,300, 30,30);
        g.setColor(new Antylopa().maluj());
        g.fillRect(0,350, 30,30);
        g.setColor(new Trawa().maluj());
        g.fillRect(0,400, 30,30);
        g.setColor(new Mlecz().maluj());
        g.fillRect(0,450, 30,30);
        g.setColor(new Guarana().maluj());
        g.fillRect(0,500, 30,30);
        g.setColor(new WilczeJagody().maluj());
        g.fillRect(0,550, 30,30);
        g.setColor(new BarszczSosnowskiego().maluj());
        g.fillRect(0,600, 30,30);
        g.setColor(new CyberOwca().maluj());
        g.fillRect(0,650, 30,30);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN,25));
        g.drawString("Owca", 50, 125);
        g.drawString("Wilk", 50, 175);
        g.drawString("Człowiek", 50, 225);
        g.drawString("Lis", 50, 275);
        g.drawString("Żółw", 50, 325);
        g.drawString("Antylopa", 50, 375);
        g.drawString("Trawa", 50, 425);
        g.drawString("Mlecz", 50, 475);
        g.drawString("Guarana", 50, 525);
        g.drawString("Wilcze Jagody", 50, 575);
        g.drawString("Barszcz Sosnowskiego", 50, 625);
        g.drawString("Cyber Owca", 50, 675);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        czlowiek.wykonajRuch(ke);
        if(czlowiek.GetTuraUm()!=0 || czlowiek.GetTuraCd()!=0)
        {
            umiejetnoscCzlowieka.setVisible(false);
        }
        else
        {
            umiejetnoscCzlowieka.setVisible(true);
        }
        swiat.WykonajTure();
    }


}


