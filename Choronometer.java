				
 import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Choronometer {
    private byte centiseconds = 0;
    private byte seconds = 00;
    private short minutes = 3;
    private DecimalFormat timeFormatter;
    private Timer timer;
    

    public Choronometer() {
    	
    }
    public void startChronometer() {
    			timeFormatter = new DecimalFormat("00"); 
    	    	timer = new Timer(10, new ActionListener() {
    	           
    	            public void actionPerformed(ActionEvent e) {
    	            	timer.start();
    	                if (centiseconds > 0) {
    	                    centiseconds--;
    	                } else {
    	                    if (seconds == 0 && minutes == 0) {
    	                        timer.stop();
    	                    } else if (seconds > 0) {
    	                        seconds--;
    	                        centiseconds = 99;
    	                    } else if (minutes > 0) {
    	                        minutes--;
    	                        seconds = 59;
    	                        centiseconds = 99;
    	                    }
    	                }
    	             
    	                timeFormatter.format(minutes) ;
    	                timeFormatter.format(seconds); 
    	                     timeFormatter.format(centiseconds);
    	             
    	            } 
    	        });   
    	    	}
			public byte getCentiseconds() {
				return centiseconds;
			}
			public void setCentiseconds(byte centiseconds) {
				this.centiseconds = centiseconds;
			}
			public byte getSeconds() {
				return seconds;
			}
			public void setSeconds(byte seconds) {
				this.seconds = seconds;
			}
			public short getMinutes() {
				return minutes;
			}
			public void setMinutes(short minutes) {
				this.minutes = minutes;
			}
			
			public DecimalFormat getTimeFormatter() {
				return timeFormatter;
			}
			public void setTimeFormatter(DecimalFormat timeFormatter) {
				this.timeFormatter = timeFormatter;
			}
			public Timer getTimer() {
				return timer;
			}
			public void setTimer(Timer timer) {
				this.timer = timer;
			}

}