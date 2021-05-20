package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class CyberOwca extends Zwierze {

    public CyberOwca() {}

    public CyberOwca(Swiat _swiat, int x, int y, int turaUrodzenia) {
        super(x, y, turaUrodzenia, 4, 11);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("CyberOwca");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(0, 240, 255);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new CyberOwca(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void akcja()
    {
        if(GetSwiat().barszcze.size()!=0) {
            int xBarszczu = GetSwiat().barszcze.get(0).GetX();
            int yBarszczu = GetSwiat().barszcze.get(0).GetY();
            Organizm Barszcz;
            for (int i = GetSwiat().barszcze.size() - 1; i >= 0; i--) {
                if (abs(GetSwiat().barszcze.get(i).GetX() - this.GetX()) + abs(GetSwiat().barszcze.get(i).GetY() - this.GetY()) <
                        abs(xBarszczu - this.GetX()) + abs(yBarszczu - this.GetY())) {
                    xBarszczu = GetSwiat().barszcze.get(i).GetX();
                    yBarszczu = GetSwiat().barszcze.get(i).GetY();
                }
            }
            Barszcz= (Organizm) this.GetSwiat().GetPole(xBarszczu,yBarszczu);

            int OsRuchu= ThreadLocalRandom.current().nextInt(0,2);

            boolean flaga=false;
            while(true)
            {
                switch(OsRuchu)
                {
                    case 0:
                        if(!zmienX(xBarszczu)) {
                        OsRuchu=1;
                        }
                        else
                        {
                            flaga=true;
                        }
                        break;
                    case 1:
                        if(!zmienY(yBarszczu)){
                            OsRuchu=0;
                        }
                        else
                        {
                            flaga=true;
                        }
                        break;
                }

                if(this.GetX()==xBarszczu && this.GetY()==yBarszczu)
                {
                    this.GetSwiat().zabijOrganizm(Barszcz);

                    break;
                }

                if(flaga)
                {
                    break;
                }
            }
        }
        else
        {
            super.akcja();
        }
    }

    public boolean zmienX(int xBarszczu)
    {
        int aktualnyX=this.GetX();
        int aktualnyY=this.GetY();
        int nastepnyX=aktualnyX;
        int nastepnyY=aktualnyY;

        if(aktualnyX>xBarszczu)
        {
            nastepnyX=aktualnyX-1;
        }
        else if(aktualnyX<xBarszczu)
        {
            nastepnyX=aktualnyX+1;
        }
        else
        {
            return false;
        }

        if (!this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE)) {
            this.kolizja((Organizm) this.GetSwiat().GetPole(nastepnyX, nastepnyY));
        }
        else
        {
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), aktualnyX, aktualnyY);
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
        }
        return true;
    }

    public boolean zmienY(int yBarszczu)
    {
        int aktualnyX=this.GetX();
        int aktualnyY=this.GetY();
        int nastepnyX=aktualnyX;
        int nastepnyY=aktualnyY;

        if(aktualnyY>yBarszczu)
        {
            nastepnyY=aktualnyY-1;
        }
        else if(aktualnyY<yBarszczu)
        {
            nastepnyY=aktualnyY+1;
        }
        else
        {
            return false;
        }

        if (!this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE)) {
            this.kolizja((Organizm) this.GetSwiat().GetPole(nastepnyX, nastepnyY));
        }
        else
        {
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), aktualnyX, aktualnyY);
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
        }
        return true;
    }
}
