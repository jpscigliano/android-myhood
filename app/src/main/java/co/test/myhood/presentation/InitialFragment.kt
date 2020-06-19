package co.test.myhood.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.test.myhood.R
import dagger.hilt.android.AndroidEntryPoint

class InitialFragment : Fragment() {
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_initial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //is user logged?
        navController.navigate(InitialFragmentDirections.actionInitialFragmentToListHoodFragment())
        super.onViewCreated(view, savedInstanceState)
    }
}