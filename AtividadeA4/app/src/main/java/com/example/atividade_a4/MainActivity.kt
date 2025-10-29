package com.example.atividade_a4

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.atividade_a4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pega o NavController do NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHostFragment.navController

        // define quais destinos são "top-level" (não mostram botão Up):
        // home, alunos, perfil (os 3 do bottom nav)
        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.alunosFragment,
                R.id.perfilFragment
            )
        )

        // conecta a toolbar custom (topAppBar) ao sistema de navegação
        setSupportActionBar(binding.topAppBar)
        setupActionBarWithNavController(navController, appBarConfig)

        // conecta bottom nav ao navController
        binding.bottomNav.setupWithNavController(navController)

        // Listener pra esconder o bottomNav em telas que não são top-level
        navController.addOnDestinationChangedListener { _, destination, _ ->

            // Se eu estiver em um fragment que faz parte do bottom nav,
            // o bottomNav fica visível.
            // Caso contrário (ex: DetalheAlunoFragment, LoginFragment, WelcomeFragment),
            // esconde.
            val mostrarBottom = when (destination.id) {
                R.id.homeFragment,
                R.id.alunosFragment,
                R.id.perfilFragment -> true
                else -> false
            }

            binding.bottomNav.visibility = if (mostrarBottom) View.VISIBLE else View.GONE
        }
    }

    // Faz o botão Up (←) da toolbar funcionar nos destinos que não são top-level
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}