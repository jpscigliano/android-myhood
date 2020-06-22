package co.test.myhood.presentation.hoodList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import co.test.myhood.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_hood.button
import kotlinx.android.synthetic.main.fragment_list_hood.hood
import kotlinx.android.synthetic.main.fragment_list_hood.hoodFlow

@AndroidEntryPoint
class HoodListFragment : Fragment() {

    private val viewModel: HoodListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_hood, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hoodListLiveData.observe(viewLifecycleOwner, Observer {
            hood.text = it.joinToString { it.name }

        })

        button.setOnClickListener {
            viewModel.onAddClicked()
        }
    }
}