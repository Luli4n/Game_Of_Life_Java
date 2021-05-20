package Projekt;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Swiat swiat=new Swiat(Stałe.SZEROKOSC,Stałe.WYSOKOSC);
        swiat.GenerujOrganizmy();
        swiat.RysujSwiat();

    }
}  