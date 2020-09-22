package com.example.warofsuits

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.warofsuits.ui.game.GameActivity
import com.example.warofsuits.ui.result.ResultActivity
import com.example.warofsuits.ui.welcome.WelcomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest{

    @Rule
    @JvmField
    val mWelcomeActivityTestRule = ActivityTestRule(WelcomeActivity::class.java)
    val mGameActivityTestRule = IntentsTestRule(GameActivity::class.java)
    val mResultActivityTestRule = IntentsTestRule(ResultActivity::class.java)

    @Test
    fun testUI(){
        //

        // Click on rules and then play
        Espresso.onView(ViewMatchers.withId(R.id.btnRules))
            .perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.btnClose))
            .perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.btnPlay))
            .perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.tvDiscard1))
            .check(ViewAssertions.matches(ViewMatchers.withText("0 discarded cards")))

        //Click restart
        Espresso.onView(ViewMatchers.withId(R.id.btnReplay1))
          .perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.btnReplay2))
          .perform(click())

        Thread.sleep(3500)

        // Start to play
        for (i in 1..26 step 1){
            Espresso.onView(ViewMatchers.withId(R.id.cvPile1))
                .perform(click())

            Espresso.onView(ViewMatchers.withId(R.id.cvPile2))
                .perform(click())

            Thread.sleep(3500)

        }

        Thread.sleep(5000)


        Espresso.onView(ViewMatchers.withId(R.id.btnReplay))
            .perform(click())

        Thread.sleep(3500)

    }


}