import lejos.remote.ev3.*;
import lejos.utility.Delay;

class BarePlasticAction {
    public static void main(String[] args) {

       if(args.length != 4) {
            System.out.println("usage: BarePlasticAction <EV3 address> <A|B|C|D> <degrees> <pause>");
            System.exit(1);
        }

        String host = args[0];
        String port = args[1];
        int degrees = Integer.parseInt(args[2]);
        int pause = Integer.parseInt(args[3]); 

        RMIRegulatedMotor m = null;

        try {
            RemoteEV3 ev3 = new RemoteEV3(host);

            m = ev3.createRegulatedMotor(port, 'L');

            m.setAcceleration(6000);

            float speed = m.getMaxSpeed();
            m.setSpeed((int)speed);

            m.rotateTo(degrees);

            if (pause >= 0) {
                Delay.msDelay(pause);
                m.rotateTo(0);
            }
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

