import kotlin.math.floor
import HealingConstants.LevelModDiv
import HealingConstants.LevelModMain
import HealingConstants.LevelModSub
import JobMainStats.getMainStatForJob
import JobSpeedAttributes.getSpeedAttributeForJob
import JobTypes.getJobTypeForJob

import kotlin.random.Random

object HealingConstants {
    const val LevelModMain = 400
    const val LevelModSub = 420
    const val LevelModDiv = 2780
}



class HealingCalculator(playerStats: PlayerStats, val mode: String = "lowRisk") {
    val stats: PlayerStats = playerStats
    val job: String = stats.job
    var healingToReturn: Int = 0
    var h1: Int = 0
    var h2: Int = 0
    var h3: Int = 0


    //TODO Create a healing calculator with a when statement
    // when job = caster / phys / melee / tank use correct OoO and stats based on job
    fun calculateHealing(potency: Int = 0, name: String = "", buffs: Float = 1.0f, autoCrit: Boolean = false): Int {
        var flatHeal: Int = 0
        val traitModifier: Float = 1.3f


        val fDET: Float = floor(130.0f * (stats.determination - LevelModMain) / LevelModDiv + 1000f.toInt())
        val fTNC: Float = floor(100f * (stats.tenacity - LevelModSub) / LevelModDiv + 1000f.toInt())

        //Use SPS for casters and SKS for Phys
        when(getSpeedAttributeForJob(job)){
            "SKS" -> {val fSPD: Float = floor(130f * (stats.skillspeed - LevelModSub) / LevelModDiv + 1000f.toInt())}
            "SPS" -> {val fSPD: Float = floor(130f * (stats.spellspeed - LevelModSub) / LevelModDiv + 1000f.toInt())}
        }

        val fCRT: Float = floor(200f * (stats.crit - LevelModSub) / LevelModDiv + 1400f.toInt())

        var fHMP: Float = 0f
        when(getMainStatForJob(job)){
            "MND" -> {fHMP = floor(207f * (stats.mind - LevelModSub) / 508f.toInt()) + 100f}
            "STR" -> {fHMP = floor(207f * (stats.strength - LevelModSub) / 508f.toInt()) + 100f}
            "DEX" -> {fHMP = floor(207f * (stats.dexterity - LevelModSub) / 508f.toInt()) + 100f}
            "INT" -> {fHMP = floor(207f * (stats.intelligence - LevelModSub) / 508f.toInt()) + 100f}
        }


        //Look up jobAttribute
        val fWD: Float = floor(LevelModMain * (JobAttributes.getAttributeForJob(job)?.toFloat() ?: 0f) / 1000f) + stats.wdPhys

        val pCHR: Float = floor(200f * (stats.crit - LevelModSub) / LevelModDiv + 50f) / 10f



        //TODO: OoO and formulas for Tank / Phys / Caster

        when(getJobTypeForJob(job)){

            //TANK OoO
            "Tank" -> {
                h1 = trunc(trunc(trunc(potency * fHMP/100f) * fDET) /1000f)
                h2 = trunc(trunc(trunc(trunc(h1 * fTNC) / 1000f) * fWD) / 100f)
                //Variance and Crit
                when (mode){
                    "lowRisk" -> flatHeal = trunc(h2 * 0.97f)
                    "random" -> {
                        if(critCheck(pCHR) || autoCrit) {
                            println("crit!!")
                            h3 = trunc(h2 * fCRT / 1000f)
                        } else {
                            println("not crit")
                            h3 = h2
                        }
                        val randomMultiplier: Float = 0.97f + Random.nextFloat() * (1.03f - 0.97f)
                        flatHeal = trunc(h3 * randomMultiplier)
                    }
                    else -> {println("error, mode not found")}
                }
                //Add buffs
                healingToReturn = trunc(flatHeal * buffs)
            }

            //CASTER OoO
            "Caster" -> {
                //Find pet potency modifier >> https://docs.google.com/spreadsheets/d/1Yt7Px7VHuKG1eJR9CRKs3RpvcR5IZKAAA3xjekvv0LY/edit?gid=0#gid=0
                val petModifier = when (name){
                    "Stellar Burst" -> 0.98f
                    "Stellar Explosion" -> 0.98f
                    "Liturgy of the Bell" -> 0.96f
                    "Liturgy of the Bell1" -> 0.96f
                    "Liturgy of the Bell2" -> 0.96f
                    "Liturgy of the Bell3" -> 0.96f
                    "Liturgy of the Bell4" -> 0.96f
                    "Liturgy of the Bell5" -> 0.96f
                    "Fey Blessing" -> 0.9f
                    "Embrace" -> 0.9f
                    else -> 1.0f
                }

                h1 = trunc(trunc(fHMP * fDET / 100f) / 1000f) * trunc(fWD * potency / 100f)
                when (mode){
                    "lowRisk" -> h2 = h1
                    "random" ->{
                        if(critCheck(pCHR) || autoCrit) {
                            //println("crit!!")
                            h2 = trunc(h1 * fCRT / 1000f)
                        } else{
                            //println("not crit")
                            h2 = h1
                        }
                    }
                    else -> {println("error, mode not found")}
                }

                h3 = trunc(h2 * traitModifier)

                when (mode) {
                    "lowRisk" -> flatHeal = trunc(h3 * 0.97f)
                    "random" -> {
                        val variance = 0.97f + Random.nextFloat() * (1.03f - 0.97f)
                        flatHeal = trunc(h3 * variance)
                    }
                }


                healingToReturn = trunc(flatHeal * petModifier * buffs)
            }

            "Physical" -> {
                h1 = trunc(trunc(potency * fHMP * fDET / 100f)/ 1000f)
                h2 = trunc(h1 * fWD / 100f)
                when (mode){
                    "lowRisk" -> flatHeal = trunc(h2 * 0.97f)
                    "random" -> {
                        if(critCheck(pCHR) || autoCrit) {
                            h3 = trunc(h2 * fCRT / 1000f)
                        } else {
                            h3 = h2
                        }
                        val randomMultiplier: Float = 0.97f + Random.nextFloat() * (1.03f - 0.97f)
                        flatHeal = trunc(h3 * randomMultiplier)
                    }
                    else -> {println("error, mode not found")}
                }
                //Add buffs
                healingToReturn = trunc(flatHeal * buffs)
            }
        }
        return healingToReturn
    }
}

fun trunc(x: Float) = floor(x.toDouble()).toInt()

fun critCheck(critChancePercent: Float): Boolean {
    return Random.nextFloat() < (critChancePercent / 100f)
}
