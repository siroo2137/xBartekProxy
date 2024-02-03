package ru.leymooo.botfilter.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings extends Config
{

    @Ignore
    public static final Settings IMP = new Settings();

    @Comment({"Please write all errors, bugs, suggestions, etc. on Github"})
    @Final
    public final String ISSUES = "https://github.com/Leymooo/BungeeCord/issues";
    @Final
    public final String HELP = "http://www.rubukkit.org/threads/137038/";
    @Final
    public String BOT_FILTER_VERSION = "3.8.14-dev";

    @Create
    public MESSAGES MESSAGES;
    @Create
    public DIMENSIONS DIMENSIONS;
    @Create
    public GEO_IP GEO_IP;
    @Create
    public PING_CHECK PING_CHECK;
    @Create
    public SERVER_PING_CHECK SERVER_PING_CHECK;
    @Create
    public PROTECTION PROTECTION;
    @Create
    public SQL SQL;
    @Comment(
        {
        "How many players/bots must log in in 1 minute for the protection to turn on?",
        "Recommended settings when there are no bots:",
        "Up to 150 online - 25, up to 250 - 30, up to 350 - 35, up to 550 - 40.45, higher - adjust to your needs",
        "During advertising or when protection has been installed, it is recommended to increase these values"
        })
    public int PROTECTION_THRESHOLD = 30;
    @Comment("How long is automatic protection active? In milliseconds. 1 sec = 1000")
    public int PROTECTION_TIME = 120000;
    @Comment("Should I check for a bot when entering the server during a bot attack, regardless of whether it passed the check or not?")
    public boolean FORCE_CHECK_ON_ATTACK = true;
    @Comment("Whether to show online with filter")
    public boolean SHOW_ONLINE = true;
    @Comment("How much time does the player have to pass the defense? In milliseconds. 1 sec = 1000")
    public int TIME_OUT = 12700;
    @Comment("Should I enable the fix from 'Team 'xxx' already exist in this scoreboard'")
    public boolean FIX_SCOREBOARD_TEAMS = true;
    @Comment("Should I record the IP addresses of players/bots that failed the test in a file?")
    public boolean SAVE_FAILED_IPS_TO_FILE = true;

    public void reload(File file)
    {
        load( file );
        save( file );
    }

    @Comment("Don't use '\\n', use %nl%")
    public static class MESSAGES
    {

        public String PREFIX = "&b&lBot&d&lFilter";
        public String CHECKING = "%prefix%&7>> &aWait for the verification to complete...";
        public String CHECKING_CAPTCHA = "%prefix%&7>> &aEnter the number from the picture into the chat";
        public String CHECKING_CAPTCHA_WRONG = "%prefix%&7>> &cYou entered the captcha incorrectly, please try again. You have &a%s &c%s";
        public String SUCCESSFULLY = "%prefix%&7>> &aVerification passed, enjoy the game";
        public String KICK_MANY_CHECKS = "%prefix%%nl%%nl%&cSuspicious activity has been detected from your IP%nl%%nl%&6Try again in 10 minutes";
        public String KICK_NOT_PLAYER = "%prefix%%nl%%nl%&cYou have not passed verification, you may be a bot%nl%&7&oIf not, please try again";
        public String KICK_COUNTRY = "%prefix%%nl%%nl%& Your country is prohibited on the server";
        public String KICK_BIG_PING = "%prefix%%nl%%nl%&cYour ping is very high, most likely you are a bot";
        @Comment(
            {
            "Title%nl%Subtitle", "Leave empty to disable (prm: CHECKING_TITLE = \"\" )",
            "Disabling titles may improve performance slightly"
            })
        public String CHECKING_TITLE = "&r&lBot&b&lFilter%nl%&aChecking in progress";
        public String CHECKING_TITLE_SUS = "&rVerification passed%nl%&aHave a nice game";
        public String CHECKING_TITLE_CAPTCHA = " %nl%&rEnter the captcha in the chat!";
    }

    @Comment("Enable or disable GeoIp")
    public static class GEO_IP
    {

        @Comment(
            {
            "When the check works",
            "0 - Always",
            "1 - Only during bot attack",
            "2 - Disable"
            })
        public int MODE = 1;
        @Comment(
            {
            "How exactly does GeoIp work?",
            "0 - White list (Only those countries that are on the list can enter)",
            "1 - Black list (Only those countries that are not on the list can log in)"
            })
        public int TYPE = 0;
        @Comment(
            {
            "Where to download GEOIP from",
            "Change the link if for some reason it doesn’t download for this reason",
            "The file must end in .mmdb or be packaged in .tar.gz"
            })
        public String NEW_GEOIP_DOWNLOAD_URL = "https://download.maxmind.com/app/geoip_download?edition_id=GeoLite2-Country&license_key=%license_key%&suffix=tar.gz";
        @Comment(
            {
            "If the key stops working, then in order to get a new one you need to register at https://www.maxmind.com/",
            "and generate a new key on the page https://www.maxmind.com/en/accounts/current/license-key"
            })
        public String MAXMIND_LICENSE_KEY = "P5g0fVdAQIq8yQau";
        @Comment("Allowed countries")
        public List<String> ALLOWED_COUNTRIES = Arrays.asList( "RU", "UA", "BY", "KZ", "EE", "MD", "KG", "AZ", "LT", "LV", "GE", "PL" );
    }

    @Comment("Enable or disable high ping checking")
    public static class PING_CHECK
    {

        @Comment(
            {
            "When the check works",
            "0 - Always",
            "1 - Only during bot attack",
            "2 - Never"
            })
        public int MODE = 1;
        @Comment("Maximum allowed ping")
        public int MAX_PING = 350;
    }

    @Comment("Direct connection checking")
    public static class SERVER_PING_CHECK
    {

        @Comment(
            {
            "When the check works",
            "0 - Always",
            "1 - Only during bot attack",
            "2 - Never",
            "Disabled by default, as it is not very stable during strong attacks"
            })
        public int MODE = 2;
        @Comment("How long can you log into the server after receiving the server mod?")
        public int CACHE_TIME = 12;
        public List<String> KICK_MESSAGE = new ArrayList()
        {
            {
                add( "%nl%" );
                add( "%nl%" );
                add( "&cYou were kicked! Don't use direct connection" );
                add( "%nl%" );
                add( "%nl%" );
                add( "&bTo log into the server:" );
                add( "%nl%" );
                add( "&71) &rAdd the server to the &llist of servers." );
                add( "%nl%" );
                add( "&lOur IP &8>> &b&lIP" );
                add( "%nl%" );
                add( "%nl%" );
                add( "&72) &rUpdate the list of servers." );
                add( "%nl%" );
                add( "&oTo update it, click the &c&lRefresh &r&oor &c&lRefresh button" );
                add( "%nl%" );
                add( "%nl%" );
                add( "&73) &rWait &c1-3&r seconds and come in!" );

            }
        };
    }

    @Comment(
        {
        "Setting up exactly how the protection will work",
        "0 - Captcha verification only",
        "1 - Drop check + captcha",
        "2 - Fall check, if it fails, then captcha"
        })
    public static class PROTECTION
    {

        @Comment("Operating mode: No attack yet")
        public int NORMAL = 2;
        @Comment("Operating mode: during an attack")
        public int ON_ATTACK = 1;
        @Comment(
            {
            "Should I enable constant verification of players upon entry?",
            "When enabling this feature, do not forget to increase the protection-threshold limits"
            })
        public boolean ALWAYS_CHECK = false;

        @Comment(
            {
            "Should I check players whose IP is 127.0.0.1?", "May be useful when using Geyser",
            "0 - Check", "1 - Never check", "2 - Check every time you enter"
            })
        public int CHECK_LOCALHOST = 0;

        @Comment("Never check for clients with Geyser-standalone? The authorization type must be floodgate.")
        public boolean SKIP_GEYSER = false;
        @Comment(
                {
                    "When additional protocol checks work",
                    "    (Packets to which the client must always respond)",
                    "0 - Always",
                    "1 - Only during bot attack",
                    "2 - Never"
                })
        public int ADDITIONAL_CHECKS = 1;

    }

    @Comment("Database setup")
    public static class SQL
    {

        @Comment("Database type. sqlite or mysql")
        public String STORAGE_TYPE = "sqlite";
        @Comment("After how many days should players be removed from the database if they have passed verification and never logged in again? 0 or less to Never")
        public int PURGE_TIME = 14;
        @Comment("Settings for mysql")
        public String HOSTNAME = "127.0.0.1";
        public int PORT = 3306;
        public String USER = "user";
        public String PASSWORD = "password";
        public String DATABASE = "database";
        @Comment("Interval in milliseconds, how often to synchronize the database if multibundle is used")
        public int SYNC_INTERVAL = -1;
    }

    @Comment("Setting up the virtual world")
    public static class DIMENSIONS
    {
        @Comment(
            {
            "Which world to use",
            "0 - Ordinary world",
            "1 - Hell",
            "2 - End"
            })
        public int TYPE = 0;
    }
}
