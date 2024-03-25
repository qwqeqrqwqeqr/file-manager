package com.gradation.databox.feature.directory.ui.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.model.ViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewBottomSheetView(
    modifier: Modifier = Modifier,
    viewType: ViewType,
    updateViewType: (ViewType) -> Unit,
    updateViewBottomSheetView: (Boolean) -> Unit,
) {
    DataboxBottomSheet(
        modifier = modifier,
        onDismissRequest = { updateViewBottomSheetView(false) },
        dragHandle = null
    ) {
        Column(
            modifier = modifier
                .padding(
                    top = DataboxTheme.space.space20,
                    bottom = DataboxTheme.space.space36
                )
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
                        DataboxTextSelector(
                            modifier = modifier.noRippleClickable {
                                updateViewType(
                                    ViewType.List
                                )
                            },
                            selected = viewType is ViewType.List,
                            text = "리스트"
                        )
                        DataboxTextSelector(
                            modifier = modifier.noRippleClickable {
                                updateViewType(
                                    ViewType.Grid
                                )
                            },
                            selected = viewType is ViewType.Grid,
                            text = "그리드"
                        )
                    }
                }
            }
        }
    }
}