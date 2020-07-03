package co.test.myhood.presentation.hoodList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.test.myhood.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_hood.button
import kotlinx.android.synthetic.main.fragment_list_hood.errorAnimation
import kotlinx.android.synthetic.main.fragment_list_hood.hoods_recyclerView
import kotlinx.android.synthetic.main.fragment_list_hood.hoods_swipeRefresh
import kotlinx.android.synthetic.main.fragment_list_hood.progress_circular
import kotlinx.android.synthetic.main.fragment_list_hood.progress_linear
import kotlinx.android.synthetic.main.fragment_list_hood.refresh

@AndroidEntryPoint
class HoodListFragment : Fragment() {

    private val viewModel: HoodListViewModel by viewModels()

    private val hoodListAdapter = HoodsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_hood, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            progress_linear.visibility = if (loading.linearLoading) VISIBLE else INVISIBLE
            progress_circular.visibility = if (loading.circularLoading) VISIBLE else INVISIBLE
            if (!loading.linearLoading && !loading.circularLoading) {
                hoods_swipeRefresh.isRefreshing = false
            }

        })
        viewModel.hoodsList.observe(viewLifecycleOwner, Observer { hoods ->
            hoodListAdapter.submitList(hoods)

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        viewModel.errorFetchingData.observe(viewLifecycleOwner, Observer {
            hoods_recyclerView.visibility = if (!it) VISIBLE else INVISIBLE
            errorAnimation.visibility = if (it) VISIBLE else INVISIBLE

        })

        button.setOnClickListener {
            viewModel.onAddClicked()
        }
        refresh.setOnClickListener {
            viewModel.onRefreshClicked()
        }

        hoods_recyclerView.adapter = hoodListAdapter
        hoods_recyclerView.layoutManager = LinearLayoutManager(context)
        hoods_recyclerView.itemAnimator = null
        hoods_swipeRefresh.setOnRefreshListener {
            viewModel.onRefreshClicked()
        }
    }
}