package org.coursera.common.jsonformat

import org.coursera.common.stringkey.StringKeyFormat
import play.api.libs.json.Json
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.__

/**
 * Similar to [[TypedFormats]] but flattens `definition` into the main body:
 * {{{
 *   {
 *     "typeName": "&lt;some name&gt;",
 *     "definitionField1": &lt;a field from the definition object&gt;,
 *     "definitionField2": &lt;another field from the definition object&gt;
 *   }
 * }}}
 */
object FlatTypedFormats {

  def flatTypedDefinitionFormat[T: StringKeyFormat, D](typeName: T, defaultFormat: OFormat[D]):
    OFormat[D] = {

    OFormat(
      flatTypedDefinitionReads(typeName, defaultFormat),
      flatTypedDefinitionWrites(typeName, defaultFormat))
  }

  def flatTypedDefinitionReads[T: StringKeyFormat, D](typeName: T, defaultReads: Reads[D]):
    Reads[D] = {

    import JsonFormats.Implicits.ReadsPathMethods
    implicit val typeNameFormat = JsonFormats.stringKeyFormat[T]

    for {
      _ <- (__ \ "typeName").read[T].filter(_ == typeName).withRootPath
      definition <- {
        val typeNameDroppingReads = (__ \ "typeName").json.prune.withRootPath
        defaultReads.compose(typeNameDroppingReads)
      }
    } yield {
      definition
    }
  }

  def flatTypedDefinitionWrites[T: StringKeyFormat, D](typeName: T, defaultWrites: OWrites[D]):
    OWrites[D] = {

    implicit val typeNameFormat = JsonFormats.stringKeyFormat[T]
    OWrites { model: D =>
      val modelJson = defaultWrites.writes(model)

      require(!modelJson.keys.contains("typeName"), "Model cannot contain reserved field 'typeName'")
      modelJson + ("typeName" -> Json.toJson(typeName))
    }
  }

}
