import kotlin.math.floor
import HealingConstants.LEVELMODDIV
import HealingConstants.LEVELMODMAIN
import HealingConstants.LEVELMODSUB
import kotlin.random.Random

object HealingConstants {
    const val LEVELMODMAIN = 400
    const val LEVELMODSUB = 420
    const val LEVELMODDIV = 2780
}



class HealingCalculator(playerStats: PlayerStats, val mode: String = "lowRisk") {
    val stats: PlayerStats = playerStats
    val characterJob: Job = stats.job
    var healingToReturn: Int = 0
    var h1: Int = 0
    var h2: Int = 0
    var h3: Int = 0


    //TODO Create a healing calculator with a when statement
    // when job = caster / phys / melee / tank use correct OoO and stats based on job
    fun calculateHealing(potency: Int = 0, name: String = "", buffs: Float = 1.0f, autoCrit: Boolean = false): Int {
        var flatHeal = 0
        val traitModifier: Float = if (characterJob.jobMainStats == JobMainStats.MND) 1.3f else 1f


        val fDET = truncate(130.0f * (stats.determination - LEVELMODMAIN) / LEVELMODDIV + 1000f)
        val fTNC = if (characterJob.jobTypes == JobTypes.Tank) {
            truncate(100f * (stats.tenacity - LEVELMODSUB) / LEVELMODDIV + 1000f)
        } else {
            1000
        }

        //Use SPS for casters and SKS for Phys

        val fSPD = when(characterJob.speedTypes){
            SpeedTypes.SKS -> truncate(130f * (stats.skillspeed - LEVELMODSUB) / LEVELMODDIV + 1000f)
            SpeedTypes.SPS -> truncate(130f * (stats.spellspeed - LEVELMODSUB) / LEVELMODDIV + 1000f)
        }

        val fCRT = truncate(200f * (stats.crit - LEVELMODSUB) / LEVELMODDIV + 1400f)


        val fHMP = when(characterJob.jobMainStats){
            JobMainStats.MND -> truncate(207f * (stats.mind - LEVELMODSUB) / 508f) + 100
            JobMainStats.STR -> truncate(207f * (stats.strength - LEVELMODSUB) / 508f) + 100
            JobMainStats.DEX -> truncate(207f * (stats.dexterity - LEVELMODSUB) / 508f) + 100
            JobMainStats.INT -> truncate(207f * (stats.intelligence - LEVELMODSUB) / 508f) + 100
        }


        //Look up jobAttribute
        val fWD = truncate(LEVELMODMAIN * characterJob.mainStatModifier / 1000f) + stats.wdPhys

        val pCHR: Float = floor(200f * (stats.crit - LEVELMODSUB) / LEVELMODDIV + 50f) / 10f



        val petModifier = when (name) {
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

        h1 = truncate(truncate(potency * fHMP / 100f * fDET) / 1000f)
        h2 = truncate(truncate(truncate(h1 * fTNC / 1000f) * fWD / 100f) * traitModifier)
        //Variance and Crit
        //If the mode is lowrisk, apply LOW ROLL and NO CRIT
        //If the mode is random, apply RANDOM ROLL and CRIT
        when (mode) {
            "lowRisk" -> flatHeal = truncate(h2 * 0.97f)
            "random" -> {
                h3 = if (critCheck(pCHR) || autoCrit) {
                    truncate(h2 * fCRT / 1000f)
                } else {
                    h2
                }
                val randomMultiplier: Float = 0.97f + Random.nextFloat() * (1.03f - 0.97f)
                flatHeal = truncate(h3 * randomMultiplier)
            }
        }
        //Add buffs to flatHeal
        healingToReturn = truncate(flatHeal * petModifier * buffs)


        return healingToReturn
    }
}

fun truncate(x: Float) = floor(x.toDouble()).toInt()

fun critCheck(critChancePercent: Float): Boolean {
    return Random.nextFloat() < (critChancePercent / 100f)
}

