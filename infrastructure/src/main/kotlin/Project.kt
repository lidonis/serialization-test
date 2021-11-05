import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import java.time.OffsetDateTime

@Serializable
data class Project(
    @Contextual val _id: Id<Project>,
    val name: String,
    val language: String,
    @Contextual val startedDate: OffsetDateTime
)