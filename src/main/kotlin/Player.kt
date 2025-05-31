class Player(
    val name: String = "Default Name",
    val job: String = "Default Job",
    val gearset: String = "Default Gearset",
    val mode: String = "lowRisk"
) {
    var healingReceivedBuffGCD = 1.0f
    var healingReceivedBuffAll = 1.0f
    var healingGivenBuffGCD = 1.0f
    var healingGivenBuffAll = 1.0f

    val stats = PlayerStats(gearset)
    val playerHealingCalculator = HealingCalculator(stats, mode)

    init {
        println("Player created with name: $name, job: $job, gearset: $gearset")
        stats.printStats()
        println("---------------------------------------")
    }

    fun introduceYourself() {
        println("Hi, I'm $name! I play $job and currently use gearset $gearset.")
        for (i in 1..5) {
            val testPotency: Int = 800
            val healingToPrint: Int = playerHealingCalculator.calculateHealing(testPotency, autoCrit = true)
            println("My flat healing with potency $testPotency right now would give you $healingToPrint hp")
        }
        println("---------------------------------------")

    }
}
