package models

import interfaces.ClimateControllable
import java.time.LocalDate

class HydroponicTower(
    id: String,
    plantingDate: LocalDate,
    plantCapacity: Int
) : CultivationModule(id, plantingDate), ClimateControllable {

    private var waterPH: Float = 6.0f
    private var plantCapacity: Int = plantCapacity
    private var lastPHAdjustment: LocalDate? = null

    override val idealConditions: String
        get() = "20-25°C, 65-75% humidity, pH 5.5-6.5"

    fun getWaterPh(): Float {
        return waterPH
    }

    fun getPlantCapacity(): Int {
        return plantCapacity
    }

    fun getDaysSinceLastPHAdjustment(): Long? {
        return lastPHAdjustment?.let {
            LocalDate.now().toEpochDay() - it.toEpochDay()
        }
    }

    override fun adjustEnvironment(temp: Float, humidity: Float) {
        val adjustedTemp = temp.coerceIn(20f, 25f)
        val adjustedHumidity = humidity.coerceIn(65f, 75f)

        println("Hydroponic environment adjusted to: " +
                "${"%.1f".format(adjustedTemp)}°C and " +
                "${"%.1f".format(adjustedHumidity)}% humidity")

        autoAdjustPHBasedOnTemp(adjustedTemp)
    }

    fun adjustWaterPH(newPH: Float) {
        require(newPH in 5.5f..6.5f) {
            "pH must be between 5.5 and 6.5 (provided: $newPH)"
        }
        waterPH = newPH
        lastPHAdjustment = LocalDate.now()
        println("Water pH successfully adjusted to: ${"%.1f".format(newPH)}")
    }

    override fun harvest() {
        require(growthStage >= 4) {
            "Plants not ready for harvest (current stage: $growthStage)"
        }
        println("Harvesting $plantCapacity leafy greens via automated conveyor")
    }

    private fun autoAdjustPHBasedOnTemp(temp: Float) {
        waterPH = when {
            temp > 24f -> (waterPH - 0.1f).coerceAtLeast(5.5f)
            temp < 21f -> (waterPH + 0.1f).coerceAtMost(6.5f)
            else -> waterPH
        }
    }
}