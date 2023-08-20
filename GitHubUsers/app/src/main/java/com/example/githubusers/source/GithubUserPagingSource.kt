package com.example.githubusers.source

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.githubusers.api.GitHubApiProvider
import com.example.githubusers.model.User
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUserPagingSource : RxPagingSource<Long, User>() {
    private val githubApi = GitHubApiProvider.getGitHubApi()

    override fun getRefreshKey(state: PagingState<Long, User>): Long? {
        val anchor = state.anchorPosition
        if(anchor == null){
            return null
        }
        val anchorPage = state.closestPageToPosition(anchor)
        if(anchorPage == null){
            return null
        }
        val prevKey = anchorPage.prevKey
        if(prevKey != null){
            return anchorPage.data.last().id
        }
        val nextKey = anchorPage.nextKey
        if(nextKey != null){
            return anchorPage.data.first().id - 10
        }
        return null
    }

    override fun loadSingle(params: LoadParams<Long>): Single<LoadResult<Long, User>> {
        var key = params.key
        if(key == null){
            key = 0
        }
        val loadSize = params.loadSize
        return githubApi.getAllUsersUsingRx(key, loadSize)
            .subscribeOn(Schedulers.io())
            .doOnError {
                it.printStackTrace()
            }
            .map { listUsers ->
                return@map LoadResult.Page(
                    listUsers,
                    null,
                    listUsers.last().id,
                    LoadResult.Page.COUNT_UNDEFINED,
                    LoadResult.Page.COUNT_UNDEFINED
                )
            }
    }
}