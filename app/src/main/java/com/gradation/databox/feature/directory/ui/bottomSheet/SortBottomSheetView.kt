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
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.data.state.TypeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheetView(
    modifier: Modifier = Modifier,
    sortType: SortType,
    ascendingType: AscendingType,
    typeState: TypeState,
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
                        typeState.ascendingTypeEntries.forEach {
                            DataboxTextSelector(
                                modifier = modifier.noRippleClickable { typeState.updateAscendingType(it) },
                                selected = ascendingType == it,
                                text = typeState.getTitleName(it)
                            )
                        }
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
                        typeState.sortTypeEntries.forEach {
                            DataboxTextSelector(
                                modifier = modifier.noRippleClickable { typeState.updateSortType(it) },
                                selected = sortType == it,
                                text = typeState.getTitleName(it)
                            )
                        }
                    }
                }
            }
        }
    }
}