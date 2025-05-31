import JobMainStats.*
import Job.*
import JobTypes.*
import SpeedTypes.*
enum class JobMainStats {
    MND, STR, DEX, INT
}

enum class JobTypes{
    Healer, Tank, DPS
}

enum class SpeedTypes{
    SKS, SPS
}

enum class Job(val jobMainStats: JobMainStats, val jobTypes: JobTypes, val speedTypes: SpeedTypes){
    WHM(MND, Healer, SPS),
    SGE(MND, Healer, SPS),
    SCH(MND, Healer, SPS),
    AST(MND, Healer, SPS),
    PLD(STR, Tank, SKS),
    WAR(STR, Tank, SKS),
    DRK(STR, Tank, SKS),
    GNB(STR, Tank, SKS),
    DRG(STR, DPS, SKS),
    MNK(STR, DPS, SKS),
    NIN(DEX, DPS, SKS),
    SAM(STR, DPS, SKS),
    RPR(STR, DPS, SKS),
    VPR(DEX, DPS, SKS),
    BRD(DEX, DPS, SKS),
    MCH(DEX, DPS, SKS),
    DNC(DEX, DPS, SKS),
    BLM(INT, DPS, SPS),
    SMN(INT, DPS, SPS),
    RDM(INT, DPS, SPS),
    BLU(INT, DPS, SPS),
    PCT(INT, DPS, SPS),
}

//TEST
fun main(){
    println(WHM.jobMainStats)
}