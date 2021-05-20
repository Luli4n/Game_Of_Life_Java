package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Żółw extends Zwierze {

    public Żółw(){};

    public Żółw(Swiat _swiat, int x, int y, int turaUrodzenia)
    {
        super(x, y, turaUrodzenia, 1, 2);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Zolw");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(97, 48, 0);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new Żółw(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void akcja()
    {
        int czySiePoruszam = (ThreadLocalRandom.current().nextInt(0, 100)) + 1;

        if (czySiePoruszam > 75)
        {
            super.akcja();
        }
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        if (atakujacy.GetSila() < 5)
        {
            this.GetSwiat().kom.OdbilAtak(this,atakujacy);
            return true;
        }
        else
        {
            return false;
        }
    }
}
