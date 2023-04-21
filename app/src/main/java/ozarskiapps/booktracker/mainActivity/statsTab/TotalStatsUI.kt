package ozarskiapps.booktracker.mainActivity.statsTab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TotalStatsUI: StatsTabUI(){

    @Composable
    override fun GenerateLayout(){
        TotalStatsLayout()
    }
    @Composable
    fun TotalStatsLayout(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            TotalPagesText(value = 123456)

            StatRow(
                stat1Name = "books read",
                stat1Value = "83",
                stat2Name = "pages per day",
                stat2Value = "54"
            )
            StatRow(
                stat1Name = "pages per book",
                stat1Value = "395.9",
                stat2Name = "days per book",
                stat2Value = "10.7"
            )
            StatRow(
                stat1Name = "books per month",
                stat1Value = "2.8",
                stat2Name = "books per week",
                stat2Value = "0.7"
            )
            StatRow(
                stat1Name = "books per year",
                stat1Value = "2.8",
                stat2Name = "max books per year",
                stat2Value = "0.7"
            )
        }
    }


    @Preview
    @Composable
    private fun StatsUIPreview() {
        TotalStatsLayout()
    }
}
