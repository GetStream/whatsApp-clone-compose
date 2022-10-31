/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.whatsappclone.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.getstream.whatsappclone.model.WhatsAppUser

@Entity(
  tableName = "users"
)
data class WhatsAppUserEntity(
  @PrimaryKey val name: String,
  val gender: String,
  val location: String,
  val email: String,
  val login: String,
  val dateOfBirth: Long,
  val registrationDate: Long,
  val phone: String,
  val cell: String,
  val picture: String,
  val nationality: String
)

fun WhatsAppUser.asEntity(): WhatsAppUserEntity {
  return WhatsAppUserEntity(
    name,
    gender,
    location,
    email,
    login,
    dateOfBirth,
    registrationDate,
    phone,
    cell,
    picture,
    nationality
  )
}
