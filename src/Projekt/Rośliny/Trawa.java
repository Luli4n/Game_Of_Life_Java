package Projekt.Rośliny;

import Projekt.Organizm;
import Projekt.Roślina;
import Projekt.Swiat;

import java.awt.*;

public class Trawa extends Roślina {

        public Trawa() {}

        public Trawa(Swiat _swiat, int x, int y, int turaUrodzenia) {
            super(x, y, turaUrodzenia, 0, 0);
            this.SetSwiat(_swiat);
            this.SetNazwaGatunku("Trawa");
            this.GetSwiat().SetPole(this,x,y);
        }

        @Override
        public Color maluj()
        {
            return new Color(12,255, 33);
        }

        @Override
        public Organizm Kopiuj(int x, int y)
        {
            return new Trawa(this.GetSwiat(),x,y,this.GetSwiat().GetTura());
        }

}
