package models

import interfaces.ClimateControllable

class VerticalFarmManager {
    private val modules = mutableListOf<CultivationModule>()
    private val climateSystems = mutableListOf<ClimateControllable>()

    fun addModule(module: CultivationModule) {
        modules.add(module)
        if (module is ClimateControllable) {
            climateSystems.add(module)
        }
    }

    fun optimizeClimate(targetTemp: Float, targetHumidity: Float) {
        println("Optimizing climate for ${climateSystems.size} systems")
        climateSystems.forEach {
            it.adjustEnvironment(targetTemp, targetHumidity)
        }
    }

    fun performDailyTasks() {
        modules.forEach { module ->
            module.incrementGrowthStage()
            if (module is SoilGrowTable) {
                module.waterPlants(15f)
            }
        }
    }
}