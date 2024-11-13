package com.eniskaner.onboarding.navigation.di

import com.eniskaner.eyojnavigation.NavigationGraph
import com.eniskaner.onboarding.navigation.OnBoardingNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @IntoSet
    @Binds
    fun bindOnboardingNavigation(onBoardingNavGraph: OnBoardingNavGraph): NavigationGraph
}
