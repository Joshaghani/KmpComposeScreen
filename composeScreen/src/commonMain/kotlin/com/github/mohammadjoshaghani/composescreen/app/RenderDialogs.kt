package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.UIBottomSheet
import com.github.mohammadjoshaghani.composescreen.compose.dialog.BaseDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.UIAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderDialogs() {
    when {
        UIAlertDialog.isShow() -> {
            UIAlertDialog.getDialog()
                ?.ShowDialog()
        }

        BaseDialog.stack.isNotEmpty() -> {
            BaseDialog.stack.last().ShowDialog()
        }

        UIBottomSheet.isShow() -> {
            UIBottomSheet.getBottomSheet()?.let { bottomSheet ->
                ModalBottomSheet(
                    onDismissRequest = { bottomSheet.hide() },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    ProvideLayoutDirection {
                        bottomSheet.ShowBottomSheet()
                    }
                }
            }
        }
    }
}