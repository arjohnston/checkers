package utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class GameTimer {
	private static Timer timer;
	private static long timeElapsedInSeconds = 0;
	
	public static void start(Consumer<Long> callback) {
		if (timer != null) cleanUp();

		timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
            public void run() {
            	timeElapsedInSeconds += 1;
            	callback.accept(timeElapsedInSeconds);
            }
          }, 0, 1000);
	}
	
	public static void reset() {
		timeElapsedInSeconds = 0;
		if (timer != null) cleanUp();
	}
	
	public static void pause() {
		timer.cancel();
	}
	
	public static void cleanUp() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
		}
	}
	
	public static long getTimeElapsedInSeconds () {
		return timeElapsedInSeconds;
	}
}
