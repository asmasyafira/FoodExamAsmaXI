package com.example.foodexamasmaxi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexamasmaxi.R
import com.example.foodexamasmaxi.fragment.DessertFragment
import com.example.foodexamasmaxi.fragment.ProfileFragment
import com.example.foodexamasmaxi.fragment.SeafoodFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_seafood -> {
                    transactationSeafoodFragment(savedInstanceState)
                }
                R.id.navigation_dessert -> {
                    transactationDessertFragment(savedInstanceState)
                }
                R.id.navigation_profile -> {
                    transactationProfileFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation_main.selectedItemId = R.id.navigation_seafood
    }

    private fun transactationSeafoodFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fm_main, SeafoodFragment(), SeafoodFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun transactationDessertFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fm_main, DessertFragment(), DessertFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun transactationProfileFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fm_main, ProfileFragment(), ProfileFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.option_logout) {
            FirebaseAuth.getInstance().signOut()
            val log = Intent(this, LogInActivity::class.java)
            Toast.makeText(this, "User Log Out", Toast.LENGTH_SHORT).show()
            startActivity(log)
        }

        return super.onOptionsItemSelected(item)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

}
