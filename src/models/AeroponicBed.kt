package models

import interfaces.ClimateControllable
import java.time.LocalDate

class AeroponicBed(
    id: String,
    plantingDate: LocalDate
) : CultivationModule(id, plantingDate), ClimateControllable {

    var mistingInterval: Int = 5
    var nutrientBoost: Boolean = false

    override fun adjustEnvironment(temp: Float, humidity: Float) {
        println("Fine-tuning aeroponic mist for ${"%.1f".format(temp)}°C environment")
    }

    override val idealConditions: String
        get() = "18-22°C, 70-80% humidity, high oxygenation"

    fun activateNutrientBoost() {
        nutrientBoost = true
        println("Nutrient boost activated for root development")
    }

    override fun harvest() {
        println("Harvesting root vegetables with robotic arms")
    }
}