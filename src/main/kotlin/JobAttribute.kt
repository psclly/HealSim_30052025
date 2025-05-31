import JobAttributes.getAttributeForJob
import JobSpeedAttributes.getSpeedAttributeForJob

object JobAttributes {
    val jobAttributeMap = mapOf(
        "WHM" to 115,
        "SGE" to 115,
        "SCH" to 115,
        "AST" to 115,
        "PLD" to 100,
        "WAR" to 105,
        "DRK" to 105,
        "GNB" to 100,
        "DRG" to 115,
        "MNK" to 110,
        "NIN" to 110,
        "SAM" to 112,
        "RPR" to 115,
        "VPR" to 110,
        "BRD" to 115,
        "MCH" to 115,
        "DNC" to 115,
        "BLM" to 115,
        "SMN" to 115,
        "RDM" to 115,
        "BLU" to 115,
        "PCT" to 115
    )

    fun getAttributeForJob(job: String): Int? {
        return jobAttributeMap[job.uppercase()]
    }
}

//TEST
fun main(){
    println(getAttributeForJob("SCH"))
}