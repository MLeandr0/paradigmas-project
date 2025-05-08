import models.AeroponicBed
import models.HydroponicTower
import models.SoilGrowTable
import models.VerticalFarmManager
import java.time.LocalDate

fun main() {
    val farm = VerticalFarmManager().apply {
        addModule(HydroponicTower("HT-1", LocalDate.now(), 120).apply {
            adjustWaterPH(5.8f)
        })

        addModule(AeroponicBed("AB-3", LocalDate.now().minusDays(7)).apply {
            activateNutrientBoost()
        })

        addModule(SoilGrowTable("ST-5", LocalDate.now(), "Peat Moss"))
    }

    farm.optimizeClimate(21.5f, 70f)
    farm.performDailyTasks()

    // Alternative with explicit calls
    //    val explicitFarm = VerticalFarmManager().also { manager ->
    //        manager.addModule(HydroponicTower("HT-2", LocalDate.now(), 90).also {
    //            it.adjustWaterPH(6.0f)
    //        })
    //        manager.addModule(AeroponicBed("AB-4", LocalDate.now()))
    //    }

    val hydro = HydroponicTower("T", LocalDate.now(), 120)
    hydro.adjustWaterPH(5.8f)

    val aero = AeroponicBed("T", LocalDate.now().minusDays((7)))
    aero.activateNutrientBoost()

    val soil = SoilGrowTable("ST-5", LocalDate.now(), "Tits")

    val farm2 = VerticalFarmManager()
    farm2.addModule(hydro)
    farm2.addModule(aero)
    farm2.addModule(soil)
}