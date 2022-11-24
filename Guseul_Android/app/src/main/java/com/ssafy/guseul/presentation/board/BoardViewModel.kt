package com.ssafy.guseul.presentation.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.usecase.board.CreatePostUseCase
import com.ssafy.guseul.domain.usecase.board.DeletePostUseCase
import com.ssafy.guseul.domain.usecase.board.EditPostUseCase
import com.ssafy.guseul.domain.usecase.board.GetPostDetailUseCase
import com.ssafy.guseul.domain.usecase.board.GetPostsUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val createPostUseCase: CreatePostUseCase,
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val editPostUseCase: EditPostUseCase
) : ViewModel() {

    private val _postLists = MutableLiveData<List<BoardEntity>>()

    private val _filteredLists = MutableLiveData<List<BoardEntity>>()

    private val _posts = MutableLiveData<ViewState<List<BoardEntity>>>()
    val posts: LiveData<ViewState<List<BoardEntity>>> = _posts

    private val _isCreated = MutableLiveData<Boolean>()
    val isCreated: LiveData<Boolean> = _isCreated

    private val _isDeleted = MutableLiveData<String>()
    val isDeleted: LiveData<String> = _isDeleted

    private val _post = MutableLiveData<BoardRequest>()
    val post: LiveData<BoardRequest> = _post

    private val _isMine = MutableLiveData<Boolean>()
    val isMine: LiveData<Boolean> = _isMine

    private val _boardEntity = MutableLiveData<ViewState<BoardEntity>>()
    val boardEntity: LiveData<ViewState<BoardEntity>> = _boardEntity

    fun getPost(postId: Int) = viewModelScope.launch {
        _boardEntity.value = ViewState.Loading()
        try {
            val response = getPostDetailUseCase(postId)
            _boardEntity.value = ViewState.Success(response.entity)
            _isMine.value = response.isMine
        } catch (e: Exception) {
            _boardEntity.value = ViewState.Error(e.message, null)
        }
    }

    fun getPosts() = viewModelScope.launch {
        _posts.value = ViewState.Loading()
        try {
            val response = getPostsUseCase.getPosts()
            _posts.value = ViewState.Success(response)
            _postLists.value = response
            _filteredLists.value = response
        } catch (e: Exception) {
            _posts.value = ViewState.Error(e.message, null)
        }
    }

    fun createPost() = viewModelScope.launch {
        val response = _post.value?.let { createPostUseCase(it) }
        _isCreated.postValue(response == true)
    }

    fun deletePost(postId: Int) = viewModelScope.launch {
        val response = deletePostUseCase(postId)
        _isDeleted.postValue(response)
    }

    fun editPost(postId: Int) = viewModelScope.launch {
        _post.value?.let { editPostUseCase(postId, it) }
    }

    // request를 만들어주는 역할
    fun makePost(
        title: String = "",
        category: Int = -1,
        content: String = "",
        departures: String? = "",
        arrivals: String? = "",
        headCount: Int? = 0,
        time: String? = "",
        openChattingUrl: String? = "",
        productUrl: String? = "",
        location: String? = "",
        product: String? = "",
        price: Int? = 0,
        end: Boolean = false
    ) {
        _post.value = post.value?.copy(
            title = title.ifEmpty { post.value?.title ?: "" },
            category = if (category == -1) post.value?.category ?: -1 else category,
            content = content.ifEmpty { post.value?.content ?: "" },
            departures = departures?.ifEmpty { post.value?.departures ?: "" },
            arrivals = arrivals?.ifEmpty { post.value?.arrivals ?: "" },
            headCount = if (headCount == 0) post.value?.headCount ?: 0 else headCount,
            time = time?.ifEmpty { post.value?.time ?: "" },
            openChattingUrl = openChattingUrl?.ifEmpty { post.value?.openChattingUrl ?: "" },
            productUrl = productUrl?.ifEmpty { post.value?.productUrl ?: "" },
            location = location?.ifEmpty { post.value?.location ?: "" },
            product = product?.ifEmpty { post.value?.product ?: "" },
            price = if (price == 0) post.value?.price ?: 0 else price,
            end = post.value?.end ?: end
        ) ?: BoardRequest(
            title,
            content,
            category,
            departures,
            arrivals,
            headCount,
            time,
            openChattingUrl,
            productUrl,
            location,
            product,
            price,
            end
        )
    }
}