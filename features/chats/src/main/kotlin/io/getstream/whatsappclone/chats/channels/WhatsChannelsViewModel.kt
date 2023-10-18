package io.getstream.whatsappclone.chats.channels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.navigation.WhatsAppScreens
import javax.inject.Inject

@HiltViewModel
class WhatsChannelsViewModel @Inject constructor(
  private val composeNavigator: AppComposeNavigator,
) : ViewModel() {


  fun navigateToMessages(channelId: String) {
    composeNavigator.navigate(WhatsAppScreens.Messages.createRoute(channelId))
  }


}
