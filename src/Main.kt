import models.AeroponicBed
import models.HydroponicTower
import models.SoilGrowTable
import models.VerticalFarmManager
import java.time.LocalDate

fun main() {

//    val hydro = HydroponicTower("T", LocalDate.now(), 120)
//    hydro.adjustWaterPH(5.8f)
//
//    val aero = AeroponicBed("T", LocalDate.now().minusDays((7)))
//
//    val soil = SoilGrowTable("ST-5", LocalDate.now(), "Tits")
//
//    val farm2 = VerticalFarmManager()
//    farm2.addModule(hydro)
//    farm2.addModule(aero)
//    farm2.addModule(soil)
//
//    farm2.optimizeClimate(21.5f, 70f)
//    farm2.performDailyTasks()

    val aeroBed = AeroponicBed("I-32", LocalDate.now())
    println(aeroBed.idealConditions + " " + aeroBed.getMistingInterval() + " "+ aeroBed.getDaysSinceMaintenance())
    println("Condições ideias: " + aeroBed.idealConditions)
    println("Intervalo de névoa: " + aeroBed.getMistingInterval())
    println("Ultima manutenção: " + aeroBed.getDaysSinceMaintenance())
    aeroBed.setMistingInterval(25)
    aeroBed.harvest()
    aeroBed.adjustEnvironment(22.0f, 2.1f)

    val hydroTower = HydroponicTower("I-32", LocalDate.now(), 120)
    hydroTower.
}