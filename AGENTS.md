# PANDOOS ANDROID — MASTER CONTEXT FILE
# This file is everything. Architecture + Setup + Code + Rules + Roadmap.
# Place this as AGENTS.md in the root of pandoos-android/.
# Read this COMPLETELY before doing anything in this codebase.

---

## 🐼 What Is This?

**pandoos-android** is the Android app for the Pandoos music ecosystem.

- **Base**: Forked from `maxrave-dev/Pandoos` (GPL-3.0)
- **License**: GPL-3.0 — app must remain open source. Do NOT remove LICENSE file.
- **App ID**: `com.pandoos.music`
- **App Name**: Pandoos
- **GitHub**: `github.com/Rajvansh-1/pandoos-android`
- **Tagline**: Feel the music
- **Mascot**: Panda (emotional, animated)

This is a **separate repo** from `pandoos/` (web + desktop).
Both connect to the **same Supabase project**.

---

## 🌐 Pandoos Ecosystem (All Platforms)

| Platform | Repo | Stack | Status |
|---|---|---|---|
| Web + Desktop | `pandoos/` | Vite + React + Electron | ✅ Active |
| Android | `pandoos-android/` (THIS REPO) | Kotlin + Jetpack Compose + Media3 | 🚧 In Progress |
| iOS | — | SwiftUI | 📅 Future |

---

## 🔧 SETUP — Step by Step (Run Once)

### Step 1 — Clone Pandoos Locally

```powershell
cd C:\Users\rajva\OneDrive\Desktop
git clone https://github.com/maxrave-dev/Pandoos.git pandoos-android
cd pandoos-android
```

---

### Step 2 — Sever All Ties to Pandoos Remote

```powershell
# Remove Pandoos's remote completely
git remote remove origin

# Verify it's gone — should return nothing
git remote -v
```

---

### Step 3 — Connect to YOUR GitHub

> First create the repo on GitHub:
> github.com → New Repository → Name: `pandoos-android` → Public → NO readme → Create

```powershell
git remote add origin https://github.com/Rajvansh-1/pandoos-android.git
git branch -M main
git push -u origin main
```

---

### Step 4 — Tag the Base

```powershell
# Required for GPL-3.0 attribution — marks where Pandoos diverges from Pandoos
git tag -a v0.0.1-pandoos-base -m "Base: forked from Pandoos by maxrave-dev (GPL-3.0)"
git push origin --tags
```

---

### Step 5 — Open in Android Studio

```
File → Open → select pandoos-android/
Wait for Gradle sync (first time: 5-10 mins)
Run on device/emulator BEFORE touching anything
Confirm Pandoos runs correctly as the starting point
```

---

### Step 6 — Rename App ID Everywhere

In Android Studio: **Edit → Find → Replace in Files**

```
Search:  com.pandoos.music
Replace: com.pandoos.music
```

```
Search:  pandoos
Replace: pandoos
(Case-sensitive: OFF)
```

```
Search:  Pandoos
Replace: Pandoos
(Case-sensitive: ON)
```

---

### Step 7 — Update Key Files Manually

**`app/build.gradle.kts`**
```kotlin
defaultConfig {
    applicationId = "com.pandoos.music"
    versionName = "1.0.0"
    // ...
}
```

**`app/src/main/res/values/strings.xml`**
```xml
<string name="app_name">Pandoos</string>
```

**`app/src/main/AndroidManifest.xml`**
```xml
android:label="Pandoos"
```

Replace all launcher icons in `res/mipmap-*/` with Pandoos panda mascot assets.

---

### Step 8 — Apply Pandoos Color Palette

