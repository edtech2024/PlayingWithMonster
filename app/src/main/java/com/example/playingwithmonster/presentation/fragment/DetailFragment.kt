package com.example.playingwithmonster.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playingwithmonster.CreatureApplication
import com.example.playingwithmonster.databinding.FragmentDetailBinding
import com.example.playingwithmonster.presentation.viewmodel.DetailViewModel
import dagger.assisted.AssistedInject
import javax.inject.Inject


class DetailFragment : Fragment() {

    // Creates a new fragment given parameters
    // DetailFragment.newInstance()
    companion object {

        private const val ID = "Id"
        private const val ATTACK = "Attack"
        private const val DEFENSE = "Defense"
        private const val MAX_HEALTH = "MaxHealth"
        private const val CURRENT_HEALTH = "CurrentHealth"
        private const val DAMAGE = "Damage"
        private const val NUMBER_OF_TIMES = "NumberOfTimes"

        fun newInstance(
            id: Int,
            attack: Int?,
            defense: Int?,
            maxHealth: Double?,
            currentHealth: Double?,
            damage: Double?,
            numberOfTimes: Int?
        ): DetailFragment {
            val fragmentDetail = DetailFragment()
            val args = Bundle()

            args.putInt(ID, id)
            args.putString(ATTACK, attack.toString())
            args.putString(DEFENSE, defense.toString())
            args.putString(MAX_HEALTH, maxHealth.toString())
            args.putString(CURRENT_HEALTH, currentHealth.toString())
            args.putString(DAMAGE, damage.toString())
            args.putString(NUMBER_OF_TIMES, numberOfTimes.toString())

            fragmentDetail.arguments = args
            return fragmentDetail
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemCloseListener{
        // This can be any number of events to be sent to the activity
        fun onCloseItem()
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerCreateUpdate: OnItemCloseListener? = null

    lateinit var detailViewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listenerCreateUpdate = context as OnItemCloseListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as CreatureApplication).appComponent.inject(this)

        detailViewModel = ViewModelProvider(this , object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailViewModel(getArguments(), listenerCreateUpdate) as T
            }
        }
        ).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentDetailBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.detailViewModel = detailViewModel

        initializationComposeViewDetail()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializationButton()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerCreateUpdate = null
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onCloseClicked() {
        listenerCreateUpdate?.onCloseItem()
    }

    private fun initializationComposeViewDetail() {
        binding.composeViewDetail.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //Navigation()
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    //fun DetailScreen(modifier: Modifier = Modifier, navController: NavController) {
    fun DetailScreen(modifier: Modifier = Modifier) {
        Row(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(onClick = {
                        onCloseClicked()
                    }) {
                        Text(text = "Завершить")
                    }

                }
            }
        }
    }

    sealed class Screen(var route: String) {
        object Detail : Screen("detail")
    }
}
