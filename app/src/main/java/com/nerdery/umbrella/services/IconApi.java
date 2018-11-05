package com.nerdery.umbrella.services;

public class IconApi {

    /**
     * Get the URL to an icon suitable for use as a replacement for the icons given by Weather Underground
     * @param icon The name of the icon provided by Weather Underground (e.g. "clear").
     * @param highlighted True to get the highlighted version, false to get the outline version
     * @return A URL to an icon
     */
    public String getUrlForIcon(String icon, boolean highlighted) {
        String highlightParam = highlighted ? "-selected" : "";
        return String.format("https://codechallenge.nerderylabs.com/mobile-nat/%s%s.png", icon, highlightParam);
    }
}
