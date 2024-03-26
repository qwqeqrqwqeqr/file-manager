package com.gradation.databox.feature.directory.ui.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.bottomSheet.DataboxBottomSheet
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.feature.directory.data.model.ModeType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.TypeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoBottomSheetView(
    modifier: Modifier = Modifier,
    typeState: TypeState,
    directoryScreenState: DirectoryScreenState
) {
    val sheetState = rememberModalBottomSheetState()

    DataboxBottomSheet(
        modifier = modifier,
        onDismissRequest = { directoryScreenState.updateInfoBottomSheetView(false) },
        sheetState=sheetState,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    vertical = DataboxTheme.space.space36,
                    horizontal = DataboxTheme.space.space20
                ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .noRippleClickable {
                        typeState.updateModeType(ModeType.Edit)
                        directoryScreenState.updateInfoBottomSheetView(false)
                    },
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No3,
                    text = "파일 선택",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
                DataboxText(
                    textStyle = DataboxTextStyle.No6,
                    text = "파일 선택하여 작업합니다.",
                    color = DataboxTheme.colorScheme.secondaryTextColor,
                    textAlign = TextAlign.Start
                )
            }
            HorizontalDivider(
                modifier = modifier.padding(vertical = DataboxTheme.space.space16),
                color = DataboxTheme.colorScheme.dividerColor,
                thickness = 0.5.dp
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .noRippleClickable { directoryScreenState.updateCreateDirectoryDialogView(true) },
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No3,
                    text = "새 폴더",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
                DataboxText(
                    textStyle = DataboxTextStyle.No6,
                    text = "새 폴더를 생성합니다.",
                    color = DataboxTheme.colorScheme.secondaryTextColor,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}