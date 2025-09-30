package com.example.playingwithmonster.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.playingwithmonster.CreatureApplication
import com.example.playingwithmonster.databinding.FragmentHistoryBinding
import com.example.playingwithmonster.presentation.viewmodel.HistoryViewModel
import javax.inject.Inject

class HistoryFragment : Fragment() {

    // Creates a new fragment given parameters
    // MainFragment.newInstance()
    companion object {

        fun newInstance(): HistoryFragment {
            val fragmentHistory = HistoryFragment()
            return fragmentHistory
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemOpenListener{
        // This can be any number of events to be sent to the activity
        fun onOpenItem()
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerOpen: OnItemOpenListener? = null

    @Inject
    lateinit var historyViewModel: HistoryViewModel

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerOpen = context as OnItemOpenListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as CreatureApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.historyViewModel = historyViewModel

        // ???
        initializationComposeViewHistory()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialization()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerOpen = null
    }

    private fun initializationComposeViewHistory() {
        binding.composeViewHistory.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }



    /*
        @Composable
        fun Navigation() {
        Text(text = "Main screen")
    }*/

// ???
// input function paste arg viewmodel

    @Composable
    fun Navigation() {

        //val data = listViewModel.itemsList.observeAsState(emptyList())

        val data = listOf("iPhone 15 Pro", "Redmi Note 12 Pro+", "Galaxy S23 Ultra", "Infinix NOTE 30 Pro", "Honor 90")

        ListScreen(data)
    }

    @Composable
//fun ListScreen(data: State<List<Item>>) {
    fun ListScreen(data: List<String>) {
        /*
        // данные для отображения
        val data = listOf("iPhone 15 Pro", "Redmi Note 12 Pro+", "Galaxy S23 Ultra", "Infinix NOTE 30 Pro", "Honor 90")
        val dataEmpty = listOf("")
        val data1 = listViewModel.itemsListType1.observeAsState(emptyList())
        val data2 = listViewModel.itemsListType2.observeAsState(emptyList())
    */
        //FirstScreen(data, { item -> onItemClicked(item) } )
        FirstScreen(data, { item -> onItemClicked("") } )

    }

    sealed class Screen(var route: String) {
        object FirstScreen : Screen("first")
        object Detail : Screen("detail")
    }

    //navController: NavController
//onClick: () -> Unit
    @Composable
//fun FirstScreen(data: State<List<Item>>,
    fun FirstScreen(data: List<String>,
        //onClick: (Item) -> Unit) {
                    onClick: (String) -> Unit) {
        Button(onClick = {
            //navController.navigate(MainFragment.Screen.Detail.route)
        }){
            Text("Click", fontSize = 25.sp)
        }
        LazyColumn(Modifier.fillMaxSize()) {
            //items(data.value) {
            items(data) {
                /*ListItem(it, onClick = {
                        //it -> onItemClicked(it)
                    navController.navigate("detail")
                })*/
                //ListItem(it)
                ListItem(it,
                    //navController = navController,
                    onClick = onClick )
                //ListItem(it, onClick = onClick)
            }
        }
    }

    @Composable
//fun ListItem(data: Item, modifier: Modifier = Modifier, onClick: Unit) { // navigate
//fun ListItem(data: Item, modifier: Modifier = Modifier,
    fun ListItem(data: String, modifier: Modifier = Modifier,
        //navController: NavController,
        //onClick: (Item) -> Unit) { // navigate
                 onClick: (String) -> Unit) { // navigate
        //fun ListItem(data: Item, modifier: Modifier = Modifier, onClick: (Item) -> Unit) { // callback
        //fun ListItem(data: Item, modifier: Modifier = Modifier) { base
        Surface() {
            Row(modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black)
                .padding(10.dp)
                .clickable {
                    onClick(data)
                    //navController.navigate("detail")
                    //navController.navigate(MainFragment.Screen.Detail.route)
                    //onClick
                    Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                }
            ) {
                //Text(text = data.title.toString())
                // … other composables required for displaying `data`

                Text(text = data)
            }
        }
    }

    @Composable
    fun DetailScreen(modifier: Modifier = Modifier,
        //navController: NavController
    ) {
        Row(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
            .clickable {
                //navController.navigate("first")
            }
        ) {
            Text(text = "Detail Screen")
            // … other composables required for displaying `data`
        }
    }

/*private fun transferString():String {
    val args: String =
        getString(R.string.type_1) + " " +
                getString(R.string.type_2) + " " +
                getString(R.string.error)
    return args
}*/

    // Now we can fire the event when the user selects something in the fragment
    private fun onOpenClicked(){
        listenerOpen?.onOpenItem()
    }


    //private fun onItemClicked(item: Item){
    private fun onItemClicked(item: String){

        val args = Bundle()
        /*
        args.putString(getString(R.string.action), getString(R.string.edit))
        args.putInt(getString(R.string.id), item.id!!)
        args.putString(getString(R.string.title), item.title)
        args.putString(getString(R.string.description), item.description)
        args.putString(getString(R.string.priority), item.priority.toString())
        args.putString(getString(R.string.type), item.type.toString())
        args.putString(getString(R.string.count), item.count.toString())
        args.putString(getString(R.string.frequency), item.frequency!!.toString())
        args.putString(getString(R.string.uid), item.uid.toString())
        args.putString(getString(R.string.date), item.date!!.toString())
        args.putString(getString(R.string.done_dates), item.done_dates!!.toString())
        args.putString(getString(R.string.color), item.color!!.toString())
        */
        //onEditClicked(args)

        onOpenClicked()

    }

}