package com.eniskaner.satellites.ui.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.feature.satellites.R
import com.eniskaner.feature.satellites.databinding.FragmentSatellitesListBinding
import com.eniskaner.satellitecommunicator.SatelliteDetailQualifier
import com.eniskaner.satellitecommunicator.SatelliteFeatureCommunicator
import com.eniskaner.satellites.navigation.SatellitesNavGraph
import com.eniskaner.satellites.ui.adapter.SatelliteClickListener
import com.eniskaner.satellites.ui.adapter.SatelliteListAdapter
import com.eniskaner.satellites.ui.event.SatelliteEvent
import com.eniskaner.satellites.ui.viewmodel.SatellitesSearchViewModel
import com.eniskaner.satellites.ui.viewmodel.SatellitesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatellitesListFragment : Fragment(), SatelliteClickListener {

    private lateinit var binding: FragmentSatellitesListBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    @SatelliteDetailQualifier
    lateinit var satelliteDetailCommunicator: SatelliteFeatureCommunicator

    private val satelliteListAdapter by lazy { SatelliteListAdapter(this@SatellitesListFragment) }

    private val satellitesViewModel: SatellitesViewModel by viewModels()

    private val searchViewModel: SatellitesSearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatellitesListBinding.inflate(inflater, container, false)
        activity?.window?.let { window ->
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getSatelliteListData()
        setUIWithSearchSatellites()
    }

    private fun initRecyclerView()  {
        binding.rvSatelliteList.apply {
            adapter = satelliteListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                private val paint = Paint().apply {
                    color = ContextCompat.getColor(requireContext(), R.color.eyoj_gray_400)
                    strokeWidth = resources.getDimension(R.dimen.divider_height)
                }

                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    val left = parent.paddingLeft + resources.getDimension(R.dimen.margin_large).toInt()
                    val right = parent.width - parent.paddingRight - resources.getDimension(R.dimen.margin_large).toInt()

                    for (i in 0 until parent.childCount - 1) {
                        val view = parent.getChildAt(i)
                        val top = view.bottom + resources.getDimension(R.dimen.margin_small) / 2
                        c.drawLine(left.toFloat(), top, right.toFloat(), top, paint)
                    }
                }

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val verticalMargin = resources.getDimensionPixelSize(R.dimen.margin_small)
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.top = verticalMargin
                    }
                    outRect.bottom = verticalMargin
                }
            })
        }
    }

    private fun getSatelliteListData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                satellitesViewModel.stateListUIState.collect {satellitesListUIState ->
                    setProgressBar(isLoading = satellitesListUIState.isLoading)
                    satelliteListAdapter.submitList(satellitesListUIState.satelliteList)
                }
            }
        }
    }

    private fun setUIWithSearchSatellites() = with(binding) {
        tvSearchSatellite.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchingSatelliteText = s.toString()
                searchViewModel.onEvent(SatelliteEvent.SearchSatellites(query = searchingSatelliteText))
                searchSatellite(query = searchingSatelliteText)
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun searchSatellite(query: String) {
        launchAndRepeatWithViewLifecycle {
            launch {
                searchViewModel.stateSearchUIState.collect { searchUIState ->
                    if (query.isEmpty()) {
                        getSatelliteListData()
                    } else {
                        setProgressBar(isLoading = searchUIState.isLoading)
                        satelliteListAdapter.submitList(
                            searchUIState.searchingSatelliteList.filter {
                                it.name.contains(query.lowercase().trim(), ignoreCase = true)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setProgressBar(isLoading: Boolean) {
        binding.progressBarSatellites.isVisible = isLoading
    }

    override fun satelliteClicked(id: Int) {
        satelliteDetailCommunicator.launchSatelliteFeature(
            SatelliteFeatureCommunicator.SatelliteFeatureArgs(
                previousRoute = SatellitesNavGraph.ROUTE,
                satelliteId = id
            )
        )
    }


}
