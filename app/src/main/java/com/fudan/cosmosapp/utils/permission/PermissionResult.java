package com.fudan.cosmosapp.utils.permission;

public interface PermissionResult {
    void onGranted();

    void onDenied();
}
