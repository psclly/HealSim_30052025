fun setup() {
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

fun initializeParty(mode: String = "lowRisk", file: String = "config/playerConfig.csv") : List<Player>{
    // Gets all party members from sheet and parses all their info into a party list
    println("loading $file")
    val parser = CsvParser(mode)
    return parser.readPlayersFromCsv(file)
}

fun initializeTimeline(file: String){
    // Gets all party members from sheet and parses all their info into a party list
    println("loading $file")
    val parser = CsvParser()
    println(parser.readTimelineFromCsv(file))
}