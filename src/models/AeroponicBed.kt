package models

import interfaces.ClimateControllable
import java.time.LocalDate

class AeroponicBed(
    val id: String,
    val plantingDate: LocalDate
) : CultivationModule(id, plantingDate), ClimateControllable {

    private var mistingInterval: Int = 5
    private var lastMaintenance: LocalDate = plantingDate

    override val idealConditions: String
        get() = "18-22°C, 70-80% humidity, high oxygenation"

    fun getMistingInterval(): Int = mistingInterval

    fun getDaysSinceMaintenance(): Long {
        return LocalDate.now().toEpochDay() - lastMaintenance.toEpochDay()
    }

    fun setMistingInterval(minutes: Int) {
        require(minutes in 1..15) { "Interval must be between 1-15 minutes" }
        mistingInterval = minutes
    }

    private fun autoAdjustMisting(temp: Float) {
        mistingInterval = when {
            temp > 28f -> 2
            temp > 25f -> 3
            else -> 5
        }
    }

    override fun harvest() {
        require(growthStage >= 5) {
            "Plants not ready for harvest (stage $growthStage)"
        }
        println("Harvesting root vegetables with robotic arms")
        lastMaintenance = LocalDate.now()
    }

    override fun adjustEnvironment(temp: Float, humidity: Float) {
        println("Adjusting mist for ${"%.1f".format(temp)}°C environment")
        autoAdjustMisting(temp)
    }
}