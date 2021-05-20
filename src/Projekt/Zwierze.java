package Projekt;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Zwierze extends Organizm {

    public Zwierze(){};

    public Zwierze(int x, int y, int turaUrodzenia, int inicjatywa, int sila) {
        super(x, y, turaUrodzenia, inicjatywa, sila);
    }

    @Override
    public void akcja() {
        int aktualnyX = this.GetX();
        int aktualnyY = this.GetY();
        int nastepnyX=aktualnyX;
        int nastepnyY=aktualnyY;

        int ruch;
        boolean powodzenieRuchu = false;
        while (!powodzenieRuchu) {
            ruch = ThreadLocalRandom.current().nextInt(0, 4);
            switch (ruch) {
                case GORA:
                    if (aktualnyY != 0) {
                        nastepnyY = aktualnyY - 1;
                        nastepnyX = aktualnyX;
                        powodzenieRuchu = true;
                    }
                    break;
                case DOL:
                    if (aktualnyY != this.GetSwiat().GetWysokosc() - 1) {
                        nastepnyY = aktualnyY + 1;
                        nastepnyX = aktualnyX;
                        powodzenieRuchu = true;
                    }
                    break;
                case LEWO:
                    if (aktualnyX != 0) {
                        nastepnyY = aktualnyY;
                        nastepnyX = aktualnyX - 1;
                        powodzenieRuchu = true;
                    }
                    break;
                case PRAWO:
                    if (aktualnyX != this.GetSwiat().GetSzerokosc() - 1) {
                        nastepnyY = aktualnyY;
                        nastepnyX = aktualnyX + 1;
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
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), aktualnyX, aktualnyY);
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
        }
    }



    @Override
    public void kolizja(Organizm kolidujacy)
    {
        String GatunekOrganizmu = this.GetNazwaGatunku();
        String GatunekKolidujacego = kolidujacy.GetNazwaGatunku();

        if (GatunekOrganizmu.equals(GatunekKolidujacego))
        {
            rozmnazaj(kolidujacy);
        }
        else if (!kolidujacy.czyOdbilAtak(this))
        {

            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), this.GetX(), this.GetY());
            if (this.GetSila() >= kolidujacy.GetSila())
            {
                this.GetSwiat().kom.Zabija(this,kolidujacy);
                this.GetSwiat().SetPole(this, kolidujacy.GetX(), kolidujacy.GetY());
                this.SetX(kolidujacy.GetX());
                this.SetY(kolidujacy.GetY());
                this.GetSwiat().zabijOrganizm(kolidujacy);
            }
				else
            {
                this.GetSwiat().kom.Zabija(kolidujacy,this);
                this.GetSwiat().zabijOrganizm(this);
            }

        }
    }


    public void rozmnazaj(Organizm kolidujacy)
        {
        if (this.KiedySieRozmnozyl() != this.GetSwiat().GetTura() && kolidujacy.KiedySieRozmnozyl() != this.GetSwiat().GetTura()) {
        boolean flaga_miejsce1 = false;
        boolean flaga_miejsce2 = false;
        int Wybor_Organizmu=0;

        if (this.sprawdzMiejsce())
        {
        flaga_miejsce1 = true;
        Wybor_Organizmu = 0;
        }
        if (kolidujacy.sprawdzMiejsce())
        {
        flaga_miejsce2 = true;
        Wybor_Organizmu = 1;
        }

        if (flaga_miejsce1 && flaga_miejsce2) {
        Wybor_Organizmu = ThreadLocalRandom.current().nextInt(0, 2);
        }

        if (flaga_miejsce1 || flaga_miejsce2) {
        int nowyX;
        int nowyY;

        int randX = ThreadLocalRandom.current().nextInt(0, 3);
        int randY = ThreadLocalRandom.current().nextInt(0, 3);
        boolean flaga = false;
        if (Wybor_Organizmu == 0)
        {
        nowyX = this.GetX();
        nowyY = this.GetY();
        }
        else
        {
        nowyX = kolidujacy.GetX();
        nowyY = kolidujacy.GetY();
        }

        while (true)
        {

            if ((nowyY - 1 + randY) >= 0 && (nowyY - 1 + randY) <= (this.GetSwiat().GetWysokosc() - 1)
            && (nowyX - 1 + randX) >= 0 && (nowyX - 1 + randX) <= (this.GetSwiat().GetSzerokosc() - 1))
            {
                flaga = true;
            }
            if (flaga) {
                if (this.GetSwiat().GetPole(nowyX - 1 + randX,nowyY - 1 + randY).maluj().equals(Color.WHITE))
                {
                    flaga = true;
                    break;
                }
            flaga = false;
            }
        randX = ThreadLocalRandom.current().nextInt(0, 3);
        randY = ThreadLocalRandom.current().nextInt(0, 3);

        }
        Organizm noweZycie = Kopiuj(nowyX - 1 + randX, nowyY - 1 + randY);
        this.GetSwiat().dodajOrganizm(noweZycie);
        this.GetSwiat().kom.NarodzilSie(noweZycie);
        this.SetRozmnozylSie(this.GetSwiat().GetTura());
        kolidujacy.SetRozmnozylSie(kolidujacy.GetSwiat().GetTura());

        }
        }
        }

        public abstract Organizm Kopiuj(int x, int y);
}
