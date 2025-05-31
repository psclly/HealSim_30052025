import JobMainStats.*
import Job.*
import JobTypes.*
import SpeedTypes.*
import kotlinx.serialization.Serializable

enum class JobMainStats {
    MND, STR, DEX, INT
}

enum class JobTypes{
    Healer, Tank, DPS
}

enum class SpeedTypes{
    SKS, SPS
}
@Serializable
enum class Job(val jobMainStats: JobMainStats, val jobTypes: JobTypes, val speedTypes: SpeedTypes, val mainStatModifier: Int){
    WHM(MND, Healer, SPS, 115),
    SGE(MND, Healer, SPS, 115),
    SCH(MND, Healer, SPS, 115),
    AST(MND, Healer, SPS, 115),
    PLD(STR, Tank, SKS, 100),
    WAR(STR, Tank, SKS, 105),
    DRK(STR, Tank, SKS, 105),
    GNB(STR, Tank, SKS, 100),
    DRG(STR, DPS, SKS, 115),
    MNK(STR, DPS, SKS, 110),
    NIN(DEX, DPS, SKS, 110),
    SAM(STR, DPS, SKS, 112),
    RPR(STR, DPS, SKS, 115),
    VPR(DEX, DPS, SKS, 110),
    BRD(DEX, DPS, SKS, 115),
    MCH(DEX, DPS, SKS, 115),
    DNC(DEX, DPS, SKS, 115),
    BLM(INT, DPS, SPS, 115),
    SMN(INT, DPS, SPS, 115),
    RDM(INT, DPS, SPS, 115),
    BLU(INT, DPS, SPS, 115),
    PCT(INT, DPS, SPS, 115),
}

//TEST
fun main(){
    println(WHM.jobMainStats)
}