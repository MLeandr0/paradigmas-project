package interfaces

interface ClimateControllable {
    val idealConditions: String  // Property in interface
    fun adjustEnvironment(temperature: Float, humidity: Float)
}