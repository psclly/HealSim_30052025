class Simulator {
    var events = mutableListOf<SheetEvent>()

    fun addEvent(event: SheetEvent) {
        events.add(event)
    }



    fun executeSimulation(){
        events.sortBy { it.timeOfExecution }
        for(event in events){
            event.executeEvent()
        }
    }
}