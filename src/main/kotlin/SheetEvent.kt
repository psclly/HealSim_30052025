class SheetEvent(
            val timeOfExecution: Float,
            val type: String,
            val name: String,
            val actor: String?,
            val targets: List<Player>,
            val autocrit: Boolean = false){
    init{
        println("Event created with time: $timeOfExecution, type: $type, name: $name")
    }

    constructor(): this(0f, "Default Type", "Default Name", "Default Actor", listOf(), false)

    fun executeEvent(){
        println("Executing event with time: $timeOfExecution, type: $type, name: $name")
    }
}