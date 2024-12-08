package com.example.familyproj.test.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConf();

    static Config readConf(){
        return ConfigFactory.load("config.conf");
    }
}
