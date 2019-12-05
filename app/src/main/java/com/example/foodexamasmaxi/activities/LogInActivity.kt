package com.example.foodexamasmaxi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.foodexamasmaxi.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    val RC_SIGN_IN: Int = 123
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var credential:AuthCredential


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        firebaseAuth = FirebaseAuth.getInstance()

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        google_login_user.setOnClickListener {
            signIn()
        }
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            finish()
            startActivity(MainActivity.getLaunchIntent(this))
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Log.d("success", "signInWithCredential:success")
                        Toast.makeText(this, "User Signed In", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Log.w("failure", "signInWithCredential:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }


    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

}
