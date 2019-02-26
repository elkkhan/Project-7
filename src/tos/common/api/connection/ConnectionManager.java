package tos.common.api.connection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tos.common.api.exceptions.ConnectionException;

public class ConnectionManager {

    private final CloseableHttpClient connection = getCloseableHttpClient();

    /**
     * @return an apache http client, sets SSL trust strategy to trust everything
     */
    private CloseableHttpClient getCloseableHttpClient() {
        try {
            return HttpClients.custom()
                .setDefaultRequestConfig(getTimeoutConfig())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setSSLContext(
                    new SSLContextBuilder()
                        .loadTrustMaterial(null, (TrustStrategy) (arg0, arg1) -> true)
                        .build())
                .build();
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            System.exit(1);
        }
        return null;
    }

    /**
     * @return timeout configuration, set to 30 seconds. program should not wait for more than 30
     * seconds for a url to respond.
     */
    private RequestConfig getTimeoutConfig() {
        RequestConfig.Builder config = RequestConfig.custom();
        config.setConnectTimeout(30 * 1000);
        config.setConnectionRequestTimeout(30 * 1000);
        config.setSocketTimeout(30 * 1000);
        return config.build();
    }

    /**
     * Executes a GET request against the url and returns a new ConnectionResponse object
     * @param url url to send a get request to
     * @return content of the response
     */
    @Nullable
    public ConnectionResponse executeGetRequest(@NotNull String url) throws ConnectionException {
        HttpEntity entity;
        String entityContent;
        String finalUrl;
        Integer statusCode = null;
        HttpGet httpGet = new HttpGet(url);
        HttpClientContext context = HttpClientContext.create();
        try (CloseableHttpResponse response = connection.execute(httpGet, context)) {
            entity = response.getEntity();
            statusCode = response.getStatusLine().getStatusCode();
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpGet.getURI(), target, redirectLocations);
            finalUrl = location.toASCIIString();
            entityContent = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (IOException | java.lang.IllegalArgumentException e) {
            throw new ConnectionException(
                "Error while connecting to " + url + ". HTTP response code: " + statusCode);
        } catch (URISyntaxException e) {
            throw new ConnectionException("URL " + url + " is invalid.");
        } finally {
            httpGet.releaseConnection();
        }
        System.gc();
        return new ConnectionResponse(finalUrl, entityContent, statusCode);
    }
}

