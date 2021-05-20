package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(){};

    public Wilk(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 5, 9);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Wilk");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(177,0,0);
    }

    @Override
    public Organizm Kopiuj( int x, int y)
    {
        return new Wilk(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }
}