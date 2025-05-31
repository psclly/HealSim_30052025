class PlayerStats(gearset: String) {
    var job: String = "Unknown"
    var maxhp: Int = 0
    var vitality: Int = 0
    var strength: Int = 0
    var dexterity: Int = 0
    var intelligence: Int = 0
    var mind: Int = 0
    var piety: Int = 0
    var crit: Int = 0
    var dhit: Int = 0
    var determination: Int = 0
    var tenacity: Int = 0
    var skillspeed: Int = 0
    var spellspeed: Int = 0
    var wdPhys: Int = 0
    var wdMag: Int = 0
    var weaponDelay: Double = 0.0

    init {
        val stats = fetchComputedStats(convertExportLinkToApiLink(gearset))

        stats?.let {
            job = it.job
            maxhp = it.hp
            vitality = it.vitality
            strength = it.strength
            dexterity = it.dexterity
            intelligence = it.intelligence
            mind = it.mind
            piety = it.piety
            crit = it.crit
            dhit = it.dhit
            determination = it.determination
            tenacity = it.tenacity
            skillspeed = it.skillspeed
            spellspeed = it.spellspeed
            wdPhys = it.wdPhys
            wdMag = it.wdMag
            weaponDelay = it.weaponDelay
        }
    }

    fun printStats() {
        println("Job: $job")
        println("HP: $maxhp | VIT: $vitality | STR: $strength | DEX: $dexterity")
        println("INT: $intelligence | MND: $mind | PIE: $piety")
        println("Crit: $crit | DH: $dhit | DET: $determination")
        println("Tenacity: $tenacity | Skill Speed: $skillspeed | Spell Speed: $spellspeed")
        println("WD Phys: $wdPhys | WD Mag: $wdMag | Delay: $weaponDelay")
    }
}
