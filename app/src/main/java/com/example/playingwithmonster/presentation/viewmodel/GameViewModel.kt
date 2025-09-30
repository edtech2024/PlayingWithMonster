package com.example.playingwithmonster.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.playingwithmonster.domain.iusecase.*
import com.example.playingwithmonster.presentation.mapper.ModelToUIMapperMonster
import com.example.playingwithmonster.presentation.mapper.ModelToUIMapperPlayer
import com.example.playingwithmonster.presentation.mapper.UIToModelMapperMonster
import com.example.playingwithmonster.presentation.mapper.UIToModelMapperPlayer
import com.example.playingwithmonster.presentation.uistate.MonsterUI
import com.example.playingwithmonster.presentation.uistate.PlayerUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameViewModel @Inject constructor(
    val useCaseAttackModifier: IUseCaseCalculateAttackModifier,
    val useCaseAttackSuccess: IUseCaseCalculateAttackSuccess,
    val useCaseForceImpact: IUseCaseCalculateForceImpact,
    val useCaseThrowDice: IUseCaseThrowDice,
    val useCaseCreateCreature: IUseCaseCreateCreature,
    val useCaseReadCreature: IUseCaseReadCreature,
    val useCaseUpdateCreature: IUseCaseUpdateCreature,
    val useCaseHealCreature: IUseCaseHealCreature,
    val uiToModelPlayer: UIToModelMapperPlayer,
    val uiToModelMonster: UIToModelMapperMonster,
    val modelToUIMapperPlayer: ModelToUIMapperPlayer,
    val modelToUIMapperMonster: ModelToUIMapperMonster
    ) : ViewModel() {


    var player: MutableLiveData<PlayerUI> = MutableLiveData()
    var playerLiveData: LiveData<PlayerUI> = player

    var monster: MutableLiveData<MonsterUI> = MutableLiveData()
    var monsterLiveData: LiveData<MonsterUI> = monster


    private val _healthPlayer: MutableStateFlow<String> = MutableStateFlow("")
    val healthPlayer: StateFlow<String> = _healthPlayer.asStateFlow()

    fun setHealthPlayer(health: Double) {
        _healthPlayer.value = health.toString()
    }

    private val _healthMonster: MutableStateFlow<String> = MutableStateFlow("")
    val healthMonster: StateFlow<String> = _healthMonster.asStateFlow()

    fun setHealthMonster(health: Double) {
        _healthMonster.value = health.toString()
    }

    private val _numberOfTimes: MutableStateFlow<String> = MutableStateFlow("")
    val numberOfTimes: StateFlow<String> = _numberOfTimes.asStateFlow()

    fun setNumberOfTimes(numberOfTimes: Int) {
        _numberOfTimes.value = numberOfTimes.toString()
    }

    init {
        var currentPlayerUI: PlayerUI = PlayerUI.invoke("","","","","","","")
        var currentMonsterUI: MonsterUI = MonsterUI.invoke("","","","","","")

        // put value in livedata
        player.value = currentPlayerUI
        monster.value = currentMonsterUI
    }


    suspend fun startPlayer() {

        //useCaseCreateCreature.invoke(uiToModelPlayer.map(player.value!!))

        var modifier: Int = useCaseAttackModifier.invoke(
            player.value?.attack.toString().toInt(),
            monster.value?.defense.toString().toInt()
        )
        Log.d("modifier", modifier.toString())

        var dice: List<Int> = useCaseThrowDice.invoke(modifier)
        Log.d("dice", dice.toString())

        var success: Boolean = useCaseAttackSuccess.invoke(dice)
        Log.d("success", success.toString())

        if (success) {
            var health: Double = useCaseForceImpact.invoke(player.value!!.damage.toDouble(),
                monster.value!!.currentHealth.toDouble())
            Log.d("health", health.toString())

            //monster.value!!.currentHealth = health.toString()
            var updatedMonster = monster.value
            updatedMonster!!.currentHealth = health.toString()
            monster.postValue(updatedMonster)

            setHealthMonster(health)
        }
        //useCaseUpdateCreature.invoke(uiToModelPlayer.map(player.value!!))

        //monster.value
        //player.value = modelToUIMapperPlayer.mapAll(useCaseReadCreature.invoke()).get(player.value!!.id.toInt())
    }

    suspend fun startMonster() {

        //useCaseCreateCreature.invoke(uiToModelPlayer.map(player.value!!))

        var modifier: Int = useCaseAttackModifier.invoke(
            player.value?.attack.toString().toInt(),
            monster.value?.defense.toString().toInt()
        )
        Log.d("modifier", modifier.toString())

        var dice: List<Int> = useCaseThrowDice.invoke(modifier)
        Log.d("dice", dice.toString())

        var success: Boolean = useCaseAttackSuccess.invoke(dice)
        Log.d("success", success.toString())

        if (success) {
            var health: Double = useCaseForceImpact.invoke(monster.value!!.damage.toDouble(),
                player.value!!.currentHealth.toDouble())
            Log.d("health", health.toString())

            //player.value!!.currentHealth = health.toString()
            var updatedPlayer = player.value
            updatedPlayer!!.currentHealth = health.toString()
            player.postValue(updatedPlayer)

            setHealthPlayer(health)
        }
        //useCaseUpdateCreature.invoke(uiToModelPlayer.map(player.value!!))

        //player.value = modelToUIMapperPlayer.mapAll(useCaseReadCreature.invoke()).get(player.value!!.id.toInt())
    }

    fun playerMove() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                //start()
                startPlayer()
            }
        }
    }

    fun monsterMove() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                //start()
                startMonster()
            }
        }
    }

    fun healPlayer() {
        var health: Double = useCaseHealCreature.invoke(player.value!!.currentHealth.toDouble(), player.value!!.maxHealth.toDouble(), player.value!!.numberOfTimes.toInt())
        var count = player.value!!.numberOfTimes.toInt() - 1
        Log.d("health", health.toString())
        Log.d("count", count.toString())

        var updatedPlayer = player.value
        updatedPlayer!!.currentHealth = health.toString()
        updatedPlayer!!.numberOfTimes = count.toString()
        player.value = updatedPlayer

        setHealthPlayer(health)
        setNumberOfTimes(count)

        // useCaseUpdateCreature.invoke()
    }

}


/*
fun buildHeroes() {
        val currentPlayerModel = uiToModelPlayer.map(player.value)
        val currentMonsterModel = uiToModelMonster.map(monster.value)
    }
    */