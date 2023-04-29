package com.example.cleanarchitecturestudy.data.source.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cleanarchitecturestudy.data.dto.Item
import com.example.cleanarchitecturestudy.data.source.datasource.DataSource
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 1

class DataPagingSource(
    private val userId: String,
    private val dataSource: DataSource
) : PagingSource<Int, Item>() {

    companion object {
        private const val TAG = "DataPagingSource"
    }

    /**
     * 스크롤 할 때마다 데이터를 비동기적으로 가져오는 메서드
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val repos = dataSource.getSearchUsers(userId, position, params.loadSize).items

            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            Log.e(TAG, "IOException occurred", e)
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "HTTPException occurred", e)
            return LoadResult.Error(e)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load repositories", e)
            LoadResult.Error(e)
        }
    }

    /**
     * 현재 목록을 대체할 새 데이터를 로드할 때 사용
     */
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}