**`app/src/main/res/values/colors.xml`**
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="md_theme_primary">#9C6ADE</color>
    <color name="md_theme_onPrimary">#FFFFFF</color>
    <color name="md_theme_primaryContainer">#2D1B69</color>
    <color name="md_theme_onPrimaryContainer">#E8D5FF</color>
    <color name="md_theme_secondary">#FF6B9D</color>
    <color name="md_theme_onSecondary">#FFFFFF</color>
    <color name="md_theme_secondaryContainer">#4A1628</color>
    <color name="md_theme_onSecondaryContainer">#FFD6E5</color>
    <color name="md_theme_background">#0A0A0F</color>
    <color name="md_theme_onBackground">#F0EEFF</color>
    <color name="md_theme_surface">#13111C</color>
    <color name="md_theme_onSurface">#F0EEFF</color>
    <color name="md_theme_surfaceVariant">#1E1A2E</color>
    <color name="md_theme_onSurfaceVariant">#C8BFE0</color>
    <color name="md_theme_surfaceContainer">#1A1726</color>
    <color name="md_theme_surfaceContainerHigh">#221F32</color>
    <color name="md_theme_surfaceContainerHighest">#2A2740</color>
    <color name="md_theme_error">#FF5370</color>
    <color name="md_theme_onError">#FFFFFF</color>
</resources>
```

**`Color.kt`** (Compose)
```kotlin
val PandoosPrimary       = Color(0xFF9C6ADE)
val PandoosSecondary     = Color(0xFFFF6B9D)
val PandoosBackground    = Color(0xFF0A0A0F)
val PandoosSurface       = Color(0xFF13111C)
val PandoosSurfaceVar    = Color(0xFF1E1A2E)
val PandoosOnBackground  = Color(0xFFF0EEFF)
val PandoosOnSurface     = Color(0xFFF0EEFF)
val PandoosError         = Color(0xFFFF5370)
val PandoosAccent        = Color(0xFF7B5EA7)
```

---

### Step 9 — Add Supabase Dependencies

**`gradle/libs.versions.toml`**
```toml
[versions]
supabase = "3.0.0"
ktor = "3.0.0"

[libraries]
supabase-postgrest = { module = "io.github.jan-tennert.supabase:postgrest-kt", version.ref = "supabase" }
supabase-auth      = { module = "io.github.jan-tennert.supabase:auth-kt", version.ref = "supabase" }
supabase-realtime  = { module = "io.github.jan-tennert.supabase:realtime-kt", version.ref = "supabase" }
ktor-android       = { module = "io.ktor:ktor-client-android", version.ref = "ktor" }
```

**`app/build.gradle.kts`** → inside `dependencies {}`
```kotlin
implementation(libs.supabase.postgrest)
implementation(libs.supabase.auth)
implementation(libs.supabase.realtime)
implementation(libs.ktor.android)
```

---

### Step 10 — Add Supabase Keys

**`local.properties`** (already gitignored — never commit this)
```properties
SUPABASE_URL=https://your-project.supabase.co
SUPABASE_ANON_KEY=your-anon-key-here
```

**`app/build.gradle.kts`** → inside `defaultConfig {}`
```kotlin
buildConfigField("String", "SUPABASE_URL", "\"${localProperties["SUPABASE_URL"]}\"")
buildConfigField("String", "SUPABASE_ANON_KEY", "\"${localProperties["SUPABASE_ANON_KEY"]}\"")
```

---

### Step 11 — Commit the Pandoos Identity

```powershell
git add -A
git commit -m "feat: rebrand to Pandoos — app ID, name, colors, Supabase dependencies"
git push
```

---

## 🗄️ Supabase Schema (AUTHORITATIVE)

Same Supabase project as web + desktop. Never change column names.

```
liked_songs
  id         uuid PK
  user_id    text  ← Supabase auth UUID
  video_id   text  ← YouTube videoId (universal key)
  title      text
  artist     text
  album_art  text
  duration   integer (seconds)
  liked_at   timestamptz
  UNIQUE(user_id, video_id)

playlists
  id          uuid PK
  user_id     text
  name        text
  description text
  cover_url   text
  is_public   boolean
  track_count integer  ← auto-updated by DB trigger
  created_at  timestamptz
  updated_at  timestamptz

