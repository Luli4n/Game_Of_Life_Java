package Projekt.Rośliny;

import Projekt.Organizm;
import Projekt.Roślina;
import Projekt.Swiat;

import java.awt.*;

public class Mlecz extends Roślina {

    public Mlecz() {}

    public Mlecz(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 0, 0);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Mlecz");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(120, 116, 121);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new Mlecz(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void akcja()
    {
        for (int i = 0; i < 3; i++)
        {
            super.akcja();
        }
    }
}
