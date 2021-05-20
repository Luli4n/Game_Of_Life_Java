package Projekt.Rośliny;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Roślina;
import Projekt.Swiat;

import java.awt.*;

public class BarszczSosnowskiego extends Roślina {

    public BarszczSosnowskiego() {}

    public BarszczSosnowskiego(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 0, 10);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("BarszczSosnowskiego");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(0, 7, 255);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new BarszczSosnowskiego(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void kolizja(Organizm atakujacy) {


        this.GetSwiat().SetPole(new Pole(this.GetSwiat()), atakujacy.GetX(), atakujacy.GetY());
        if (this.GetSila() >= atakujacy.GetSila())
        {
            this.GetSwiat().kom.Zabija(this,atakujacy);
            this.GetSwiat().zabijOrganizm(atakujacy);
        }
        else
        {
            this.GetSwiat().kom.Zjada(atakujacy,this);

            this.GetSwiat().SetPole(atakujacy, this.GetX(), this.GetY());
            atakujacy.SetX(this.GetX());
            atakujacy.SetY(this.GetY());
            this.GetSwiat().zabijOrganizm(this);
        }
    }

    @Override
    public void akcja()
    {

        int aktualnyX = this.GetX();
        int aktualnyY = this.GetY();

        if (aktualnyY != 0)
        {
            if (sprawdzCzyZwierze(aktualnyX, aktualnyY - 1))
            {
                zabij(aktualnyX, aktualnyY - 1);
            }
        }

        if (aktualnyY != this.GetSwiat().GetWysokosc() - 1)
        {
            if (sprawdzCzyZwierze(aktualnyX, aktualnyY + 1))
            {
                zabij(aktualnyX, aktualnyY + 1);
            }
        }

        if (aktualnyX != 0)
        {
            if (sprawdzCzyZwierze(aktualnyX - 1, aktualnyY))
            {
                zabij(aktualnyX-1, aktualnyY);
            }
        }

        if (aktualnyX != this.GetSwiat().GetSzerokosc() - 1)
        {
            if (sprawdzCzyZwierze(aktualnyX + 1, aktualnyY))
            {
                zabij(aktualnyX+1, aktualnyY);
            }
        }

        super.akcja();
    }

    public boolean sprawdzCzyZwierze(int x, int y)
    {
        if (!this.GetSwiat().GetPole(x, y).maluj().equals(Color.WHITE)) {
        String klasaOrganizmu = this.GetSwiat().GetPole(x, y).GetNazwaGatunku();
        if (klasaOrganizmu.equals("Guarana")
                || klasaOrganizmu.equals("Mlecz")  || klasaOrganizmu.equals("WilczeJagody")
                || klasaOrganizmu.equals("Trawa")  || klasaOrganizmu.equals("BarszczSosnowskiego") )
        {
            return false;
        }
        return true;
    }
	else
        {
            return false;
        }
    }

    public void zabij(int x, int y)
    {
        Organizm organizm = (Organizm) this.GetSwiat().GetPole(x, y);

        this.GetSwiat().kom.ZabijaZwierzeWSasiedztwie(this);
        if(!organizm.GetNazwaGatunku().equals("CyberOwca")) {
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), organizm.GetX(), organizm.GetY());
            this.GetSwiat().zabijOrganizm(organizm);
        }
    }
}
