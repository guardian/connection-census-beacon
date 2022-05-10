package controllers

import org.slf4j.LoggerFactory
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import java.nio.file.Paths
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class Endpoints @Inject() (config:Configuration, cc:ControllerComponents, dropParser:DroppingBodyParser)(implicit ec:ExecutionContext) extends AbstractController(cc) {
  private val logger = LoggerFactory.getLogger(getClass)

  def index = Action {
    Ok(Json.obj("status"->"ok"))
  }

  def testUpload = Action(dropParser) { req=>
    Ok(Json.obj("status"->"ok", "detail"->"received content"))
  }

  def testDownload(sizeHint:String) = Action {
    config.getOptional[String](s"testfiles.$sizeHint") match {
      case Some(filename) =>
        val filePath = Paths.get(config.get[String]("testfiles.basepath"), filename)
        try {
          Ok.sendFile(filePath.toFile)
        } catch {
          case err:Throwable=>
            logger.error(s"Could not serve file for $sizeHint: ${err.getMessage}")
            BadRequest(Json.obj("status"->"invalid"))
        }
      case None =>
        NotFound(Json.obj("status"->"notfound"))
    }
  }
}
