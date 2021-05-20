package Projekt.Wizualizacja;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierzeta.Człowiek;

import javax.swing.*;

public class Komentator extends JLabel {

    private JFrame okno;
    private String komentarz;
    private Swiat swiat;
    private Człowiek czlowiek;

    public Komentator(JFrame okno,Swiat swiat,Człowiek czl)
    {
        this.okno=okno;
        this.swiat=swiat;
        this.czlowiek=czl;
        komentarz="<html>Komentarz:";
        Dodaj();
    }

    public void Dodaj()
    {

        this.setVisible(true);
        okno.setFocusable(true);
    }

    public void Komentuj()
    {
        komentarz+="</html>";
        this.setText(komentarz);
        okno.setFocusable(true);
    }

    public void Wyczysc()
    {
        komentarz="<html>Komentarz:<br/> Sila czlowieka wynosi: "+czlowiek.GetSila()+" <br/>";
    }

    public void Zabija(Organizm org1,Organizm org2)
    {
        komentarz+=org1.GetNazwaGatunku()+" zabija "+org2.GetNazwaGatunku()+"<br/>";
    }

    public void OdbilAtak(Organizm org1, Organizm org2)
    {
        komentarz+=org1.GetNazwaGatunku()+" odbil atak ze strony "+org2.GetNazwaGatunku()+"<br/>";
    }

    public void UmiejetnoscAktywna(Człowiek czl)
    {
        komentarz+="Magiczny Eliksir bedzie jeszcze aktywny przez "+czl.GetTuraUm()+" tur <br/>";
    }

    public void UmiejetnoscCooldown(Człowiek czl)
    {
        komentarz+="Magiczny Eliksir bedzie dostepny za "+czl.GetTuraCd()+" tur <br/>";
    }

    public void NarodzilSie(Organizm org)
    {
        komentarz+=org.GetNazwaGatunku()+" narodzil sie <br/>";
    }
    public void RozprzestrzeniaSie(Organizm org)
    {
        komentarz+=org.GetNazwaGatunku()+" rozprzestrzenil sie <br/>";
    }

    public void Zjada(Organizm org, Organizm rosl)
    {
        komentarz+=org.GetNazwaGatunku()+" zjada "+ rosl.GetNazwaGatunku()+" <br/>";
    }

    public void RosnieWSile(Organizm org1, Organizm org2)
    {
        komentarz+=org1.GetNazwaGatunku()+" sila rosnie o 3 po zjedzeniu "+ org2.GetNazwaGatunku()+" <br/>";
    }

    public void ZabijaZwierzeWSasiedztwie(Organizm org)
    {
        komentarz+=org.GetNazwaGatunku()+" zabija organizm w swoim sasiedztwie <br/>";
    }

}
