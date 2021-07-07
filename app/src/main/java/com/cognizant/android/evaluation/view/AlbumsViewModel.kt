package com.cognizant.android.evaluation.view


import androidx.lifecycle.ViewModel
import com.cognizant.android.evaluation.repository.AlbumsRepository
import com.cognizant.android.evaluation.testing.OpenForTesting
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@OpenForTesting
class AlbumsViewModel @Inject constructor( albumsRepository: AlbumsRepository):ViewModel() {
    val albums=albumsRepository.albums
}