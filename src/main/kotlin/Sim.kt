class Simulator {
    var events = mutableListOf<Event>()

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun executeSimulation(){
        events.sortBy { it.timeOfExecution }
        for(event in events){
            event.executeEvent()
        }
    }
}