package com.example.gitcompose.infrastructure.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitcompose.models.Page
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PagingDataSource<T : Any> @AssistedInject constructor(
    @Assisted("dataBlock") private val dataBlock: suspend (page: Int, loadSize: Int) -> Page<T>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        return try {
            val result = dataBlock(page, pageSize)

            LoadResult.Page(
                data = result.results,
                prevKey = result.previousPage,
                nextKey = result.nextPage,
            )
        } catch (tr: Throwable) {
            Log.e("qwe", tr.toString(), tr)
            LoadResult.Error(throwable = tr)
        }
    }

    @AssistedFactory
    interface Factory<T : Any> {
        fun create(@Assisted("dataBlock") dataBlock: suspend (page: Int, loadSize: Int) -> Page<T>): PagingDataSource<T>
    }
}