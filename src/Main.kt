import models.AeroponicBed
import models.CultivationModule
import models.HydroponicTower
import models.SoilGrowTable
import models.VerticalFarmManager
import java.time.LocalDate

fun main() {

//    val aeroBed = AeroponicBed("I-32", LocalDate.now())
//    println(aeroBed.idealConditions + " " + aeroBed.getMistingInterval() + " "+ aeroBed.getDaysSinceMaintenance())
//    println("Condições ideias: " + aeroBed.idealConditions)
//    println("Intervalo de névoa: " + aeroBed.getMistingInterval())
//    println("Ultima manutenção: " + aeroBed.getDaysSinceMaintenance())
//    aeroBed.setMistingInterval(10)
//    //aeroBed.harvest()
//    aeroBed.adjustEnvironment(22.0f, 2.1f)
//
//    val hydroTower = HydroponicTower("I-32", LocalDate.now(), 120)
//
//    hydroTower.idealConditions
//    hydroTower.getWaterPh()
//    hydroTower.getPlantCapacity()
//    hydroTower.getDaysSinceLastPHAdjustment()
//
//    hydroTower.adjustWaterPH(5.8f)
//    //hydroTower.harvest()
//    hydroTower.adjustEnvironment(22.0f, 2.1f)

    val aeroBed = AeroponicBed("A-01", LocalDate.now())
    val hydroTower = HydroponicTower("W-01", LocalDate.now(), 120)
    val soilTable = SoilGrowTable("S-01", LocalDate.now(), "Vermiculite")

    val modulosManager = VerticalFarmManager()
    modulosManager.addModule(aeroBed)
    modulosManager.addModule(hydroTower)
    modulosManager.addModule(soilTable)
    modulosManager.optimizeClimate(30f, 5f)

    modulosManager.performDailyTasks()

}