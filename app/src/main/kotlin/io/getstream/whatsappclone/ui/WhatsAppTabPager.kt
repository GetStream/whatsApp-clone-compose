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

package io.getstream.whatsappclone.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.getstream.whatsappclone.designsystem.component.WhatsAppCloneBackground
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.WHITE200
import io.getstream.whatsappclone.designsystem.theme.getTabPrimaryColor
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.navigation.TOP_LEVEL_DESTINATIONS
import io.getstream.whatsappclone.navigation.WhatsAppPage
import io.getstream.whatsappclone.navigation.WhatsAppPagerContent
import kotlinx.coroutines.launch

@Composable
fun WhatsAppTabPager(
  modifier: Modifier = Modifier,
  composeNavigator: AppComposeNavigator
) {
  val coroutineScope = rememberCoroutineScope()
  val pagerState = rememberPagerState()

  LaunchedEffect(key1 = Unit) {
    if (pagerState.currentPage == WhatsAppPage.Camera.index) {
      pagerState.scrollToPage(WhatsAppPage.Chats.index)
    }
  }

  WhatsAppCloneBackground {
    Column(modifier = modifier.fillMaxSize()) {
      TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions ->
          TabRowDefaults.Indicator(
            modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
            color = getTabPrimaryColor(),
            height = 2.5.dp
          )
        },
        divider = {},
        backgroundColor = MaterialTheme.colorScheme.primary
      ) {
        // Add tabs for all of our pages
        TOP_LEVEL_DESTINATIONS.forEachIndexed { index, destination ->
          val selected = pagerState.currentPage == index
          Tab(
            modifier = Modifier.padding(10.dp),
            selected = pagerState.currentPage == index,
            onClick = {
              coroutineScope.launch {
                pagerState.animateScrollToPage(index)
              }
            }
          ) {
            if (destination.route != WhatsAppPage.Camera.route) {
              Text(
                text = destination.route.uppercase(),
                color = getSelectedPrimaryColor(selected),
                style = MaterialTheme.typography.titleSmall
              )
            } else {
              Icon(
                imageVector = WhatsAppIcons.Camera,
                tint = getSelectedPrimaryColor(selected),
                contentDescription = null
              )
            }
          }
        }
      }

      HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = TOP_LEVEL_DESTINATIONS.size,
        state = pagerState
      ) { page ->

        WhatsAppPagerContent(
          page = page,
          composeNavigator = composeNavigator
        )
      }
    }
  }
}

@Composable
private fun getSelectedPrimaryColor(selected: Boolean): Color {
  return if (selected) {
    getTabPrimaryColor()
  } else {
    WHITE200
  }
}
