package models

import java.time.LocalDate

abstract class CultivationModule(
    val moduleId: String,
    val plantingDate: LocalDate
) {
    var growthStage: Int = 0
        protected set

    fun incrementGrowthStage() {
        growthStage++
        println("Growth stage advanced to: $growthStage")
    }

    abstract fun harvest()
}