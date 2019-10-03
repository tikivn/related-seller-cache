package vn.tiki.cache;


import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        var main = new Main();
        main.run();
    }

    public void run() {
        try {
            var env = System.getenv("env_name");
            System.out.println("ENV_NAME: " + env);
            XmlConfigBuilder builder = null;
            if (env == null || env.equals("local")) {
                builder = new XmlConfigBuilder("config/thanos-hazelcast-local.xml");
            } else if (env.equals("uat")) {
                builder = new XmlConfigBuilder("config/thanos-hazelcast-uat.xml");
            } else if (env.equals("staging")) {
                builder = new XmlConfigBuilder("config/thanos-hazelcast-staging.xml");
            } else {
                builder = new XmlConfigBuilder("config/thanos-hazelcast-production.xml");
            }
            Config config = builder.build();
            HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
            System.out.println("Deploy Hazelcast instance success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
