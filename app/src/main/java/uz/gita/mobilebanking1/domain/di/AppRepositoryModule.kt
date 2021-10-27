package uz.gita.mobilebanking1.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking1.domain.AppRepository
import uz.gita.mobilebanking1.domain.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {
    @Binds
    @Singleton
    abstract fun getAppRepository(impl: AppRepositoryImpl): AppRepository

}