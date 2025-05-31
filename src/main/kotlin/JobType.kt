import JobTypes.getJobTypeForJob
import JobSpeedAttributes.getSpeedAttributeForJob

object JobTypes {
    val jobTypeMap = mapOf(
        "WHM" to "Healer",
        "SGE" to "Healer",
        "SCH" to "Healer",
        "AST" to "Healer",
        "PLD" to "Tank",
        "WAR" to "Tank",
        "DRK" to "Tank",
        "GNB" to "Tank",
        "DRG" to "Physical",
        "MNK" to "Physical",
        "NIN" to "Physical",
        "SAM" to "Physical",
        "RPR" to "Physical",
        "VPR" to "Physical",
        "BRD" to "Physical",
        "MCH" to "Physical",
        "DNC" to "Physical",
        "BLM" to "Caster",
        "SMN" to "Caster",
        "RDM" to "Caster",
        "BLU" to "Caster",
        "PCT" to "Caster",
    )

    fun getJobTypeForJob(job: String): String? {
        return jobTypeMap[job.uppercase()]
    }
}

//TEST
fun main(){
    println(getJobTypeForJob("SCH"))
}