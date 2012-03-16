package id.sabril.testingPA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	public static int WAKTU = 5000;
	public void onCreate(Bundle saveInstanceBundle) {
		super.onCreate(saveInstanceBundle);
		setContentView(R.layout.splashscreen);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this,TestingPAActivity.class);
				SplashScreen.this.startActivity(intent);
				SplashScreen.this.finish();
				
			}
		}, WAKTU); 
	}
}
