import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class ProjectMongoRepository(private val client: MongoClient) {

    private val database = client.getDatabase("Projects")
    private val col = database.getCollection<Project>()

    fun saveProject(project: Project){
        col.insertOne(project)
    }

    fun findProjectByName(name: String): Project? {
        return col.findOne(Project::name eq name)
    }
}