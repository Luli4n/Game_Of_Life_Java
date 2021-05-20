package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Antylopa extends Zwierze {

    public Antylopa(){};

    public Antylopa(Swiat _swiat, int x, int y, int turaUrodzenia)
    {
        super(x, y, turaUrodzenia, 4, 4);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Antylopa");
        this.GetSwiat().SetPole(this,x,y);
    }

    @Override
    public Color maluj()
    {
        return new Color(143, 153, 9);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new Antylopa(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
    }

    @Override
    public void akcja()
    {
        int aktualnyX = this.GetX();
        int aktualnyY = this.GetY();
        int nastepnyX=aktualnyX;
        int nastepnyY=aktualnyY;

        int ruch;
        boolean powodzenieRuchu = false;
        while (!powodzenieRuchu)
        {
            ruch = ThreadLocalRandom.current().nextInt(0, 8);;
            switch (ruch)
            {
                case GORA:
                    if (aktualnyY != 0)
                    {
                        nastepnyY = aktualnyY - 1;
                        nastepnyX = aktualnyX;
                        powodzenieRuchu = true;
                    }
                    break;
                case DOL:
                    if (aktualnyY != this.GetSwiat().GetWysokosc() - 1)
                {
                    nastepnyY = aktualnyY + 1;
                    nastepnyX = aktualnyX;
                    powodzenieRuchu = true;
                }
                break;
                case LEWO:
                    if (aktualnyX != 0)
                    {
                        nastepnyY = aktualnyY;
                        nastepnyX = aktualnyX - 1;
                        powodzenieRuchu = true;
                    }
                    break;
                case PRAWO:
                    if (aktualnyX != this.GetSwiat().GetSzerokosc() - 1)
                {
                    nastepnyY = aktualnyY;
                    nastepnyX = aktualnyX + 1;
                    powodzenieRuchu = true;
                }
                break;
                case GORA2X:
                    if (aktualnyY > 1)
                    {
                        nastepnyY = aktualnyY - 2;
                        nastepnyX = aktualnyX;
                        powodzenieRuchu = true;
                    }
                    break;
                case DOL2X:
                    if (aktualnyY < this.GetSwiat().GetWysokosc() - 2)
                {
                    nastepnyY = aktualnyY + 2;
                    nastepnyX = aktualnyX;
                    powodzenieRuchu = true;
                }
                break;
                case LEWO2X:
                    if (aktualnyX > 1)
                    {
                        nastepnyY = aktualnyY;
                        nastepnyX = aktualnyX - 2;
                        powodzenieRuchu = true;
                    }
                    break;
                case PRAWO2X:
                    if (aktualnyX < this.GetSwiat().GetSzerokosc() - 2)
                {
                    nastepnyY = aktualnyY;
                    nastepnyX = aktualnyX + 2;
                    powodzenieRuchu = true;
                }
                break;
            }
        }

        if (!this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE)) {
        this.kolizja((Organizm) this.GetSwiat().GetPole(nastepnyX, nastepnyY));
    }
	else
        {
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), this.GetX(), this.GetY());
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
        }
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        int random = ThreadLocalRandom.current().nextInt(0, 100) + 1;

        if (random < 50)
        {
            int aktualnyX = this.GetX();
            int aktualnyY = this.GetY();
            int nastepnyX = aktualnyX;
            int nastepnyY = aktualnyY;

            int ruch;
            boolean powodzenieRuchu = false;
            boolean[] sprawdzenieStron = { false,false,false,false };

            while (!powodzenieRuchu)
            {
                ruch = ThreadLocalRandom.current().nextInt(0, 4);;
                switch (ruch)
                {
                    case GORA:
                        if (aktualnyY != 0)
                        {
                            nastepnyY = aktualnyY - 1;
                            nastepnyX = aktualnyX;

                        }
                        sprawdzenieStron[0] = true;
                        break;
                    case DOL:
                        if (aktualnyY != this.GetSwiat().GetWysokosc() - 1)
                    {
                        nastepnyY = aktualnyY + 1;
                        nastepnyX = aktualnyX;

                    }
                    sprawdzenieStron[1] = true;
                    break;
                    case LEWO:
                        if (aktualnyX != 0)
                        {
                            nastepnyY = aktualnyY;
                            nastepnyX = aktualnyX - 1;

                        }
                        sprawdzenieStron[2] = true;
                        break;
                    case PRAWO:
                        if (aktualnyX != this.GetSwiat().GetSzerokosc() - 1)
                    {
                        nastepnyY = aktualnyY;
                        nastepnyX = aktualnyX + 1;

                    }
                    sprawdzenieStron[3] = true;
                    break;
                }
                if (this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE))
                {
                    powodzenieRuchu = true;
                }

                if (sprawdzenieStron[0] && sprawdzenieStron[1] && sprawdzenieStron[2] && sprawdzenieStron[3] && !powodzenieRuchu)
                {
                    return false;
                }
            }


            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), atakujacy.GetX(), atakujacy.GetY());
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            atakujacy.SetX(aktualnyX);
            atakujacy.SetY(aktualnyY);
            atakujacy.GetSwiat().SetPole(atakujacy, aktualnyX, aktualnyY);

            return true;
        }
        else
        {
            return false;
        }
    }

}
