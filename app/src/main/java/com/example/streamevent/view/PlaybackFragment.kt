package com.example.streamevent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streamevent.databinding.FragmentPlaybackBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaybackFragment : Fragment() {
    private lateinit var binding: FragmentPlaybackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlaybackBinding.inflate(inflater, container, false)

        return binding.root
    }
}