/**
 * Created by jamsl on 2017-04-18.
 */
public class Simulation implements Runnable {
    private Thread t;
    private Canvas canvas;
    private boolean isRunning=true;
    public Simulation(Canvas canvas){
        this.canvas = canvas;
    }
    @Override
    public void run() {
        while(isRunning) {
            try {
                canvas.nextGen();
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
    public void stop(){
        isRunning = false;
    }
}