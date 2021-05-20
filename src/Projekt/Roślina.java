package Projekt;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Roślina extends Organizm {

    public Roślina(){};

    public Roślina(int x, int y, int turaUrodzenia, int inicjatywa, int sila) {
        super(x, y, turaUrodzenia, inicjatywa, sila);
    }

    @Override
    public void akcja() {
        int prawdopodobienstwo = ThreadLocalRandom.current().nextInt(0, 100);

        if (prawdopodobienstwo < 5)
        {

            int aktualnyX = this.GetX();
            int aktualnyY = this.GetY();
            int nowyX=aktualnyX;
            int nowyY=aktualnyY;

            int rozprz;
            boolean powodzenieRozprz = false;
            boolean[] sprawdzenieStron = { false,false,false,false };

            boolean flaga=false;

            while (!powodzenieRozprz)
            {
                rozprz = ThreadLocalRandom.current().nextInt(0, 4);
                switch (rozprz)
                {
                    case GORA:
                        if (aktualnyY != 0)
                        {
                            nowyY = aktualnyY - 1;
                            nowyX = aktualnyX;
                            flaga=true;
                        }
                        sprawdzenieStron[0] = true;
                        break;
                    case DOL:
                        if (aktualnyY != this.GetSwiat().GetWysokosc() - 1)
                    {
                        nowyY = aktualnyY + 1;
                        nowyX = aktualnyX;
                        flaga=true;
                    }
                    sprawdzenieStron[1] = true;
                    break;
                    case LEWO:
                        if (aktualnyX != 0)
                        {
                            nowyY = aktualnyY;
                            nowyX = aktualnyX - 1;
                            flaga=true;
                        }
                        sprawdzenieStron[2] = true;
                        break;
                    case PRAWO:
                        if (aktualnyX != this.GetSwiat().GetSzerokosc() - 1)
                    {
                        nowyY = aktualnyY;
                        nowyX = aktualnyX + 1;
                        flaga=true;
                    }
                    sprawdzenieStron[3] = true;
                    break;
                }
                if (flaga && this.GetSwiat().GetPole(nowyX, nowyY).maluj().equals(Color.WHITE))
                {
                    powodzenieRozprz = true;
                }

                if (sprawdzenieStron[0] && sprawdzenieStron[1] && sprawdzenieStron[2] && sprawdzenieStron[3] && !powodzenieRozprz)
                {
                    break;
                }
                flaga=false;
            }

            if (powodzenieRozprz)
            {
                this.GetSwiat().kom.RozprzestrzeniaSie(this);
                Organizm nowy=this.Kopiuj(nowyX, nowyY);
                this.GetSwiat().dodajOrganizm(nowy);
            }

        }
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        this.kolizja(atakujacy);
        return true;
    }

    @Override
    public void kolizja(Organizm atakujacy)
    {
        this.GetSwiat().SetPole(new Pole(this.GetSwiat()), atakujacy.GetX(), atakujacy.GetY());
        this.GetSwiat().kom.Zjada(atakujacy,this);
        this.GetSwiat().SetPole(atakujacy, this.GetX(), this.GetY());
        atakujacy.SetX(this.GetX());
        atakujacy.SetY(this.GetY());
        this.GetSwiat().zabijOrganizm(this);
    }


    public abstract Organizm Kopiuj(int x, int y);
}
