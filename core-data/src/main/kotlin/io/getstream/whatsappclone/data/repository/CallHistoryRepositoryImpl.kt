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

package io.getstream.whatsappclone.data.repository

import io.getstream.whatsappclone.data.model.toModel
import io.getstream.whatsappclone.database.dao.WhatsAppUserDao
import io.getstream.whatsappclone.database.entity.asEntity
import io.getstream.whatsappclone.model.WhatsAppUser
import io.getstream.whatsappclone.network.Dispatcher
import io.getstream.whatsappclone.network.WhatsAppDispatchers
import io.getstream.whatsappclone.network.service.WhatsAppUserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CallHistoryRepositoryImpl @Inject constructor(
  @Dispatcher(WhatsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val whatsAppUserService: WhatsAppUserService,
  private val whatsAppUserDao: WhatsAppUserDao
) : CallHistoryRepository {

  override fun getCallHistoryUsersStream(): Flow<Result<List<WhatsAppUser>>> = flow {
    val offlineCallHistory = whatsAppUserDao.getWhatsAppUser()
    if (offlineCallHistory.isEmpty()) {
      val result = whatsAppUserService.fetchWhatsAppUsers()
      updateCallCallHistoryUsers(result)
      emit(result)
    } else {
      emit(Result.success(offlineCallHistory.map { it.toModel() }))
    }
  }.flowOn(ioDispatcher)

  override suspend fun updateCallCallHistoryUsers(whatsappUsers: Result<List<WhatsAppUser>>) {
    val entities = whatsappUsers.getOrNull()?.map { it.asEntity() } ?: return
    whatsAppUserDao.insertWhatsAppUsers(entities)
  }
}
