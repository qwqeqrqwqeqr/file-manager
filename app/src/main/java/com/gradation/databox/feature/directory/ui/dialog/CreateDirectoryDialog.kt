package com.gradation.databox.feature.directory.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.button.DataboxDefaultButton
import com.gradation.databox.core.designsystem.component.button.DataboxPrimaryButton
import com.gradation.databox.core.designsystem.component.dialog.DataboxDialog
import com.gradation.databox.core.designsystem.component.surface.DataboxSurface
import com.gradation.databox.core.designsystem.component.surface.SurfaceType
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.component.textField.DataboxTextField
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.data.file.utils.validateFileName
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.FileState

@Composable
fun CreateDirectoryDialog(
    modifier: Modifier = Modifier,
    directoryPath: String,
    fileState: FileState,
    directoryScreenState: DirectoryScreenState
) {
    var directoryName: String by remember { mutableStateOf("새 폴더") }
    val onValueClear: () -> Unit = { directoryName = "" }
    val onValueChange: (String) -> Unit = { directoryName = it }

    DataboxDialog(
        onDismissRequest = { directoryScreenState.updateCreateDirectoryDialogView(false) }
    ) {
        DataboxSurface(
            modifier = modifier.width(directoryScreenState.configuration.screenWidthDp.dp / 4 * 3),
            paddingValues = PaddingValues(
                horizontal = DataboxTheme.space.space20,
                vertical = DataboxTheme.space.space20
            ),
            shape = RoundedCornerShape(DataboxTheme.space.space12),
            surfaceType = SurfaceType.Secondary,
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space16)
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No2,
                    text = "폴더 이름",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
                DataboxTextField(
                    value = directoryName,
                    placeHolderValue = "이름을 입력해주세요",
                    onValueClear = onValueClear,
                    onValueChange = onValueChange
                )


                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
                ) {
                    DataboxDefaultButton(
                        modifier = modifier.weight(1f),
                        text = "취소"
                    ) {
                        directoryScreenState.updateCreateDirectoryDialogView(false)
                    }
                    DataboxPrimaryButton(
                        modifier = modifier.weight(1f),
                        enabled = directoryName.validateFileName(),
                        text = "생성"
                    ) {
                        fileState.createDirectory(directoryPath, directoryName)
                        directoryScreenState.updateInfoBottomSheetView(false)
                        directoryScreenState.updateCreateDirectoryDialogView(false)
                    }
                }
            }
        }

    }
}