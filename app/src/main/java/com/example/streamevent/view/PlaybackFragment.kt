package com.example.streamevent.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.streamevent.databinding.FragmentPlaybackBinding
import com.example.streamevent.model.dto.Event
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaybackFragment : Fragment() {
    private lateinit var binding: FragmentPlaybackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlaybackBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventDetails = PlaybackFragmentArgs.fromBundle(requireArguments())
        binding.eventTitle.text = eventDetails.eventData.title
        loadVideo(eventDetails.eventData)
//        initializePlayer(eventDetails.eventData)
    }

    private fun loadVideo(event: Event) {
        val uri = Uri.parse(event.videoUrl)
        binding.eventVideo.setVideoURI(uri)

        val mediaController = MediaController(context)

        binding.eventVideo.setMediaController(mediaController)
        binding.eventVideo.start()
    }

//    private var currentItem = 0
//    private var playbackPosition = 0L
//    private var player: ExoPlayer? = null
//    private var playWhenReady = true
//    private fun initializePlayer(event: Event) {
//        player = ExoPlayer.Builder(requireContext())
//            .build()
//            .also { exoPlayer ->
//                binding.eventVideo.player = exoPlayer
//                val mediaItem = MediaItem.fromUri(event.videoUrl)
//                exoPlayer.setMediaItem(mediaItem)
//                exoPlayer.playWhenReady = playWhenReady
//                exoPlayer.seekTo(currentItem, playbackPosition)
//                exoPlayer.prepare()
//            }
//    }

//    private fun releasePlayer() {
//        player?.let { exoPlayer ->
//            playbackPosition = exoPlayer.currentPosition
//            currentItem = exoPlayer.currentMediaItemIndex
//            playWhenReady = exoPlayer.playWhenReady
//            exoPlayer.release()
//        }
//        player = null
//    }

//    @SuppressLint("InlinedApi")
//    private fun hideSystemUi() {
//        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
//        WindowInsetsControllerCompat(requireActivity().window, binding.eventVideo).let { controller ->
//            controller.hide(WindowInsetsCompat.Type.systemBars())
//            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }
//    }

//    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
//    override fun onStart() {
//        super.onStart()
//        if (Util.SDK_INT > 23) {
////            initializePlayer()
//        }
//    }
//
//    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
//    override fun onResume() {
//        super.onResume()
//        hideSystemUi()
//        if ((Util.SDK_INT <= 23 || player == null)) {
////            initializePlayer()
//        }
//    }
}