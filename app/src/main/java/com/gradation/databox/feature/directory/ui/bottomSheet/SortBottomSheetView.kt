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
import com.gradation.databox.feature.directory.data.model.AscendingType
import com.gradation.databox.feature.directory.data.model.SortType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.TypeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheetView(
    modifier: Modifier = Modifier,
    typeState: TypeState,
    sortTypeEntries: List<SortType>,
    directoryScreenState: DirectoryScreenState,
) {
    DataboxBottomSheet(
        modifier = modifier,
        onDismissRequest = { directoryScreenState.updateSortBottomSheetView(false) },
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
                    text = "정렬",
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
                ){
                    DataboxText(
                        textStyle = DataboxTextStyle.No5,
                        text = "정렬순서",
                        color = DataboxTheme.colorScheme.primaryTextColor,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
                    ) {
                        DataboxTextSelector(
                            modifier = modifier.noRippleClickable {
                                typeState.updateAscendingType(
                                    AscendingType.Ascending
                                )
                            },
                            selected = typeState.ascendingType is AscendingType.Ascending,
                            text = "오름차순"
                        )
                        DataboxTextSelector(
                            modifier = modifier.noRippleClickable {
                                typeState.updateAscendingType(
                                    AscendingType.Descending
                                )
                            },
                            selected = typeState.ascendingType is AscendingType.Descending,
                            text = "내림차순"
                        )
                    }
                }
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
                ){
                    DataboxText(
                        textStyle = DataboxTextStyle.No5,
                        text = "정렬방식",
                        color = DataboxTheme.colorScheme.primaryTextColor,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
                    ) {
                        sortTypeEntries.forEach {
                            DataboxTextSelector(
                                modifier = modifier.noRippleClickable { typeState.updateSortType(it) },
                                selected = typeState.sortType == it,
                                text = it.getTitleName()
                            )
                        }
                    }
                }
            }
        }
    }
}