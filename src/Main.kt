import models.AeroponicBed
import models.CultivationModule
import models.HydroponicTower
import models.SoilGrowTable
import models.VerticalFarmManager
import java.time.LocalDate

fun main() {
    val ar = AeroponicBed("A-01", LocalDate.now())
    val agua = HydroponicTower("W-01", LocalDate.now(), 120)
    val soil = SoilGrowTable("S-01", LocalDate.now(), "Vermiculite")

    val manager = VerticalFarmManager()
    manager.addModule(ar)
    manager.addModule(agua)
    manager.addModule(soil)

    manager.optimizeClimate(22f, 5f)
    manager.performDailyTasks()
}