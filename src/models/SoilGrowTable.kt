package models

import java.time.LocalDate

class SoilGrowTable(
    id: String,
    plantingDate: LocalDate,
    soilType: String
) : CultivationModule(id, plantingDate) {

    private var moistureLevel: Float = 40.0f
    private var soilType: String = validateSoilType(soilType)
    private var lastWatering: LocalDate = plantingDate
    private var waterConsumption: Float = 0f

    val daysSinceLastWatering: Long
        get() = LocalDate.now().toEpochDay() - lastWatering.toEpochDay()

    fun getWaterConsumption(): Float {
        return waterConsumption
    }

    fun getMoistureLevel(): Float {
        return moistureLevel
    }

    fun getSoilType(): String {
        return soilType
    }

    fun waterPlants(amount: Float) {
        require(amount > 0) { "Water amount must be positive" }

        moistureLevel = (moistureLevel + amount).coerceAtMost(100f)
        lastWatering = LocalDate.now()
        waterConsumption += amount

        println("""
            Watered with ${"%.1f".format(amount)}L. 
            Current moisture: ${"%.1f".format(moistureLevel)}%
            Total consumed: ${"%.1f".format(waterConsumption)}L
        """.trimIndent())
    }

    fun changeSoilType(newType: String) {
        soilType = validateSoilType(newType)
        println("Soil changed to: $newType")
    }

    override fun harvest() {
        require(moistureLevel in 30f..70f) {
            "Inadequate moisture for harvest (${"%.1f".format(moistureLevel)}%)"
        }
        require(growthStage >= 6) {
            "Plants not ready (stage $growthStage)"
        }

        println("Harvesting fruiting plants")
    }

    private fun validateSoilType(type: String): String {
        val validTypes = setOf("Loam", "Clay", "Sandy", "Peat Moss", "Vermiculite")
        require(type in validTypes) {
            "Invalid soil type. Choose from: ${validTypes.joinToString()}"
        }
        return type
    }
}