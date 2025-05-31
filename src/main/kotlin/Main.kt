fun main() {

    // Create a simulator and a party from csv
    // TODO: the mode passed in initializeParty should be in a config file
    val simulator = Simulator()
    val playerList = initializeParty("random")

    // Introduce all players!
    for(player in playerList){
        player.introduceYourself()
    }

    // Add events to the simulator TODO: Auto event adder from sheet
    val eventToAdd = SheetEvent(timeOfExecution= 10f, type = "Healing", name = "Fey Blessing", targets = playerList, actor = "Caro Kann")
    simulator.addEvent(eventToAdd)

    simulator.executeSimulation()
}


fun initializeParty(mode: String = "lowRisk") : List<Player>{
    // Gets all party members from sheet and parses all their info into a party list
    val parser = CsvParser(mode)
    return parser.readPlayersFromCsv("config/playerConfig.csv")
}
