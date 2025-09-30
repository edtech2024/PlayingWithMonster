package com.example.playingwithmonster.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.playingwithmonster.R
import com.example.playingwithmonster.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    // Creates a new fragment given parameters
    // MainFragment.newInstance()
    companion object {

        fun newInstance(): MainFragment {
            val fragmentMain = MainFragment()
            return fragmentMain
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnButtonClickListener{
        // This can be any number of events to be sent to the activity
        fun onOpeningHistory()

        fun onOpeningGame()
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerOpen: OnButtonClickListener? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerOpen = context as OnButtonClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate( inflater, container, false)

        initializationComposeViewMain()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerOpen = null
    }

    private fun initializationComposeViewMain() {
        binding.composeViewMain.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }


    @Composable
    fun Navigation() {
        MainScreen(modifier = Modifier,
            openHistory = { onButtonHistoryClicked() } ,
            startGame = { onButtonGameClicked() } )
    }

    @Composable
    fun MainScreen(modifier: Modifier = Modifier,
                   openHistory: () -> Unit,
                   startGame: () -> Unit) {

        Column(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
            //.clickable { navController.navigate("first") }
        ) {

            // call vm.method
            Button(onClick = { openHistory() }) {
                Text(text = getString(R.string.history))
            }

            Button(onClick = { startGame() }) {
                Text(text = getString(R.string.start_game))
            }
        }

    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onHistoryClicked(){
        listenerOpen?.onOpeningHistory()
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onGameClicked(){
        listenerOpen?.onOpeningGame()
    }

    private fun onButtonHistoryClicked(){
        onHistoryClicked()
    }

    private fun onButtonGameClicked(){
        onGameClicked()
    }
}