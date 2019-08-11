package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import be.jakoblierman.flits.R
import be.jakoblierman.flits.dummy.DummyContent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ListItemFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        
        // Set logger
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val newFragment: Fragment
        when (item.itemId) {
            R.id.nav_speedCamera -> {
                newFragment = ListItemFragment.newInstance(1)
                this.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detail_container, newFragment)
                    .commit()
            }
            R.id.nav_avgSpeedCheck -> {
                newFragment = ListItemFragment.newInstance(1)
                this.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detail_container, newFragment)
                    .commit()
            }
            R.id.nav_policeCheck -> {
                newFragment = ListItemFragment.newInstance(1)
                this.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detail_container, newFragment)
                    .commit()
            }
            R.id.nav_logout -> {
                // TODO - Implement logout
                // TESTING FRAGMENT
                newFragment = SpeedCameraFragment.newInstance()
                this.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detail_container, newFragment)
                    .commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }
}