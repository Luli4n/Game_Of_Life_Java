package Projekt;

import java.awt.*;

public abstract class Organizm extends Pole implements InterfejsOrganizmu{

    private int sila;
    private int inicjatywa;
    private int turaUrodzenia;
    private int turaRozmnazania;
    private int polozenieX;
    private int polozenieY;
    private String nazwaGatunku;

    public Organizm(){};

    public Organizm(int x, int y, int turaUrodzenia, int inicjatywa, int sila){
        polozenieX=x;
        polozenieY=y;
        this.turaUrodzenia = turaUrodzenia;
        this.turaRozmnazania=turaUrodzenia;
        this.inicjatywa=inicjatywa;
        this.sila=sila;
        this.SetZywy(true);
    }

    public void SetX(int x)
    {
        this.polozenieX = x;
    }

    public void SetY(int y)
    {
        this.polozenieY = y;
    }

    public int GetX()
    {
        return this.polozenieX;
    }

    public int GetY()
    {
        return this.polozenieY;
    }

    @Override
    public String GetNazwaGatunku()
    {
        return this.nazwaGatunku;
    }

    public void SetNazwaGatunku(String gatunek)
    {
        this.nazwaGatunku=gatunek;
    }

    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        return false;
    }

    @Override
    public int GetSila()
    {
        return this.sila;
    }

    public int KiedySieRozmnozyl()
    {
        return turaRozmnazania;
    }

    public void SetRozmnozylSie(int kiedy)
    {
        this.turaRozmnazania = kiedy;
    }

    public boolean sprawdzMiejsce()
    {
        int ilosc_miejsc=0;
        int x = this.GetX();
        int y = this.GetY();

        if (y != 0)
        {
            if (this.GetSwiat().GetPole(x,y-1).maluj().equals(Color.WHITE) || !this.GetSwiat().GetPole(x, y - 1).GetZywy())
            {
                ilosc_miejsc++;
            }
        }

        if (y != this.GetSwiat().GetWysokosc() - 1)
        {
            if (this.GetSwiat().GetPole(x,y+1).maluj().equals(Color.WHITE) || !this.GetSwiat().GetPole(x, y + 1).GetZywy())
            {
                ilosc_miejsc++;
            }

        }

        if (x != 0)
        {
            if (this.GetSwiat().GetPole(x-1,y).maluj().equals(Color.WHITE) || !this.GetSwiat().GetPole(x - 1, y).GetZywy())
            {
                ilosc_miejsc++;
            }
        }

        if (x!= this.GetSwiat().GetSzerokosc() - 1)
        {
            if (this.GetSwiat().GetPole(x+1,y).maluj().equals(Color.WHITE) || !this.GetSwiat().GetPole(x + 1, y).GetZywy())
            {
                ilosc_miejsc++;
            }
        }

        return ilosc_miejsc != 0;
    }

    public int GetTuraUrodzenia()
    {
        return this.turaUrodzenia;
    }

    public int GetInicjatywa()
    {
        return this.inicjatywa;
    }

    public void SetSila(int sila)
    {
        this.sila=sila;
    }

    public String ZapiszOrganizm()
    {
        String organizm="";
        organizm += this.GetNazwaGatunku();
        organizm += "\n";
        organizm += this.GetX();
        organizm += " ";
        organizm += this.GetY();
        organizm += " ";
        organizm += this.GetSila();
        organizm += " ";
        organizm += this.GetInicjatywa();
        organizm+= " ";
        organizm+=this.GetTuraUrodzenia();

        return organizm;
    }

    public void SetInicjatywa(int inicjatywa)
    {
        this.inicjatywa=inicjatywa;
    };
}