playlist_tracks
  id          uuid PK
  playlist_id uuid FK → playlists(id) CASCADE
  video_id    text
  title       text
  artist      text
  album_art   text
  duration    integer
  position    bigint   ← ordering field, not array index
  added_at    timestamptz

followed_artists
  id            uuid PK
  user_id       text
  artist_id     text  ← YTM browseId
  name          text
  thumbnail_url text
  followed_at   timestamptz
  UNIQUE(user_id, artist_id)

now_playing   ← Cross-device realtime sync
  user_id     text PK  ← one row per user, UPSERT always
  video_id    text
  title       text
  artist      text
  album_art   text
  is_playing  boolean
  progress    float  (0.0 – 1.0)
  device_name text   ← "Android" for this app
  updated_at  timestamptz
```

Realtime enabled on all tables.

---

## 📦 Supabase Kotlin Code

### SupabaseClient.kt
```kotlin
package com.pandoos.music.data.remote.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import com.pandoos.music.BuildConfig
import javax.inject.Singleton

@Singleton
object PandoosSupabase {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_ANON_KEY
    ) {
        install(Auth)
        install(Postgrest)
        install(Realtime)
    }
}
```

---

### Data Models
```kotlin
package com.pandoos.music.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PandoosUser(
    val id: String,
    val email: String? = null,
    val username: String? = null,
    val avatar_url: String? = null,
    val created_at: String? = null
)

@Serializable
data class LikedSong(
    val id: String,
    val user_id: String,
    val video_id: String,
    val title: String,
    val artist: String,
    val album_art: String? = null,
    val duration: Int = 0,
    val liked_at: String
)

@Serializable
data class PandoosPlaylist(
    val id: String,
    val user_id: String,
    val name: String,
    val description: String? = null,
    val cover_url: String? = null,
    val is_public: Boolean = false,
    val track_count: Int = 0,
    val created_at: String,
    val updated_at: String
)

@Serializable
data class PlaylistTrack(
    val id: String,
    val playlist_id: String,
    val video_id: String,
    val title: String,
    val artist: String,
    val album_art: String? = null,
    val duration: Int = 0,
    val position: Long,
    val added_at: String
)

