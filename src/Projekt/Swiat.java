package Projekt;

import Projekt.Rośliny.*;
import Projekt.Wizualizacja.Komentator;
import Projekt.Wizualizacja.Plansza;
import Projekt.Zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Swiat {

    private Organizm czlowiek;
    private int szerokosc;
    private int wysokosc;
    private int tura;
    private int randX;
    private int randY;
    private List<Organizm> organizmy = new ArrayList<>();
    private Projekt.Wizualizacja.Menu m;
    private Plansza p;
    public List<Organizm> barszcze = new ArrayList<>();
    public JFrame okno;
    public Komentator kom;
    private Pole[][] plansza;

    Swiat(int _szerokosc, int _wysokosc)
    {
        szerokosc=_szerokosc;
        wysokosc=_wysokosc;
        plansza=new Pole[wysokosc][szerokosc];
        tura=0;

        this.randX = ThreadLocalRandom.current().nextInt(0, szerokosc);
        this.randY = ThreadLocalRandom.current().nextInt(0, wysokosc);

        for(int j=0;j<wysokosc;j++)
        {
            for(int i=0;i<szerokosc;i++) {
            plansza[j][i]=new Pole(this);
            }
        }
        okno=new JFrame();

    }

    public void RysujSwiat() throws IOException, InterruptedException {

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLayout(new GridLayout(1,1,1,1));
        okno.setSize(1700,800);
        okno.setResizable(false);

        p = new Plansza(okno, this,plansza,wysokosc,szerokosc);
        okno.add(p);
        p.setVisible(true);

        m = new Projekt.Wizualizacja.Menu(okno,this,czlowiek,plansza);
        okno.add(m);
        m.setVisible(true);

        kom=new Komentator(okno,this, (Człowiek) czlowiek);
        okno.add(kom);
        kom.setVisible(true);
        okno.setVisible(true);
    }

    public void WykonajTure()
    {
        tura++;
        kom.Wyczysc();
        Collections.sort(organizmy, Swiat.porownajOrganizmy);

        for (int i=0;i<organizmy.size();i++)
        {
            if(organizmy.get(i).GetZywy() && organizmy.get(i).GetTuraUrodzenia()!=this.GetTura()) {
                organizmy.get(i).akcja();
            }
        }

        for (int i = organizmy.size() - 1; i >= 0; i--) {
            if (!organizmy.get(i).GetZywy()) {
                if(organizmy.get(i).GetNazwaGatunku().equals("BarszczSosnowskiego"))
                {
                    barszcze.remove(organizmy.get(i));
                }
                organizmy.remove(i);
            }
        }
        okno.revalidate();
        okno.repaint();

        kom.Komentuj();
    }

    public int GetSzerokosc()
    {
        return this.szerokosc;
    }

    public int GetWysokosc()
    {
        return this.wysokosc;
    }

    public Pole GetPole(int x, int y)
    {
        return this.plansza[y][x];
    }

    public void SetPole(Pole pole, int x, int y)
    {
        this.plansza[y][x]=pole;
    }

    private void randomizeXandY()
    {
        while (!plansza[randY][randX].maluj().equals(Color.WHITE)) {
            this.randX = ThreadLocalRandom.current().nextInt(0, szerokosc);
            this.randY = ThreadLocalRandom.current().nextInt(0, wysokosc);
        }
    }

    public void GenerujOrganizmy()
    {
        for( int c =0;c<Stałe.ILOSC_LUDZI;c++ ) {
            randomizeXandY();
            czlowiek=new Człowiek(this, randX, randY,this.tura,0,0);
            organizmy.add(czlowiek);
        }
        for (int o = 0; o < Stałe.ILOSC_OWIEC; o++)
        {
            randomizeXandY();
            organizmy.add(new Owca(this, randX, randY,this.tura));
        }

        for (int w = 0; w < Stałe.ILOSC_WILKOW; w++)
        {
            randomizeXandY();
            organizmy.add(new Wilk(this, randX, randY,this.tura));
        }

        for (int l = 0; l < Stałe.ILOSC_LISOW; l++)
        {
            randomizeXandY();
            organizmy.add(new Lis(this, randX, randY,this.tura));
        }

        for (int z = 0; z < Stałe.ILOSC_ZOLWIOW; z++)
        {
            randomizeXandY();
            organizmy.add(new Żółw(this, randX, randY,this.tura));
        }

        for (int a = 0; a < Stałe.ILOSC_ANTYLOP; a++)
        {
            randomizeXandY();
            organizmy.add(new Antylopa(this, randX, randY,this.tura));
        }

        for (int t = 0; t < Stałe.ILOSC_TRAW; t++)
        {
            randomizeXandY();
            organizmy.add(new Trawa(this, randX, randY,this.tura));
        }

        for (int m = 0; m < Stałe.ILOSC_MLECZY; m++)
        {
            randomizeXandY();
            organizmy.add(new Mlecz(this, randX, randY,this.tura));
        }

        for (int g = 0; g < Stałe.ILOSC_GUARANY; g++)
        {
            randomizeXandY();
            organizmy.add(new Guarana(this, randX, randY,this.tura));
        }

        for (int j = 0; j < Stałe.ILOSC_WILCZYCHJAGOD; j++)
        {
            randomizeXandY();
            organizmy.add(new WilczeJagody(this, randX, randY,this.tura));
        }

        Organizm temp;
        for (int b = 0; b < Stałe.ILOSC_BARSZCZU_SOSNOWSKIEGO; b++)
        {
            randomizeXandY();
            temp=new BarszczSosnowskiego(this, randX, randY,this.tura);
            organizmy.add(temp);
            barszcze.add(temp);
        }

        for (int c = 0; c < Stałe.ILOSC_CYBER_OWIEC; c++)
        {
            randomizeXandY();
            organizmy.add(new CyberOwca(this, randX, randY,this.tura));
        }
    }

    public int GetTura()
    {
        return this.tura;
    }

    public void dodajOrganizm(Organizm org)
    {
        this.organizmy.add(org);

        if(org.GetNazwaGatunku().equals("BarszczSosnowskiego"))
        {
            barszcze.add(org);
        }
    }

    public void zabijOrganizm(Organizm org)
    {
        org.SetZywy(false);
    }

    public static Comparator<Organizm> porownajOrganizmy = new Comparator<Organizm>() {

        public int compare(Organizm o1, Organizm o2) {

            if (o1.GetInicjatywa() != o2.GetInicjatywa())
            {
                return (o2.GetInicjatywa()-o1.GetInicjatywa());
            }
            return ( o1.GetTuraUrodzenia()-o2.GetTuraUrodzenia());

        }};

    public boolean ZapiszSwiat(String nazwaPliku) throws FileNotFoundException {
        File plik=new File(nazwaPliku+".txt");

        PrintWriter zapis=new PrintWriter(plik);
        
         zapis.println(wysokosc+" "+szerokosc+" "+tura);
        for (Organizm organizm : organizmy)
            zapis.println(organizm.ZapiszOrganizm());
        zapis.println("K");
        zapis.close();
        return true;
    }

    public boolean ZaladujSwiat(String sciezkaDoPlikuZapisu) throws FileNotFoundException {
        File plik=new File(sciezkaDoPlikuZapisu);
        Scanner in=new Scanner(plik);

        

        int wysokosc,szerokosc,tura;
        wysokosc=in.nextInt();
        szerokosc=in.nextInt();
        tura=in.nextInt();


        this.nowaGra(wysokosc, szerokosc,tura);

        String gatunek;

        int x, y, sila, inicjatywa, umiejetnosc, cooldown, turaUrodzenia;
        while (true)
        {
            gatunek=in.nextLine();
            gatunek=in.nextLine();
            if (gatunek.equals("K"))
            {
                break;
            }

            x=in.nextInt();
            y=in.nextInt();
            sila=in.nextInt();
            inicjatywa=in.nextInt();
            turaUrodzenia=in.nextInt();
            Organizm organizm;
            switch (gatunek)
            {
                case "Czlowiek":
                    umiejetnosc=in.nextInt();
                    cooldown=in.nextInt();
                    czlowiek=new Człowiek(this,x,y,turaUrodzenia,umiejetnosc,cooldown);
                    organizm=czlowiek;

                    break;
                case "Antylopa":
                    organizm= new Antylopa(this,x,y,turaUrodzenia);
                    break;
                case "Lis":
                    organizm= new Lis(this, x, y,turaUrodzenia);
                    break;
                case "Owca":
                    organizm= new Owca(this, x, y,turaUrodzenia);
                    break;
                case "Wilk":
                    organizm= new Wilk(this, x, y,turaUrodzenia);
                    break;
                case "Zolw":
                    organizm= new Żółw(this, x, y,turaUrodzenia);
                    break;
                case "BarszczSosnowskiego":
                    organizm= new BarszczSosnowskiego(this, x, y,turaUrodzenia);
                    this.barszcze.add(organizm);
                    break;
                case "Guarana":
                    organizm= new Guarana(this, x, y,turaUrodzenia);
                    break;
                case "Mlecz":
                    organizm= new Mlecz(this, x, y,turaUrodzenia);
                    break;
                case "Trawa":
                    organizm= new Trawa(this, x,y,turaUrodzenia);
                    break;
                case "WilczeJagody":
                    organizm= new WilczeJagody(this, x, y,turaUrodzenia);
                    break;
                case "CyberOwca":
                    organizm= new CyberOwca(this, x, y,turaUrodzenia);
                    break;
                default:
                    throw new IllegalStateException("Nieznany gatunek: " + gatunek);
            }

            organizm.SetSila(sila);
            organizm.SetInicjatywa(inicjatywa);
            this.organizmy.add(organizm);

        }

        okno.removeKeyListener(m);
        okno.remove(m);
        m=new Projekt.Wizualizacja.Menu(okno,this,czlowiek,plansza);
        okno.add(m);
        p=new Plansza(okno,this,plansza,wysokosc,szerokosc);
        okno.remove(kom);
        okno.revalidate();
        okno.repaint();
        kom=new Komentator(okno,this, (Człowiek) czlowiek);
        okno.add(kom);

        okno.revalidate();
        okno.repaint();

        return true;
    }

    public void nowaGra(int wysokosc,int szerokosc,int tura)
    {
        for (int i = 0; i < this.wysokosc; i++)
        {
            for (int j = 0; j < this.szerokosc; j++)
            {
                this.plansza[i][j] = new Pole(this);
            }
        }

        for(int j=barszcze.size()-1;j>=0;j--)
        {
            barszcze.remove(j);
        }

        for (int i = organizmy.size() - 1; i >= 0; i--) {
            organizmy.remove(i);
        }

        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        this.tura=tura;
    }

    public void ZmienOrganizm(String org,int x, int y)
    {
        Organizm organizm;
        this.GetPole(x,y).SetZywy(false);
        switch (org)
        {
            case "Antylopa":
                organizm= new Antylopa(this,x,y,tura);
                break;
            case "Lis":
                organizm= new Lis(this, x, y,tura);
                break;
            case "Owca":
                organizm= new Owca(this, x, y,tura);
                break;
            case "Wilk":
                organizm= new Wilk(this, x, y,tura);
                break;
            case "Zolw":
                organizm= new Żółw(this, x, y,tura);
                break;
            case "BarszczSosnowskiego":
                organizm= new BarszczSosnowskiego(this, x, y,tura);
                this.barszcze.add(organizm);
                break;
            case "Guarana":
                organizm= new Guarana(this, x, y,tura);
                break;
            case "Mlecz":
                organizm= new Mlecz(this, x, y,tura);
                break;
            case "Trawa":
                organizm= new Trawa(this, x,y,tura);
                break;
            case "WilczeJagody":
                organizm= new WilczeJagody(this, x, y,tura);
                break;
            case "CyberOwca":
                organizm= new CyberOwca(this, x, y,tura);
                break;
            default:
                throw new IllegalStateException("Nieznany gatunek: " + org);

        }
        this.organizmy.add(organizm);
    }



}


