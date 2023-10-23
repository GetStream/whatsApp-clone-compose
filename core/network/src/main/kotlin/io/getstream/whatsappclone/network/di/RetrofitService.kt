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

package io.getstream.whatsappclone.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitService(val baseService: BaseService)

enum class BaseService(val baseUrl: String) {
  WhatsApp(
    baseUrl = "https://gist.githubusercontent.com/skydoves/44140b10c3b1057b8ac00e2a59eaaa86/raw/" +
      "0ca2cdbb34c7eaf365130c75969a29d4e33bd2fc/"
  ),
  StreamVideo(
    baseUrl = "https://stream-calls-dogfood.vercel.app/"
  )
}
