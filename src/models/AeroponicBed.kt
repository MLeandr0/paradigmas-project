package models

import interfaces.ClimateControllable
import java.time.LocalDate

class AeroponicBed(
    private val id: String,
    private val plantingDate: LocalDate
) : CultivationModule(id, plantingDate), ClimateControllable {

    private var _mistingInterval: Int = 5
    private var _nutrientBoost: Boolean = false
    private var _lastMaintenance: LocalDate = plantingDate

    override val idealConditions: String = "18-22°C, 70-80% humidity, high oxygenation"

    fun getMistingInterval(): Int = _mistingInterval

    fun getNutrientBoost(): Boolean = _nutrientBoost

    fun getIdealConditions(): String = idealConditions

    fun getDaysSinceMaintenance(): Long {
        return LocalDate.now().toEpochDay() - _lastMaintenance.toEpochDay()
    }

    fun setMistingInterval(minutes: Int) {
        require(minutes in 1..15) { "Intervalo deve ser entre 1-15 minutos" }
        _mistingInterval = minutes
    }

    fun setNutrientBoost(active: Boolean) {
        if (active) {
            require(!_nutrientBoost) { "Boost já está ativo" }
        }
        _nutrientBoost = active
    }

    fun activateNutrientBoost(hours: Int) {
        setNutrientBoost(true)
        println("Boost ativado por $hours horas")
    }

    private fun autoAdjustMisting(temp: Float) {
        _mistingInterval = when {
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
        _lastMaintenance = LocalDate.now()
    }

    override fun adjustEnvironment(temp: Float, humidity: Float) {
        println("Ajustando névoa para ${"%.1f".format(temp)}°C")
        autoAdjustMisting(temp)
    }
}