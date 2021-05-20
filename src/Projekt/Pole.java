package Projekt;

import java.awt.*;

public class Pole {

    public final static int GORA=0;
    public final static int DOL=1;
    public final static int LEWO=2;
    public final static int PRAWO=3;
    public final static int GORA2X=4;
    public final static int DOL2X=5;
    public final static int LEWO2X=6;
    public final static int PRAWO2X=7;

    private Swiat swiat;
    private boolean CzyZapelnione;
    public Pole() {};

    public Pole(Swiat _swiat)
    {
        swiat=_swiat;
        CzyZapelnione=false;
    }
    public Color maluj()
    {
        return Color.WHITE;
    }

    public void SetSwiat(Swiat _swiat)
    {
        this.swiat = _swiat;
    }

    public Swiat GetSwiat()
    {
        return this.swiat;
    }

    public void SetZywy(boolean czy)
    {
        this.CzyZapelnione = czy;
    }

    public boolean GetZywy()
    {
        return this.CzyZapelnione;
    }

    public int GetSila()
    {
        return 0;
    }

    public String GetNazwaGatunku() {return "";}

}
