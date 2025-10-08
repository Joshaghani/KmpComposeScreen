package com.github.mohammadjoshaghani.composescreen.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.github.mohammadjoshaghani.composescreen.compose.bottomSheet.UIBottomSheet
import com.github.mohammadjoshaghani.composescreen.compose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.base.IBaseDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderDialogs() {
    when {
        UIAlertDialog.isShow() -> {
            UIAlertDialog.getDialog()
                ?.ShowDialog()
        }

        IBaseDialog.stack.isNotEmpty() -> {
            IBaseDialog.stack.last().ShowDialog()
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