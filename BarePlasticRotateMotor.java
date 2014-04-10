import lejos.remote.ev3.*;
import lejos.utility.Delay;

class BarePlasticRotateMotor {
    public static void main(String[] args) {

       if(args.length < 3 || args.length > 5) {
            System.out.println("usage: BarePlasticAdjustMotor <EV3 address> <A|B|C|D> <degrees> [speed] [acceleration]");
            System.exit(1);
        }

        String host = args[0];
        String port = args[1];
        int degrees = Integer.parseInt(args[2]);

        float speed = 0;
        if (args.length > 3) {
            speed = Float.parseFloat(args[3]);
        }

        int acceleration = 6000;
        if (args.length > 4) {
            acceleration = Integer.parseInt(args[4]);
        }

        RMIRegulatedMotor m = null;

        try {
            RemoteEV3 ev3 = new RemoteEV3(host);
            m = ev3.createRegulatedMotor(port, 'L');
            m.setAcceleration(acceleration);

            if (speed == 0) {
                speed = m.getMaxSpeed();
            }
            m.setSpeed((int)speed);

            m.rotate(degrees);
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

