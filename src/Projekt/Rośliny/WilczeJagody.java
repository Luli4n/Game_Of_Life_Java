package Projekt.Rośliny;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Roślina;
import Projekt.Swiat;

import java.awt.*;

public class WilczeJagody extends Roślina {

    public WilczeJagody() {}

    public WilczeJagody(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 0, 99);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("WilczeJagody");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(124, 0, 255);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new WilczeJagody(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        this.GetSwiat().kom.Zabija(this,atakujacy);

        this.GetSwiat().SetPole(new Pole(this.GetSwiat()), atakujacy.GetX(), atakujacy.GetY());
        this.GetSwiat().zabijOrganizm(atakujacy);
    }
}