@Serializable
data class NowPlaying(
    val user_id: String,
    val video_id: String,
    val title: String,
    val artist: String,
    val album_art: String? = null,
    val is_playing: Boolean,
    val progress: Float,
    val device_name: String = "Android",
    val updated_at: String
)
```

---

### PlaylistRepository.kt
```kotlin
package com.pandoos.music.data.remote.supabase

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import com.pandoos.music.data.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaylistRepository @Inject constructor() {

    private val db = PandoosSupabase.client

    suspend fun getLikedSongs(userId: String): List<LikedSong> =
        db.from("liked_songs").select {
            filter { eq("user_id", userId) }
            order("liked_at", Order.DESCENDING)
        }.decodeList()

    suspend fun likeSong(userId: String, videoId: String, title: String,
                         artist: String, albumArt: String?, duration: Int) {
        db.from("liked_songs").upsert(
            mapOf(
                "user_id" to userId,
                "video_id" to videoId,
                "title" to title,
                "artist" to artist,
                "album_art" to albumArt,
                "duration" to duration,
                "liked_at" to java.time.Instant.now().toString()
            )
        )
    }

    suspend fun unlikeSong(userId: String, videoId: String) {
        db.from("liked_songs").delete {
            filter {
                eq("user_id", userId)
                eq("video_id", videoId)
            }
        }
    }

    suspend fun getUserPlaylists(userId: String): List<PandoosPlaylist> =
        db.from("playlists").select {
            filter { eq("user_id", userId) }
            order("updated_at", Order.DESCENDING)
        }.decodeList()

    suspend fun getPlaylistTracks(playlistId: String): List<PlaylistTrack> =
        db.from("playlist_tracks").select {
            filter { eq("playlist_id", playlistId) }
            order("position", Order.ASCENDING)
        }.decodeList()

    suspend fun createPlaylist(userId: String, name: String, description: String = ""): PandoosPlaylist =
        db.from("playlists").insert(
            mapOf(
                "user_id" to userId,
                "name" to name,
                "description" to description,
                "is_public" to false
            )
        ).decodeSingle()

    // now_playing: always UPSERT (one row per user)
    suspend fun updateNowPlaying(userId: String, videoId: String, title: String,
                                  artist: String, albumArt: String?,
                                  isPlaying: Boolean, progress: Float) {
        db.from("now_playing").upsert(
            mapOf(
                "user_id" to userId,
                "video_id" to videoId,
                "title" to title,
                "artist" to artist,
                "album_art" to albumArt,
                "is_playing" to isPlaying,
                "progress" to progress,
                "device_name" to "Android",
                "updated_at" to java.time.Instant.now().toString()
            )
        )
    }

    // Check if user was listening on web/desktop — offer "continue listening"
    suspend fun getLastSession(userId: String): NowPlaying? =
        db.from("now_playing").select {
            filter { eq("user_id", userId) }
        }.decodeSingleOrNull()
}
```

---

### AuthRepository.kt
```kotlin
package com.pandoos.music.data.remote.supabase

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import com.pandoos.music.data.model.PandoosUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    private val auth = PandoosSupabase.client.auth

    suspend fun signInWithGoogle(idToken: String): PandoosUser? {
        auth.signInWith(IDToken) {
            this.idToken = idToken
            provider = Google
        }
        return getCurrentUser()
    }

    suspend fun getCurrentUser(): PandoosUser? {
        val user = auth.currentSessionOrNull()?.user ?: return null
        return PandoosUser(
            id = user.id,
            email = user.email,
            username = user.userMetadata?.get("full_name")?.toString()?.trim('"'),
            avatar_url = user.userMetadata?.get("avatar_url")?.toString()?.trim('"'),
            created_at = user.createdAt.toString()
        )
    }

    fun isLoggedIn(): Boolean = auth.currentSessionOrNull() != null

    suspend fun signOut() = auth.signOut()
}
```

---

### SyncViewModel.kt
```kotlin
package com.pandoos.music.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandoos.music.data.model.*
import com.pandoos.music.data.remote.supabase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<PandoosUser?>(null)
    val currentUser: StateFlow<PandoosUser?> = _currentUser

    private val _playlists = MutableStateFlow<List<PandoosPlaylist>>(emptyList())
    val playlists: StateFlow<List<PandoosPlaylist>> = _playlists

    private val _likedSongs = MutableStateFlow<List<LikedSong>>(emptyList())
    val likedSongs: StateFlow<List<LikedSong>> = _likedSongs

    private val _lastSession = MutableStateFlow<NowPlaying?>(null)
    val lastSession: StateFlow<NowPlaying?> = _lastSession  // for "continue listening"

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    init { checkAuthAndSync() }

    private fun checkAuthAndSync() = viewModelScope.launch {
        _currentUser.value = authRepository.getCurrentUser()
        _currentUser.value?.let { syncAll(it.id) }
    }

    fun syncAll(userId: String) = viewModelScope.launch {
        _isSyncing.value = true
        try {
            _playlists.value = playlistRepository.getUserPlaylists(userId)
            _likedSongs.value = playlistRepository.getLikedSongs(userId)
            _lastSession.value = playlistRepository.getLastSession(userId)
                ?.takeIf { it.device_name != "Android" }  // only show if from another platform
        } catch (e: Exception) {
            // Network error — show Room DB cached data (fallback)
        } finally {
            _isSyncing.value = false
        }
    }

    fun likeSong(videoId: String, title: String, artist: String,
                 albumArt: String?, duration: Int) {
        val userId = _currentUser.value?.id ?: return
        viewModelScope.launch {
            playlistRepository.likeSong(userId, videoId, title, artist, albumArt, duration)
            syncAll(userId) // refresh
        }
    }

    fun isLiked(videoId: String): Boolean =
        _likedSongs.value.any { it.video_id == videoId }

    fun updateNowPlaying(videoId: String, title: String, artist: String,
                          albumArt: String?, isPlaying: Boolean, progress: Float) {
        val userId = _currentUser.value?.id ?: return
        viewModelScope.launch {
            playlistRepository.updateNowPlaying(userId, videoId, title,
                artist, albumArt, isPlaying, progress)
        }
    }
}
```

---

## 🏗️ Full Project Structure

```
app/src/main/kotlin/com/pandoos/music/
├── ui/
│   ├── MainActivity.kt
│   ├── theme/
│   │   ├── PandoosTheme.kt
│   │   ├── Color.kt          ← Pandoos palette above
│   │   └── Type.kt
│   ├── screens/
│   │   ├── home/HomeScreen.kt
│   │   ├── search/SearchScreen.kt
│   │   ├── library/LibraryScreen.kt
│   │   ├── nowplaying/
│   │   │   ├── NowPlayingScreen.kt  ← Pandoos emotional design
│   │   │   └── MiniPlayer.kt
│   │   ├── playlist/PlaylistScreen.kt
│   │   └── mood/MoodScreen.kt       ← NEW: Panda Emotions
│   └── components/
│       ├── SongCard.kt
│       ├── AlbumArt.kt
│       └── PandoosTopBar.kt
├── service/                         ← FROM SIMPMUSIC — DO NOT TOUCH
│   ├── MusicService.kt
│   ├── SimpleMediaServiceHandler.kt
│   └── PlayerConnection.kt
├── viewmodel/
│   ├── MusicViewModel.kt            ← FROM SIMPMUSIC — extend only
│   ├── HomeViewModel.kt
│   ├── SearchViewModel.kt
│   ├── LibraryViewModel.kt
│   ├── SyncViewModel.kt             ← NEW (code above)
│   └── MoodViewModel.kt             ← NEW
├── data/
│   ├── remote/
│   │   ├── innertube/InnerTube.kt   ← FROM SIMPMUSIC — DO NOT TOUCH
│   │   └── supabase/                ← NEW (code above)
│   │       ├── PandoosSupabase.kt
│   │       ├── AuthRepository.kt
│   │       ├── PlaylistRepository.kt
│   │       └── SyncRepository.kt
│   ├── local/
│   │   ├── room/                    ← FROM SIMPMUSIC — extend only
│   │   └── datastore/
│   └── model/                       ← Data classes above
└── di/
    ├── DatabaseModule.kt
    ├── NetworkModule.kt
    └── SupabaseModule.kt            ← NEW
