package com.prawesome;

import prawesome.SplashActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;


public class SignInActivity extends Activity
		implements
		ConnectionCallbacks,
		OnConnectionFailedListener,
		com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks,
		com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener {

	/* Request code used to invoke sign in user interactions. */
	private static final int RC_SIGN_IN = 0;

	/* Client used to interact with Google APIs. */
	public static GoogleApiClient mGoogleApiClient;

	/*
	 * A flag indicating that a PendingIntent is in progress and prevents us
	 * from starting further intents.
	 */
	private boolean mIntentInProgress;
	private boolean mSignInClicked;
	private ConnectionResult mConnectionResult;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		View but1 = (SignInButton) findViewById(R.id.sign_in_button);


			// Set a listener to connect the user when the G+ button is clicked.
			but1.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					mGoogleApiClient.connect();
				}
			});
		
			// Set a listener to connect the user when the G+ button is clicked.
//			but1.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View view) {
//					signIn();
//				}
//			});
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN).build();
	}

	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();

		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				startIntentSenderForResult(mConnectionResult.getResolution()
						.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			} catch (SendIntentException e) {
				// The intent was canceled before it was sent. Return to the
				// default
				// state and attempt to connect to get an updated
				// ConnectionResult.
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	public void onConnectionFailed(ConnectionResult result) {
		if (!mIntentInProgress && result.hasResolution()) {
			try {
				mIntentInProgress = true;

				startIntentSenderForResult(result.getResolution()
						.getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			} catch (SendIntentException e) {
				// The intent was canceled before it was sent. Return to the
				// default
				// state and attempt to connect to get an updated
				// ConnectionResult.
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
		if (!mIntentInProgress) {
			// Store the ConnectionResult so that we can use it later when the
			// user clicks
			// 'sign-in'.
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
		}

	}

//	public void onClick(View view) {
////		if (view.getId() == R.id.sign_in_button
////				&& !mGoogleApiClient.isConnecting()) {
////			Toast.makeText(this, "Log in pressed", Toast.LENGTH_SHORT).show();
////			mSignInClicked = true;
////			resolveSignInError();
////			mGoogleApiClient.connect();
////		}
////		if (view.getId() == R.id.sign_out_button) {
////		    if (mGoogleApiClient.isConnected()) {
////		    	Toast.makeText(this, "Log in pressed", Toast.LENGTH_SHORT).show();
////		      Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
////		      mGoogleApiClient.disconnect();
////		      
////		    }
////		  }
//		if (view.getId() == R.id.sign_in_button){
//			Toast.makeText(this, "Log in pressed", Toast.LENGTH_SHORT).show();
//		}
//		if (view.getId() == R.id.button1) {
//			Toast.makeText(this, "Log in pressed", Toast.LENGTH_SHORT).show();
//			mGoogleApiClient.connect();
//		  }
//
//	}

	public void onConnected(Bundle connectionHint) {
		mSignInClicked = false;
		String personName = null;
		String personGooglePlusProfile = null;
		if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
		    Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
		    personName = currentPerson.getDisplayName();
		    //String personPhoto = currentPerson.getImage();
		    personGooglePlusProfile = currentPerson.getUrl();
		  }
		Intent login = new Intent(this, SplashActivity.class);

		login.putExtra("personName", personName);
		login.putExtra("personGooglePlusProfile", personGooglePlusProfile);
		startActivity(login);
		finish();
		//Toast.makeText(this, "User data: " + personName + "," + personGooglePlusProfile, Toast.LENGTH_LONG).show();
	}

	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			mIntentInProgress = false;

			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		}
	}

	public void onConnectionSuspended(int cause) {
		mGoogleApiClient.connect();
	}

	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

}