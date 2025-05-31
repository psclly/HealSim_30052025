import JobMainStats.getMainStatForJob

object JobMainStats {
    val jobMainStatMap = mapOf(
        "WHM" to "MND",
        "SGE" to "MND",
        "SCH" to "MND",
        "AST" to "MND",
        "PLD" to "STR",
        "WAR" to "STR",
        "DRK" to "STR",
        "GNB" to "STR",
        "DRG" to "STR",
        "MNK" to "STR",
        "NIN" to "DEX",
        "SAM" to "STR",
        "RPR" to "STR",
        "VPR" to "DEX",
        "BRD" to "DEX",
        "MCH" to "DEX",
        "DNC" to "DEX",
        "BLM" to "INT",
        "SMN" to "INT",
        "RDM" to "INT",
        "BLU" to "INT",
        "PCT" to "INT",
    )

    fun getMainStatForJob(job: String): String? {
        return jobMainStatMap[job.uppercase()]
    }
}

//TEST
fun main(){
    println(getMainStatForJob("SCH"))
}