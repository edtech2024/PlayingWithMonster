package com.example.playingwithmonster

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.playingwithmonster.presentation.fragment.DetailFragment
import com.example.playingwithmonster.presentation.fragment.HistoryFragment
import com.example.playingwithmonster.presentation.fragment.MainFragment
import com.example.playingwithmonster.presentation.fragment.GameFragment

class MainActivity : AppCompatActivity(),
    MainFragment.OnButtonClickListener,
    DetailFragment.OnItemCloseListener,
    HistoryFragment.OnItemOpenListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // using toolbar as ActionBar

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        switchHamburger()

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            transactionToMainFragment()
        }

    }

    private fun switchHamburger(){
        toggle.isDrawerIndicatorEnabled = true // Чтобы получить значок гамбургера, установим индикатор
    }

    private fun switchBack(){
        toggle.isDrawerIndicatorEnabled = false

        toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        toggle.setToolbarNavigationClickListener() {

            onBackPressed()

            switchHamburger()
        }
    }

    override fun onOpeningHistory() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentHistory: HistoryFragment = HistoryFragment.newInstance()
        fragmentTransaction.replace(R.id.container, fragmentHistory) // контейнер в активити
        fragmentTransaction.commit()

        toolbar.setTitle(getString(R.string.history))
    }

    override fun onOpeningGame() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentQuiz: GameFragment = GameFragment.newInstance(
            0,
            null,
            null,
            null,
            null,
            null,
            null
        )
        fragmentTransaction.replace(R.id.container, fragmentQuiz) // контейнер в активити
        fragmentTransaction.commit()

        toolbar.setTitle(getString(R.string.game_with_monster))
    }

    override fun onOpenItem() {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentDetail: DetailFragment = DetailFragment.newInstance(
            0,
            null,
            null,
            null,
            null,
            null,
            null
        )
        fragmentTransaction.replace(R.id.container, fragmentDetail) // контейнер в активити
        fragmentTransaction.commit()

        toolbar.setTitle(getString(R.string.results))

        switchBack()
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemCreateUpdateListener` interface methods
    override fun onCloseItem(){
        transactionToMainFragment()
    }

    private fun transactionToMainFragment(){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentMain: MainFragment = MainFragment.newInstance()
        fragmentTransaction.replace(R.id.container, fragmentMain) // контейнер в активити
        fragmentTransaction.commit()

        toolbar.setTitle(getString(R.string.game_with_monster))

        switchHamburger()
    }

}