/*
 * Copyright 2023 Stream.IO, Inc. All Rights Reserved.
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

@file:OptIn(ExperimentalSerializationApi::class)

package io.getstream.whatsappclone.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.getstream.whatsappclone.network.service.StreamVideoTokenService
import io.getstream.whatsappclone.network.service.WhatsAppUserService
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

  @Provides
  @Singleton
  fun providesNetworkJson(): Json = Json {
    ignoreUnknownKeys = true
  }

  @Provides
  @Singleton
  @RetrofitService(BaseService.WhatsApp)
  fun provideWhatsAppRetrofit(networkJson: Json): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BaseService.WhatsApp.baseUrl)
      .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
      .addCallAdapterFactory(ResultCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideWhatsAppUerService(
    @RetrofitService(BaseService.WhatsApp) retrofit: Retrofit
  ): WhatsAppUserService {
    return retrofit.create()
  }

  @Provides
  @Singleton
  @RetrofitService(BaseService.StreamVideo)
  fun provideStreamVideoRetrofit(networkJson: Json): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BaseService.StreamVideo.baseUrl)
      .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
      .addCallAdapterFactory(ResultCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideStreamVideoTokenService(
    @RetrofitService(BaseService.StreamVideo) retrofit: Retrofit
  ): StreamVideoTokenService {
    return retrofit.create()
  }
}
