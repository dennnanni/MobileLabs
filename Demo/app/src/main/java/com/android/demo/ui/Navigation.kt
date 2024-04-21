package com.android.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.demo.ui.screens.AllTransactionsPage
import com.android.demo.ui.screens.CreateTransaction
import com.android.demo.ui.screens.HomePage
import com.android.demo.ui.screens.Settings
import com.android.demo.ui.screens.TransactionDetails


sealed class DemoRoute (
    val route: String,
    val title: String,
    val arguments : List<NamedNavArgument> = emptyList()
) {
    data object HomePage: DemoRoute("homepage", "Homepage")
    data object AllTransactionPage: DemoRoute("transactions", "All transactions")
    data object TransactionDetail: DemoRoute(
        "transactions/{transactionId}",
        "Details",
        listOf(navArgument("transactionId") { type = NavType.StringType })
    ) {
        fun buildRoute(transactionId: String) = "transactions/${transactionId}"
    }
    data object CreateTransaction: DemoRoute("create", "Create transaction")
    data object  Settings: DemoRoute("settings", "Settings")

    companion object {
        val routes = setOf(HomePage, AllTransactionPage, TransactionDetail, CreateTransaction,
            Settings)
    }
}

@Composable
fun DemoNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = DemoRoute.HomePage.route
    ) {
        with(DemoRoute.HomePage) {
            composable(route) {
                HomePage(navController)
            }
        }
        with(DemoRoute.AllTransactionPage) {
            composable(route) {
                AllTransactionsPage(navController)
            }
        }
        with(DemoRoute.TransactionDetail) {
            composable(route) { backstackEntry ->
                TransactionDetails(backstackEntry.arguments?.getString("transactionId") ?: "")
            }
        }
        with(DemoRoute.CreateTransaction) {
            composable(route) {
                CreateTransaction(navController)
            }
        }
        with(DemoRoute.Settings) {
            composable(route) {
                Settings()
            }
        }
    }
}
