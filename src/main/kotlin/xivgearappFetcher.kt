import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ComputedStats(
    val job: String,
    val hp: Int,
    val vitality: Int,
    val strength: Int,
    val dexterity: Int,
    val intelligence: Int,
    val mind: Int,
    val piety: Int,
    val crit: Int,
    val dhit: Int,
    val determination: Int,
    val tenacity: Int,
    val skillspeed: Int,
    val spellspeed: Int,
    val wdPhys: Int,
    val wdMag: Int,
    val weaponDelay: Double
)

@Serializable
data class GearSet(
    val computedStats: ComputedStats
)

@Serializable
data class GearData(
    val sets: List<GearSet>
)

fun fetchComputedStats(apiUrl: String?): ComputedStats? = runBlocking {
    if (apiUrl == null) return@runBlocking null

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    try {
        val gearData: GearData = client.get(apiUrl).body()
        gearData.sets.firstOrNull()?.computedStats
    } catch (e: Exception) {
        println("Error: ${e.message}")
        null
    } finally {
        client.close()
    }
}

fun convertExportLinkToApiLink(exportLink: String): String? {
    // Parse the URL and get the "page" query parameter
    val url = java.net.URL(exportLink)
    val query = url.query ?: return null

    // The page parameter looks like: sl|<UUID>
    val pageParam = query.split("&").firstNotNullOfOrNull { param ->
        val parts = param.split("=")
        if (parts.size == 2 && parts[0] == "page") parts[1] else null
    } ?: return null

    // URL decode the page param (e.g., sl%7Cuuid -> sl|uuid)
    val decoded = java.net.URLDecoder.decode(pageParam, "UTF-8")

    // Split by '|' and get the UUID part
    val parts = decoded.split("|")
    if (parts.size != 2) return null
    val uuid = parts[1]

    // Build the API link
    return "https://api.xivgear.app/fulldata/$uuid"
}


fun main() {
    val url = "https://api.xivgear.app/fulldata/ca187884-058a-4c53-8a72-221585786c7d"
    val stats = fetchComputedStats(url)
    println(stats)
}
