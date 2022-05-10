package com.ak.samplefirebasenotification
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ak.samplefirebasenotification.databinding.ActivitySplashBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        registrationToken()

    }

    private fun registrationToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(
                        "getInstanceId failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }

                val token = task.result?.token
               //println("tttt:${token}")
            })
    }
}