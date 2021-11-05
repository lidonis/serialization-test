import kotlin.test.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.KMongo
import org.litote.kmongo.newId
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import java.time.OffsetDateTime

class ProjectMongoRepositoryTest {

    private lateinit var mongoDBContainer: MongoDBContainer
    private lateinit var repository: ProjectMongoRepository

    @Before
    fun setUp() {
        mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        mongoDBContainer.start()
        val client = KMongo.createClient(mongoDBContainer.replicaSetUrl)
        repository = ProjectMongoRepository(client)
    }

    @Test
    fun test() {
        val project = Project(
            _id = newId(),
            name = "kotlinx.serialization",
            language = "Kotlin",
            startedDate = OffsetDateTime.parse("1977-04-22T01:00:00-05:00")
        )

        repository.saveProject(project)

        val savedProject=  repository.findProjectByName(project.name)

        assertEquals(project.name, savedProject?.name)
    }

    @After
    fun tearDown() {
        mongoDBContainer.stop()
    }
}