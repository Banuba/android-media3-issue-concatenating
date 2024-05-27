package com.example.media3sample

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ConcatenatingMediaSource2
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import java.io.File


@SuppressLint("UnsafeOptInUsageError")
class MainActivity : AppCompatActivity() {
    private var player: ExoPlayer? = null
    private lateinit var surfaceView: SurfaceView
    private var surface: Surface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceView = findViewById(R.id.surface_view)
        initializePlayer()
        setupSurfaceView()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(DefaultMediaSourceFactory(this))
            .build()

        player?.setMediaSource(createMediaSource())
        player?.prepare()
        player?.repeatMode = REPEAT_MODE_ALL
        player?.play()
    }

    private fun setupSurfaceView() {
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                surface = holder.surface
                player?.setVideoSurface(surface)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                player?.setVideoSurface(null)
                surface = null
            }
        })
    }

    private fun createMediaSource(): MediaSource {
        val listOfMediaItems = createMediaItemsList()

        val concatenatingMediaSource2Builder = ConcatenatingMediaSource2.Builder()
        concatenatingMediaSource2Builder.useDefaultMediaSourceFactory(this)

        for (item in listOfMediaItems) {
            concatenatingMediaSource2Builder.add(item)
        }

        return concatenatingMediaSource2Builder.build()
    }

    private fun createMediaItemsList(): List<MediaItem> {
        val uri = Uri.fromFile(File("//android_asset/1.mp4"))

        return listOf(
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(0)
                        .setEndPositionMs(300)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(300)
                        .setEndPositionMs(700)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(700)
                        .setEndPositionMs(2700)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(2700)
                        .setEndPositionMs(3000)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(3000)
                        .setEndPositionMs(3250)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(3300)
                        .setEndPositionMs(3700)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(3880)
                        .setEndPositionMs(4500)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(4500)
                        .setEndPositionMs(4700)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(4800)
                        .setEndPositionMs(5100)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(5200)
                        .setEndPositionMs(5500)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(5500)
                        .setEndPositionMs(5800)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(5800)
                        .setEndPositionMs(6250)
                        .build()
                )
                .build(),
            MediaItem.Builder()
                .setUri(uri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(6250)
                        .setEndPositionMs(6700)
                        .build()
                )
                .build()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}