```

---

## 🚫 DO NOT TOUCH — Pandoos Core (It Works, Leave It)

| File/Component | Why |
|---|---|
| `MusicService.kt` | Background audio, MediaSession — production tested |
| `SimpleMediaServiceHandler.kt` | ExoPlayer wrapper — don't rewrite |
| `PlayerConnection.kt` | Player state bridge — works |
| `InnerTube.kt` | YouTube Music API — tested by thousands |
| `DownloadUtils.kt` | Offline cache — works |
| `LyricsManager.kt` | LRC sync — works |
| `QueueManager.kt` | Queue logic — EXTEND only, don't replace |
| Room DB entities | EXTEND only — add Supabase sync on top |

---

## 🐼 Pandoos-Unique Features (Build These On Top)

### 1. Cross-Platform Continue Listening ⭐ (Week 2)
```
On app open:
  → Check now_playing table for user's last session
  → If device_name = "Web" or "Desktop" (not "Android"):
  → Show dialog: "Continue [Song] from where you left off on Web?"
  → Resume at exact progress position
```
Uses: `SyncViewModel.lastSession`

### 2. Panda Moods / Emotions 🎭 (Week 3)
```
Home screen shows mood selector:
  😤 Energized  😌 Chill  😢 Melancholic  🔥 Hype
  💕 Romantic   🌙 Late Night  🧘 Focus  🎉 Party

