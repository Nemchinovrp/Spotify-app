package com.example.nemchinovrp.connect;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

public class ConnectToSpotifyServer {
    private static final String clientId = "b2d16acc53824c18859e5f889f9ad544";
    private static final String clientSecret = "30a4b709b3a34972ac8475433355ea93";

    public static SpotifyApi clientCredentials() throws IOException, SpotifyWebApiException {
        final SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
