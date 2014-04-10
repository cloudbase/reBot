import lejos.remote.ev3.*;
import lejos.utility.Delay;

class BarePlasticReset {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: BarePlasticReset <EV3 address> <A|B|C|D>");
	    System.exit(1);
        }

        String host = args[0];
        String port = args[1];

        RMIRegulatedMotor m = null;

        try {
            RemoteEV3 ev3 = new RemoteEV3(host);
            m = ev3.createRegulatedMotor(port, 'L');
            m.resetTachoCount();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (m != null) {
                try {
                    m.close();
                }
                catch(Exception ex) {
                    // ignore
                }

            }
        }
    }
}

