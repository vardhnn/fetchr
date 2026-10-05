# Fetchr

Paste a link → pick a quality → download video or audio. Ultra-minimal, retro-styled Android downloader (Jetpack Compose, AGP 9.4, Kotlin 2.4, Gradle 9.6).

## Build

```bash
# Requires JDK 17+ (Android Studio's bundled JBR works) and the Android SDK
gradle :app:assembleDebug
# → app/build/outputs/apk/debug/app-debug.apk
```

`local.properties` must point `sdk.dir` at your SDK.

### APK size

- **arm64-v8a only** — the bundled python/ffmpeg for yt-dlp is per-ABI, and
  arm64 covers virtually all Android 8+ phones. Old 32-bit phones and x86
  emulators aren't covered.
- **Per-ABI splits** — a build now produces `app-arm64-v8a-debug.apk` (the one
  to share) plus `app-universal-debug.apk`. Re-add an ABI in `ndk.abiFilters`
  and splits builds its APK automatically.
- **English-only resources** — AndroidX translation tables for other languages
  are stripped via `localeFilters`.

yt-dlp's remaining payload (the Python source in `raw_ytdlp.flat`) is
architecture-independent — one copy regardless of ABI — so once the ABI
filter excludes the x86 natives there is nothing further to strip.

## Features

- **Paste URL** (or share a link from YouTube/Instagram/TikTok straight into the app — it auto-analyzes)
- **Quality picker**: video streams sorted by resolution; DASH video-only streams get paired with best audio and **muxed via Media3 Transformer stream-copy** (no re-encode, near-instant)
- **Audio-only downloads** (SoundCloud/Bandcamp/YouTube audio tracks)
- **Fast parallel downloads**: OkHttp + HTTP Range, up to 8 concurrent segments per file; video + audio for a mux pair download **simultaneously**
- **Downloads page**: live progress, speed, cancel, muxing state, history
- Files land in `Movies/Fetchr` or `Music/Fetchr` (MediaStore, no storage permission needed)

## Platforms

| Platform   | Engine |
|------------|--------|
| YouTube    | NewPipeExtractor |
| SoundCloud | NewPipeExtractor |
| Bandcamp   | NewPipeExtractor |
| Instagram  | built-in scraper (og:video / embedded JSON) |
| TikTok     | built-in scraper (__UNIVERSAL_DATA__ / SIGI_STATE) |

Instagram/TikTok use unofficial web endpoints — they can break when those platforms change; YouTube depends on NewPipeExtractor keeping up.

## Design

"Precision Instrument Utility" theme — near-black `#121316` panels on `#0C0C0E`, brand orange `#E85638`, amber `#D97706`, teal `#008FAE`, Space Grotesk headlines, Plus Jakarta body, JetBrains Mono labels, 2dp-round stroke icons.
