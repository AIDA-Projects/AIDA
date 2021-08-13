package me.zhenxin.aida.activity

import android.os.Bundle
import com.xuexiang.xutil.app.ActivityUtils
import me.zhenxin.aida.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityUtils.startActivity(HistoryProjectActivity::class.java)
    }
}