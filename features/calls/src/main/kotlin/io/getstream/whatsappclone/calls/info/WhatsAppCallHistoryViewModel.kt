package io.getstream.whatsappclone.calls.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import javax.inject.Inject

@HiltViewModel
class WhatsAppCallHistoryViewModel @Inject constructor(
  private val composeNavigator: AppComposeNavigator
) : ViewModel() {


  fun navigateUp() {
    composeNavigator.navigateUp()
  }
}
