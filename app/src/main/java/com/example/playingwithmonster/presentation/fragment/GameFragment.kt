package com.example.playingwithmonster.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playingwithmonster.CreatureApplication
import com.example.playingwithmonster.databinding.FragmentGameBinding
import com.example.playingwithmonster.domain.converter.StringValue
import com.example.playingwithmonster.presentation.uistate.MonsterUI
import com.example.playingwithmonster.presentation.uistate.PlayerUI
import com.example.playingwithmonster.presentation.viewmodel.GameViewModel
import javax.inject.Inject
import com.example.playingwithmonster.R

class GameFragment : Fragment() {

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
        ): GameFragment {
            val fragmentGame = GameFragment()
            val args = Bundle()

            args.putInt(ID, id)
            args.putString(ATTACK, attack.toString())
            args.putString(DEFENSE, defense.toString())
            args.putString(MAX_HEALTH, maxHealth.toString())
            args.putString(CURRENT_HEALTH, currentHealth.toString())
            args.putString(DAMAGE, damage.toString())
            args.putString(NUMBER_OF_TIMES, numberOfTimes.toString())

            fragmentGame.arguments = args
            return fragmentGame
        }
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerQuizClose: DetailFragment.OnItemCloseListener? = null

    @Inject
    lateinit var gameViewModel: GameViewModel

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listenerQuizClose = context as DetailFragment.OnItemCloseListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as CreatureApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentGameBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.quizViewModel = gameViewModel

        initializationComposeViewQuiz()

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
        listenerQuizClose = null
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onCloseClicked() {
        listenerQuizClose?.onCloseItem()
    }

    private fun initializationComposeViewQuiz() {
        binding.composeViewQuiz.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    /*
                    val player = gameViewModel.playerLiveData.observeAsState()
                    val monster = gameViewModel.monsterLiveData.observeAsState()
                    GameScreen(monster = monster as State<MonsterUI>, player = player as State<PlayerUI>) { onCloseClicked() }
                     */
                    Navigation(gameViewModel)
                }
            }
        }
    }

    @Composable
    fun Navigation(viewModel: GameViewModel = viewModel()) {

        val player = viewModel.playerLiveData.observeAsState()
        val monster = viewModel.monsterLiveData.observeAsState()

        GameScreen(monster = monster as State<MonsterUI>, player = player as State<PlayerUI>) { onCloseClicked() }
    }

    @Composable
    fun GameScreen(modifier: Modifier = Modifier,
                   player: State<PlayerUI>,
                   monster: State<MonsterUI>,
                   onClick: () -> Unit
    ) {
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

                    /*Button(onClick = {
                        //onCloseClicked()
                        onClick()
                    }) {
                        Text(text = "Ход игрока")
                    }*/

                    // макс здоровье игрока
                    Text(getString(R.string.player_health_max))
                    Text(gameViewModel.playerLiveData.value!!.maxHealth)
                    // текущее здоровоье игрока
                    Text(getString(R.string.player_health_current))
                    //Text(gameViewModel.playerLiveData.value!!.currentHealth)
                    val healthPlayer = gameViewModel.healthPlayer.collectAsState()
                    Text(healthPlayer.value)

                    // атака игрока
                    Text(getString(R.string.player_attack))
                    Text(gameViewModel.playerLiveData.value!!.attack)
                    // защита игрока
                    Text(getString(R.string.player_defense))
                    Text(gameViewModel.playerLiveData.value!!.defense)
                    // урон игрока
                    Text(getString(R.string.player_damage))
                    Text(gameViewModel.playerLiveData.value!!.damage)

                    // ход игрока кнопка
                    Button(onClick = {
                        gameViewModel.playerMove()
                        Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = getString(R.string.player_move))
                    }

                    // количество попыток остаток
                    Text(getString(R.string.count_first_aid_kits))
                    //Text(gameViewModel.playerLiveData.value!!.numberOfTimes)
                    val numberOfTimes = gameViewModel.numberOfTimes.collectAsState()
                    Text(numberOfTimes.value)
                    // исцеление игрока кнопка
                    Button(onClick = {
                        gameViewModel.healPlayer()
                        Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = getString(R.string.heal_player))
                    }

                    // макс здоровье монстра
                    Text(getString(R.string.monster_health_max))
                    Text(gameViewModel.monsterLiveData.value!!.maxHealth)
                    // текущее здоровоье монстра
                    Text(getString(R.string.monster_health_current))
                    //Text(gameViewModel.monsterLiveData.value!!.currentHealth)
                    val healthMonster = gameViewModel.healthMonster.collectAsState()
                    Text(healthMonster.value)

                    // атака монстра
                    Text(getString(R.string.monster_attack))
                    Text(gameViewModel.monsterLiveData.value!!.attack)
                    // защита монстра
                    Text(getString(R.string.monster_defense))
                    Text(gameViewModel.monsterLiveData.value!!.defense)
                    // урон монстра
                    Text(getString(R.string.monster_damage))
                    Text(gameViewModel.monsterLiveData.value!!.damage)

                    // ход монстра кнопка
                    Button(onClick = {
                        gameViewModel.monsterMove()
                        Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = getString(R.string.monster_move))
                    }

                }

            }
        }
    }

}