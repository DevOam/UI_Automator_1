package com.example.ui_automator_2;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest<UiDevice> {
    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.testingwithuiautomator";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;
    @Before
    public void startMainActivityFromHomeScreen() {
// Initialiser l'instance UiDevice
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
// Démarrer à partir de l'écran d'accueil
        device.pressHome();
// Attendre le lanceur
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
// Lancer l'application
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
// Attendre que l'application apparaisse
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }
    @Test
    public void test() throws UiObjectNotFoundException {
        UiObject cancelButton = device.findObject(new UiSelector()
                .text("Cancel")
                .className("android.widget.Button"));
        UiObject okButton = device.findObject(new UiSelector()
                .text("OK")
                .className("android.widget.Button"));
// Simuler un clic de l'utilisateur sur le bouton OK, si il existe.
        if(okButton.exists() && okButton.isEnabled()) {
            okButton.click();
        }
    }
}