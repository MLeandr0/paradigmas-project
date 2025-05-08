package models

import java.time.LocalDate

class SoilGrowTable(
    id: String,
    plantingDate: LocalDate,
    val soilType: String
) : CultivationModule(id, plantingDate) {

    var moistureLevel: Float = 40.0f
        private set

    fun waterPlants(amount: Float) {
        moistureLevel += amount
        println("Moisture level now at ${"%.1f".format(moistureLevel)}%")
    }

    override fun harvest() {
        println("Manual harvesting of fruiting plants")
    }
}