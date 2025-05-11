package models

import java.time.LocalDate
import kotlin.math.max
import kotlin.math.min

class SoilGrowTable(
    id: String,
    plantingDate: LocalDate,
    soilType: String
) : CultivationModule(id, plantingDate) {

    private var _moistureLevel: Float = 40.0f
    private var _soilType: String = validateSoilType(soilType)
    private var _lastWatering: LocalDate = plantingDate
    private var _waterConsumption: Float = 0f

    val moistureLevel: Float
        get() = _moistureLevel

    val soilType: String
        get() = _soilType

    val daysSinceLastWatering: Long
        get() = LocalDate.now().toEpochDay() - _lastWatering.toEpochDay()

    val waterConsumption: Float
        get() = _waterConsumption

    fun waterPlants(amount: Float) {
        require(amount > 0) { "Water amount must be positive" }

        _moistureLevel = (_moistureLevel + amount).coerceAtMost(100f)
        _lastWatering = LocalDate.now()
        _waterConsumption += amount

        println("""
            Watered with ${"%.1f".format(amount)}L. 
            Current moisture: ${"%.1f".format(_moistureLevel)}%
            Total consumed: ${"%.1f".format(_waterConsumption)}L
        """.trimIndent())
    }

    fun changeSoilType(newType: String) {
        _soilType = validateSoilType(newType)
        println("Soil changed to: $newType")
    }

    override fun harvest() {
        require(_moistureLevel in 30f..70f) {
            "Inadequate moisture for harvest (${"%.1f".format(_moistureLevel)}%)"
        }
        require(growthStage >= 6) {
            "Plants not ready (stage $growthStage)"
        }

        println("Harvesting fruiting plants with care")
    }

    private fun validateSoilType(type: String): String {
        val validTypes = setOf("Loam", "Clay", "Sandy", "Peat Moss", "Vermiculite")
        require(type in validTypes) {
            "Invalid soil type. Choose from: ${validTypes.joinToString()}"
        }
        return type
    }
}