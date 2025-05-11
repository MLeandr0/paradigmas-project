package models

import interfaces.ClimateControllable
import java.time.LocalDate
import kotlin.math.max
import kotlin.math.min

class HydroponicTower(
    id: String,
    plantingDate: LocalDate,
    plantCapacity: Int
) : CultivationModule(id, plantingDate), ClimateControllable {

    private var _waterPH: Float = 6.0f
    private var _plantCapacity: Int = plantCapacity
    private var _lastPHAdjustment: LocalDate? = null

    val waterPH: Float
        get() = _waterPH

    val plantCapacity: Int
        get() = _plantCapacity

    val daysSinceLastPHAdjustment: Long?
        get() = _lastPHAdjustment?.let {
            LocalDate.now().toEpochDay() - it.toEpochDay()
        }

    override val idealConditions: String
        get() = "20-25°C, 65-75% humidity, pH 5.5-6.5"

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
        _waterPH = newPH
        _lastPHAdjustment = LocalDate.now()
        println("Water pH successfully adjusted to: ${"%.1f".format(newPH)}")
    }

    override fun harvest() {
        require(growthStage >= 4) {
            "Plants not ready for harvest (current stage: $growthStage)"
        }
        println("Harvesting $plantCapacity leafy greens via automated conveyor")
    }

    private fun autoAdjustPHBasedOnTemp(temp: Float) {
        _waterPH = when {
            temp > 24f -> (_waterPH - 0.1f).coerceAtLeast(5.5f)
            temp < 21f -> (_waterPH + 0.1f).coerceAtMost(6.5f)
            else -> _waterPH
        }
    }
}