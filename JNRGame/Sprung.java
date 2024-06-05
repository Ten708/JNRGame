package JNRGame;



public class Sprung extends Thread {

    static boolean fertig = true;
    static boolean hochpunkt = false;

    int sprunghoehe = 86;
    static int ursprung_y = 235;
    static int sprungpos = ursprung_y;



    public Sprung() {

    }



    public void run() {

        fertig = false;

        int Verzoegerung;

        while (fertig == false) {
            
            Sprung();

            Verzoegerung = 3;

            try{

                Thread.sleep(Verzoegerung);
            }
            catch(Exception e) {

            }
        }

        hochpunkt = false;
    }



    public void Sprung() {

        if (hochpunkt == false) {

            sprungpos--;
        }

        if (sprungpos == (ursprung_y-sprunghoehe)) {

            hochpunkt = true;
        }

        if (hochpunkt == true && sprungpos <= ursprung_y) {

            sprungpos ++;
            if (sprungpos == ursprung_y) {

                fertig = true;
            }
        }
    }
}
