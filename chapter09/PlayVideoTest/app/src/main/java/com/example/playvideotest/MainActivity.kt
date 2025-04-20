package com.example.playvideotest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import android.widget.Button
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    
    private lateinit var videoView: VideoView
    private lateinit var play: Button
    private lateinit var pause: Button
    private lateinit var replay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化视图
        videoView = findViewById(R.id.videoView)
        play = findViewById(R.id.play)
        pause = findViewById(R.id.pause)
        replay = findViewById(R.id.replay)

        // 初始化
        val uri = "android.resource://$packageName/${R.raw.video}".toUri()
        videoView.setVideoURI(uri)

        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
            Log.d(TAG, "video is playing")
        }

        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
        }

        replay.setOnClickListener {
            videoView.seekTo(0) // 重新播放从头开始
            videoView.start()
            Log.d(TAG, "video is replaying")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback() // 使用stopPlayback替代suspend
    }
}
