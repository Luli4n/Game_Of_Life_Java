package Projekt.Zwierzeta;

import Projekt.Organizm;
import Projekt.Pole;
import Projekt.Swiat;
import Projekt.Zwierze;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class Człowiek extends Zwierze {

    private int turaUmiejetnosci;
    private int turaCooldownu;
    private int ruch;
    private boolean czyAkt;

    public Człowiek(){};

    public Człowiek(Swiat _swiat, int x, int y, int turaUrodzenia,int turaUmiejetnosci, int turaCooldownu) {
        super(x, y, turaUrodzenia, 4, 5);
        this.SetSwiat(_swiat);
        this.SetNazwaGatunku("Czlowiek");
        this.GetSwiat().SetPole(this,x,y);
        this.turaUmiejetnosci=turaUmiejetnosci;
        this.turaCooldownu=turaCooldownu;
        ruch=-1;
        czyAkt=false;
    }

    @Override
    public Color maluj()
    {
        return new Color(0, 0, 0, 255);
    }

    @Override
    public Organizm Kopiuj(int x, int y)
    {
        return new Człowiek(this.GetSwiat(),x,y,this.GetSwiat().GetTura(),0,0);
    }

    public int GetTuraCd()
    {
        return this.turaCooldownu;
    }

    public int GetTuraUm()
    {
        return this.turaUmiejetnosci;
    }

    public void aktywujUmiejetnosc()
    {
        this.turaUmiejetnosci = 5;
        this.SetSila(this.GetSila() + turaUmiejetnosci);
        this.czyAkt=true;
    }

    public void wykonajRuch(KeyEvent ke)
    {
        switch(ke.getKeyCode())
        {
            case VK_DOWN:
                ruch=DOL;
                break;
            case VK_UP:
                ruch=GORA;
                break;
            case VK_LEFT:
                ruch=LEWO;
                break;
            case VK_RIGHT:
                ruch=PRAWO;
                break;
        }
    }

    @Override
    public void akcja() {

        if (turaUmiejetnosci > 1) {
            this.SetSila(this.GetSila() - 1);
        }

        if (!czyAkt) {

            if (turaUmiejetnosci != 0) {
                --turaUmiejetnosci;
                this.GetSwiat().kom.UmiejetnoscAktywna(this);
                ++turaCooldownu;
            }
            else if(turaCooldownu!=0)
            {
                --turaCooldownu;
                this.GetSwiat().kom.UmiejetnoscCooldown(this);
            }
        }

        czyAkt=false;


        if (this.ruch != -1) {
            int aktualnyX = this.GetX();
            int aktualnyY = this.GetY();
            int nastepnyX = aktualnyX;
            int nastepnyY = aktualnyY;

            boolean powodzenieRuchu = false;
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

    if(powodzenieRuchu) {
        if (!this.GetSwiat().GetPole(nastepnyX, nastepnyY).maluj().equals(Color.WHITE)) {
            this.kolizja((Organizm) this.GetSwiat().GetPole(nastepnyX, nastepnyY));
        } else {
            this.GetSwiat().SetPole(new Pole(this.GetSwiat()), aktualnyX, aktualnyY);
            this.GetSwiat().SetPole(this, nastepnyX, nastepnyY);
            this.SetX(nastepnyX);
            this.SetY(nastepnyY);
        }

    }
        }
    ruch=-1;
    }

    @Override
    public String ZapiszOrganizm()
    {
        String organizm="";
        organizm+=super.ZapiszOrganizm();
        organizm+=" ";
        organizm += turaUmiejetnosci;
        organizm += " ";
        organizm +=turaCooldownu;

        return organizm;
    }

}
