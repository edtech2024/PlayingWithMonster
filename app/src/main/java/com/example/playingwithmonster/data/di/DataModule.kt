package com.example.playingwithmonster.data.di

import android.content.Context
import com.example.playingwithmonster.data.database.CreatureDatabase
import com.example.playingwithmonster.data.mapper.EntityToModelMapper
import com.example.playingwithmonster.data.mapper.ModelToEntityMapper
import com.example.playingwithmonster.data.repositoryimpl.CreatureRepositoryImpl
import com.example.playingwithmonster.domain.irepository.ICreatureRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CreatureDatabase = CreatureDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideModelToEntityMapper(): ModelToEntityMapper = ModelToEntityMapper()

    @Singleton
    @Provides
    fun provideEntityToModelMapper(): EntityToModelMapper = EntityToModelMapper()

    @Singleton
    @Provides
    fun provideRepositoryImpl(database: CreatureDatabase,
                              modelToEntity: ModelToEntityMapper,
                              entityToModel: EntityToModelMapper
    ): ICreatureRepository = CreatureRepositoryImpl(
        database.itemDao(),
        modelToEntity,
        entityToModel
    )

}