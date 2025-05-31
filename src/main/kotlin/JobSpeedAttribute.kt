import JobSpeedAttributes.getSpeedAttributeForJob

object JobSpeedAttributes {
    val jobSpeedAttributeMap = mapOf(
        "WHM" to "SPS",
        "SGE" to "SPS",
        "SCH" to "SPS",
        "AST" to "SPS",
        "PLD" to "SKS",
        "WAR" to "SKS",
        "DRK" to "SKS",
        "GNB" to "SKS",
        "DRG" to "SKS",
        "MNK" to "SKS",
        "NIN" to "SKS",
        "SAM" to "SKS",
        "RPR" to "SKS",
        "VPR" to "SKS",
        "BRD" to "SKS",
        "MCH" to "SKS",
        "DNC" to "SKS",
        "BLM" to "SPS",
        "SMN" to "SPS",
        "RDM" to "SPS",
        "BLU" to "SPS",
        "PCT" to "SPS"
    )



    fun getSpeedAttributeForJob(job: String): String? {
        return jobSpeedAttributeMap[job.uppercase()]
    }
}

//TEST
fun main(){
    println(getSpeedAttributeForJob("SCH"))
}