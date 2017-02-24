package com.roxiemobile.androidcommons.data;

public interface CommonKeys
{
// MARK: - Constants

    String URN = "urn:roxiemobile:shared";

    interface Prefix {
        String ACTION = URN + ":action.";
        String EXTRA  = URN + ":extra.";
        String PREFS  = URN + ":prefs.";
        String STATE  = URN + ":state.";
    }

    interface Action {
        String NAVIGATE_UP_TO = Prefix.ACTION + "NAVIGATE_UP_TO";
    }

    interface State {
        String UNDEFINED = Prefix.STATE + "UNDEFINED";
    }
}
