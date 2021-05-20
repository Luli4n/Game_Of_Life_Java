package Projekt.Rośliny;

import Projekt.Organizm;
import Projekt.Roślina;
import Projekt.Swiat;

import java.awt.*;

public class Guarana extends Roślina {

    public Guarana() {}

    public Guarana(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 0, 0);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Guarana");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(255, 124, 191);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new Guarana(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void kolizja(Organizm atakujacy)
    {
        this.GetSwiat().kom.RosnieWSile(atakujacy,this);
        atakujacy.SetSila(atakujacy.GetSila() + 3);
        super.kolizja(atakujacy);
    }
}
