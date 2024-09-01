package api.helpers;

import api.auth.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TokenExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        AuthorizationApi.addToken();
    }
}
