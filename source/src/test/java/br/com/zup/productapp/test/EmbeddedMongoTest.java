package br.com.zup.productapp.test;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;


public enum EmbeddedMongoTest {
    DB;
    private transient MongodProcess mongodProcess;
    private boolean active;
    private boolean mongoIPv6;
    private String mongoHost = "localhost";
    private int mongoPort;

    private EmbeddedMongoTest() {
        this.mongoPort = 27017;
    }

    public EmbeddedMongoTest port(int port) {
        this.mongoPort = port;
        return this;
    }

    public EmbeddedMongoTest host(String host) {
        this.mongoHost = host;
        return this;
    }

    public EmbeddedMongoTest ipv6(boolean ipv6) {
        this.mongoIPv6 = ipv6;
        return this;
    }

    public void start() {
        if (!this.active) {
            try {
                this.mongodProcess = MongodStarter.getDefaultInstance().prepare(new MongodConfigBuilder()
                .version(Version.Main.V3_3)
                .net(new Net(this.mongoHost, this.mongoPort, this.mongoIPv6))
                .build()).start();

                LoggerFactory.getLogger(EmbeddedMongoTest.class).info("Successfully started EmbeddedMongoDB @ {}:{}", this.mongoHost, this.mongoPort);
                this.active = true;
            } catch (final IOException e) {
                LoggerFactory.getLogger(EmbeddedMongoTest.class).error("Failed to start EmbeddedMongoDB", e);
            }
        }
    }

    public void stop() {
        if (this.active) {
            this.mongodProcess.stop();
            this.active = false;

            LoggerFactory.getLogger(EmbeddedMongoTest.class).info("Successfully stopped EmbeddedMongoDB @ {}:{}", this.mongoHost, this.mongoPort);
        }
    }

    public MongoClient getMongoClient() throws UnknownHostException {
        return new MongoClient(this.getHost(), this.getPort());
    }

    public int getPort() {
        return this.mongoPort;
    }

    public String getHost() {
        return this.mongoHost;
    }

    public boolean isIPv6() {
        return this.mongoIPv6;
    }
}