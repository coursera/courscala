/*
 * Copyright 2016 Coursera Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.coursera.common.jsonformat

import org.coursera.common.stringkey.StringKeyFormat
import play.api.libs.json.Format
import play.api.libs.json.Json
import play.api.libs.json.OFormat
import play.api.libs.json.OWrites
import play.api.libs.json.Reads
import play.api.libs.json.Writes
import play.api.libs.json.__

/**
 * Formats a subclass of a variable-schema superclass.
 *
 * Variable-schema objects must have a type name (string) and a definition (JSON). The schema of
 * the definition is specified based on the type name. These formats read the type name and then
 * appropriately handle the JSON definition.
 *
 * For example, the type names for a user id object may be "guest" and "registered" and the
 * corresponding definitions may include a random string for the guest and an integer id for
 * the registered user.
 * {{{
 *   sealed trait UserId
 *
 *   case class GuestUserId(id: String)
 *
 *   object GuestUserId {
 *     implicit val format: OFormat[GuestUserId] =
 *       TypedFormats.typedDefinitionFormat("guest", Json.format[GuestUserId])
 *   }
 *
 *   case class RegisteredUserId(id: Int)
 *
 *   object RegisteredUserId {
 *     implicit val format: OFormat[RegisteredUserId] =
 *       TypedFormats.typedDefinitionFormat("registered", Json.format[RegisteredUserId])
 *   }
 * }}}
 *
 * The JSON object looks like:
 * {{{
 *   {
 *     "typeName": "&lt;some name&gt;",
 *     "definition": &lt;some JSON&gt;
 *   }
 * }}}
 * See [[OrFormats]] for defining `UserId`'s [[OFormat]] in this example.
 */
object TypedFormats {

  def typedDefinitionFormat[T, D](
      typeName: T,
      defaultFormat: Format[D])
      (implicit stringKeyFormat: StringKeyFormat[T]): OFormat[D] = {
    OFormat(
      typedDefinitionReads(typeName, defaultFormat),
      typedDefinitionWrites(typeName, defaultFormat))
  }

  def typedDefinitionReads[T, D](
      typeName: T,
      defaultReads: Reads[D])
      (implicit stringKeyFormat: StringKeyFormat[T]): Reads[D] = {
    import JsonFormats.Implicits.ReadsPathMethods
    implicit val typeNameFormat = JsonFormats.stringKeyFormat[T]

    for {
      _ <- (__ \ "typeName").read[T].filter(_ == typeName).withRootPath
      definition <- {
        val definitionExtractingReads = (__ \ "definition").json.pick.withRootPath
        defaultReads.compose(definitionExtractingReads)
      }
    } yield {
      definition
    }
  }

  def typedDefinitionWrites[T, D](
      typeName: T,
      defaultWrites: Writes[D])
      (implicit stringKeyFormat: StringKeyFormat[T]): OWrites[D] = {
    implicit val typeNameFormat = JsonFormats.stringKeyFormat[T]
    OWrites { model: D =>
      val modelJson = Json.toJson(model)(defaultWrites)
      Json.obj("typeName" -> typeName, "definition" -> modelJson)
    }
  }

}
