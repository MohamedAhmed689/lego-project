package threads;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Robot;

public class ReadData implements Runnable {
    URL url = null;
    HttpURLConnection conn = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    String s = null;

    @Override
    public void run() {
        while (Robot.getRun() == 1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                url = new URL("http://192.168.63.45:8080/legoproject/rest/lego/getdirspeed");
                conn = (HttpURLConnection) url.openConnection();
                InputStream is = null;
                try {
                    is = conn.getInputStream();
                } catch (Exception e) {
                    System.out.println("Exception conn.getInputStream()");
                    e.printStackTrace();
                    System.out.println("Cannot get InputStream!");
                }
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                System.out.println("ITS WORKIIIIIIIIIIIIIIIIIIIIIIIIING");
               
                while ((s = br.readLine()) != null) {
                    String[] ds = s.split("#"); 
                    Robot.setDirection(ds[0]);
                    Robot.setSpeed(ds[1]);
                    Robot.setRun(ds[2]);
                    System.out.println("DATA: DIR="+ds[0] +" SPEED="+ds[1] + " RUN=" +ds[2]);
                }
                br.close();
                isr.close();
                is.close();
                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some problem!");
            }
        }
    }

}