import java.io.File

class CsvParser(val mode: String = "lowRisk"){
    fun readPlayersFromCsv(path: String): List<Player> {
        val players = mutableListOf<Player>()
        val lines = File(path).readLines()

        // Skip the first line (legend/header)
        for (line in lines.drop(1)) {
            val columns = line.split(",")
            if (columns.size >= 3) {
                val name = columns[0].trim()
                val job = columns[1].trim()
                val gearset = columns[2].trim()
                players.add(Player(name, job, gearset, mode))
            }
        }
        return players
    }
}

