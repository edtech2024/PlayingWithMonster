package com.example.playingwithmonster.presentation.di

import android.os.Bundle
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import com.example.playingwithmonster.domain.iusecase.*
import com.example.playingwithmonster.domain.usecaseImpl.*
import com.example.playingwithmonster.presentation.fragment.DetailFragment
import com.example.playingwithmonster.presentation.mapper.ModelToUIMapperMonster
import com.example.playingwithmonster.presentation.mapper.ModelToUIMapperPlayer
import com.example.playingwithmonster.presentation.mapper.UIToModelMapperMonster
import com.example.playingwithmonster.presentation.mapper.UIToModelMapperPlayer
import com.example.playingwithmonster.presentation.viewmodel.DetailViewModel
import com.example.playingwithmonster.presentation.viewmodel.GameViewModel
import com.example.playingwithmonster.presentation.viewmodel.HistoryViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Singleton
    @Provides
    fun provideUseCaseCreateImpl(repository: ICreatureRepository): IUseCaseCreateCreature = UseCaseCreateCreatureImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseReadImpl(repository: ICreatureRepository): IUseCaseReadCreature = UseCaseReadCreatureImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseUpdateImpl(repository: ICreatureRepository): IUseCaseUpdateCreature = UseCaseUpdateCreatureImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseDeleteImpl(repository: ICreatureRepository): IUseCaseDeleteCreature = UseCaseDeleteCreatureImpl(repository)

    @Singleton
    @Provides
    fun provideUseCaseAttackModifierImpl(repository: ICreatureRepository): IUseCaseCalculateAttackModifier = UseCaseCalculateAttackModifierImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseAttackSuccessImpl(repository: ICreatureRepository): IUseCaseCalculateAttackSuccess = UseCaseCalculateAttackSuccessImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseForceImpactImpl(repository: ICreatureRepository): IUseCaseCalculateForceImpact = UseCaseCalculateForceImpactImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseTrowDiceImpl(repository: ICreatureRepository): IUseCaseThrowDice = UseCaseThrowDiceImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseHealCreatureImpl(repository: ICreatureRepository): IUseCaseHealCreature = UseCaseHealCreatureImpl(repository)

    @Singleton
    @Provides
    fun provideModelToUIMapperPlayer(): ModelToUIMapperPlayer = ModelToUIMapperPlayer()
    @Singleton
    @Provides
    fun provideUIToModelMapperPlayer(): UIToModelMapperPlayer = UIToModelMapperPlayer()
    @Singleton
    @Provides
    fun provideModelToUIMapperMonster(): ModelToUIMapperMonster = ModelToUIMapperMonster()
    @Singleton
    @Provides
    fun provideUIToModelMapperMonster(): UIToModelMapperMonster = UIToModelMapperMonster()

    @Provides
    fun provideHistoryViewModel(useCaseReadCreature: IUseCaseReadCreature,
                             modelToUI: ModelToUIMapperPlayer
    ): HistoryViewModel = HistoryViewModel(
        useCaseReadCreature,
        modelToUI
    )

    @Provides
    fun provideGameViewModel(
        useCaseAttackModifier: IUseCaseCalculateAttackModifier,
        useCaseAttackSuccess: IUseCaseCalculateAttackSuccess,
        useCaseForceImpact: IUseCaseCalculateForceImpact,
        useCaseThrowDice: IUseCaseThrowDice,
        useCaseCreateCreature: IUseCaseCreateCreature,
        useCaseReadCreature: IUseCaseReadCreature,
        useCaseUpdateCreature: IUseCaseUpdateCreature,
        useCaseHealCreature: IUseCaseHealCreature,
        uiToModelPlayer: UIToModelMapperPlayer,
        uiToModelMonster: UIToModelMapperMonster,
        modelToUIMapperPlayer: ModelToUIMapperPlayer,
        modelToUIMapperMonster: ModelToUIMapperMonster
    ): GameViewModel = GameViewModel(
        useCaseAttackModifier,
        useCaseAttackSuccess,
        useCaseForceImpact,
        useCaseThrowDice,
        useCaseCreateCreature,
        useCaseReadCreature,
        useCaseUpdateCreature,
        useCaseHealCreature,
        uiToModelPlayer,
        uiToModelMonster,
        modelToUIMapperPlayer,
        modelToUIMapperMonster
    )

    @Provides
    fun provideDetailViewModel(bundle: Bundle, context: DetailFragment.OnItemCloseListener): DetailViewModel = DetailViewModel(bundle, context)

}