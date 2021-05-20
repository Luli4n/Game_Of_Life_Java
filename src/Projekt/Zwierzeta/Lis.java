package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Lis extends Zwierze {

    public Lis(){};

    public Lis(Swiat _swiat, int x, int y, int turaUrodzenia)
    {
        super(x, y, turaUrodzenia, 7, 3);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Lis");
        this.GetSwiat().SetPole(this,x,y);
    }

        @Override
        public Color maluj()
        {
            return new Color(255, 151, 27);
        }

        @Override
        public Organizm Kopiuj( int x, int y)
        {
            return new Lis(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
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
        boolean[] sprawdzenieStron = { false,false,false,false };
        while (!powodzenieRuchu)
        {
            ruch = ThreadLocalRandom.current().nextInt(0, 4);
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
            if (this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE) || this.GetSwiat().GetPole(nastepnyX, nastepnyY).GetSila() < this.GetSila())
            {
                powodzenieRuchu = true;
            }

            if (sprawdzenieStron[0] && sprawdzenieStron[1] && sprawdzenieStron[2] && sprawdzenieStron[3] && !powodzenieRuchu)
            {
                powodzenieRuchu = true;
                nastepnyY = aktualnyY;
                nastepnyX = aktualnyX;
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
}
