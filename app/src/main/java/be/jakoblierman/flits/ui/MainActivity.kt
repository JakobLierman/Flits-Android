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
import com.google.android.material.navigation.NavigationView
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity :
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    ListFragment.OnListFragmentInteractionListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
        if (savedInstanceState == null) {
            // Check the first item in the navigation menu
            navView.menu.findItem(R.id.nav_speedCamera).isChecked = true
            navView.menu.performIdentifierAction(R.id.nav_speedCamera, 0)
        }

        // The detail container view will be present only in the large-screen layouts (res/values-w900dp).
        // If this view is present, then the activity should be in two-pane mode.
        if (main_detail_container != null)
            twoPane = true

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
            R.id.action_new -> {
                when (nav_view.checkedItem!!.itemId) {
                    R.id.nav_speedCamera -> openDetailFragment(AddSpeedCameraFragment.newInstance())
                    R.id.nav_avgSpeedCheck -> openDetailFragment(AddAvgSpeedCheckFragment.newInstance())
                    R.id.nav_policeCheck -> openDetailFragment(AddPoliceCheckFragment.newInstance())
                }
                return true
            }
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks.
        val newFragment: Fragment
        if (item.itemId != R.id.nav_logout) {
            // Change ListFragment according to item type
            newFragment = ListFragment.newInstance(item.itemId)
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content_container, newFragment)
                .commit()
        } else {
            TODO("logout not implemented")
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onListFragmentInteraction(itemKindId: Int, itemId: String?) {
        when (itemKindId) {
            R.id.nav_speedCamera -> openDetailFragment(SpeedCameraFragment.newInstance(itemId))
            R.id.nav_avgSpeedCheck -> openDetailFragment(AvgSpeedCheckFragment.newInstance(itemId))
            R.id.nav_policeCheck -> openDetailFragment(PoliceCheckFragment.newInstance(itemId))
        }
    }

    private fun openDetailFragment(newFragment: Fragment) {
        if (twoPane) {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_detail_container, newFragment)
                .commit()
        } else {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content_container, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }

}
