package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca() {

    };
    public Owca(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 4, 4);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Owca");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(255,255,179);
    }

    @Override
    public Organizm Kopiuj( int x, int y)
    {
        return new Owca(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }
}

