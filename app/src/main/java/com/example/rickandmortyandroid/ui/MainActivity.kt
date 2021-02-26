package com.example.rickandmortyandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyandroid.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navView: NavigationView
    private lateinit var toolbarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navBottomView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Não funcionando corretamente, toolbar some
//        val layout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navView = findViewById(R.id.nav_view)
        navBottomView = findViewById(R.id.nav_bottom_view)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbarLayout = findViewById<AppBarLayout>(R.id.toolbar_layout)
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.homeFragment,
                        R.id.episodeFragment,
                        R.id.locationFragment,
                        R.id.splashFragment
                ), drawerLayout
        )
        // aparecer o icone de estrela
        setSupportActionBar(toolbar)
        // aparecer o titulo de acordo com a janela
        toolbar.setupWithNavController(navController, appBarConfiguration)
        //aparecer o bottom menu
        navBottomView.setupWithNavController(navController)
        //aparecer o drawer
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                Log.i("teste", "clicou no favoritos")
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        navBottomView.visibility = visibility
    }

    fun setToolbarVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        toolbarLayout.visibility = visibility

    }

    fun resizeFragment() {
        // Resize navhost de acordo com os outros elementos
        val view: View? = navHostFragment.view?.parent as ViewGroup

        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        )

        if (toolbarLayout.visibility == View.VISIBLE) {
            params.addRule(RelativeLayout.BELOW, R.id.toolbar_layout)
        }

        if (navBottomView.visibility == View.VISIBLE) {
            params.addRule(RelativeLayout.ABOVE, R.id.nav_bottom_view)
        }

        if (view != null) {
            view.layoutParams = params
        }
    }

}