Each mood → seeds a YT Music radio/playlist via InnerTube
Mood saved to Supabase user_preferences table
Panda mascot animates to match the mood
```

### 3. Panda Now Playing 🐼 (Week 3-4)
```
Full-screen player extras:
  → Animated panda mascot (bobs fast for hype, slow for chill, sleeps for ballads)
  → Dynamic gradient background from album art color extraction
  → "Vibe Check" button — did this match your mood? (trains recommendations)
```

### 4. Smart Queue 🧠 (Week 4-5)
```
Extends Pandoos's QueueManager:
  → Considers: current BPM/energy, time of day, user history
  → "Vibe Lock" — keep same energy level in queue
  → "Vibe Shift" — intentional mood transition
```

### 5. Pandoos Wrapped 📊 (Month 2)
```
Monthly stats from liked_songs + now_playing:
  → Most played artists, songs, moods
  → Cross-platform listening time breakdown
  → Shareable card
```

---

## 🔄 Sync Flow (How Everything Connects)

```
User plays a song on Android:

  ExoPlayer plays stream
  → MusicViewModel receives state
  → SyncViewModel.updateNowPlaying() called every 5s
  → UPSERT to Supabase now_playing (device_name = "Android")
  → Web/Desktop sees it instantly via Realtime subscription

User likes a song:

  Tap heart in NowPlayingScreen
  → SyncViewModel.likeSong()
  → Optimistic UI update immediately
  → PlaylistRepository.likeSong() → Supabase liked_songs INSERT
  → Web reflects it on next load or via Realtime

User opens Android after using Web:

  App launch → SyncViewModel.init()
  → getLastSession() checks now_playing table
  → If last session was from "Web" or "Desktop":
  → Show "Continue Listening" dialog
  → Resume at exact progress
```

---

## ⚠️ Critical Rules — Always Follow

1. **App ID = `com.pandoos.music`** — never revert to `com.pandoos.music`
2. **App name = "Pandoos"** — never "Pandoos"
3. **LICENSE file stays** — GPL-3.0 legal requirement
4. **`video_id` = YouTube videoId** — universal key across ALL platforms and ALL Supabase tables
5. **`user_id` = Supabase auth UUID** (text, not integer)
6. **`now_playing` uses UPSERT** — one row per user, `user_id` is the primary key
7. **All Supabase tables have RLS disabled** — always filter by `user_id` manually in every query
8. **Never put Supabase service role key in Android app** — anon key only
9. **Never touch Pandoos audio engine** — ExoPlayer/Media3/InnerTube/MusicService are untouchable
10. **`position` in `playlist_tracks` is bigint** — use for ordering, never use array index

---

## 🚀 Running the App

```powershell
# Debug build + install
./gradlew installDebug

# Or in Android Studio:
# Run → Select device → ▶ Run
```

### Prerequisites for first run:
- `local.properties` has `SUPABASE_URL` and `SUPABASE_ANON_KEY`
- `google-services.json` is in `app/` folder (for Google Auth)
- Android Studio Hedgehog or newer
- JDK 17+
- Android SDK 34+

---

## 📋 Execution Roadmap

```
Week 1  → Git setup, rename (Steps 1-6), colors, splash screen
Week 2  → Supabase auth (Google Sign-In working end-to-end)
          Playlists + liked songs sync
          Cross-platform continue listening
Week 3  → Pandoos Now Playing UI (emotional design)
          Panda Moods screen
Week 4  → Smart Queue foundation
          Offline downloads polish
Week 5  → Stats / history tracking
          Performance + polish
Month 2 → Pandoos Wrapped
          Android widget
          Android Auto
```

---

## 🔗 Related Resources

| Resource | Location |
|---|---|
| Web + Desktop repo | `C:\Users\rajva\OneDrive\Desktop\pandoos\` |
| Web AGENTS.md | `pandoos/AGENTS.md` |
| Supabase schema | `pandoos/SUPABASE_SETUP_FINAL.sql` |
| Pandoos original | `github.com/maxrave-dev/Pandoos` |
| Supabase project | Your Supabase dashboard |
