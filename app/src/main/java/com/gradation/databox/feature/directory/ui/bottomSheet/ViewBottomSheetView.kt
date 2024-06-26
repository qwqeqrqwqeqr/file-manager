package com.gradation.databox.feature.directory.ui.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.bottomSheet.DataboxBottomSheet
import com.gradation.databox.core.designsystem.component.selector.DataboxTextSelector
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.domain.model.type.ViewType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.TypeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewBottomSheetView(
    modifier: Modifier = Modifier,
    viewType: ViewType,
    typeState: TypeState,
    directoryScreenState: DirectoryScreenState
) {
    DataboxBottomSheet(
        modifier = modifier,
        onDismissRequest = { directoryScreenState.updateViewBottomSheetView(false) },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = DataboxTheme.space.space36)
                .padding(horizontal = DataboxTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space36)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No2,
                    text = "보기",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
                ) {
                    DataboxText(
                        textStyle = DataboxTextStyle.No5,
                        text = "보기방식",
                        color = DataboxTheme.colorScheme.primaryTextColor,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
                    ) {
                        typeState.viewTypeEntries.forEach {
                            DataboxTextSelector(
                                modifier = modifier.noRippleClickable { typeState.updateViewType(it) },
                                selected = typeState.viewType == it,
                                text = typeState.getTitleName(it)
                            )
                        }


                    }
                }
            }
        }
    }
}