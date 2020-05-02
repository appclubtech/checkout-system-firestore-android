package tech.appclub.arslan.checkoutsystem

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import tech.appclub.arslan.checkoutsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appbarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val LOG_TAG = "APP_LOG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set toolbar
        setSupportActionBar(this.binding.toolbar)

        // Getting Navigation Host and Controller
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController

        // Setting App Bar Configuration
        appbarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_destination
            )
        )

        // Setting up Action bar
        setupActionBarWithNavController(navController, appbarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appbarConfiguration)
    }
}
