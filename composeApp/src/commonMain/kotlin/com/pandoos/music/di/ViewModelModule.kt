package com.pandoos.music.di

import com.pandoos.music.viewModel.AlbumViewModel
import com.pandoos.music.viewModel.AnalyticsViewModel
import com.pandoos.music.viewModel.ArtistViewModel
import com.pandoos.music.viewModel.HomeViewModel
import com.pandoos.music.viewModel.LibraryDynamicPlaylistViewModel
import com.pandoos.music.viewModel.LibraryViewModel
import com.pandoos.music.viewModel.LocalPlaylistViewModel
import com.pandoos.music.viewModel.LogInViewModel
import com.pandoos.music.viewModel.MoodViewModel
import com.pandoos.music.viewModel.MoreAlbumsViewModel
import com.pandoos.music.viewModel.NotificationViewModel
import com.pandoos.music.viewModel.NowPlayingBottomSheetViewModel
import com.pandoos.music.viewModel.PlaylistViewModel
import com.pandoos.music.viewModel.PodcastViewModel
import com.pandoos.music.viewModel.RecentlySongsViewModel
import com.pandoos.music.viewModel.SearchViewModel
import com.pandoos.music.viewModel.SettingsViewModel
import com.pandoos.music.viewModel.SharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        single {
            SharedViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        single {
            SearchViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            NowPlayingBottomSheetViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryDynamicPlaylistViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AlbumViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            HomeViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            SettingsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ArtistViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            PlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LogInViewModel(
                get(),
            )
        }
        viewModel {
            PodcastViewModel(
                get(),
            )
        }
        viewModel {
            MoreAlbumsViewModel(
                get(),
            )
        }
        viewModel {
            RecentlySongsViewModel(
                get(),
            )
        }
        viewModel {
            LocalPlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            NotificationViewModel(
                get(),
            )
        }
        viewModel {
            MoodViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AnalyticsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
    }