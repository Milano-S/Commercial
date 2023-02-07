package com.exclr8.n1corporate.Fragments.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.exclr8.n1corporate.BuildConfig
import com.exclr8.n1corporate.Helpers.*
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
    lateinit var rootView: View
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater)
        rootView = binding.root

        binding.webView.settings.javaScriptEnabled = true
        binding.txtvVersionNumber.text = "Version: " + BuildConfig.VERSION_NAME

        binding.btnCloseWebView.setOnClickListener {
            binding.webView.loadUrl("about:blank")
            binding.cardWebView.gone()
            Helper().animateImage(binding.cardWebView, Direction.DOWN, requireContext())
        }

        binding.btnTermsAndConditions.setOnClickListener {
            binding.progWebView.visible()
            binding.webView.loadUrl(PreferenceHelper().getBaseURLString() + PreferenceHelper().getTermsURL())
            binding.cardWebView.visible()

            Helper().animateImage(binding.cardWebView, Direction.UP, requireContext())
        }

        binding.btnPrivacyPolicy.setOnClickListener {
            binding.progWebView.visible()
            binding.webView.loadUrl(PreferenceHelper().getBaseURLString() + PreferenceHelper().getPrivacyURL())
            binding.cardWebView.visible()
            Helper().animateImage(binding.cardWebView, Direction.UP, requireContext())
        }

        binding.btnReturnsPolicy.setOnClickListener {
            binding.progWebView.visible()
            binding.webView.loadUrl(PreferenceHelper().getBaseURLString() + PreferenceHelper().getReturnURL())
            binding.cardWebView.visible()
            Helper().animateImage(binding.cardWebView, Direction.UP, requireContext())
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.progWebView.gone()
            }
        }

        return rootView
    }

}