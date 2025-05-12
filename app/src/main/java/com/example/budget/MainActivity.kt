package com.example.budget

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.budget.Fragments.AddExpenseFragment
import com.example.budget.Fragments.BudgetFragment
import com.example.budget.Fragments.ChartFragment
import com.example.budget.Fragments.HomeFragment
import com.example.budget.Fragments.Options
import com.example.budget.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var bottomNavigationView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        bottomNavigationView = viewBinding.bottomNavigation

        bottomNavigationView.setOnItemSelectedListener {
            menuItem -> when(menuItem.itemId){
                R.id.homeBtn ->{
                    replaceFragments(HomeFragment())
                    true
                }
            R.id.chartBtn ->{
                replaceFragments(ChartFragment())
                true
            }
            R.id.addBtn ->{
                replaceFragments(AddExpenseFragment())
                true
            }
            R.id.budgetBtn ->{
                replaceFragments(BudgetFragment())
                true
            }
            R.id.optionBtn ->{
                replaceFragments(Options())
                true
            }
            else -> false
            }

        }
        replaceFragments(HomeFragment())

    }

    private fun replaceFragments(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameContainer,fragment).commit()



    }
}