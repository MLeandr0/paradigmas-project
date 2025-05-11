package interfaces

interface ClimateControllable {
    val idealConditions: String
    fun adjustEnvironment(temperature: Float, humidity: Float)
}