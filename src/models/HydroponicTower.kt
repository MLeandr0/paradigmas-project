package models

import interfaces.ClimateControllable
import java.time.LocalDate

class HydroponicTower(
    id: String,
    plantingDate: LocalDate,
    val plantCapacity: Int
) : CultivationModule(id, plantingDate), ClimateControllable {

    var waterPH: Float = 6.0f
        private set

    // Interface implementation
    override fun adjustEnvironment(temp: Float, humidity: Float) {
        println("Adjusting hydroponics to ${"%.1f".format(temp)}°C and ${"%.1f".format(humidity)}% humidity")
    }

    override val idealConditions: String
        get() = "20-25°C, 65-75% humidity, pH 5.5-6.5"

    // Unique method
    fun adjustWaterPH(newPH: Float) {
        waterPH = newPH
        println("Water pH adjusted to: $newPH")
    }

    override fun harvest() {
        println("Harvesting leafy greens via conveyor belt")
    }
}