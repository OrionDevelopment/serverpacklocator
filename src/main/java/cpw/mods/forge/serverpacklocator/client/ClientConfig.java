package cpw.mods.forge.serverpacklocator.client;

import com.electronwill.nightconfig.core.conversion.Conversion;
import com.electronwill.nightconfig.core.conversion.SpecIntInRange;
import com.electronwill.nightconfig.core.conversion.SpecNotNull;
import cpw.mods.forge.serverpacklocator.secure.SecurityConfig;
import cpw.mods.forge.serverpacklocator.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientConfig {

    public static final class Default {

        private static final Client CLIENT = ObjectUtils.make(
                new Client(),
                c -> {
                    c.remoteServer = "https://localhost:8080/";
                    c.threadCount = Math.max(1, Runtime.getRuntime().availableProcessors() - 2);
                }
        );

        public static final ClientConfig INSTANCE = ObjectUtils.make(
                new ClientConfig(),
                c -> {
                    c.client = CLIENT;
                    c.security = SecurityConfig.Default.INSTANCE;
                }
        );
    }


    @SpecNotNull
    private Client client;
    @SpecNotNull
    private SecurityConfig security;


    public Client getClient() {
        return client;
    }

    public SecurityConfig getSecurity() {
        return security;
    }

    public static class Client {

        @SpecIntInRange(min = 1, max = 128)
        private int threadCount;

        private String remoteServer;

        private List<String> excludedModIds = new ArrayList<>();

        @SpecNotNull
        private List<DownloadedServerContent> downloadedServerContent = new ArrayList<>();

        public int getThreadCount() {
            return threadCount;
        }

        public String getRemoteServer() {
            return remoteServer;
        }

        public List<DownloadedServerContent> getDownloadedServerContent() {
            return downloadedServerContent;
        }

        public List<String> getExcludedModIds() {
            return excludedModIds;
        }
    }

    public static class DownloadedServerContent {
        @SpecNotNull
        private String name;

        @SpecNotNull
        private List<String> blackListRegex;

        public String getName() {
            return name;
        }

        public List<String> getBlackListRegex() {
            return blackListRegex;
        }

    }

}
