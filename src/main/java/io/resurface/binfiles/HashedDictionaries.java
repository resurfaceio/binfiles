// © 2016-2023 Resurface Labs Inc.

package io.resurface.binfiles;

/**
 * Predefined dictionaries for persistent string hashing.
 */
public final class HashedDictionaries {

    public static final HashedDictionary AGENT_CATEGORIES = new HashedDictionary();
    public static final HashedDictionary AGENT_DEVICES = new HashedDictionary();
    public static final HashedDictionary AGENT_NAMES = new HashedDictionary();
    public static final HashedDictionary CONTENT_TYPES = new HashedDictionary();
    public static final HashedDictionary JSON_TYPES = new HashedDictionary();
    public static final HashedDictionary REQUEST_METHODS = new HashedDictionary();
    public static final HashedDictionary RESPONSE_CODES = new HashedDictionary();

    static {
        // sourced from Yauaa
        AGENT_CATEGORIES.put(-1, "Mobile App");
        AGENT_CATEGORIES.put(-2, "Browser");
        AGENT_CATEGORIES.put(-3, "Robot");
        AGENT_CATEGORIES.put(-4, "Unknown");
        AGENT_CATEGORIES.put(-5, "Hacker");
        AGENT_CATEGORIES.put(-6, "Special");
        AGENT_CATEGORIES.put(-7, "Cloud");
        AGENT_CATEGORIES.put(-8, "Desktop App");

        // sourced from Yauaa
        AGENT_DEVICES.put(-1, "Mobile");
        AGENT_DEVICES.put(-2, "Robot");
        AGENT_DEVICES.put(-3, "Desktop");
        AGENT_DEVICES.put(-4, "Unknown");
        AGENT_DEVICES.put(-5, "Phone");
        AGENT_DEVICES.put(-6, "Robot Mobile");
        AGENT_DEVICES.put(-7, "Hacker");
        AGENT_DEVICES.put(-8, "Tablet");
        AGENT_DEVICES.put(-9, "Set-top box");
        AGENT_DEVICES.put(-10, "Robot Imitator");
        AGENT_DEVICES.put(-11, "Anonymized");
        AGENT_DEVICES.put(-12, "TV");
        AGENT_DEVICES.put(-13, "Watch");
        AGENT_DEVICES.put(-14, "Game Console");

        // sourced from Yauaa
        AGENT_NAMES.put(-1, "Chrome");
        AGENT_NAMES.put(-2, "Curl");
        AGENT_NAMES.put(-3, "Insomnia");
        AGENT_NAMES.put(-4, "AWS Security Scanner");
        AGENT_NAMES.put(-5, "Nimbostratus-Bot");
        AGENT_NAMES.put(-6, "SemrushBot");
        AGENT_NAMES.put(-7, "HTTP Banner Detection");
        AGENT_NAMES.put(-8, "PetalBot");
        AGENT_NAMES.put(-9, "CloudFlare AlwaysOnline");
        AGENT_NAMES.put(-10, "SeznamBot");
        AGENT_NAMES.put(-11, "SeaMonkey");
        AGENT_NAMES.put(-12, "CensysInspect");
        AGENT_NAMES.put(-13, "Hacker");
        AGENT_NAMES.put(-14, "Applebot");
        AGENT_NAMES.put(-15, "ZoominfoBot");
        AGENT_NAMES.put(-16, "Libwww-Perl");
        AGENT_NAMES.put(-17, "Google-Site-Verification");
        AGENT_NAMES.put(-18, "aiHitBot");
        AGENT_NAMES.put(-19, "CCBot");
        AGENT_NAMES.put(-20, "Apache-HttpClient");
        AGENT_NAMES.put(-21, "BLEXBot");
        AGENT_NAMES.put(-22, "Google Favicon");
        AGENT_NAMES.put(-23, "Twitterbot");
        AGENT_NAMES.put(-24, "Kubectl");
        AGENT_NAMES.put(-25, "UIWebView");
        AGENT_NAMES.put(-26, "Python-Requests");
        AGENT_NAMES.put(-27, "Firefox");
        AGENT_NAMES.put(-28, "Unknown");
        AGENT_NAMES.put(-29, "Chrome Webview");
        AGENT_NAMES.put(-30, "Internet Explorer");
        AGENT_NAMES.put(-31, "Zgrab");
        AGENT_NAMES.put(-32, "Edge");
        AGENT_NAMES.put(-33, "UCBrowser");
        AGENT_NAMES.put(-34, "Safari");
        AGENT_NAMES.put(-35, "Go-http-client");
        AGENT_NAMES.put(-36, "Linux Gnu");
        AGENT_NAMES.put(-37, "Mozila");
        AGENT_NAMES.put(-38, "L9EXPLORE");
        AGENT_NAMES.put(-39, "AhrefsBot");
        AGENT_NAMES.put(-40, "Python-urllib");
        AGENT_NAMES.put(-41, "Googlebot");
        AGENT_NAMES.put(-42, "Sogou Explorer");
        AGENT_NAMES.put(-43, "UptimeRobot");
        AGENT_NAMES.put(-44, "Masscan");
        AGENT_NAMES.put(-45, "AppleWebKit");
        AGENT_NAMES.put(-46, "Python");
        AGENT_NAMES.put(-47, "MJ12bot");
        AGENT_NAMES.put(-48, "Bingbot");
        AGENT_NAMES.put(-49, "Barkrowler");
        AGENT_NAMES.put(-50, "Opera");
        AGENT_NAMES.put(-51, "Adsbot");
        AGENT_NAMES.put(-52, "HeadlessChrome");
        AGENT_NAMES.put(-53, "Wget");
        AGENT_NAMES.put(-54, "Roku");
        AGENT_NAMES.put(-55, "Java");
        AGENT_NAMES.put(-56, "PycURL");
        AGENT_NAMES.put(-57, "YandexBot");

        // from web standards
        CONTENT_TYPES.put(-1, "application/octet-stream");
        CONTENT_TYPES.put(-2, "application/json");
        CONTENT_TYPES.put(-3, "application/json;charset=UTF-8");
        CONTENT_TYPES.put(-4, "application/json;charset=utf-8");
        CONTENT_TYPES.put(-5, "application/json; charset=UTF-8");
        CONTENT_TYPES.put(-6, "application/json; charset=utf-8");
        CONTENT_TYPES.put(-7, "application/x-www-form-urlencoded");
        CONTENT_TYPES.put(-8, "application/x-www-form-urlencoded;charset=UTF-8");
        CONTENT_TYPES.put(-9, "application/x-www-form-urlencoded;charset=utf-8");
        CONTENT_TYPES.put(-10, "application/x-www-form-urlencoded; charset=UTF-8");
        CONTENT_TYPES.put(-11, "application/x-www-form-urlencoded; charset=utf-8");
        CONTENT_TYPES.put(-12, "application/xml");
        CONTENT_TYPES.put(-13, "application/xml;charset=UTF-8");
        CONTENT_TYPES.put(-14, "application/xml;charset=utf-8");
        CONTENT_TYPES.put(-15, "application/xml; charset=UTF-8");
        CONTENT_TYPES.put(-16, "application/xml; charset=utf-8");
        CONTENT_TYPES.put(-17, "text/css");
        CONTENT_TYPES.put(-18, "text/css;charset=UTF-8");
        CONTENT_TYPES.put(-19, "text/css;charset=utf-8");
        CONTENT_TYPES.put(-20, "text/css; charset=UTF-8");
        CONTENT_TYPES.put(-21, "text/css; charset=utf-8");
        CONTENT_TYPES.put(-22, "text/html");
        CONTENT_TYPES.put(-23, "text/html;charset=UTF-8");
        CONTENT_TYPES.put(-24, "text/html;charset=utf-8");
        CONTENT_TYPES.put(-25, "text/html; charset=UTF-8");
        CONTENT_TYPES.put(-26, "text/html; charset=utf-8");
        CONTENT_TYPES.put(-27, "text/plain");
        CONTENT_TYPES.put(-28, "text/plain;charset=UTF-8");
        CONTENT_TYPES.put(-29, "text/plain;charset=utf-8");
        CONTENT_TYPES.put(-30, "text/plain; charset=UTF-8");
        CONTENT_TYPES.put(-31, "text/plain; charset=utf-8");
        CONTENT_TYPES.put(-32, "text/xml");
        CONTENT_TYPES.put(-33, "text/xml;charset=UTF-8");
        CONTENT_TYPES.put(-34, "text/xml;charset=utf-8");
        CONTENT_TYPES.put(-35, "text/xml; charset=UTF-8");
        CONTENT_TYPES.put(-36, "text/xml; charset=utf-8");

        // from Jackson (JsonNodeType)
        JSON_TYPES.put(-1, "ARRAY");
        JSON_TYPES.put(-2, "OBJECT");
        JSON_TYPES.put(-3, "SCALAR");
        JSON_TYPES.put(-4, "MALFORMED");

        // from web standards
        REQUEST_METHODS.put(-1, "GET");
        REQUEST_METHODS.put(-2, "POST");
        REQUEST_METHODS.put(-3, "CONNECT");
        REQUEST_METHODS.put(-4, "DELETE");
        REQUEST_METHODS.put(-5, "HEAD");
        REQUEST_METHODS.put(-6, "OPTIONS");
        REQUEST_METHODS.put(-7, "PATCH");
        REQUEST_METHODS.put(-8, "PUT");
        REQUEST_METHODS.put(-9, "TRACE");
        REQUEST_METHODS.put(-10, "UPDATE");

        // from web standards
        RESPONSE_CODES.put(-1, "100");
        RESPONSE_CODES.put(-2, "101");
        RESPONSE_CODES.put(-3, "102");
        RESPONSE_CODES.put(-4, "103");
        RESPONSE_CODES.put(-5, "200");
        RESPONSE_CODES.put(-6, "201");
        RESPONSE_CODES.put(-7, "202");
        RESPONSE_CODES.put(-8, "203");
        RESPONSE_CODES.put(-9, "204");
        RESPONSE_CODES.put(-10, "205");
        RESPONSE_CODES.put(-11, "206");
        RESPONSE_CODES.put(-12, "207");
        RESPONSE_CODES.put(-13, "208");
        RESPONSE_CODES.put(-14, "226");
        RESPONSE_CODES.put(-15, "300");
        RESPONSE_CODES.put(-16, "301");
        RESPONSE_CODES.put(-17, "302");
        RESPONSE_CODES.put(-18, "303");
        RESPONSE_CODES.put(-19, "304");
        RESPONSE_CODES.put(-20, "305");
        RESPONSE_CODES.put(-21, "306");
        RESPONSE_CODES.put(-22, "307");
        RESPONSE_CODES.put(-23, "308");
        RESPONSE_CODES.put(-24, "400");
        RESPONSE_CODES.put(-25, "401");
        RESPONSE_CODES.put(-26, "402");
        RESPONSE_CODES.put(-27, "403");
        RESPONSE_CODES.put(-28, "404");
        RESPONSE_CODES.put(-29, "405");
        RESPONSE_CODES.put(-30, "406");
        RESPONSE_CODES.put(-31, "407");
        RESPONSE_CODES.put(-32, "408");
        RESPONSE_CODES.put(-33, "409");
        RESPONSE_CODES.put(-34, "410");
        RESPONSE_CODES.put(-35, "411");
        RESPONSE_CODES.put(-36, "412");
        RESPONSE_CODES.put(-37, "413");
        RESPONSE_CODES.put(-38, "414");
        RESPONSE_CODES.put(-39, "415");
        RESPONSE_CODES.put(-40, "416");
        RESPONSE_CODES.put(-41, "417");
        RESPONSE_CODES.put(-42, "418");
        RESPONSE_CODES.put(-43, "419");
        RESPONSE_CODES.put(-44, "420");
        RESPONSE_CODES.put(-45, "421");
        RESPONSE_CODES.put(-46, "422");
        RESPONSE_CODES.put(-47, "423");
        RESPONSE_CODES.put(-48, "424");
        RESPONSE_CODES.put(-49, "425");
        RESPONSE_CODES.put(-50, "426");
        RESPONSE_CODES.put(-51, "428");
        RESPONSE_CODES.put(-52, "429");
        RESPONSE_CODES.put(-53, "430");
        RESPONSE_CODES.put(-54, "431");
        RESPONSE_CODES.put(-55, "450");
        RESPONSE_CODES.put(-56, "451");
        RESPONSE_CODES.put(-57, "498");
        RESPONSE_CODES.put(-58, "499");
        RESPONSE_CODES.put(-59, "500");
        RESPONSE_CODES.put(-60, "501");
        RESPONSE_CODES.put(-61, "502");
        RESPONSE_CODES.put(-62, "503");
        RESPONSE_CODES.put(-63, "504");
        RESPONSE_CODES.put(-64, "505");
        RESPONSE_CODES.put(-65, "506");
        RESPONSE_CODES.put(-66, "507");
        RESPONSE_CODES.put(-67, "508");
        RESPONSE_CODES.put(-68, "509");
        RESPONSE_CODES.put(-69, "510");
        RESPONSE_CODES.put(-70, "511");
        RESPONSE_CODES.put(-71, "529");
        RESPONSE_CODES.put(-72, "530");
        RESPONSE_CODES.put(-73, "598");
        RESPONSE_CODES.put(-74, "599");
    }

}
