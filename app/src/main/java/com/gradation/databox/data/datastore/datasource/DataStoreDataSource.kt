package com.gradation.databox.data.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.databox.data.datastore.Constants.ASCENDING_TYPE
import com.gradation.databox.data.datastore.Constants.SORT_TYPE
import com.gradation.databox.data.datastore.Constants.VIEW_TYPE
import com.gradation.databox.domain.mapper.type.toAscendingType
import com.gradation.databox.domain.mapper.type.toSortType
import com.gradation.databox.domain.mapper.type.toViewType
import com.gradation.databox.domain.model.type.AscendingType
import com.gradation.databox.domain.model.type.SortType
import com.gradation.databox.domain.model.type.ViewType
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun updateSortType(sortType: SortType) {
        dataStore.edit { preferences -> preferences[SORT_TYPE] = sortType.toString() }
    }

    suspend fun updateAscendingType(ascendingType: AscendingType) {
        dataStore.edit { preferences -> preferences[ASCENDING_TYPE] = ascendingType.toString() }
    }

    suspend fun updateViewType(viewType: ViewType) {

        dataStore.edit { preferences -> preferences[VIEW_TYPE] = viewType.toString() }
    }


    val sortType: Flow<SortType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }
        }
        .map { it[SORT_TYPE] ?: EMPTY_VALUE }.map { it.toSortType() }




    val ascendingType: Flow<AscendingType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }
        }
        .map { it[ASCENDING_TYPE] ?: EMPTY_VALUE }.map { it.toAscendingType() }


    val viewType: Flow<ViewType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }
        }.map { it[VIEW_TYPE] ?: EMPTY_VALUE }.map { it.toViewType() }






    suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }


    companion object {
        const val EMPTY_VALUE = ""
    }
}


