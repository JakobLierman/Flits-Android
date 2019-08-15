package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import be.jakoblierman.flits.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_auth)

        // Open welcome fragment
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_content_container, WelcomeFragment())
            .commit()

        supportActionBar!!.hide()
    }

}
